package tkosen.com.android_sql_sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentDialog extends DialogFragment implements View.OnClickListener{
    private static final String ARG_DATA_FROM_ACTIVTY = "dataFromActivity";
    private String mDataFromActivity;
    private OnFragmentInteractionListener mListener;

    EditText edt_name,edt_code,edt_continent,edt_population;
    Button btn_save;

    public FragmentDialog() {
        // Required empty public constructor
    }

    public static FragmentDialog newInstance(String dataFromActivity) {
        FragmentDialog fragment = new FragmentDialog();
        Bundle args = new Bundle();
        args.putString(ARG_DATA_FROM_ACTIVTY, dataFromActivity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDataFromActivity = getArguments().getString(ARG_DATA_FROM_ACTIVTY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.dialog_fragment,container,false);

        edt_code = (EditText) vi.findViewById(R.id.edt_code);
        edt_name = (EditText) vi.findViewById(R.id.edt_name);
        edt_continent = (EditText) vi.findViewById(R.id.edt_continent);
        edt_population = (EditText) vi.findViewById(R.id.edt_population);

        btn_save = (Button) vi.findViewById(R.id.btn_save);
        btn_save.setText(mDataFromActivity);
        btn_save.setOnClickListener(this);
        return vi;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                onButtonPressed(edt_name.getText().toString(),
                        edt_code.getText().toString(),
                        edt_continent.getText().toString(),
                        edt_population.getText().toString());

                dismiss();
                break;
        }
    }

    public void onButtonPressed(String name, String code, String continent, String population) {
        if (mListener != null) {
            mListener.onButtonClicked(name, code, continent, population);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        void onButtonClicked(String name, String code, String continent, String population);
    }
}
