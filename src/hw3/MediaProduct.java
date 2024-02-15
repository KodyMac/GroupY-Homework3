package hw3;

public class MediaProduct {
	//attributes
		protected String title;
		protected double price;
		protected int year;
		protected Genre genre;
		
			//Constructor
		public MediaProduct(String title, double price, int year, Genre genre) {
			this.title=title;
			this.price=price;
			this.year=year;
			this.genre=genre;
		}
			public MediaProduct(CDRecordProduct cdRecordProduct) {
		}
			public MediaProduct(VinylRecordProduct vinylRecordProduct) {
		}
		public MediaProduct(TapeRecordProduct tapeRecordProduct) {
			}
		//vinyl subclass
		class VinylRecordProduct extends MediaProduct {
			public VinylRecordProduct(String title, double price, int year, Genre genre) {
				super(title,price,year,genre);
			}
			//copy constructor?
			//public VinylRecordProduct(VinylRecordProduct vinyl) {
			//	super(vinyl);
			//	//title=vinyl.title;
			//	//price=vinyl.price;
			//	//year=vinyl.year;
			//	//genre=vinyl.genre;
			//}
			}
		
		//cd subclass
		class CDRecordProduct extends MediaProduct {
			public CDRecordProduct(String title, double price, int year, Genre genre) {
				super(title, price, year, genre);
			}
			
			//copy constructor
			//public CDRecordProduct(CDRecordProduct cd) {
				//super(cd);
			//}
		}
		
		//tape subclass
			class TapeRecordProduct extends MediaProduct {
				public TapeRecordProduct(String title, double price, int year, Genre genre) {
					super(title, price, year, genre);
				}
				
				//copy constructor
				//public TapeRecordProduct(TapeRecordProduct tape) {
					//super(tape);
				//}
			}
		
		
		
			//getters and setters
		protected String getTitle() {
			return title;
		}
		
		protected void setTitle(String title) {
			this.title=title;
		}
		
		protected double getPrice() {
			return price;
		}
		
		protected void setPrice(double price) {
			this.price=price;
		}
		
		protected int getYear() {
			return year;
		}
		
		protected void setYear(int year) {
			this.year=year;
		}
		
		protected Genre getGenre() {
			return genre;
		}
		
		protected void setGenre(Genre genre) {
			this.genre=genre;
		}
		
		public String toString() {
			return "Title: " + title + ", Price: " + price + ", Year: " + year + ", Genre: " + genre;
		}
	}
