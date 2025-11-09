public class AlertaVermelhoState implements EstadoUsina {

    @Override
    public void verificarTransicao(UsinaNuclear usina) {

        if (usina.isFalhaResfriamento()) {
            usina.setEstado(new EmergenciaState());
            
        } else if (usina.getTemperatura() < 350) {
            usina.setEstado(new AlertaAmareloState());
        }
    }

    @Override
    public String getNome() {
        return "ALERTA_VERMELHO";
    }
}
