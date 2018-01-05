package com.harik.lenovo2017.activits;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.R.attr.filter;

public class ServiceGpsWebServiceRest extends Activity implements View.OnClickListener, LocationListener {
    Button bgps, bok;
    EditText elat, elon;
    private String provider = "aucun";
    private LocationManager lm;
    private RecepteurGPS  gpsReceiver=null;
    IntentFilter filter;
    TextView tvaddress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
private class RecepteurGPS extends BroadcastReceiver{
String reponse="aucune";
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(ServiceGpsWebServiceRest.this, "reponse GPS : "+reponse, Toast.LENGTH_SHORT).show();

            //  if(intent != null && intent.getAction()==IntentServiceRestful.ACTION_REPONSE_GPS  && intent.hasExtra(IntentServiceRestful.REPONSE_EXTRA_GPS)){
reponse=intent.getStringExtra(IntentServiceRestful.REPONSE_EXTRA_GPS);
            //}
           // Toast.makeText(ServiceGpsWebServiceRest.this, "reponse GPS : "+reponse, Toast.LENGTH_SHORT).show();
            List<Map<String, String>> datalist = Utils.getList(reponse, "results");
          //  Toast.makeText(ServiceGpsWebServiceRest.this, "reponse GPS : "+datalist.get(0).get("formatted_address").toString(), Toast.LENGTH_SHORT).show();
if(!(datalist.isEmpty() || datalist.get(0).get("formatted_address").isEmpty())) {
    tvaddress.setText("Adresse :  " + datalist.get(0).get("formatted_address").toString());


} Log.i("REPONSE GPS RECU", reponse);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_gps_web_service_rest);
        elat = (EditText) findViewById(R.id.elat);
        elon = (EditText) findViewById(R.id.elon);
        bok = (Button) findViewById(R.id.bok);
tvaddress= (TextView) findViewById(R.id.tvaddress);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_MEDIUM);
        provider = lm.getBestProvider(c, true);
gpsReceiver=new RecepteurGPS();

        bgps = (Button) findViewById(R.id.bgps);
        bok.setOnClickListener(this);
        bgps.setOnClickListener(this);
        filter =new IntentFilter(IntentServiceRestful.ACTION_REPONSE_GPS);
        registerReceiver(gpsReceiver,filter);
     }

    @Override
    public void onClick(View v) {
        try {
            int id = v.getId();
            switch (id) {
                case R.id.bgps:


                    if (lm.isProviderEnabled(provider)) {
                        Toast.makeText(this, "best  provider activé " + provider, Toast.LENGTH_SHORT).show();
                    } else if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
provider=LocationManager.NETWORK_PROVIDER;
                    }  else if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        provider=LocationManager.NETWORK_PROVIDER;
                    }else

                    {
                        Toast.makeText(this, " provider non activé " + provider, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }

                    Location l = lm.getLastKnownLocation(provider);
                    if (l != null) {
                        elat.setText((""+l.getLatitude()));
                        elon.setText("" + l.getLongitude());
                    } else {
                        elat.setText("0.0");
                        elon.setText("0.0");

                       Utils.alertSetting("Absence de fournisseur de localisation","Voulez vous Activer les services de Localisation ? ",getApplicationContext());

                    }

                    ;
                    break;
                case  R.id.bok:
                   IntentServiceRestful.startActionGPS(this,elat.getText().toString(),elon.getText().toString());
break;
            }
        } catch (Exception e) {

            // Log.e("Erreur gps",e.getMessage());
            Toast.makeText(getApplicationContext(), "aucune connection gps , provider " + provider, Toast.LENGTH_LONG).show();
        }

    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();

        lm.requestLocationUpdates(provider, 400, 1, this);

    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(gpsReceiver);
    }

    @Override
    public void onLocationChanged(Location l) {
        if (l != null) {
            elat.setText(""+l.getLatitude());
            elon.setText("" + l.getLongitude());
        } else{
           // startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            Utils.alertSetting("absence de fournisseur de localisation","Voulez vous Activer les services de Localisation ? ",ServiceGpsWebServiceRest.this);

        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
       //  https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-7.589841666666667,33.57310833333334&radius=500&key=AIzaSyCoEC6ztw5fXNS5rJ9M-SG_EzbrY3lf5yM
//API KEY google places  AIzaSyCoEC6ztw5fXNS5rJ9M-SG_EzbrY3lf5yM

        //geocoding inversé :
        //https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=AIzaSyCH-BtEQw-RASlp2lYRST6mrCxep9ntWZI
        //API KY =  AIzaSyCH-BtEQw-RASlp2lYRST6mrCxep9ntWZI
        //exemple https://maps.googleapis.com/maps/api/geocode/json?latlng=33.57310833333334,-7.589841666666667&key=AIzaSyCH-BtEQw-RASlp2lYRST6mrCxep9ntWZI
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ServiceGpsWebServiceRest Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      //  client.connect();
        //AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      //  AppIndex.AppIndexApi.end(client, getIndexApiAction());
       // client.disconnect();
    }
}
