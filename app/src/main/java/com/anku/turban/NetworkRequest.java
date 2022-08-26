package com.anku.turban;

import static java.lang.System.err;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

//        try {
//           StringBuilder stringBuilder = new StringBuilder();
//           for(int i=1; i<result.length()-1; i++)
//               stringBuilder.append(result.charAt(i));
//
//           String[] stringList = stringBuilder.toString().split((","));
//
//           for(int i=0; i<stringList.length; i++)
//           {
//               String jsonStr = stringList[i];
//
//               JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
//
////               Gson gson = new Gson();
////               turban_objects turban = gson.fromJson(jsonStr, turban_objects.class);
//
//               Log.i("Converted turban: ", jsonObject.toString());
//
//
//           }
//
//           Log.i(": ", Arrays.toString(stringList));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            JSONObject jsonObject = new JSONObject(result);
//            JSONArray jsonArray = new JSONArray(jsonObject);
//
//            Log.i("JSON Data: ", JSON.stringify());
//
//            for(int i=0; i<jsonArray.length(); i++)
//            {
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                Log.i("JSON Data: ", jsonObject1.toString());
//                Log.i("JSON Data: ", jsonObject1.getString("name").toString());
//            }
//
//        }catch (Exception e){
//
//        }
//        //Printing JSON object
//        System.out.println("JSON Object");

//        super.onPostExecute(result);
    }
}
