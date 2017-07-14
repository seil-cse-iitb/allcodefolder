package com.example.sid.accontrol;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity{

    ImageButton tempUpIB, tempDownIB, powerIB;
    TextView setTempTV;
    TCPClient mTcpClient;
    boolean power = false;
    String url = "http://192.168.1.171/LED=OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        tempDownIB = (ImageButton)findViewById(R.id.temp_down_ib);
//        tempUpIB = (ImageButton)findViewById(R.id.temp_up_ib);
//        powerIB = (ImageButton)findViewById(R.id.power_ib);
//        setTempTV = (TextView)findViewById(R.id.set_temp_tv);
//
//        powerIB.setOnClickListener(this);
//        tempDownIB.setOnClickListener(this);

//        new ConnectTask().execute("");


    }

//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//
//        switch (id){
//
//            case R.id.temp_down_ib:{
//
//                if (mTcpClient != null) {
//                    mTcpClient.stopClient();
//                }
//                break;
//            }
//            case R.id.temp_up_ib:{
//
//
//                break;
//            }
//            case R.id.power_ib:{
//
//                if(power){
//
//                    url = "http://192.168.1.171/LED=OFF";
//                    power = false;
//                    new storeFeedbackAsyncTask().execute("");
//                }else{
//
//                    power = true;
//                    url = "http://192.168.1.171/LED=ON";
//                    new storeFeedbackAsyncTask().execute("");
//                }
//
//                break;
//            }
//
//
//        }
//
//    }
//
//    public class ConnectTask extends AsyncTask<String, String, TCPClient> {
//
//        @Override
//        protected TCPClient doInBackground(String... message) {
//
//            //we create a TCPClient object
//            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
//                @Override
//                //here the messageReceived method is implemented
//                public void messageReceived(String message) {
//                    //this method calls the onProgressUpdate
//                    publishProgress(message);
//                }
//            });
//            mTcpClient.run();
//
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            //response received from server
//            Log.d("test", "response " + values[0]);
//            //process server response here....
//
//        }
//
//    }
//
//    // function to get json object from url
//    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
//
//
////        creating a url connection to send data
//        HttpURLConnection urlConnection = null;
//
//        URL url = new URL(urlString);
//
//        urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setRequestMethod("GET");
//        urlConnection.setReadTimeout(10000 /* milliseconds */);
//        urlConnection.setConnectTimeout(15000 /* milliseconds */);
//
//        urlConnection.setDoOutput(true);
//
//        urlConnection.connect();
//
//        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
//
//        char[] buffer = new char[1024];
//
//        String jsonString = new String();
//
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            sb.append(line+"\n");
//        }
//        br.close();
//
//        jsonString = sb.toString();
//
//        System.out.println("JSON: " + jsonString);
//
//        return new JSONObject(jsonString);
//    }
//
//
//    public class storeFeedbackAsyncTask extends AsyncTask<String,String,String> {
//
//
//        ProgressDialog pd;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////            show the progress dialog
//            pd = new ProgressDialog(MainActivity.this);
//            pd.setMessage("Sending Data to nodemcu");
//            pd.show();
//            //Toast.makeText(getActivity(),"exec" ,Toast.LENGTH_SHORT).show();
//        }
//
//
//
//
//        @Override
//        protected String doInBackground(String... params) {
//
//
//
//            String ip = url;
//            try{
//                URL url = new URL(ip);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                try {
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
////                    readStream(in);
//                } finally {
//                    urlConnection.disconnect();
//                }
//
//                return  "";
//
//
//                // Parse your json here
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//// catch (JSONException e) {
////                e.printStackTrace();
////            }
//
//
//            return "Download failed";
//
//        }
//
//        @Override
//        protected void onPostExecute(String response) {
//            System.out.println("sensors response"+response);
//
////                remove the progress dialog if it is showing
//                if(pd.isShowing())
//                    pd.dismiss();
//
//
//
//
//
//
//            if(response.equals("Download failed")){
//
//            }else{
//
//
//            }
//
//
//        }
//    }
//




}

