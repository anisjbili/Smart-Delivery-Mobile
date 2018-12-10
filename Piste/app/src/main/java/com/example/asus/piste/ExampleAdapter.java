package com.example.asus.piste;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> mExampleList) {
        this.mContext = context;
        this.mExampleList = mExampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

       final String mClient = currentItem.getmClient();
       final String mX = currentItem.getmX();
       final String mY = currentItem.getmY();
        final String mCode = currentItem.getCode();
        final String mAddress = currentItem.getAddress();

        holder.mClientText.setText(mClient);
       // holder.mXText.setText(mX);
        holder.mYText.setText(mAddress);

        holder.LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Details_Activity.class);
                intent.putExtra("iclientname",mClient);
                intent.putExtra("imX",mX);
                intent.putExtra("imY",mY);
                intent.putExtra("mCode", mCode);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mClientText;
        public TextView mXText;
        public TextView mYText;
        public android.widget.LinearLayout LinearLayout;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mClientText = itemView.findViewById(R.id.Clientname);
            mXText = itemView.findViewById(R.id.RefX);
            mYText = itemView.findViewById(R.id.RefY);
            LinearLayout = itemView.findViewById((R.id.LinearLayout));


        }
    }
}
