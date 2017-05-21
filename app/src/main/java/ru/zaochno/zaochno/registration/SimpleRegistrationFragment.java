package ru.zaochno.zaochno.registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.zaochno.zaochno.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleRegistrationFragment extends Fragment {


    public SimpleRegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_registration, container, false);
    }

}
