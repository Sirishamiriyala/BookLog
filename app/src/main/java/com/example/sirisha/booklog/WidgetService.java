package com.example.sirisha.booklog;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;

public class WidgetService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    static final String SERVICE_ACTION = "action";

    public WidgetService() {
        super("WidgetService");
    }

    static void setCall(Context context){
        Intent intent = new Intent(context,WidgetService.class);
        intent.setAction(SERVICE_ACTION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            String action = intent.getAction();
            if(action.equals(SERVICE_ACTION)){
                setData();
            }
        }
    }

    void setData(){

        String titleinfo = "";
        Cursor bookdetails = getContentResolver().query(SqlLiteHelper.CONTENT_URI, null, null, null, null);
        if (bookdetails.getCount() > 0) {
            if (bookdetails.moveToFirst()) {
                do {
                    titleinfo += "\n" + bookdetails.getString(5);
                } while (bookdetails.moveToNext());
            }
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,widget.class));
        widget.callUpdate(WidgetService.this,appWidgetManager,appWidgetIds,titleinfo);
    }

}
