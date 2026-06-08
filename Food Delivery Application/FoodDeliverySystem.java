//package threadsDemo;
//
//import java.util.*;
//import java.util.concurrent.*;
//
//class Order {
//    private final int orderId;
//    private final String foodItem;
//
//    public Order(int orderId, String foodItem) {
//        this.orderId = orderId;
//        this.foodItem = foodItem;
//    }
//
//    public int getOrderId() { return orderId; }
//    public String getFoodItem() { return foodItem; }
//
//    @Override
//    public String toString() { return "Order{" + "id=" + orderId + ", item='" + foodItem + "'}"; }
//}
//
//class Kitchen{
//    private final List<Order> orders;
//
//    public Kitchen(List<Order> orders) {
//        this.orders = orders;
//    }
//
//    public void process(Order order) {
//        System.out.println("Received order: " + order);
//        try { Thread.sleep(3000); } catch (InterruptedException e) { }
//        System.out.println("Prepared order: " + order);
//    }
//}
//
//class Driver{
//    private final List<Order> deliveries;
//
//    public Driver(List<Order> deliveries) { this.deliveries = deliveries; }
//
//    
//    public void deliver(Order order) {
//    	System.out.println("Finding delivery partner: " + order);
//        try { Thread.sleep(3000); } catch (InterruptedException e) { }
//        System.out.println("Driver delivered: " + order);
//    }
//}
//
//public class FoodDeliverySystem {
//    public static void main(String[] args) {
//    	long startTime = System.currentTimeMillis();
//
//    	List<Order> kitchenOrders = new ArrayList<>();
//        List<Order> deliveryOrders = new ArrayList<>();
//
//        Kitchen kitchen = new Kitchen(kitchenOrders);
//        Driver driver = new Driver(deliveryOrders);
//
//        Random rand = new Random();
//        String[] foodItems = {"Pizza", "Burger", "Sushi", "Pasta", "Salad"};
//
//        for (int i = 1; i <= 10; i++) {
//            Order order = new Order(i, foodItems[rand.nextInt(foodItems.length)]);
//            
//            kitchenOrders.add(order);
//            kitchen.process(order);
//
//            System.out.println("New order placed: " + order);
//
//            deliveryOrders.add(order);
//            driver.deliver(order);
//
//            try { Thread.sleep(2000); } catch (InterruptedException e) { }
//        }
//        
//        long endTime = System.currentTimeMillis();
//        long timeTaken = endTime-startTime;
//        System.out.println("Total time taken: " + timeTaken);
//    }
//}