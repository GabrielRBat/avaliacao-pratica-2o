import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class NFEDocument {
    
    public String xmlContent;
    public Instant certificadoExpiracao;
    public boolean certificadoRevogado;
    public String numeroNFE;
    public Map<String, Object> metadata = new HashMap<>();

    public NFEDocument(String xmlContent, Instant certificadoExpiracao, boolean certificadoRevogado, String numeroNFE) {

        this.xmlContent = xmlContent;
        this.certificadoExpiracao = certificadoExpiracao;
        this.certificadoRevogado = certificadoRevogado;
        this.numeroNFE = numeroNFE;
    }
}