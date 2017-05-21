package ru.zaochno.zaochno.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import ru.zaochno.zaochno.R;

public class FilterDialogFragment extends DialogFragment {

    public static FilterDialogFragment newInstance() {
        FilterDialogFragment frag = new FilterDialogFragment();

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set title for this dialog
        getDialog().setTitle("My Dialog Title");

        View v = inflater.inflate(R.layout.fragment_dialog_item, container, false);

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) v.findViewById(R.id.dialogFragmentRangeSeekbarId);

//// get min and max text view
//        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin1);
//        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax1);
//
//// set listener
//        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
//            @Override
//            public void valueChanged(Number minValue, Number maxValue) {
//                tvMin.setText(String.valueOf(minValue));
//                tvMax.setText(String.valueOf(maxValue));
//            }
//        });
//
//// set final value listener
//        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
//            @Override
//            public void finalValue(Number minValue, Number maxValue) {
//                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
//            }
//        });
//        View tv = v.findViewById(R.id.text);
//        ((TextView)tv).setText("This is an instance of MyDialogFragment");
        return v;
    }

}
