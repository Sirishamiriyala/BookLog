package com.example.sirisha.booklog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
//import android.support.v4.app.LoaderManager;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    static final String api_key = BuildConfig.api;
    public String contexte="c" , arrow = "myKey";
    public static int scroll_value = 1, scroll_val_fav = 1;
    public String SCROLL_KEY = "scrollKey";
    RecyclerView booksapp;
    public static int Loader_id=7;
    GridLayoutManager myFavLayoutManager;
    ArrayList<Pojo> booksInfo;
    String url = "https://www.googleapis.com/books/v1/volumes?q=python&maxResults=40";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();

        final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user==null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
            }
        };
        booksapp = (RecyclerView) findViewById(R.id.Bookslist);
        if (isonline()){
            if (savedInstanceState!=null){
                contexte=savedInstanceState.getString(arrow);
               switch (contexte){
                   case "python":contexte="python";
                       new MyAsyncTask(this, booksapp).execute(url);
                       break;
                   case "c":contexte="c";
                        url="https://www.googleapis.com/books/v1/volumes?q=c&maxResults=40";
                       new MyAsyncTask(this, booksapp).execute(url);
                       break;
                   case "java":contexte="java";
                        url="https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";
                       new MyAsyncTask(this, booksapp).execute(url);
                       break;
                   case "oops":contexte="oops";
                        url="https://www.googleapis.com/books/v1/volumes?q=OOPs&maxResults=40";
                       new MyAsyncTask(this, booksapp).execute(url);
                       break;
                   case "analogcommunication":contexte="analogcommunication";
                        url="https://www.googleapis.com/books/v1/volumes?q=AnalogCommunication&maxResults=40";
                        new MyAsyncTask(this,booksapp).execute(url);
                        break;
                   case "signalsandsystems":contexte="signalsandsystems";
                       url="https://www.googleapis.com/books/v1/volumes?q=SignalsAndSystems&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   case "fieldtheory":contexte="fieldtheory";
                       url="https://www.googleapis.com/books/v1/volumes?q=FieldTheory&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   case "logicdesign":contexte="logicdesign";
                       url="https://www.googleapis.com/books/v1/volumes?q=Logic_Design&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   case "cad":contexte="cad";
                       url="https://www.googleapis.com/books/v1/volumes?q=CAD&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   case "cam":contexte="cam";
                       url="https://www.googleapis.com/books/v1/volumes?q=CAM&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   case "robotics":contexte="robotics";
                       url="https://www.googleapis.com/books/v1/volumes?q=Robotics&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   case "machinedrawing":contexte="machinedrawing";
                       url="https://www.googleapis.com/books/v1/volumes?q=MachineDrawing&maxResults=40";
                       new MyAsyncTask(this,booksapp).execute(url);
                       break;
                   default:
                       Toast.makeText(this, "..", Toast.LENGTH_SHORT).show();

               }
            }else {
                contexte="python";
                new MyAsyncTask(this,booksapp).execute(url);
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (contexte == "favorites") {
            getSupportLoaderManager().restartLoader(Loader_id, null, MainActivity.this);
        }
    }

    protected boolean isonline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.adapter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.python:
                if (isonline()) {
                    contexte = "python";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=python&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, "NO Network Connection", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.c:
                if (isonline()) {
                    contexte = "c";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=c&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, "NO Network Connection", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.java:
                if (isonline()) {
                    contexte = "java";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=JAVA&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, "NO Network Connection", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.OOPs:
                if (isonline()) {
                    contexte = "oops";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=OOPs&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, "NO Network Connection", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.AnalogCommunication:
                if (isonline()) {
                    contexte = "analogcommunication";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=AnalogCommunication&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_nett, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.SignalsAndSystems:
                if (isonline()) {
                    contexte = "signalandsystem";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=SignalsAndSystems&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.FieldTheory:
                if (isonline()) {
                    contexte = "fieldtheory";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=FieldTheory&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.Logic_Design:
                if (isonline()) {
                    contexte = "logicdesign";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=Logic_Design&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.CAD:
                if (isonline()) {
                    contexte = "cad";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=CAD&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.CAM:
                if (isonline()) {
                    contexte = "cam";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=CAM&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.Robotics:
                if (isonline()) {
                    contexte = "robotics";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=Robotics&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.MachineDrawing:
                if (isonline()) {
                    contexte = "machinedrawing";
                    new MyAsyncTask(this, booksapp).execute("https://www.googleapis.com/books/v1/volumes?q=MachineDrawing&maxResults=40");
                    break;
                } else {
                    Toast.makeText(this, R.string.no_net, Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.favourites:
                contexte = "favorites";
                getSupportLoaderManager().restartLoader(Loader_id, null, MainActivity.this);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(arrow, contexte);

        scroll_value = MyAsyncTask.myLayoutManager.findFirstVisibleItemPosition();

        if (contexte == "favorites") {
            scroll_val_fav = myFavLayoutManager.findFirstVisibleItemPosition();
            outState.putInt("myFavScrollPos", scroll_val_fav);
        }else {
            outState.putInt(SCROLL_KEY, scroll_value);
            outState.putString(arrow, contexte);
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            @Override
            public Cursor loadInBackground() {
                String titleinfo = "";
                Cursor bookdetails = getContentResolver().query(SqlLiteHelper.CONTENT_URI, null, null, null, null);
                booksInfo = new ArrayList<>();
                if (bookdetails.getCount() > 0) {
                    if (bookdetails.moveToFirst()) {
                        do {
                            titleinfo += "\n" + bookdetails.getString(5);
                            Pojo bookinformation = new Pojo(bookdetails.getString(1),
                                    bookdetails.getString(2),
                                    bookdetails.getString(3),
                                    bookdetails.getString(4),
                                    bookdetails.getString(5),
                                    bookdetails.getString(6),
                                    bookdetails.getString(7),
                                    bookdetails.getString(8),
                                    bookdetails.getString(9),
                                    bookdetails.getString(10),
                                    bookdetails.getString(11),
                                    bookdetails.getString(12),
                                    bookdetails.getString(13),
                                    bookdetails.getString(14));
                            booksInfo.add(bookinformation);
                        } while (bookdetails.moveToNext());
                        widget w = new widget();
                        w.text = titleinfo;
                    }
                }
                return bookdetails;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, booksInfo);
        RecyclerView rv = findViewById(R.id.Bookslist);
        myFavLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(myFavLayoutManager);
        rv.setAdapter(myAdapter);
        rv.scrollToPosition(scroll_val_fav);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}