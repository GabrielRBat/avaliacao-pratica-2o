import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DatabaseValidator extends ValidatorHandler {
    private static final Set<String> fakeDb = Collections.synchronizedSet(new HashSet<>());

    public DatabaseValidator(long timeoutMillis) {
        super(timeoutMillis);
    }

    @Override
    protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) {
        if (fakeDb.contains(doc.numeroNFE))
            return new ValidationResult(false, "Duplicidade de número NFe");

        fakeDb.add(doc.numeroNFE);
        System.out.println("[DB] Inserido NFe " + doc.numeroNFE);

        ctx.registerRollback(() -> {
            System.out.println("[DB::rollback] Removendo NFe " + doc.numeroNFE);
            fakeDb.remove(doc.numeroNFE);
        });

        return new ValidationResult(true, "NFe registrada no banco");
    }

    @Override
    protected String getName() {
        return "DatabaseValidator";
    }
}
