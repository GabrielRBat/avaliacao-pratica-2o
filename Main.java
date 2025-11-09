public class Main {
    public static void main(String[] args) {

        RiskContext context = new RiskContext(1_000_000, 0.15, 0.05);

        RiskHandler varHandler = new ValueAtRiskHandler();
        RiskHandler esHandler = new ExpectedShortfallHandler();
        RiskHandler stressHandler = new StressTestingHandler();

        varHandler.setNext(esHandler).setNext(stressHandler);

        System.out.println("=== Execução 1: VaR ===");
        varHandler.handle("VaR", context);

        System.out.println("=== Execução 2: ES ===");
        varHandler.handle("ES", context);

        System.out.println("=== Execução 3: Stress ===");
        varHandler.handle("Stress", context);
    }
}
