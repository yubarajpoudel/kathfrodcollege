package kathford.ktm.yuvi;

import java.util.List;

import kathfrod.ktm.yuvi.lib.Bookdetails;
import kathfrod.ktm.yuvi.lib.DatabaseHandler;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LibraryActivity extends BaseActivity {

	DatabaseHandler dh;
	BaseAdapter adapter;
	List<Bookdetails> bookList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library);
		setHeader(getString(R.string.LibraryTitle));
		dh = new DatabaseHandler(getApplicationContext());
		bookList = dh.getAllBooks();
		adapter = new ArrayAdapter<Bookdetails>(this,
				android.R.layout.simple_list_item_1, bookList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(R.layout.list_bg,
							null);
				}
				Bookdetails s = bookList.get(position);
				// ((TextView)findViewById(R.id.booknum)).setText(Integer.toString(position));
				TextView name = (TextView) convertView
						.findViewById(R.id.bookname);
				name.setText(s.getBookname());
				TextView authors = (TextView) convertView
						.findViewById(R.id.bookauthor);
				authors.setText(s.getBookauthor());

				return convertView;
			}
		};
		ListView list = (ListView) findViewById(R.id.booklist);
		list.setAdapter(adapter);

		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				showOptions(position);
				return true;
			}
		});
		/*
		 * private void showOptions(Bookdetails bookdetails) { // TODO
		 * Auto-generated method stub AlertDialog.Builder dialogbox = new
		 * AlertDialog.Builder(getApplicationContext());
		 * /*dialogbox.setTitle("DEscription");
		 * dialogbox.setMessage("Book name :" + bookdetails.getBookname() + "\n"
		 * + "Author :"+ bookdetails.getBookauthor()+ "\n" + "Date:" +
		 * bookdetails.getdate()); .setPositiveButton("Dismiss", null)
		 * .dialogbox.create().show(); }
		 */

	}

	protected void showOptions(final int position) {
		Bookdetails bookdetails = bookList.get(position);
		final int book_id = bookdetails.getId();
		AlertDialog.Builder dialogbox = new AlertDialog.Builder(this);
		dialogbox.setTitle("Description");
		dialogbox
				.setMessage(
						"Book name :" + bookdetails.getBookname() + "\n"
								+ "Author :" + bookdetails.getBookauthor()
								+ "\n" + "Date:" + bookdetails.getdate())
								.setPositiveButton("Dismiss", null)
				.setNegativeButton("Delete", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						// TODO Auto-generated method stub
						// Bookdetails bds = bookList.remove(which);

						// dh.deleterecord(bds.getId());

						// adapter.notifyDataSetChanged();

						dh.deleterecord(book_id);
						bookList.remove(position);
						adapter.notifyDataSetChanged();

					}
				}).create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bookmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.save_button:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			final View custom = getLayoutInflater().inflate(R.layout.add_books,
					null);
			builder.setView(custom);
			builder.setPositiveButton("Add New",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							String book_name = ((TextView) custom
									.findViewById(R.id.txt_name)).getText()
									.toString();
							String book_author = ((TextView) custom
									.findViewById(R.id.txt_author)).getText()
									.toString();
							if (book_name.length() > 1) {
								Bookdetails bd = new Bookdetails(0, book_name,
										book_author, null);
								dh = dh.addBook(bd);

							}
							// Toast.makeText(getApplicationContext(),
							// "success", Toast.LENGTH_LONG).show();

						}

					}).setNegativeButton("Cancel", null).create().show();
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}