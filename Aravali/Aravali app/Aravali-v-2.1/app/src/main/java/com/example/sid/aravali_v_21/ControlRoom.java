package com.example.sid.aravali_v_21;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class ControlRoom extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control_room, container, false);
        WebView myWebView = (WebView) view.findViewById(R.id.control_room_wv);
        myWebView.loadUrl("http://10.129.23.216:9999/control");


        return view;
    }

}
