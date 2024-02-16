package hw3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class StockManagerSingleton {
	private static StockManagerSingleton instance = null;
	
	private final static String logFilePath="files/inventoryGiven.csv";
	
	private List<MediaProduct> productList = new ArrayList<>();

	private ArrayList<MediaProduct> mediaProductsBelowPrice = new ArrayList<>();
	
	//private constructor prevents class being created outside of this (only be created by itself)
	private StockManagerSingleton() {
	}
	
	public static StockManagerSingleton getInstance() {
		if(instance==null) {
			instance = new StockManagerSingleton();
		}
		return instance;
	}
//source: https://www.studytonight.com/java-examples/reading-a-csv-file-in-java#:~:text=Reading%20CSV%20Files%20by%20Using,the%20comma%20as%20a%20delimiter.
	public boolean initializeStock() {
		try {
			List < List <String> > data = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(logFilePath));
			br.readLine();
			String line = br.readLine();
			while (line != null) {
				List<String> lineData = Arrays.asList(line.split(","));
				data.add(lineData);
				line = br.readLine();
			}
			for(List<String> list : data) {
				String type = list.get(0);
				String title = list.get(1);
				double price = Double.parseDouble(list.get(2));
				int year = Integer.parseInt(list.get(3));
				Genre genre = Genre.valueOf(list.get(4));
				
				MediaProduct product = null;
				if (type.equals("CD")) {
						product = new CDRecordProduct(title, price, year, genre);
				} else if (type.equals("Vinyl")) {
					product = new VinylRecordProduct(title, price, year, genre);
				} else if (type.equals("Tape")) {
					product = new TapeRecordProduct(title, price, year, genre);
				} else {
					System.out.println("Invalid type");
				}
				
				if (product != null) {
					productList.add(product);
				}
				//System.out.println(product); test
			}
			
			br.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean updateItemPrice(MediaProduct product, double newPrice) {
		for(MediaProduct item : productList) {
			if(item.equals(product)) {
				item.setPrice(newPrice);
				return true;
			}
		}
		return false;
	}
	public boolean addItem(MediaProduct product) {
		if (product != null) {
			productList.add(product);
			return true;
		}
		return false;
	}
	public boolean removeItem(MediaProduct product) {
		if (product != null) {
			productList.remove(product);
			return true;
		}
		return false;
	}
	//public boolean saveStock()
	
	public ArrayList<MediaProduct> getMediaProductsBelowPrice(int maxPrice) {
		for (MediaProduct product: productList) {
			if(product.getPrice() < maxPrice) {
				mediaProductsBelowPrice.add(product);
			}
		}
		return mediaProductsBelowPrice;
	}
	//access
	public List<MediaProduct> getProductList() {
	    return productList;
	}
	
	public void printListOfMediaProduct(ArrayList<MediaProduct> productList) {
		for (MediaProduct product: productList) {
			System.out.println(product);
		}
	}
	//public ArrayList<VinylRecordProduct> getVinylRecordList(ArrayList<MediaProduct> productList) {}
	//public ArrayList<CDRecordProduct> getCDRecordsList(ArrayList<MediaProduct> productList) {}
	//public ArrayList<TapeRecordProduct> getTapeRecordList(ArrayList<MediaProduct> productList) {}
}
