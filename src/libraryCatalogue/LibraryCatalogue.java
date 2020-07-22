package libraryCatalogue;

import java.util.HashMap;
import java.util.Map;

public class LibraryCatalogue {
	
	Map<String, Book> bookCollection = new HashMap<String, Book>();
	static int currentDay;
	int lengthOfCheckOutPeriod = 7;
	double intitialLateFee = 0.50;
	double feePerLateDay = 1.00;
	

	//constructors
	public LibraryCatalogue(Map<String, Book> collection) {
		this.bookCollection = collection;
	}
	
	public LibraryCatalogue(Map<String, Book> collection, int lengthOfCheckOutPeriod, 
		double initialLateFee, double feePerLateDay) {
		this.bookCollection = collection;
		this.lengthOfCheckOutPeriod = lengthOfCheckOutPeriod;
		this.intitialLateFee = initialLateFee;
		this.feePerLateDay = feePerLateDay;
		
	}
		
	//getters
	public Map<String, Book> getBookCollection(){
		return this.bookCollection;
	}
	
	public Book getBook(String bookTitle) {
		return getBookCollection().get(bookTitle);
	}
	
	public int getCurrentDay() {
		return this.currentDay;
	}
	
	public int getLengthOfCheckOutPeriod() {
		return this.lengthOfCheckOutPeriod;
	}
	
	public double getInitialLateFee() {
		return this.intitialLateFee;
	}
	
	public double getFeePerLateDay() {
		return this.feePerLateDay;
	}
	
	//setters
	
	public void nextDay(int currentDay) {
		currentDay++;
	}
	
	public void setDay(int day) {
		currentDay = day;
	}
	
	//instance methods
	public void checkOutBook(String title) {
		Book book = getBook(title);
		if(book.getIsCheckedOut()) {
			sorryBookAlreadyCheckedOut(book);
		}else {
			book.setIsCheckedOut(true, currentDay);
			System.out.println("You just checked out " + title + " it is due on day " +
					(getCurrentDay() + getLengthOfCheckOutPeriod()));
		}
	}
	
	public void returnBook(String title) {
		Book book = getBook(title);
		int daysLate = currentDay - (book.getDayCheckedOut() + getLengthOfCheckOutPeriod());
		if (daysLate > 0) {
			System.out.println("You owe the library " + (getInitialLateFee() + daysLate * getFeePerLateDay())
					+ " because your book is " + daysLate + " days overdue.");
		}else {
			System.out.println("Book returned, thank you.");
		}
		
	}
	
	public void sorryBookAlreadyCheckedOut(Book book) {
		System.out.println("Sorry " + book.getTitle() + " is already checked out. "
				+ "It should be back on " + book.getDayCheckedOut() + getLengthOfCheckOutPeriod() + ".");
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Book> bookCollection = new HashMap<String, Book>();
		Book harry = new Book("Harry Potter", 87139, 9999999);
		bookCollection.put("Harry Potter", harry);
		LibraryCatalogue lib = new LibraryCatalogue(bookCollection);
		lib.checkOutBook("Harry Potter");
		lib.nextDay(currentDay);
		lib.checkOutBook("Harry Potter");
		lib.setDay(15);
		
		

	}

}
