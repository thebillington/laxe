package uk.co.thebillington.laxe;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

public class UpdateService extends Service {

    private static final String TAG = UpdateService.class.getSimpleName();

    public static final String ACTION_UPDATE = "uk.co.thebillington.laxe.ACTION_UPDATE";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(null, flags, startId);
        }

        String action = intent.getAction();
        if (action == null) {
            return super.onStartCommand(intent, flags, startId);
        }

        Log.d(TAG, String.format("onStartCommand: %s", action));

        if (action.equals(ACTION_UPDATE)) {
            int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            for (int widgetId : allWidgetIds) {
                updateWidget(widgetId);
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget(int widgetId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());

        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.fibonacci_widget);

//        remoteViews.setOnClickPendingIntent(
//                R.id.root,
//                PendingIntent.getActivity(
//                        this,
//                        REQUEST_CODE_UPDATE,
//                        new Intent(this, SettingsActivity.class),
//                        PendingIntent.FLAG_UPDATE_CURRENT));
//
//        Settings settings = new Settings(this);
//        Bundle options = appWidgetManager.getAppWidgetOptions(widgetId);

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour > 12) {
            if (hour == 24) {
                hour = 0;
            } else {
                hour -= 12;
            }

        }
        int minutes = c.get(Calendar.MINUTE);
        double mins = (double) minutes / 5;
        minutes = ((int) (mins + 0.5)) * 5;
        if (minutes == 60) {
            minutes = 0;
            hour += 1;
        }

        boolean fiveHour = false;
        boolean threeHour = false;
        boolean twoHour = false;
        boolean topOneHour = false;
        boolean bottomOneHour = false;

        boolean fiveMinute = false;
        boolean threeMinute = false;
        boolean twoMinute = false;
        boolean topOneMinute = false;
        boolean bottomOneMinute = false;

        switch (hour) {
            case 1:
                bottomOneHour = true;
                break;
            case 2:
                twoHour = true;
                break;
            case 3:
                threeHour = true;
                break;
            case 4:
                topOneHour = true;
                bottomOneHour = true;
                twoHour = true;
                break;
            case 5:
                fiveHour = true;
                break;
            case 6:
                bottomOneHour = true;
                fiveHour = true;
                break;
            case 7:
                fiveHour = true;
                twoHour = true;
                break;
            case 8:
                fiveHour = true;
                twoHour = true;
                bottomOneHour = true;
                break;
            case 9:
                fiveHour = true;
                threeHour = true;
                bottomOneHour = true;
                break;
            case 10:
                fiveHour = true;
                twoHour = true;
                threeHour = true;
                break;
            case 11:
                fiveHour = true;
                threeHour = true;
                twoHour = true;
                bottomOneHour = true;
                break;
            case 12:
                fiveHour = true;
                threeHour = true;
                twoHour = true;
                bottomOneHour = true;
                topOneHour = true;
                break;
            default:
                break;
        }

        switch (minutes) {
            case 5:
                bottomOneMinute = true;
                break;
            case 10:
                twoMinute = true;
                break;
            case 15:
                threeMinute = true;
                break;
            case 20:
                topOneMinute = true;
                bottomOneMinute = true;
                twoMinute = true;
                break;
            case 25:
                fiveMinute = true;
                break;
            case 30:
                bottomOneMinute = true;
                fiveMinute = true;
                break;
            case 35:
                fiveMinute = true;
                twoMinute = true;
                break;
            case 40:
                fiveMinute = true;
                twoMinute = true;
                bottomOneMinute = true;
                break;
            case 45:
                fiveMinute = true;
                threeMinute = true;
                bottomOneMinute = true;
                break;
            case 50:
                fiveMinute = true;
                twoMinute = true;
                threeMinute = true;
                break;
            case 55:
                fiveMinute = true;
                threeMinute = true;
                twoMinute = true;
                bottomOneMinute = true;
                break;
            default:
                break;
        }

        if (fiveHour) {
            if (fiveMinute) {
                views.setImageViewResource(R.id.five, R.drawable.blue);
            } else {
                views.setImageViewResource(R.id.five, R.drawable.red);
            }
        } else if (fiveMinute) {
            views.setImageViewResource(R.id.five, R.drawable.green);
        } else {
            views.setImageViewResource(R.id.five, R.drawable.white);
        }
        if (threeHour) {
            if (threeMinute) {
                views.setImageViewResource(R.id.three, R.drawable.blue);
            } else {
                views.setImageViewResource(R.id.three, R.drawable.red);
            }
        } else if (threeMinute) {
            views.setImageViewResource(R.id.three, R.drawable.green);
        } else {
            views.setImageViewResource(R.id.three, R.drawable.white);
        }
        if (twoHour) {
            if (twoMinute) {
                views.setImageViewResource(R.id.two, R.drawable.blue);
            } else {
                views.setImageViewResource(R.id.two, R.drawable.red);
            }
        } else if (twoMinute) {
            views.setImageViewResource(R.id.two, R.drawable.green);
        } else {
            views.setImageViewResource(R.id.two, R.drawable.white);
        }
        if (topOneHour) {
            if (topOneMinute) {
                views.setImageViewResource(R.id.topOne, R.drawable.blue);
            } else {
                views.setImageViewResource(R.id.topOne, R.drawable.red);
            }
        } else if (topOneMinute) {
            views.setImageViewResource(R.id.topOne, R.drawable.green);
        } else {
            views.setImageViewResource(R.id.topOne, R.drawable.white);
        }
        if (bottomOneHour) {
            if (bottomOneMinute) {
                views.setImageViewResource(R.id.bottomOne, R.drawable.blue);
            } else {
                views.setImageViewResource(R.id.bottomOne, R.drawable.red);
            }
        } else if (bottomOneMinute) {
            views.setImageViewResource(R.id.bottomOne, R.drawable.green);
        } else {
            views.setImageViewResource(R.id.bottomOne, R.drawable.white);
        }

        appWidgetManager.updateAppWidget(widgetId, views);
    }

}
