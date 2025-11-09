public class OperacaoNormalState implements EstadoUsina {
    
    @Override
    public void verificarTransicao(UsinaNuclear usina) {

        if (usina.getTemperatura() > 300) {
            usina.setEstado(new AlertaAmareloState());
        }
    }

    @Override
    public String getNome() {
        return "OPERACAO_NORMAL";
    }
}
