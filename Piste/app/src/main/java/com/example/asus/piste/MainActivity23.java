package com.example.asus.piste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity23 extends AppCompatActivity {

    private static String url_all_missions = "http://192.168.1.7/Android/includes/missions.php";
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> missionsList;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private static final String TAG_ID = "id";


    private static final String TAG_USERS = "missionlist";

    private static final String TAG_USER = "mission";

    JSONArray users = null;
    private ListAdapter listAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        missionsList = new ArrayList<HashMap<String, String>>();

        new getMissionList().execute();

        ListView lv = getListView();
    }

    public void setListAdapter(ListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    public ListView getListView() {
        return listView;
    }

    class getMissionList extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity23.this.setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            // Building Parameters
            List<NameValuePair> parametres = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jsonParser.makeHttpRequest(url_all_missions,
                    "GET", parametres);

            // Check your log cat for JSON reponse
            Log.d("All Users: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    users = json.getJSONArray(TAG_USERS);

                    // looping through All Users
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String user = c.getString(TAG_USER);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_USER, user);

                        // adding HashList to ArrayList
                        missionsList.add(map);

                        return json.getString(TAG_MESSAGE);
                    }
                } else {

                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            // dismiss the dialog after getting all products
            if (result != null) {

                MainActivity23.this
                        .setProgressBarIndeterminateVisibility(false);
                // updating UI from Background Thread
                runOnUiThread(new Runnable() {
                    public void run() {
                        /**
                         * Updating parsed JSON data into ListView
                         * */
                        ListAdapter adapter = new SimpleAdapter(
                                MainActivity23.this, missionsList,
                                R.layout.list_item, new String[]{TAG_ID,
                                TAG_USER}, new int[]{R.id.IdText,
                                R.id.AdressText});
                        // updating listview
                        setListAdapter(adapter);
                    }
                });

                Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG)
                        .show();

            }
        }

    }


}
