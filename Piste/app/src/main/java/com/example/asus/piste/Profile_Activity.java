package com.example.asus.piste;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Profile_Activity extends AppCompatActivity {

    TextView Username2 , UserEmail , Greeting;
    Button but ;

    String JSON_STRING;
    String json_st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        Username2 = (TextView) findViewById(R.id.username2);
        UserEmail = (TextView)findViewById(R.id.userEmail);
        Greeting = (TextView)findViewById(R.id.mission);
        but = (Button)findViewById(R.id.button3) ;

        Username2.setText(SharedPrefManager.getInstance(this).getUsername());
        UserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());




    }

    public void getJSON(View view)
    {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>
    {

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url ="http://192.168.1.3/Android/includes/missions.php";

        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
           TextView textView = (TextView) findViewById(R.id.textView);
           textView.setText(result);
           json_st = result;
        }
    }

    public void parseJSON(View view)
    {
        if(json_st == null )
        {
            Toast.makeText(getApplicationContext(),"First Get JSON",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this,Missions_Activity.class);
            intent.putExtra("json_data",json_st);
            startActivity(intent);
        }
    }

}
