package hw3;
import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException; 


public class StockManagerSingleton {
	private static StockManagerSingleton instance = null;
	
	private final static String logFilePath="files/inventoryGiven.csv";
	
	//private constructor prevents class being created outside of this (only be created by itself)
	private StockManagerSingleton() {
	}
	
	public static StockManagerSingleton getInstance() {
		if(instance==null) {
			instance = new StockManagerSingleton();
		}
		return instance;
	}
	
}
