// Main.java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        UsinaNuclear usina = new UsinaNuclear();

        System.out.println("Estado inicial: " + usina.getEstadoAtual().getNome());

        usina.atualizarLeituras(100, 2.0, 0.01, false);
        usina.atualizarLeituras(320, 2.5, 0.02, false);  // Vai para Alerta Amarelo
        Thread.sleep(31000); 
        usina.atualizarLeituras(420, 2.6, 0.02, false);  // Vai para Alerta Vermelho
        usina.atualizarLeituras(420, 2.8, 0.03, true);   // Falha de resfriamento → Emergência

        // Ativa manutenção
        usina.ativarModoManutencao(true);
        usina.atualizarLeituras(100, 2.0, 0.01, false);

        // Desativa manutenção
        usina.ativarModoManutencao(false);
        usina.atualizarLeituras(200, 2.0, 0.01, false);
    }
}
