package com.anku.turban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.*;

public class MainActivity extends AppCompatActivity {

    private MyListAdapter turbanListAdapter;
    private NestedScrollView nestedScrollView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private ArrayList<String> turbanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turbanList = new ArrayList<>();

        new NetworkRequest().execute();

        turbanListAdapter = new MyListAdapter(this.getApplicationContext(), turbanList);



        recyclerView = findViewById(R.id.recyclerHome);
//        nestedScrollView = findViewById(R.id.nestedScrollView);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(turbanListAdapter);

//        setNestedScrollView();
    }

//    Get Post From Server
/**
 postAdapter.notifyDataSetChanged();
 */

    private void setNestedScrollView()
    {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);

                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView
                        .getScrollY()));
            }
        });
    }


    public class NetworkRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";

//    Server URL: https://turb-onn-server-sih2022.onrender.com/

        @Override
        protected String doInBackground(String... params){
//        String stringUrl = params[0];
            String stringUrl = "https://turb-onn-server-sih2022.onrender.com/turban";
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

//    private static JSONObject jObject = null;

        protected void onPostExecute(String result) {
            Log.i("Results From Server: ", result);

            //Converting jsonData string into JSON object

            try {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i=1; i<result.length()-1; i++)
                    stringBuilder.append(result.charAt(i));

                String[] stringList = stringBuilder.toString().split((","));

                for(int i=0; i<stringList.length; i++)
                {
                    String jsonStr = stringList[i];
                    turbanList.add(jsonStr);
                }

                turbanListAdapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //Printing JSON object
            System.out.println("JSON Object");

//        super.onPostExecute(result);
        }
    }


//    private void getDataViaRest () {
//        List<Object> data = RestAssured.get
//    }

}

