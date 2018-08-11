package com.example.sirisha.booklog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sirisha on 15-05-2018.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {

    Context context;
    ArrayList<Pojo> dataList=new ArrayList<>();
    public MyAdapter(Context c, ArrayList<Pojo> data){

        super();
        this.context=c;
        this.dataList=data;
        Log.i("data size", String.valueOf(data.size()));
    }
    @Override
    public MyAdapter.MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image,parent,false);
        return new MyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyAdapterViewHolder holder, int position) {

        Pojo POJO =dataList.get(position);
        Picasso.with(context).load(POJO.getSmallThumb()).into(holder.iv);
    }
    @Override
    public int getItemCount()
    {
        return dataList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        ImageView iv;

        public MyAdapterViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int details=getAdapterPosition();
            String[] str=new String[20];
            str[1]=dataList.get(details).getEtag();
            str[2]=dataList.get(details).getTitle();
            str[3]=dataList.get(details).getAuthor();
            str[4]=dataList.get(details).getKind();
            str[5]=dataList.get(details).getCountry();
            str[6]=dataList.get(details).getInfoLink();
            str[7]=dataList.get(details).getLang();
            str[8]=dataList.get(details).getPageCount();
            str[9]=dataList.get(details).getPreview();
            str[10]=dataList.get(details).getId();
            str[11]=dataList.get(details).getSelfLink();
            str[12]=dataList.get(details).getPublishedDate();
            str[15]=dataList.get(details).getSmallThumb();
            str[16]=dataList.get(details).getThumb();
            Intent intent=new Intent(context,SecondActivity.class);
            intent.putExtra("details_must_required",str);
            v.getContext().startActivity(intent);
        }
    }

}

