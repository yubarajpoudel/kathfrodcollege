package kathfrod.ktm.yuvi.lib;

import android.app.Activity;

public class Bookdetails extends Activity {
	int id;
	String bookname;
	String bookauthor;
	String bookdate;

	public Bookdetails(int id, String bookname, String bookauthor ,String bookdate) {
		this.id = id;
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.bookdate =bookdate;
	
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getdate() {
		return bookdate;
	}
	public void setdate(String bookdate) {
		this.bookdate = bookdate;
	}
}
