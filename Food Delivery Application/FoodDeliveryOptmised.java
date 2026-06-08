package threadsDemo;

import java.util.*;
import java.util.concurrent.*;

class Order {
    private final int orderId;
    private final String foodItem;

    public Order(int orderId, String foodItem) {
        this.orderId = orderId;
        this.foodItem = foodItem;
    }

    public int getOrderId() { return orderId; }
    public String getFoodItem() { return foodItem; }

    @Override
    public String toString() { return "Order{" + "id=" + orderId + ", item='" + foodItem + "'}"; }
}

class ProcessOrderAction extends RecursiveAction{
	private final List<Order> orders;
	private final int start;
	private final int end;
	
	public ProcessOrderAction(List<Order> orders, int start, int end) {
		this.orders = orders;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected void compute() {
		for(int i=start;i<end;i++) {
			processOrder(orders.get(i));
		}
	}
	
	private void processOrder(Order order) {
		System.out.println("Kitchen preparing:" + order);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Kitchen prepared:" + order);
	}
}

class Kitchen extends Thread{
    private final List<Order> orders;
    private final ForkJoinPool forkJoinPool;
    
    public Kitchen(List<Order> orders) {
        this.orders = orders;
        this.forkJoinPool = new ForkJoinPool();
    }

    public void run() {
    	while(true) {
    		synchronized (orders) {
    			while(orders.isEmpty()) {
        			try {
    					orders.wait();
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
        		}
        		
    			List<Order> ordersToProcessList = new ArrayList<Order>(orders);
    			orders.clear();
    			forkJoinPool.execute(new ProcessOrderAction(ordersToProcessList, 0, ordersToProcessList.size()));
			}
    	}
    	
    }
}

class Driver extends Thread{
    private final List<Order> deliveries;

    public Driver(List<Order> deliveries) { this.deliveries = deliveries; }

    
    public void run() {
    	while(true) {
    		synchronized (deliveries) {
    			while(deliveries.isEmpty()) {
        			try {
        				deliveries.wait();
    				} catch (Exception e) {
    					System.out.println(e);
    				}
        		}
        		Order order = deliveries.remove(0);
        		System.out.println("Finding delivery partner: " + order);
                try { Thread.sleep(3000); } catch (InterruptedException e) { }
                System.out.println("Driver delivered: " + order);
			}
    	}
    }
}

public class FoodDeliveryOptmised {
    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();

    	List<Order> kitchenOrders = new ArrayList<>();
        List<Order> deliveryOrders = new ArrayList<>();

        Kitchen kitchen = new Kitchen(kitchenOrders);
        Driver driver = new Driver(deliveryOrders);
        
        kitchen.start();
        driver.start();

        Random rand = new Random();
        String[] foodItems = {"Pizza", "Burger", "Sushi", "Pasta", "Salad"};

        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i, foodItems[rand.nextInt(foodItems.length)]);
            
            synchronized (kitchenOrders) {
            	kitchenOrders.add(order);
            	kitchenOrders.notify();
			}

            System.out.println("New order placed: " + order);

            synchronized (deliveryOrders) {
            	deliveryOrders.add(order);
            	deliveryOrders.notify();
			}
            

            try { Thread.sleep(2000); } catch (InterruptedException e) { }
        }
        
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime-startTime;
        System.out.println("Total time taken: " + timeTaken);
    }
}
