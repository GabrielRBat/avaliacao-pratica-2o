import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Executor para gerenciar timeouts
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ValidationContext ctx = new ValidationContext(executor);

        // Schema -> Certificado -> Regras Fiscais -> Banco -> SEFAZ
        ValidatorHandler schema = new SchemaValidator(1000);
        ValidatorHandler cert = new CertificadoValidator(1000);
        ValidatorHandler regras = new RegrasFiscaisValidator(2000);
        ValidatorHandler db = new DatabaseValidator(1000);
        ValidatorHandler sefaz = new SEFAZValidator(1000);

        schema.setNext(cert).setNext(regras).setNext(db).setNext(sefaz);

        // ------------------- TESTE 1 -------------------
        System.out.println("\n=== TESTE 1: Fluxo OK ===");
        NFEDocument docOk = new NFEDocument("<nfe><schemaOk></schemaOk></nfe>", Instant.now().plusSeconds(3600), false, "NFE-123");
        schema.handle(docOk, ctx);
        if (ctx.getFailureCount() > 0 && ctx.hasRollbacks()) ctx.executeRollbacks();

        // ------------------- TESTE 2 -------------------
        executor.shutdownNow();
        executor = Executors.newFixedThreadPool(4);
        ctx = new ValidationContext(executor);
        System.out.println("\n=== TESTE 2: SEFAZ offline (timeout + rollback) ===");

        schema = new SchemaValidator(1000);
        cert = new CertificadoValidator(1000);
        regras = new RegrasFiscaisValidator(2000);
        db = new DatabaseValidator(1000);
        sefaz = new SEFAZValidator(500); // timeout curto
        schema.setNext(cert).setNext(regras).setNext(db).setNext(sefaz);

        NFEDocument docTimeout = new NFEDocument("<nfe><schemaOk></schemaOk><sefazOffline></sefazOffline></nfe>", Instant.now().plusSeconds(3600), false, "NFE-456");
        schema.handle(docTimeout, ctx);
        if (ctx.getFailureCount() > 0 && ctx.hasRollbacks()) ctx.executeRollbacks();

        // ------------------- TESTE 3 -------------------
        executor.shutdownNow();
        executor = Executors.newFixedThreadPool(4);
        ctx = new ValidationContext(executor);
        System.out.println("\n=== TESTE 3: Circuit Breaker (3 falhas) ===");

        schema = new SchemaValidator(1000);
        cert = new CertificadoValidator(1000);
        regras = new RegrasFiscaisValidator(2000) {
            @Override
            protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) {
                return new ValidationResult(false, "Falha proposital em Regras Fiscais");
            }
        };
        db = new DatabaseValidator(1000) {
            @Override
            protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) {
                return new ValidationResult(false, "Falha proposital em DB");
            }
        };
        sefaz = new SEFAZValidator(500) {
            @Override
            protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) {
                return new ValidationResult(false, "Falha proposital em SEFAZ");
            }
        };

        schema.setNext(cert).setNext(regras).setNext(db).setNext(sefaz);

        NFEDocument docBreaker = new NFEDocument("<nfe><schemaOk></schemaOk></nfe>", Instant.now().plusSeconds(3600), false, "NFE-789");
        schema.handle(docBreaker, ctx);

        if (ctx.isCircuitBroken()) {
            System.out.println("[MAIN] Circuit breaker ativado!");
        }

        executor.shutdownNow();
    }
}