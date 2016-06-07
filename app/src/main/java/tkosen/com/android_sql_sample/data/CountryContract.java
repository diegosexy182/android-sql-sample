package tkosen.com.android_sql_sample.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by tctkosen on 06/06/16.
 */
public final class CountryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CountryContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CountryEntry implements BaseColumns {
        public static final String CONTENT_AUTHORITY = "tkosen.com.sqlsample.provider";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_COUNTRY = "country";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COUNTRY).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTRY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTRY;

        public static final String TABLE_NAME = "country";
        public static final String COLUMN_NAME_COUNTRY_NAME = "name";
        public static final String COLUMN_NAME_COUNTRY_CODE = "code";
        public static final String COLUMN_NAME_COUNTRY_CONTINENT = "continent";
        public static final String COLUMN_NAME_COUNTRY_POPULATION = "population";
        public static final String COLUMN_NAME_NULLABLE = null;

        public static Uri buildCountryUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getPopulationFromUri(Uri uri) {
            return uri.getPathSegments().get(5);
        }

        public static String getContinentFromUri(Uri uri) {
            return uri.getPathSegments().get(5);
        }
    }
}
