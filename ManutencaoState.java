public class ManutencaoState implements EstadoUsina {
    
    @Override
    public void verificarTransicao(UsinaNuclear usina) {
        System.out.println("[MANUTENÇÃO] Nenhuma verificação de estado sendo feita.");
    }

    @Override
    public String getNome() {
        return "MANUTENCAO";
    }
}
