package com.example.sid.aravali_v_21;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainFragment extends Fragment {

    ImageView preDayPowerIV,avgPowerMonthIV;
    String ip;
    TextView UserNameTV, billTillNowTV, userRankTV, expMonthlyBillTV,updatedOnTV,unitsConsumedTV,noInternetTV;
    EditText IPET;
    ScrollView mainContentSV;
    Button seeUsersBtn , saveIPBtn;
    String user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle("Home");
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (16*scale + 0.5f);
        int dpAsPixelsNoInternet = (int) (26*scale + 0.5f);
        mainContentSV = (ScrollView)view.findViewById(R.id.content_sv);
        noInternetTV = (TextView)view.findViewById(R.id.no_internet_tv);
        if(!Util.isNetworkAvailable(getActivity())){
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_SHORT).show();
            noInternetTV.setVisibility(View.VISIBLE);
            mainContentSV.setPadding(dpAsPixels,dpAsPixelsNoInternet,dpAsPixels,dpAsPixels);
        }else{
            mainContentSV.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
            noInternetTV.setVisibility(View.INVISIBLE);
        }

        user = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("username","");

        preDayPowerIV = (ImageView)view.findViewById(R.id.power_prev_day_iv);
        avgPowerMonthIV = (ImageView)view.findViewById(R.id.avg_power_month_iv);
        ip = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("ip","0.0.0.0");
        Glide.with(this).load("http://"+ip+":80/"+user+"_power_previous_day.png")
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(preDayPowerIV);
        Glide.with(this).load("http://"+ip+":80/"+user+"_power_this_month.png")
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(avgPowerMonthIV);
        new getUserInfo().execute("");
        IPET = (EditText)view.findViewById(R.id.ip_et);
        unitsConsumedTV = (TextView)view.findViewById(R.id.units_consumed_tv);
        updatedOnTV = (TextView)view.findViewById(R.id.updated_on_tv);

        saveIPBtn = (Button)view.findViewById(R.id.save_ip_btn);
        saveIPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(IPET.getText().toString().equals("")){
                   Toast.makeText(getActivity().getApplicationContext(),"Enter IP",Toast.LENGTH_LONG).show();
               }else{
                   PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putString("ip", IPET.getText().toString()).commit();

               }


            }
        });

        billTillNowTV = (TextView)view.findViewById(R.id.bill_till_now_tv);
        expMonthlyBillTV = (TextView)view.findViewById(R.id.Exp_monthly_bill_name_tv);
        userRankTV = (TextView)view.findViewById(R.id.user_rank_tv);

        new getUserInfo().execute();
        return  view;
    }

        public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

            HttpURLConnection urlConnection = null;

            URL url = new URL(urlString);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            urlConnection.setDoOutput(true);

            urlConnection.connect();

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();

            jsonString = sb.toString();

            System.out.println("JSON: " + jsonString);

            return new JSONObject(jsonString);
        }
    public class getUserInfo extends AsyncTask<String,String,String> {


        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {

            String user = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("username","");
            String ipp = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("ip","0.0.0.0");
            String ip = "http://"+ipp+":8000/myapp/user/"+user+"/";
            try{
                JSONObject jsonObject = getJSONObjectFromURL(ip);
                return  jsonObject.toString();


                // Parse your json here

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return "Download failed";

        }

        @Override
        protected void onPostExecute(String response) {
            try {
                String user = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("username","");

                JSONObject root = new JSONObject(response);
                unitsConsumedTV.setText("Units Consumed: "+root.getString("units_this_mon"));

                billTillNowTV.setText("Bill till now: Rs."+root.get("bill_now"));

                updatedOnTV.setText("Updated on: "+root.getString("TS"));
                userRankTV.setText("Your Rank: " +root.getString("rank"));
                expMonthlyBillTV.setText("Exp bill this month: Rs.");

                Glide.with(getActivity()).load("http://"+ip+":80/"+user+"_power_previous_day.png")
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(preDayPowerIV);

                Glide.with(getActivity()).load("http://"+ip+":80/"+user+"_power_this_month.png")
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(avgPowerMonthIV);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("response from server", response.toString());
            if(response.equals("Download failed")){
                
            }else{


            }


        }
    }


}
