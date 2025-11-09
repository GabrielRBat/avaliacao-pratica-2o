public class SchemaValidator extends ValidatorHandler {

    public SchemaValidator(long timeoutMillis) {
        super(timeoutMillis);
    }

    @Override
    protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) {
        
        if (doc.xmlContent != null && doc.xmlContent.contains("<schemaOk>")) {
            return new ValidationResult(true, "XML válido");
        }
        return new ValidationResult(false, "XML inválido");
    }

    @Override
    protected String getName() {
        return "SchemaValidator";
    }
}
