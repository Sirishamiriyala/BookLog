package com.example.sirisha.booklog;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sirisha on 31-05-2018.
 */

public class DbTableNames {
    public static final String  AUTHORITE = "com.example.sirisha.booklog";
    public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+AUTHORITE);
    public static final String PATH_TASK = "favourites";

    public static final class TableData implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASK).build();



        public static final String COLUMN_kind="kind";
        public static final String COLUMN_selfLink="selfLink";
        public static final String COLUMN_etag="etag";
        public static final String COLUMN_id="id";
        public static final String COLUMN_title="title";
        public static final String COLUMN_author="author";
        public static final String COLUMN_publishedDate="publishedDate";
        public static final String COLUMN_pageCount="pageCount";
        public static final String COLUMN_smallThumb="smallThumbnail";
        public static final String COLUMN_thumb="thumbnail";
        public static final String COLUMN_infoLink="infoLink";
        public static final String COLUMN_preview="previewLink";
        public static final String COLUMN_lang="lang";
        public static final String COLUMN_country="country";


    }
}
