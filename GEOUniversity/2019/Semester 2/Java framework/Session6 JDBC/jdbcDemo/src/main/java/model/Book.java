package model;

import java.util.Date;

public class Book {
	
	private Integer bookId;
	private String bookName;
	private String authors;
	private String publisher;
	private Date   pubDate;
	private Double price;
	
	public Book(){
		
	}

	public Book(Integer bookId, String bookName, String authors, String publisher, Date pubDate, Double price) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authors = authors;
		this.publisher = publisher;
		this.pubDate = pubDate;
		this.price = price;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authors=" + authors + ", publisher=" + publisher
				+ ", pubDate=" + pubDate + ", price=" + price + "]";
	}
	

}
