import java.time.Instant;

public class CertificadoValidator extends ValidatorHandler {
    public CertificadoValidator(long timeoutMillis) {
        super(timeoutMillis);
    }

    @Override
    protected ValidationResult validate(NFEDocument doc, ValidationContext ctx) {
        if (doc.certificadoRevogado) return new ValidationResult(false, "Certificado revogado");
        if (doc.certificadoExpiracao == null || doc.certificadoExpiracao.isBefore(Instant.now()))
            return new ValidationResult(false, "Certificado expirado");
        return new ValidationResult(true, "Certificado válido");
    }

    @Override
    protected String getName() {
        return "CertificadoValidator";
    }
}
