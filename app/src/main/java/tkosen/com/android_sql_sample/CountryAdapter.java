package tkosen.com.android_sql_sample;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by tctkosen on 06/06/16.
 */
public class CountryAdapter extends CursorAdapter {

    public CountryAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_country, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtName =  (TextView) view.findViewById(R.id.txt_name);
        TextView txtCode =  (TextView) view.findViewById(R.id.txt_code);
        TextView txtContinent =  (TextView) view.findViewById(R.id.txt_continent);
        TextView txtPopulation =  (TextView) view.findViewById(R.id.txt_population);

        txtName.setText(cursor.getString(0));
        txtCode.setText(cursor.getString(1));
        txtContinent.setText(cursor.getString(2));
        txtPopulation.setText(cursor.getString(3));
    }
}
