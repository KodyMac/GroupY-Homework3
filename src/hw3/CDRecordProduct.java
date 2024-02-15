package hw3;

public class CDRecordProduct extends MediaProduct {
		
		public CDRecordProduct(String title, double price, int year, Genre genre) {
			
			super(title, price, year, genre);
		}
		
		public CDRecordProduct(CDRecordProduct cDRecordProduct) {
			super(cDRecordProduct);
		}
}
