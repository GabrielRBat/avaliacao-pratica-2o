public class ValueAtRiskHandler extends RiskHandler {

    @Override
    public void handle(String algoName, RiskContext context) {
        if (algoName.equalsIgnoreCase("VaR")) {

            System.out.println("[VaR] Calculando Value at Risk...");
            System.out.println("Usando volatilidade = " + context.getVolatility() + " e valor = " + context.getPortfolioValue());
            
            double result = context.getPortfolioValue() * context.getVolatility() * 0.02;
            System.out.printf("Resultado simulado: Risco estimado de %.2f%n%n", result);
        } else if (nextHandler != null) {
            nextHandler.handle(algoName, context);
        }
    }
}
