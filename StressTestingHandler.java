public class StressTestingHandler extends RiskHandler {

    @Override
    public void handle(String algoName, RiskContext context) {
        if (algoName.equalsIgnoreCase("Stress")) {
            
            System.out.println("[Stress Test] Realizando teste de estresse...");
            System.out.println("Simulando cenário extremo com volatilidade " + (context.getVolatility() * 2));

            double result = context.getPortfolioValue() * context.getVolatility() * 0.05;
            System.out.printf("Resultado simulado: Perda potencial de %.2f%n%n", result);
        } else if (nextHandler != null) {
            nextHandler.handle(algoName, context);
        }
    }
}
