package com.anku.turban;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<String> listdata;
    private Context mContext;

    // RecyclerView recyclerView;
    public MyListAdapter(Context context, ArrayList<String> listdata) {
        this.listdata = listdata;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycler_home_single_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String data = listdata.get(position);

        try {
            JSONObject jsonObject = new JSONObject(data);
            String name = jsonObject.getJSONObject("name").toString();
            String desc = jsonObject.getJSONObject("description").toString();

            Log.i("Parse: ", name + " " + desc);

            holder.name.setText(name);
            holder.description.setText(desc);

        } catch (JSONException e) {
            Log.i("Parse Error: ", e.toString());
            e.printStackTrace();
        }



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,
                        TurbanDetail.class
                );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", "90983901823981903");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView;
        public TextView name, description;
        public CardView cardView;
//        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
//            this.imageView = (ImageView) itemView.findViewById(R.id.imgBookImage);
//            this.name = (TextView) itemView.findViewById(R.id.testName);
            this.cardView = (CardView) itemView.findViewById(R.id.turbanCard);
            this.name = (TextView) itemView.findViewById(R.id.txtTurbanName);
            this.description = (TextView) itemView.findViewById(R.id.txtTurbanDesc);
//            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}