package com.example.yumyum;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList rec_id, rec_name, rec_cat, rec_ing, rec_desc;


     CustomAdapter(Activity activity, Context context, ArrayList rec_id, ArrayList rec_name, ArrayList rec_cat, ArrayList rec_ing, ArrayList rec_desc){
        this.activity = activity;
         this.context = context;
        this.rec_cat = rec_cat;
        this.rec_desc = rec_desc;
        this.rec_id = rec_id;
        this.rec_name = rec_name;
        this.rec_ing = rec_ing;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


         holder.recp_name_out.setText(String.valueOf(rec_name.get(position)));
         holder.recp_cat_out.setText(String.valueOf(rec_cat.get(position)));
         holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RecipeDet.class);
                i.putExtra("id", String.valueOf(rec_id.get(position)));
                i.putExtra("name", String.valueOf(rec_name.get(position)));
                i.putExtra("cat", String.valueOf(rec_cat.get(position)));
                i.putExtra("ing", String.valueOf(rec_ing.get(position)));
                i.putExtra("desc", String.valueOf(rec_desc.get(position)));

                activity.startActivity(i);
            }
         });
     }

    @Override
    public int getItemCount() {
        return rec_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

         LinearLayout mainLayout;
        TextView recp_name_out, recp_cat_out;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recp_name_out = itemView.findViewById(R.id.name_out);
            recp_cat_out = itemView.findViewById(R.id.cat_out);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
