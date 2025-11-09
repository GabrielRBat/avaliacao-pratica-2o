public class SEFAZValidator extends ValidatorHandler {

    public SEFAZValidator(long timeoutMillis) {
        super(timeoutMillis);
    }

    @Override
    protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) throws InterruptedException {
        if (doc.xmlContent.contains("<sefazOffline>")) {
            Thread.sleep(timeoutMillis + 500); // causa timeout
            return new ValidationResult(false, "SEFAZ fora do ar");
        }
        
        Thread.sleep(300);
        return new ValidationResult(true, "Consulta SEFAZ bem-sucedida");
    }

    @Override
    protected String getName() {
        return "SEFAZValidator";
    }
}
