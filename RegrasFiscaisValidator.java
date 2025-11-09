public class RegrasFiscaisValidator extends ValidatorHandler {

    public RegrasFiscaisValidator(long timeoutMillis) {
        super(timeoutMillis);
    }

    @Override
    protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) throws InterruptedException {
        
        Thread.sleep(200);
        if (doc.xmlContent.contains("<impostoInvalido>")) {
            return new ValidationResult(false, "Erro no cálculo de impostos");
        }

        return new ValidationResult(true, "Regras fiscais validadas");
    }

    @Override
    protected String getName() {
        return "RegrasFiscaisValidator";
    }
}
