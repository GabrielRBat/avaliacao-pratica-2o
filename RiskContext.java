public class RiskContext {
    private double portfolioValue;
    private double volatility;
    private double interestRate;

    public RiskContext(double portfolioValue, double volatility, double interestRate) {
        this.portfolioValue = portfolioValue;
        this.volatility = volatility;
        this.interestRate = interestRate;
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
