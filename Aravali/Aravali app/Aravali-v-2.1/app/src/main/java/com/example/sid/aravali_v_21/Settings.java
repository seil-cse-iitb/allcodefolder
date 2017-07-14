package com.example.sid.aravali_v_21;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class Settings extends Fragment {



    CheckBox amountAlertCB;
    EditText setAmountET;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_settings, container, false);
        getActivity().setTitle("Settings");
        amountAlertCB = (CheckBox)view.findViewById(R.id.amount_alert_cb);
        setAmountET = (EditText)view.findViewById(R.id.set_amount_et);

        amountAlertCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(amountAlertCB.isChecked()){
                    setAmountET.setVisibility(View.VISIBLE);
                }else{
                    setAmountET.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;
    }





}
