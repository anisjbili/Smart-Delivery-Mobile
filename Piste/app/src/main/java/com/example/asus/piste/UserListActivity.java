package com.example.asus.piste;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UserListActivity extends AppCompatActivity {

    private static String address = "http://192.168.1.7/Android/includes/missions.php";

    ListView lv;
    ArrayAdapter<String> adapter;
    InputStream is = null;
    String line = null;
    String result=null;
    String[] data ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        lv = (ListView) findViewById(R.id.list);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        getData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
    }

    private void getData()
    {
        try
        {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }

        //READ IS CONTENT INTO A STRING
        try
        {
            BufferedReader br= new BufferedReader(new InputStreamReader(is));
            StringBuilder sb =new StringBuilder();

            while((line=br.readLine()) != null )
            {
                sb.append(line+"\n");
            }
            is.close();
            result = sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }

        //PARSE JSON DATA
        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;

            data = new String[ja.length()];

            for(int i=0;i<ja.length();i++)
            {
                jo = ja.getJSONObject(i);
                data[i]=jo.getString("owner");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
