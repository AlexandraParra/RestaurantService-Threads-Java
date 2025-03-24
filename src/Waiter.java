import java.util.Random;

public class Waiter implements Runnable{

    private final Restaurant restaurant;

    private final String name;

    public Waiter(Restaurant restaurant, String name) {
        this.restaurant = restaurant;
        this.name = name;
    }

    public void run(){
        Random random = new Random();
        try{
            while (restaurant.isRunning()) {
                Customer customer = restaurant.getCustomer();
                if (customer == null) break;

                System.out.println(name + " begins to attend to " + customer.name());
                int attendTime = random.nextInt(5) + 1;
                Thread.sleep(attendTime * 1000);
                System.out.println(name + " has finished attending to " + customer.name() + " in " + attendTime + " seconds.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + " was interrupted.");
        }
    }
}