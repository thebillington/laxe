package uk.co.thebillington.laxe;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Implementation of App Widget functionality.
 */
public class FibonacciWidgetLarge extends AppWidgetProvider {

    static Timer timer = null;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
    }

    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        final Handler mHandler = new Handler();
        TimerTask minuteTask = new TimerTask() {

            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

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

                        // Construct the RemoteViews object
                        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fibonacci_widget_large);
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

                        // Instruct the widget manager to update the widget
                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    }
                });
            }
        };

        timer = new Timer();

        // schedule the task to run starting now and then every hour...
        timer.scheduleAtFixedRate(minuteTask, 0l, 1000 * 60);

    }
}


