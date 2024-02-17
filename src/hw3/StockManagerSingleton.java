package hw3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
//import java.io.BufferedWriter;
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
	public boolean saveStock() {
        try (FileWriter writer = new FileWriter(logFilePath)) {
            String[] headers = { "Type", "Title", "Price", "Year", "Genre" };
            for (int i = 0; i < headers.length; i++) {
                writer.append(headers[i]);
                if (i < headers.length - 1) {
                    writer.append(", ");
                }
            }
            writer.append(System.getProperty("line.separator"));

            for (MediaProduct product : productList) {
                if (product instanceof CDRecordProduct) {
                    writer.write("CD, " + product.getTitle() + "," + product.getPrice() + "," + product.getYear()
                            + "," + product.getGenre() + System.getProperty("line.separator"));
                } else if (product instanceof TapeRecordProduct) {
                    writer.write("Tape," + product.getTitle() + "," + product.getPrice() + "," + product.getYear()
                            + "," + product.getGenre() + System.getProperty("line.separator"));
                } else {
                    writer.write("Vinyl," + product.getTitle() + "," + product.getPrice() + "," + product.getYear()
                            + "," + product.getGenre() + System.getProperty("line.separator"));
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
            return false;
        }
    }
	
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
	
	public ArrayList<VinylRecordProduct> getVinylRecordList(ArrayList<MediaProduct> productList) {
        ArrayList<VinylRecordProduct> vinylRecordList = new ArrayList<>();
        for (MediaProduct product : productList) {
        	if ("Vinyl".equals(product.getType())) {
                if (product instanceof VinylRecordProduct) {
                    vinylRecordList.add((VinylRecordProduct) product);
                }
            }
        }
        return vinylRecordList;
    }

    public ArrayList<CDRecordProduct> getCDRecordList(ArrayList<MediaProduct> productList) {
        ArrayList<CDRecordProduct> CDRecordList = new ArrayList<>();
        for (MediaProduct product : productList) {
        	if ("CD".equals(product.getType())) {
                if (product instanceof CDRecordProduct) {
                    CDRecordList.add((CDRecordProduct) product);
                }
            }
        }
        return CDRecordList;
    }

    public ArrayList<TapeRecordProduct> getTapeRecordList(ArrayList<MediaProduct> productList) {
        ArrayList<TapeRecordProduct> TapeRecordList = new ArrayList<>();
        for (MediaProduct product : productList) {
        	if ("Tape".equals(product.getType())) {
                if (product instanceof TapeRecordProduct) {
                    TapeRecordList.add((TapeRecordProduct) product);
                }
            }
        }
        return TapeRecordList;
    }
}
