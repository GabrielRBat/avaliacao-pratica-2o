public class AlertaAmareloState implements EstadoUsina {

    private long tempoInicio;

    public AlertaAmareloState() {
        this.tempoInicio = System.currentTimeMillis();
    }

    @Override
    public void verificarTransicao(UsinaNuclear usina) {

        long tempo = System.currentTimeMillis() - tempoInicio;
        
        if (usina.getTemperatura() > 400 && tempo > 30000) {

            usina.setEstado(new AlertaVermelhoState());
            
        } else if (usina.getTemperatura() < 280) {
            usina.setEstado(new OperacaoNormalState());
        }
    }

    @Override
    public String getNome() {
        return "ALERTA_AMARELO";
    }
}
