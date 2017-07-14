package com.example.sid.aravali_v_21;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class Profile extends Fragment {

    ImageView ivDone,ivEdit;
    CardView editCard;
    FrameLayout profileFrame;
    String localName, localAge, localWeight, localHeight, localRoomNumber;
    TextView tAge, tName, tWeight, tHeight, tRoom;

    TextInputEditText eFName, eLName, eAddress, ePincode , eEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        getActivity().setTitle("Profile");

        editCard = (CardView)view.findViewById(R.id.card_view2);
        profileFrame = (FrameLayout) view.findViewById(R.id.frameLayout);
        tName = (TextView) view.findViewById(R.id.tProfileName);
        tWeight = (TextView) view.findViewById(R.id.tProfileWeight);
        tHeight = (TextView) view.findViewById(R.id.tProfileHeight);
        tRoom = (TextView) view.findViewById(R.id.tProfileRoom);
        tAge = (TextView) view.findViewById(R.id.tProfileAge);

        ivEdit = (ImageView) view.findViewById(R.id.ivEdit);

        eEmail = (TextInputEditText)view.findViewById(R.id.eEditEmail);
        eFName = (TextInputEditText) view.findViewById(R.id.eEditProfileFName);
        eLName = (TextInputEditText) view.findViewById(R.id.eEditProfileLName);
        ePincode = (TextInputEditText) view.findViewById(R.id.eEditProfilePincode);

        eAddress = (TextInputEditText) view.findViewById(R.id.eEditProfileAddress);
        /*eAddress.setScroller(new Scroller(this));
        eAddress.setVerticalScrollBarEnabled(true);*/
        eAddress.setMovementMethod(new ScrollingMovementMethod());

        ivDone = (ImageView) view.findViewById(R.id.ivDone);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCard.setVisibility(View.VISIBLE);
            }
        });
        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            editCard.setVisibility(View.GONE);

            }
        });

        return view;

    }
    public final static boolean isValidEmail(String target) {
        if(target == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    void saveData()
    {
        localName = eFName.getText().toString().trim();
        localAge = eLName.getText().toString().trim();
        localWeight = eAddress.getText().toString().trim();
        localHeight = ePincode.getText().toString().trim();
        localRoomNumber = eEmail.getText().toString().trim();

        if(localName.equals("") || localAge.equals("") || localWeight.equals("") || localHeight.equals("")|| localRoomNumber.equals(""))
        {
            if(localName.equals(""))
                eFName.setError(getString(R.string.error_required));
            if(localAge.equals(""))
                eLName.setError(getString(R.string.error_required));
            if(localWeight.equals(""))
                eAddress.setError(getString(R.string.error_required));
            if(localHeight.equals(""))
                ePincode.setError(getString(R.string.error_required));

            if(localRoomNumber.equals(""))
                eEmail.setError(getString(R.string.error_required));


        }
        else if(localHeight.length() != 6)
            Toast.makeText(getActivity(), "asa", Toast.LENGTH_LONG).show();
        else if(!isValidEmail(localRoomNumber)){
            Toast.makeText(getActivity(), "Enter valid email", Toast.LENGTH_LONG).show();
        }
        else
        {
            JSONObject jsonObject = new JSONObject();

        }
    }

}
