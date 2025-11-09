public class EmergenciaState implements EstadoUsina {
    
    @Override
    public void verificarTransicao(UsinaNuclear usina) {
        System.out.println("[EMERGÊNCIA] Todos os sistemas de segurança ativados!");
    }

    @Override
    public String getNome() {
        return "EMERGENCIA";
    }
}
