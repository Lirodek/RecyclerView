package com.example.androidrecyclerviewex;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_content.setText(arrayList.get(position).getTv_content());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String curName = holder.tv_name.getText().toString();
                String curContent = holder.tv_content.getText().toString();
                if(holder.answer == true) {
                    holder.tv_name.setPaintFlags(holder.tv_name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.answer = false;
                } else{
                    holder.tv_name.setPaintFlags(0);
                }
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.getIv_profile().setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;
        protected boolean answer= true;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.pofile);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }

        public ImageView getIv_profile() {
            return iv_profile;
        }

        public void setIv_profile(ImageView iv_profile) {
            this.iv_profile = iv_profile;
        }

        public TextView getTv_name() {
            return tv_name;
        }

        public void setTv_name(TextView tv_name) {
            this.tv_name = tv_name;
        }

        public TextView getTv_content() {
            return tv_content;
        }

        public void setTv_content(TextView tv_content) {
            this.tv_content = tv_content;
        }
    }
}
