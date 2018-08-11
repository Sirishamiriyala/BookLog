package com.example.sirisha.booklog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;





class MyAsyncTask extends AsyncTask<String,Void,String> {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Context context;
    public static GridLayoutManager myLayoutManager;
    ArrayList<Pojo> data= new ArrayList<>();

    public MyAsyncTask(MainActivity mainActivity, RecyclerView bookapp) {

        this.context=mainActivity;
        this.recyclerView=bookapp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("processing....");
        progressDialog.setMessage("wait for while....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        String url=strings[0];
        StringBuilder output=new StringBuilder();
        try {
            URL buildUrl =new URL(url);
            HttpURLConnection connection=(HttpURLConnection)buildUrl.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line!=null)
            {
                line=bufferedReader.readLine();
                output.append(line);
            }
            return output.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String kind,etag,selfLink,id;
        try{
            JSONObject jsonObject =new JSONObject(s);
            JSONArray jsonArray1 =jsonObject.getJSONArray("items");
            for (int i=0;i<jsonArray1.length();i++){
                JSONObject jb=jsonArray1.getJSONObject(i);
                kind=jb.optString("kind");
                etag=jb.optString("etag");
                selfLink=jb.optString("selfLink");
                id=jb.optString("id");
                JSONObject VolumeInfo=jb.getJSONObject("volumeInfo");
                String title=VolumeInfo.optString("title");
                String author=VolumeInfo.optString("authors");
                String publishedDate=VolumeInfo.optString("publishedDate");
                String pageCount=VolumeInfo.optString("pageCount");
                JSONObject imageLinks=VolumeInfo.getJSONObject("imageLinks");
                String smallThumbnail=imageLinks.optString("smallThumbnail");
                String thumbnail=imageLinks.optString("thumbnail");
                String infoLink=VolumeInfo.optString("infoLink");
                String previewLink=VolumeInfo.optString("previewLink");
                String lang=VolumeInfo.optString("language");
                JSONObject saleInfo=jb.getJSONObject("saleInfo");
                String country=saleInfo.optString("country");
               Pojo p=new Pojo(kind,selfLink,etag,id,title,author
                        ,publishedDate,pageCount
                        ,smallThumbnail,thumbnail,infoLink,previewLink,lang,country);
                data.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myLayoutManager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(myLayoutManager);
        progressDialog.dismiss();
        recyclerView.setAdapter(new MyAdapter(context,data));
        recyclerView.scrollToPosition(MainActivity.scroll_value);
    }

}