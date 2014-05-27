package kathfrod.ktm.yuvi.lib;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

   
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Books";
    // Bookdetails table name
    private static final String TABLE_BOOKS = "booklist";
 
    // Bookdetails Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AUTH = "author";

	public static final String KEY_DATE = "date";

	    
    
    
 
	public DatabaseHandler(Context context) {
		super(context,DATABASE_NAME, null,DATABASE_VERSION );
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		String table_create = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		                      + KEY_NAME + " TEXT, " + KEY_AUTH + " TEXT, " + KEY_DATE + " TIMESTAMP NOT NULL DEFAULT current_timestamp );";
		db.execSQL(table_create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		onCreate(db);
		
	}
	
	public DatabaseHandler addBook(Bookdetails c){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, c.getBookname());
		values.put(KEY_AUTH, c.getBookauthor());
	    db.insert(TABLE_BOOKS, null, values);
		db.close();
	   return null;
		
	}
	



	public List<Bookdetails> getAllBooks() {
		// TODO Auto-generated method stub
		List<Bookdetails> bookList = new ArrayList<Bookdetails>();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.query(TABLE_BOOKS, null, null, null, null, null, null);

		if(cursor.moveToFirst()){
			do {
				Bookdetails s = new Bookdetails(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
				bookList.add(s);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return bookList;
	}





	public void deleterecord(int book_id) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		try
	    {
			String deletequery = "DELETE FROM " + TABLE_BOOKS + "WHERE " + KEY_ID + " =\"" + book_id +"\";"; 
	        db.execSQL(deletequery);
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        db.close();
	    }
	}


	
}

