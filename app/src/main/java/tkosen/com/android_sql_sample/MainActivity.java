package tkosen.com.android_sql_sample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import tkosen.com.android_sql_sample.data.CountryDbHelper;

import static tkosen.com.android_sql_sample.data.CountryContract.CountryEntry;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button btn_add;
    CountryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new CountryDbHelper(getApplicationContext());


        list = (ListView) findViewById(R.id.list);
        btn_add = (Button) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValues("Test",1);
                readValues();
            }
        });

    }


    public void putValues(String title, int code) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CountryEntry.COLUMN_NAME_COUNTRY_NAME, title);
        values.put(CountryEntry.COLUMN_NAME_COUNTRY_CODE, code);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                CountryEntry.TABLE_NAME,
                CountryEntry.COLUMN_NAME_NULLABLE,
                values);

        System.out.print("SQLTest  write : " + newRowId);

    }

    public void readValues() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CountryEntry._ID,
                CountryEntry.COLUMN_NAME_COUNTRY_NAME,
                CountryEntry.COLUMN_NAME_COUNTRY_CODE
        };

        String[] args = {"90"};
        // How you want the results sorted in the resulting Cursor
        String sortOrder = CountryEntry.COLUMN_NAME_COUNTRY_CODE + " DESC";

        Cursor cursor = db.query(
                CountryEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                CountryEntry.COLUMN_NAME_COUNTRY_CODE + " = ?",// The columns for the WHERE clause
                args,                                          // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        cursor.moveToFirst();

        long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(CountryEntry._ID));
        System.out.print("SQLTest  Read : " + itemId);
    }
}
