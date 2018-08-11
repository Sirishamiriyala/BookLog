package com.example.sirisha.booklog;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.sirisha.booklog.MainActivity.Loader_id;

public class SecondActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    private ArrayList<String> bookdetails=new ArrayList<>();
     TextView name;
     ImageView thumb;
     TextView publish;
     TextView author;
     TextView selfLink;
    ToggleButton favorites;
    SqlLiteHelper sqlLiteHelper;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        name=(TextView)findViewById(R.id.title);
        thumb=(ImageView)findViewById(R.id.thumb);
        publish=(TextView)findViewById(R.id.published_date);
        author=(TextView)findViewById(R.id.authorName);
        selfLink=(TextView)findViewById(R.id.selfLink);
        favorites=(ToggleButton)findViewById(R.id.favorites);
        final String[] getDetails = getIntent().getStringArrayExtra("details_must_required");
        Picasso.with(this).load(getDetails[15]).into(thumb);
        bookdetails = getIntent().getStringArrayListExtra("results");
        sqlLiteHelper=new SqlLiteHelper(this);
        Log.i("image", "title");
       publish.setText(getDetails[12]);
      name.setText(getDetails[2]);
        author.setText(getDetails[3]);
        selfLink.setText(getDetails[9]);

        Cursor cursor=getContentResolver().query(Uri.parse(SqlLiteHelper.CONTENT_URI+"/*"),null,SqlLiteHelper.Bookid+" LIKE ?",new String[]{getDetails[10]},null);
       // Toast.makeText(this,Integer.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();
        if (cursor.getCount()>0){
            favorites.setChecked(true);
        }else
            favorites.setChecked(false);
        favorites.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!favorites.isChecked()) {
                   getContentResolver().delete(Uri.parse(SqlLiteHelper.CONTENT_URI+"/*"),SqlLiteHelper.Bookid+ " LIKE ?",new String[]{getDetails[10]});
                    WidgetService.setCall(SecondActivity.this);
                } else {
                    ContentValues bookdetailinfovalues = new ContentValues();
                    bookdetailinfovalues.put(SqlLiteHelper.kind, getDetails[4]);
                    bookdetailinfovalues.put(SqlLiteHelper.selfLink, getDetails[1]);
                    bookdetailinfovalues.put(SqlLiteHelper.etag, getDetails[11]);
                    bookdetailinfovalues.put(SqlLiteHelper.Bookid, getDetails[10]);
                    bookdetailinfovalues.put(SqlLiteHelper.title, getDetails[2]);
                    bookdetailinfovalues.put(SqlLiteHelper.author, getDetails[3]);
                    bookdetailinfovalues.put(SqlLiteHelper.publishedDate, getDetails[12]);
                    bookdetailinfovalues.put(SqlLiteHelper.pageCount, getDetails[8]);
                    bookdetailinfovalues.put(SqlLiteHelper.smallThumb, getDetails[15]);
                    bookdetailinfovalues.put(SqlLiteHelper.thumb, getDetails[16]);
                    bookdetailinfovalues.put(SqlLiteHelper.infoLink, getDetails[6]);
                    bookdetailinfovalues.put(SqlLiteHelper.preview, getDetails[9]);
                    bookdetailinfovalues.put(SqlLiteHelper.lang, getDetails[7]);
                    bookdetailinfovalues.put(SqlLiteHelper.country, getDetails[5]);
                    getContentResolver().insert(Uri.parse(SqlLiteHelper.CONTENT_URI + ""), bookdetailinfovalues);
                    WidgetService.setCall(SecondActivity.this);
                }
            }
        });
        mAdView=findViewById(R.id.ads);
        MobileAds.initialize(this,"ca-app-pub-6336523906622902~6817819328");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("C3528C0925D3AB8424433F9070BD2A5F")
                .build();

        mAdView.loadAd(adRequest);

    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader(this) {
            @Override
            public Object loadInBackground() {
                String titleinfo = "";
                Cursor bookdetails = getContentResolver().query(SqlLiteHelper.CONTENT_URI, null, null, null, null);
                if (bookdetails.getCount() > 0) {
                    if (bookdetails.moveToFirst()) {
                        do {
                            titleinfo += "\n" + bookdetails.getString(5);
                        } while (bookdetails.moveToNext());
                        widget w = new widget();
                        w.text = titleinfo;
                    }


                }
                return null;

            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
        };
    }
    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
