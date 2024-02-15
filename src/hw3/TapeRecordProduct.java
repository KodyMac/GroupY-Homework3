package hw3;

public class TapeRecordProduct extends MediaProduct{
		
		public TapeRecordProduct(String title, double price, int year, Genre genre) {
			
			super(title, price, year, genre);
		}
		
		public TapeRecordProduct(TapeRecordProduct tapeRecordProduct) {
			super(tapeRecordProduct);
		}
}
