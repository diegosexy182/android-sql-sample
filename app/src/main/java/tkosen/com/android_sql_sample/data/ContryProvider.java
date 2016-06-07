package tkosen.com.android_sql_sample.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by tctkosen on 07/06/16.
 */
public class ContryProvider extends ContentProvider {
    static final int COUNTRY = 100;
    private static final SQLiteQueryBuilder sCountriesQueryBuilder;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static {
        sCountriesQueryBuilder = new SQLiteQueryBuilder();
        sCountriesQueryBuilder.setTables(CountryContract.CountryEntry.TABLE_NAME);
    }

    private CountryDbHelper countryDbHelper;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CountryContract.CountryEntry.CONTENT_AUTHORITY;
        matcher.addURI(authority, CountryContract.CountryEntry.PATH_COUNTRY, COUNTRY);
        return matcher;
    }

    public Cursor getCountries() {
        return countryDbHelper.getReadableDatabase().query(CountryContract.CountryEntry.TABLE_NAME, null, "", null, "", "", "");
    }

    @Override
    public boolean onCreate() {
        countryDbHelper = new CountryDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case COUNTRY:
                return CountryContract.CountryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case COUNTRY:
                retCursor = getCountries();
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = countryDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case COUNTRY: {
                long _id = db.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = CountryContract.CountryEntry.buildCountryUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = countryDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        if (null == selection) selection = "1";
        switch (match) {
            case COUNTRY: {
                rowsDeleted = db.delete(CountryContract.CountryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
