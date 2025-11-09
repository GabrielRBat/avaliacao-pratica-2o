import java.util.*;
import java.util.concurrent.ExecutorService;

public class ValidationContext {

    private final ExecutorService executor;
    private int failureCount = 0;
    private boolean circuitBroken = false;
    private final List<Runnable> rollbackActions = new ArrayList<>();

    public ValidationContext(ExecutorService executor) {
        this.executor = executor;
    }

    public synchronized void addFailure() {

        failureCount++;

        if (failureCount >= 3) {
            circuitBroken = true;
            System.out.println("[CIRCUIT] 3 falhas => cadeia interrompida!");

        } else {
            System.out.println("[CIRCUIT] Falha registrada (" + failureCount + ")");
        }
    }

    public synchronized int getFailureCount() {
        return failureCount;
    }

    public synchronized boolean isCircuitBroken() {
        return circuitBroken;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public synchronized void registerRollback(Runnable rollback) {
        rollbackActions.add(rollback);
    }

    public synchronized boolean hasRollbacks() {
        return !rollbackActions.isEmpty();
    }

    public synchronized void executeRollbacks() {

        System.out.println("[ROLLBACK] Executando " + rollbackActions.size() + " rollback(s)...");
        ListIterator<Runnable> it = rollbackActions.listIterator(rollbackActions.size());

        while (it.hasPrevious()) {
            try {
                it.previous().run();
            } catch (Exception e) {
                System.out.println("[ROLLBACK] Erro: " + e.getMessage());
            }
        }

        rollbackActions.clear();
        System.out.println("[ROLLBACK] Concluído!");
    }
}
