package tkosen.com.android_sql_sample;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import tkosen.com.android_sql_sample.data.CountryDbHelper;

import static tkosen.com.android_sql_sample.data.CountryContract.CountryEntry;

public class MainActivity extends AppCompatActivity implements FragmentDialog.OnFragmentInteractionListener {
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
                  FragmentDialog.newInstance("Kaydet").show(getSupportFragmentManager(),"FragmentDialog");
            }
        });

    }


    @Override
    public void onButtonClicked(String name, String code, String continent, String population) {
        Toast.makeText(this,name + " "  + code + " " + continent + " " +population,Toast.LENGTH_SHORT).show();
        addCountries(name,Integer.valueOf(code),continent,Integer.valueOf(population));
    }

    long addCountries(String name, int code, String continent, int population) {
        long countryId;
        Cursor countryCursor = getContentResolver().query(CountryEntry.CONTENT_URI
                , new String[]{CountryEntry.COLUMN_NAME_COUNTRY_NAME,CountryEntry.COLUMN_NAME_COUNTRY_CODE,CountryEntry.COLUMN_NAME_COUNTRY_CONTINENT,CountryEntry.COLUMN_NAME_COUNTRY_POPULATION}
                , null
                , null
                , null);

        ContentValues countryValues = new ContentValues();
        countryValues.put(CountryEntry.COLUMN_NAME_COUNTRY_NAME, name);
        countryValues.put(CountryEntry.COLUMN_NAME_COUNTRY_CODE, code);
        countryValues.put(CountryEntry.COLUMN_NAME_COUNTRY_CONTINENT, continent);
        countryValues.put(CountryEntry.COLUMN_NAME_COUNTRY_POPULATION, population);

        Uri insertedUri = getContentResolver().insert(CountryEntry.CONTENT_URI, countryValues);
        countryId = ContentUris.parseId(insertedUri);

        countryCursor.close();

        return countryId;
    }
}
