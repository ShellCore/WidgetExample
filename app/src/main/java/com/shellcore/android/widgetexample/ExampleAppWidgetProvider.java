package com.shellcore.android.widgetexample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Cesar on 20/06/2017.
 */

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Get the layout for the App Widget and attach the onClick listener
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.txt_name, getRandomName());

            // Create an Intent to update Widget
            Intent intent = new Intent(context, ExampleAppWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.btn_name, pendingIntent);

            // Tell the AppWidgetManager to performa an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    /**
     * We are faking data, but this method should be a call to a ContentProvider or Database to get real data
     * @return
     */
    private String getRandomName() {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        names.add("Anna");
        names.add("Peter");
        names.add("Saul");

        Random rnd = new Random();
        int randomPosition = rnd.nextInt(names.size());

        return names.get(randomPosition);
    }
}
