// GatewayAdapter.java
import java.util.HashMap;

public class GatewayAdapter implements ProcessadorTransacoes {
    private SistemaBancarioLegado sistemaLegado;

    public GatewayAdapter(SistemaBancarioLegado sistemaLegado) {
        this.sistemaLegado = sistemaLegado;
    }

    @Override
    public boolean autorizar(String cartao, double valor, String moeda) {
        System.out.println("[ADAPTER] Convertendo requisição moderna para formato legado...");

        // Converte os dados modernos em HashMap para o legado
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("numeroCartao", cartao);
        parametros.put("valor", valor);
        parametros.put("moeda", moeda);

        // Campo obrigatório do legado que o sistema novo não tem
        parametros.put("codigoBanco", "001"); 

        HashMap<String, Object> resposta = sistemaLegado.processarTransacao(parametros);

        System.out.println("[ADAPTER] Convertendo resposta legado =>  moderno...");

        String status = (String) resposta.get("status");
        if ("APROVADO".equals(status)) {
            System.out.println("[ADAPTER] Transação aprovada (ID: " + resposta.get("idTransacao") + ")");
            return true;
        } else {
            System.out.println("[ADAPTER] Transação falhou no legado.");
            return false;
        }
    }
}
