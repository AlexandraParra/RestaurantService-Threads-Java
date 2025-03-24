import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {

    private static final int WAITERS_NUM = 2;

    private static final int CUSTOMERS_NUM = 5;

    private Queue<Customer> customersList = new LinkedList<>();

    private final Object lock = new Object();

    private final ExecutorService waitersPool = Executors.newFixedThreadPool(WAITERS_NUM);

    private volatile boolean running = true;

    public void start() {
        for (int i = 0; i < WAITERS_NUM; i++)
            waitersPool.execute(new Waiter(this, "Waiter" + (i + 1)));

        for (int i = 0; i < CUSTOMERS_NUM; i++)
            addCustomer(new Customer("Customer" + (i + 1)));
    }

    public void addCustomer(Customer customer){
        synchronized (lock){
            this.customersList.offer(customer);
            lock.notify();
        }
    }

    public Customer getCustomer() throws InterruptedException{
        synchronized (lock){
            while (customersList.isEmpty()){
                if (!running) return null;
                lock.wait();
            }
            return customersList.poll();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        synchronized (lock) {
            running = false;
            lock.notifyAll();
        }
        waitersPool.shutdown();
        try {
            if (!waitersPool.awaitTermination(5, TimeUnit.SECONDS)) {
                waitersPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            waitersPool.shutdownNow();
        }
        System.out.println("Restaurant is now closed.");
    }
}
