public abstract class RiskHandler {
    protected RiskHandler nextHandler;

    public RiskHandler setNext(RiskHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract void handle(String algoName, RiskContext context);
}
