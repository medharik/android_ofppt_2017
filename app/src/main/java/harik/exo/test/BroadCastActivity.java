package harik.exo.test;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.harik.lenovo2017.activits.R;
public class BroadCastActivity extends Activity {
Recepteur r;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        tv= (TextView) findViewById(R.id.tvtable1);
        r=new Recepteur();
        IntentFilter f=new IntentFilter("ofppt");
       f.addAction(Intent.ACTION_CALL);
        //enregister le BroadcastReceiver auprès de système
        // //android pour écouter les intents ayant comme action "ofppt"
        registerReceiver(r,f);
    }
    @Override
    protected void onDestroy() {
        //supprimer l'enregistement du bbroadCast à la descrution de l'app
        unregisterReceiver(r);
        super.onDestroy();
    }
  public void  lancerBr(View v){
      Intent i =new Intent();
      i.setAction("ofppt");
      sendBroadcast(i);
      Intent x=new Intent(this,Recepteur.class);
      PendingIntent pi=PendingIntent.getBroadcast(this,0,x,0);
      AlarmManager a= (AlarmManager) getSystemService(ALARM_SERVICE);
      a.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+1000,pi);
      Toast.makeText(getApplicationContext(), "recu", Toast.LENGTH_SHORT).show();
  }

    class  Recepteur extends BroadcastReceiver{
         @Override
         public void onReceive(Context context, Intent intent) {
             Toast.makeText(context, "message reçu", Toast.LENGTH_SHORT).show();
             tv.setText("message recu , de l'intent ayant comme action "+intent.getAction());
             Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
             v.vibrate(3000);
         }
     }

}
