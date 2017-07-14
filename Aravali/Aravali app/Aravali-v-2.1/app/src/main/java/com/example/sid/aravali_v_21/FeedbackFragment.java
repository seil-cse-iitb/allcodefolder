package com.example.sid.aravali_v_21;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {


    EditText feedbackEditText;
    TextView charRemainingTV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        getActivity().setTitle("Feedback");

        charRemainingTV = (TextView)view.findViewById(R.id.char_remaining_TV);
        Button submit = (Button)view.findViewById(R.id.submit_button);

        feedbackEditText = (EditText)view.findViewById(R.id.feedback_edittext);


        if(Util.isNetworkAvailable(getActivity())){

             }else{

            Toast.makeText(getActivity(),"Please check your internet connection", Toast.LENGTH_LONG).show();
            submit.setEnabled(false);

        }


        feedbackEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    try {
                        getActivity().getWindow().setSoftInputMode(
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        feedbackEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != -1)
                    charRemainingTV.setText((500 - s.length()) +" "+ "Characters remaining");
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Message = feedbackEditText.getText().toString();


                //check if the feedbcak edit text has any text
                if(Message.trim().equals("")
                        ||Message.trim().isEmpty()){

                    Toast.makeText(getActivity(),"Please enter your feedback before submitting", Toast.LENGTH_LONG).show();

                }else{




                }


            }
        });




        @DrawableRes int editTextShape = R.drawable.edittextshape;
        //setBackground was added in API level 16 so use setBackgroundDrawable for versions before that
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {


            feedbackEditText.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),editTextShape,null));

        } else {
            feedbackEditText.setBackground(ResourcesCompat.getDrawable(getResources(),editTextShape,null));

        }





        return view;



    }


}
