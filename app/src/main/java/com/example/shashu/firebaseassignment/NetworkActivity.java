package com.example.shashu.firebaseassignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        findViewById(R.id.network_call_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        HttpPostAsyncTask httpPostAsyncTask = new HttpPostAsyncTask();
        httpPostAsyncTask.execute("http://jsonplaceholder.typicode.com/posts");
    }


    public class HttpPostAsyncTask extends AsyncTask<String, Void, String> {


        public HttpPostAsyncTask() {
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                // This is getting the url from the string we passed in
                URL url = new URL(strings[0]);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                //urlConnection.setDoInput(true);
                //urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("GET");


                // OPTIONAL - Sets an authorization header
                //urlConnection.setRequestProperty("Authorization", "someAuthString");

                // Send the post body

//                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
//                writer.write(postData.toString());
//                writer.flush();


                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);

                    // From here you can convert the string to JSON with whatever JSON parser you like to use

                    return response;

                } else {
                    // Status code is not 200
                    // Do something to handle the error
                    return null;
                }

            } catch (Exception e) {
                Log.d("Http Call", e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null)
            {
                try
                {
                    //Log.d("String", s);
                    JSONArray jsonArray = new JSONArray(s);
                    //Log.d("String2", "Number of entries " + jsonArray.length());
                    ArrayList<ApiData> MyData_list ;
                    MyData_list= new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        ApiData data = new ApiData(jsonObject.getString("userId"), jsonObject.getString("id"), jsonObject.getString("title"), jsonObject.getString("body"));
                        MyData_list.add(data);


                    }
                    Log.d("Size01",String.valueOf(MyData_list.size()));

                    Intent intent = new Intent(NetworkActivity.this,Api_RecyclerView.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", (Serializable) MyData_list);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                catch (final JSONException e) {
                    System.out.println("Json parsing error: " + e.getMessage());
                }

            }else {

            }
        }

        private String convertInputStreamToString(InputStream inputStream) {

            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }//end

    } // end Http Post Async task
}