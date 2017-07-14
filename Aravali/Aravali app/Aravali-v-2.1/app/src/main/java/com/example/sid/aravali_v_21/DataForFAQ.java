package com.example.sid.aravali_v_21;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sid on 30/12/16.
 */
public class DataForFAQ {

    public static LinkedHashMap<String, List<String>> getData(Context con) {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();





        List<String> cricket = new ArrayList<String>();
    cricket.add("India");
    cricket.add("Pakistan");
    cricket.add("Australia");
    cricket.add("England");
    cricket.add("South Africa");

    List<String> football = new ArrayList<String>();

        football.add(con.getString(R.string.avoid_peak));


    List<String> basketball = new ArrayList<String>();
    basketball.add("United States");
    basketball.add("Spain");
    basketball.add("Argentina");
    basketball.add("France");
    basketball.add("Russia");

    expandableListDetail.put("How is the bill calculated?", cricket);
    expandableListDetail.put("What is peak power usage?", football);
    expandableListDetail.put("What is peak penalty?", basketball);
    expandableListDetail.put("How to avoid peak occurrence", football);
    expandableListDetail.put("Tips for saving electricity", basketball);

    return expandableListDetail;
}
}
