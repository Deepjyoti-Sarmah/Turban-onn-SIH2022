package com.anku.turban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class TurbanDetail extends AppCompatActivity {

    private String turbanId;
    private ProgressBar progressBar;
    private TextView description, name, location;
    private ImageView imageView;
    private Button makeButton;
    private Button buyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turban_detail);

        progressBar = findViewById(R.id.turbanDetailActivityProgressBar);

        makeButton = findViewById(R.id.btnMakeTurban);

        buyNow = findViewById(R.id.btnBuyNow);

        new NetworkRequest().execute();

        description = findViewById(R.id.txtTurbanDesc);
        location = findViewById(R.id.txtTurLocat);
        name = findViewById(R.id.txtTurbanName);
        imageView = findViewById(R.id.imgTurban);

        Intent intent = getIntent();
        turbanId = intent.getStringExtra("id");

        btnMakeTurbanOnClick();
        buyNowButton();
    }


    private void btnMakeTurbanOnClick(){
        makeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=KjvCjXSEJeo"));
                try {
//                    MainActivity.this.startActivity(webIntent);
                    TurbanDetail.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
    }

    private void buyNowButton() {

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TurbanDetail.this, BuyNow.class));


            }
        });
    }


    public class NetworkRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";

//    Server URL: https://turb-onn-server-sih2022.onrender.com/

        @Override
        protected String doInBackground(String... params){
//        String stringUrl = params[0];
//            String stringUrl = "https://turb-onn-server-sih2022.onrender.com/turban/";
            String stringUrl = "https://turb-onn-server-sih2022.onrender.com/turban/" + turbanId;
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

            try {
//                GsonBuilder builder = new GsonBuilder();
//                builder.setPrettyPrinting();
//                Gson gson = builder.create();
//                turban_objects data = gson.fromJson(result, turban_objects.class);
//                turban_objects[] user = gson.fromJson(result, turban_objects[].class);

                Gson gson = new Gson();
                turban_objects data = gson.fromJson(result, turban_objects.class);
                Log.i("Test user: ", data.getName());

                name.setText(data.getName());
                description.setText(data.getDescription());
                location.setText(data.getLocation());

                Glide.with(getApplicationContext()).load(data.getImageUrl()).into(imageView);

                progressBar.setVisibility(View.GONE);

            }catch(JsonIOException err){
                System.out.println("Exception : "+err.toString());
            }


        }
    }

}