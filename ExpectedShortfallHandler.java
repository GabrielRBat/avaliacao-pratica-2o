public class ExpectedShortfallHandler extends RiskHandler {

    @Override
    public void handle(String algoName, RiskContext context) {
        if (algoName.equalsIgnoreCase("ES")) {
            
            System.out.println("[ES] Calculando Expected Shortfall...");
            System.out.println("Usando taxa de juros = " + context.getInterestRate() +  " e valor = " + context.getPortfolioValue());

            double result = context.getPortfolioValue() * context.getInterestRate() * 0.03;
            System.out.printf("Resultado simulado: Perda esperada de %.2f%n%n", result);
        } else if (nextHandler != null) {
            nextHandler.handle(algoName, context);
        }
    }
}
