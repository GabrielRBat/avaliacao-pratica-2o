public class Main {
    public static void main(String[] args) {

        SistemaBancarioLegado legado = new SistemaBancarioLegado();


        ProcessadorTransacoes processador = new GatewayAdapter(legado);

        System.out.println("=== Teste 1 ===");
        processador.autorizar("4111-2222-3333-4444", 250.0, "BRL");

        System.out.println("\n=== Teste 2 (simulando outro valor) ===");
        processador.autorizar("5555-6666-7777-8888", 750.0, "USD");
    }
}
