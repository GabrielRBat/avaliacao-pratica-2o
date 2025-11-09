// UsinaNuclear.java
public class UsinaNuclear {
    private EstadoUsina estadoAtual;
    private double temperatura;
    private double pressao;
    private double radiacao;
    private boolean falhaResfriamento;
    private boolean modoManutencao;

    public UsinaNuclear() {
        this.estadoAtual = new DesligadaState();
    }

    public void setEstado(EstadoUsina novoEstado) {

        if (!modoManutencao) {
            System.out.println("Transição: " + estadoAtual.getNome() + " => " + novoEstado.getNome());
            this.estadoAtual = novoEstado;

        } else {
            System.out.println("[MODO MANUTENÇÃO] Transições ignoradas.");
        }
    }

    public EstadoUsina getEstadoAtual() {
        return estadoAtual;
    }

    public void atualizarLeituras(double temperatura, double pressao, double radiacao, boolean falhaResfriamento) {
        this.temperatura = temperatura;
        this.pressao = pressao;
        this.radiacao = radiacao;
        this.falhaResfriamento = falhaResfriamento;
        estadoAtual.verificarTransicao(this);
    }

    public double getTemperatura() { return temperatura; }
    public double getPressao() { return pressao; }
    public double getRadiacao() { return radiacao; }
    public boolean isFalhaResfriamento() { return falhaResfriamento; }

    public void ativarModoManutencao(boolean ativo) {

        modoManutencao = ativo;

        if (ativo) {

            System.out.println("[MODO MANUTENÇÃO ATIVADO]");
            this.estadoAtual = new ManutencaoState();
        } else {

            System.out.println("[MODO MANUTENÇÃO DESATIVADO]");
            this.estadoAtual = new OperacaoNormalState(); 
        }
    }
}
