package tkosen.com.android_sql_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import tkosen.com.android_sql_sample.data.CountryDbHelper;

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
    }
}
