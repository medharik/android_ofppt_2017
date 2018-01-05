package com.harik.lenovo2017.activits;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceRestful extends IntentService {
    public  static final  String API_KEY="AIzaSyCH-BtEQw-RASlp2lYRST6mrCxep9ntWZI";
    public static final String  URL="https://maps.googleapis.com/maps/api/geocode/json?latlng=";
            //"https://maps.googleapis.com/maps/api/geocode/json?latlng=33.5890745,-7.6133062&key=AIzaSyCH-BtEQw-RASlp2lYRST6mrCxep9ntWZI";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_REPONSE_GPS = "com.harik.lenovo2017.activits.action.REPONSE.gps";
    public static final String REPONSE_EXTRA_GPS = "com.harik.lenovo2017.activits.action.REPONSE.gps_REPONSE_EXTRA";

    public  static final String ACTION_GPS = "com.harik.lenovo2017.activits.action.GPS";
    public   static final String ACTION_BAZ = "com.harik.lenovo2017.activits.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_LAT = "com.harik.lenovo2017.activits.extra.LAT";
    private static final String EXTRA_LON = "com.harik.lenovo2017.activits.extra.LON";

    public IntentServiceRestful() {
        super("IntentServiceRestful");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGPS(Context context, String lat, String lon) {
        Intent intent = new Intent(context, IntentServiceRestful.class);
        intent.setAction(ACTION_GPS);
        intent.putExtra(EXTRA_LAT, lat);
        intent.putExtra(EXTRA_LON, lon);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, IntentServiceRestful.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_LAT, param1);
        intent.putExtra(EXTRA_LON, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GPS.equals(action)) {
                final String lat = intent.getStringExtra(EXTRA_LAT);
                final String lon = intent.getStringExtra(EXTRA_LON);
                String url =URL+lat+","+lon+"&key="+API_KEY;

                handleActionGPS(url);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_LAT);
                final String param2 = intent.getStringExtra(EXTRA_LON);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGPS(String url) {
        String reponse="aucune";
        try {
             reponse=Utils.HttpGetReponse(url);
            Toast.makeText(getApplicationContext(), "envoie en cours...", Toast.LENGTH_SHORT).show();
            Log.i("REPONSE RESTFUL", reponse);
            Log.i("URL", url);
          //  Toast.makeText(getApplicationContext(), "envoie en cours...", Toast.LENGTH_SHORT).show();

            List<Map<String,String>> dataList =      Utils.getList(reponse,"results");
            Log.i("data list ",dataList.toArray().toString());

            Intent b=new Intent();
            b.setAction(ACTION_REPONSE_GPS);
         //   b.addCategory(Intent.CATEGORY_DEFAULT);
            b.putExtra(REPONSE_EXTRA_GPS,reponse);
            sendBroadcast(b);
        } catch (IOException e) {
            Log.e("Erreur handle action ",e.getMessage());
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
