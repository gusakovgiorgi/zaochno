package ru.zaochno.zaochno.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.Training;

/**
 * Created by notbl on 5/21/2017.
 */

public class BuyDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private Training mTraining;

    public static BuyDialogFragment newInstance(Training training) {
        BuyDialogFragment frag = new BuyDialogFragment();
        Bundle args=new Bundle();
        args.putParcelable(ARG_PARAM1,training);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog);
        if (getArguments() != null) {
            mTraining = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Set title for this dialog
//        getDialog().setTitle(mTraining.getTitle());

        View v = inflater.inflate(R.layout.fragment_buy_item, container, false);

        return v;
    }
}
