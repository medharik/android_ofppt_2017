package harik.exo.test;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;
import com.harik.lenovo2017.activits.R;



public class ActivityFilter extends Activity {
    TextView tv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
tv= (TextView) findViewById(R.id.tvnav);
      Intent i=  getIntent();
     Uri data=i.getData();
        if(data.getHost()!=null ){
            Toast.makeText(this, "vous êtes entrain d'accéder à "+data.getHost(), Toast.LENGTH_SHORT).show();
       tv.setText("vous êtes entrain d'accéder à "+data.getHost());
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(3000);
        }

       /* i.putExtra("age",29);
        if (i.hasExtra("age")){
            i.getStringExtra("age");
        }*/

    }

}
