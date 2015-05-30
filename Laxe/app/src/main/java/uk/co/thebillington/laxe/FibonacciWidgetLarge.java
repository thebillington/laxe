package uk.co.thebillington.laxe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;


/**
 * Implementation of App Widget functionality.
 */
public class FibonacciWidgetLarge extends AppWidgetProvider {

    private static final String TAG = FibonacciWidgetLarge.class.getSimpleName();

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, FibonacciWidgetLarge.class);
        onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(componentName));
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ComponentName componentName = new ComponentName(context, FibonacciWidgetLarge.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

        Intent intent = new Intent(context.getApplicationContext(), UpdateService.class);
        intent.setAction(UpdateService.ACTION_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
        context.startService(intent);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        m.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000 * 60, pendingIntent);
    }
}



