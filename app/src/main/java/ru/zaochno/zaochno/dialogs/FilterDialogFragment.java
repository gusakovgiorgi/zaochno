package ru.zaochno.zaochno.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.database.DatabaseCallBack;
import ru.zaochno.zaochno.database.DatabaseManager;
import ru.zaochno.zaochno.model.Category;

public class FilterDialogFragment extends DialogFragment {

    @BindView(R.id.dialogFragmentRegionSpinnerId)
    Spinner mSpinner;

    public static FilterDialogFragment newInstance() {
        FilterDialogFragment frag = new FilterDialogFragment();

        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dialog_item, container, false);

        ButterKnife.bind(this, v);

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) v.findViewById(R.id.dialogFragmentRangeSeekbarId);

// get min and max text view
        final TextView tvMin = (TextView) v.findViewById(R.id.dialogFragmentRangeSeekbarLeftTextViewId);
        final TextView tvMax = (TextView) v.findViewById(R.id.dialogFragmentRangeSeekbarRightTextViewId);

// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        View closeImg = v.findViewById(R.id.dialogFragmentCloseImageViewId);
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Log.v("test", "cur thread is " + Thread.currentThread());


        DatabaseManager.getInstance().getCategories(new DatabaseCallBack<Category[]>() {
            @Override
            public void returnData(final Category[] data) {
                Log.v("test", "callback thread is " + Thread.currentThread());
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
                //todo change adapter
                String[] names = new String[data.length];
                for (int i = 0; i < data.length; i++) {
                    names[i] = data[i].getCategoryName();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, names);
                mSpinner.post(new Runnable() {
                    @Override
                    public void run() {
                        mSpinner.setAdapter(adapter);
                    }
                });
            }
        });

        return v;
    }

}
