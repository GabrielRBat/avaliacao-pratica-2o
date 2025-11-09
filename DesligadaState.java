public class DesligadaState implements EstadoUsina {

    @Override
    public void verificarTransicao(UsinaNuclear usina) {

        if (usina.getTemperatura() > 50) {

            usina.setEstado(new OperacaoNormalState());
        }
    }

    @Override
    public String getNome() {
        return "DESLIGADA";
    }
}
