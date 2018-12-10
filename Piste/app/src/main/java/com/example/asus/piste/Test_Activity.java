package com.example.asus.piste;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;

public class Test_Activity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Button Directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        Directions = (Button)findViewById(R.id.button_directions);
        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });

        Directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Routing = new Intent(Test_Activity.this, MainActivity.class);
                startActivity(Routing);
            }
        });

    }

    private void jsonParse(){
        String url = "http://192.168.1.9/Android/includes/missions.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Missions");
                            for(int i=0; i < jsonArray.length();i++){
                                JSONObject mission = jsonArray.getJSONObject(i);

                                String name = mission.getString("ClientName");
                                String address = mission.getString("SpacialReferenceX");
                                String mobile = mission.getString("SpacialReferenceY");

                                mTextViewResult.append("Owner : " + name + "\n" +"SpacialReferenceX : " + address +"\n" + "SpacialReferenceY" +" : "+ mobile + "\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
