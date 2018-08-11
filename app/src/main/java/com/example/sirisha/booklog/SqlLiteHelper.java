package com.example.sirisha.booklog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by sirisha on 31-05-2018.
 */

public class SqlLiteHelper extends SQLiteOpenHelper {
    public static final String AUTHORITY = "com.example.sirisha.booklog";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String path_Tasks = "bookDetails";
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(path_Tasks).build();
    public static final String Databasename = "BookDatabase";
    public static final String tablename = "BookDetails";
    public static final int version = 1;
    public static final String kind="kind";
    public static final String selfLink="selfLink";
    public static final String etag="etag";
    public static final String id="id";
    public static final String Bookid="Bookid";
    public static final String title="title";
    public static final String author="author";
    public static final String publishedDate="publishedDate";
    public static final String pageCount="pageCount";
    public static final String smallThumb="smallThumbnail";
    public static final String thumb="thumbnail";
    public static final String infoLink="infoLink";
    public static final String preview="previewLink";
    public static final String lang="lang";
    public static final String country="country";

    public Context c;

    public SqlLiteHelper(Context context) {
        super(context, Databasename, null, version);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String query = "CREATE TABLE " + tablename + "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT," + selfLink + " TEXT," +
                kind + " TEXT," + etag + " TEXT," +
                Bookid + " TEXT," + title + " TEXT," + author + " TEXT," +
                 publishedDate  + " TEXT," + pageCount +" TEXT," + smallThumb
                +" TEXT," + thumb +" TEXT," + infoLink + " TEXT," + preview + " TEXT," +
                lang + " TEXT," + country + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
        onCreate(db);
    }

}