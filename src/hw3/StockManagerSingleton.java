package hw3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StockManagerSingleton {
	private final static String FilePath="files/inventoryGiven.csv";
	ArrayList<MediaProduct>productList = new ArrayList<MediaProduct>();
	public boolean initializeStock() {
		try {
			Scanner fileIn= new Scanner(new FileInputStream(FilePath));
			fileIn.nextLine();
			
			while(fileIn.hasNextLine()) {
				String[] parts = fileIn.nextLine().split(",");
				String type = parts[0];
				String title = parts[1];
				double price = Double.parseDouble(parts[2]);
				int year = Integer.parseInt(parts[3]);
				Genre genre = Genre.valueOf(parts[4]);
				
				MediaProduct product;
				switch(type) {  //if (parts[0] isn't a valid type, print out it's title and continue
				case "CD":
					product = new CDRecordProduct(title,price,year,genre);
					break;
				case "Tape":
					product = new TapeRecordProduct(title,price,year,genre);
					break;
				case "Vinyl":
					product = new VinylRecordProduct(title,price,year,genre);
				break;
			default:
				System.out.println("Line failed to be read. Title: " + title);
				continue;
				}
				productList.add(product);
			}
			fileIn.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
			}
		}

//update price, return false if fail
	public boolean updateItemPrice(MediaProduct product, double newPrice) {
		for(MediaProduct item : productList) {
			if(item.equals(product)) {
				item.setPrice(newPrice);;
				return true;
			}
		}
		return false;
	}
	
	//add new item to inventory
	public boolean addItem(MediaProduct product) {
		if(product!=null) {
			productList.add(product);
			return true;
		}
		return false;
	}
		//remove given product from inventory
	public boolean removeItem(MediaProduct product) {
		int counter = 0;
		for(MediaProduct i : productList) {
			System.out.println(i.toString());
			//if(product.genre == i.genre && product price == i.price && product.title == i.title && product.year == i.year) {)
			if(product == i) {
				productList.remove(counter);
				return true;
			}
			counter++;
		}
		return false;
	}
	
	public boolean saveStock() {
		try(FileWriter writer = new FileWriter(FilePath)) {
			String[] headers = {"Type", "Title", "Price", "Year", "Genre"};
			
			for(int i=0; i<headers.length; i++) {
				writer.append(headers[i]);
				if(i < headers.length - 1) {
					writer.append(", ");
				}
			}
			writer.append(System.getProperty("newLine"));
			
			for(MediaProduct product : productList) {
				if(product instanceof CDRecordProduct) {
					writer.write("CD, " + product.getTitle() + ", " + product.getPrice() + ", " + product.getYear() + ", " + product.getGenre() + System.getProperty("newLine"));
				} else if(product instanceof TapeRecordProduct) { // Correct variable used
	                writer.write("Tape, " + product.getTitle() + ", " + product.getPrice() + ", " + product.getYear() + ", " + product.getGenre() + System.getProperty("newLine"));
	            } else {
	                writer.write("Vinyl, " + product.getTitle() + ", " + product.getPrice() + ", " + product.getYear() + ", " + product.getGenre() + System.getProperty("newLine"));
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			return false;
		}
	}

//package hw3;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList; 
//
//
//
//public class StockManagerSingleton {
//	private static StockManagerSingleton instance = null;
//	
//	private final static String FilePath="files/inventoryGiven.csv";
//	
//	//private constructor prevents class being created outside of this (only be created by itself)
//	private StockManagerSingleton() {
//	}
//	
//	public static StockManagerSingleton getInstance() {
//		if(instance==null) {
//			instance = new StockManagerSingleton();
//		}
//		return instance;
//	}
//	
//	public boolean stockInitialize() {
//		try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))) {
//			String line;
//			while((line=reader.readLine())!=null) {
//				String[] parts = line.split(","); //separate lines
//				
//				if(parts.length == 5) {  //line has the 4 needed parts (title,price,year,genre)
//						String type = parts[0].trim();
//						String title = parts[1].trim();
//						double price = Double.parseDouble(parts[2].trim());
//						int year = Integer.parseInt(parts[3].trim());
//						Genre genre = Genre.valueOf(parts[4].trim());
//					StockManagerSingleton.getInstance().getInventory().addItem(mediaProduct);
//									} 
//													} 
//		} catch (IOException err) {
//			return false;
//		}
//	}
//	
//	//update price, return false if fail
//	public boolean updateItemPrice(MediaProduct product, double newPrice) {
//	
//	}
//	
//	//add new item to inventory
//	public boolean addItem(MediaProduct product) {
//		
//	}
//	
//	public boolean removeItem(MediaProduct product) {
//		//remove given product from inventory
//	}
//	
//	public boolean saveStock() {
//		try(BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath))) {
//		   //save	
//		} catch (IOException e) {
//			return false;
//		}
//	}
//	
//	public ArrayList<MediaProduct> getMediaProductBelowPrice(int maxPrice) {
//			//gets media products that are below given price
//	}
//	
//	public void printListOfMediaProduct(ArrayList<MediaProduct> productList) {
//			//print array list
//	}
//	
//	public ArrayList<VinylRecordProduct> getVinylRecordList(ArrayList<MediaProduct> productList) {
//		
//	}
//	
//}
