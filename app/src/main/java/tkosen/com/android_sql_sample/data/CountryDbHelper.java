package tkosen.com.android_sql_sample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static tkosen.com.android_sql_sample.data.CountryContract.*;

/**
 * Created by tctkosen on 06/06/16.
 */
public class CountryDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Country.db";

    public CountryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        final String TEXT_TYPE = " TEXT NOT NULL";
        final String INTEGER_TYPE = " INTEGER NOT NULL";

        final String COMMA_SEP = ",";
        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + CountryEntry.TABLE_NAME + " (" +
                        CountryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CountryEntry.COLUMN_NAME_COUNTRY_NAME + TEXT_TYPE + COMMA_SEP +
                        CountryEntry.COLUMN_NAME_COUNTRY_CODE + INTEGER_TYPE + COMMA_SEP +
                        CountryEntry.COLUMN_NAME_COUNTRY_CONTINENT + TEXT_TYPE + COMMA_SEP +
                        CountryEntry.COLUMN_NAME_COUNTRY_POPULATION + INTEGER_TYPE + " )";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_CREATE_ENTRIES = "DROP TABLE IF EXISTS " + CountryEntry.TABLE_NAME;
        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}