import java.util.HashMap;

public class SistemaBancarioLegado {
    // Simula um método legado com parâmetros num HashMap
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("[LEGADO] Processando transação no sistema antigo...");

        // Simula campo obrigatório do legado
        if (!parametros.containsKey("codigoBanco")) {
            System.out.println("[ERRO] Campo 'codigoBanco' é obrigatório no sistema legado!");
            parametros.put("status", "FALHA");
            return parametros;
        }

        // Simulação de processamento
        parametros.put("status", "APROVADO");
        parametros.put("idTransacao", "TX-" + Math.round(Math.random() * 10000));
        System.out.println("[LEGADO] Transação processada com sucesso!");

        return parametros;
    }
}
