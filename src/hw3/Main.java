package hw3;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		StockManagerSingleton stockManager = StockManagerSingleton.getInstance();
        boolean initialized = stockManager.initializeStock();
        
        if (initialized) {
            ArrayList<MediaProduct> productList = (ArrayList<MediaProduct>) stockManager.getProductList(); 
            stockManager.printListOfMediaProduct(productList);
        } else {
            System.out.println("Failed to initialize stock.");
        }
        
	}

}
