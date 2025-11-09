import java.util.concurrent.*;

public abstract class ValidatorHandler {
    protected ValidatorHandler next;
    protected final long timeoutMillis;

    public ValidatorHandler(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public ValidatorHandler setNext(ValidatorHandler next) {
        this.next = next;
        return next;
    }

    public final void handle(NFEDocument doc, ValidationContext ctx) {
        
        if (ctx.isCircuitBroken()) {
            System.out.println("[" + getName() + "] Circuito aberto — parando cadeia.");
            return;
        }

        Future<ValidationResult> future = ctx.getExecutor().submit(() -> validate(doc, ctx));
        ValidationResult result = null;

        try {
            result = future.get(timeoutMillis, TimeUnit.MILLISECONDS);

        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println("[" + getName() + "] Timeout após " + timeoutMillis + "ms");
            ctx.addFailure();

        } catch (Exception e) {
            future.cancel(true);
            System.out.println("[" + getName() + "] Erro: " + e.getMessage());
            ctx.addFailure();
        }

        if (result != null) {
            if (!result.success) ctx.addFailure();
            System.out.println("[" + getName() + "] " + result.message);

            if (!ctx.isCircuitBroken()) {
                if (result.skipNext && next != null && next.next != null) {
                    next.next.handle(doc, ctx); // pula o próximo

                } else if (next != null) {
                    next.handle(doc, ctx);
                }
            }
        }
    }

    protected abstract ValidationResult validate(NFEDocument doc, ValidationContext ctx) throws Exception;
    protected abstract String getName();
}