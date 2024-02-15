package hw3;

public class VinylRecordProduct extends MediaProduct {
	public VinylRecordProduct(String title, double price, int year, Genre genre) {
			
		super(title, price, year, genre);
	}
		
	public VinylRecordProduct(VinylRecordProduct vinylRecordProduct) {
		super(vinylRecordProduct);
	}
}
