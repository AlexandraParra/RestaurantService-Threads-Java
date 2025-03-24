public class Main {
    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant = new Restaurant();
        restaurant.start();
        Thread.sleep(10_000);
        restaurant.stop();
    }
}