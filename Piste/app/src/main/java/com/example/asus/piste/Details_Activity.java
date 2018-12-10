package com.example.asus.piste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details_Activity extends AppCompatActivity {

    private Button FindRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);

        getIncomingIntent();

        FindRoute = findViewById(R.id.find_route_butt);

        FindRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Fintent = new Intent(Details_Activity.this, MainActivity.class);
                startActivity(Fintent);
            }
        });

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("iclientname") && getIntent().hasExtra("imX") && getIntent().hasExtra("imY") && getIntent().hasExtra("mCode")){
            String name = getIntent().getStringExtra("iclientname");
            String xxx = getIntent().getStringExtra("imX");
            String yyy = getIntent().getStringExtra("imY");

            String Codee = getIntent().getStringExtra("mCode");
            setNames(name,xxx,yyy, Codee);
        }
    }

    private void setNames(String name, String xxx, String yyy, String Codee){
        TextView fname = findViewById(R.id.text_view_ClientName);
        fname.setText("Owner Name : "+name );

        TextView fx = findViewById(R.id.text_view_X);
        fx.setText("Spacial Reference X = " +xxx);

        TextView fy = findViewById((R.id.text_view_Y));
        fy.setText("Spacial Reference Y = " + yyy );

        ImageView fCode = findViewById(R.id.text_view_Code);

        Picasso.with(this).load(Codee).fit().centerInside().into(fCode);


    }
}
