package com.example.asus.piste;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.apache.http.NameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Missions_Activity extends ListActivity {

    String json_st;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missions_);
        listView = (ListView)findViewById(R.id.listview);

        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        json_st = getIntent().getExtras().getString("json_data");

        try{
            jsonObject = new JSONObject(json_st);
            jsonArray = jsonObject.getJSONArray("missions");
            int count = 0 ;
            String name,email,mobile;
            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                email = JO.getString("email");
                mobile = JO.getString("mobile");

                Contacts contacts = new Contacts(name,email,mobile);

                contactAdapter.add(contacts);

                count++;
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
