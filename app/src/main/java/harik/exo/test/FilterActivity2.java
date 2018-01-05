package harik.exo.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.harik.lenovo2017.activits.R;

public class FilterActivity2 extends Activity {
public static  final String  MON_LIEN="monlien";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter2);

Intent recu =getIntent();
        Uri data = recu.getData();
        String lien =data.getHost();
      Intent demarerweView =new Intent(this,Web.class);
        demarerweView.putExtra(MON_LIEN,lien);
        startActivity(demarerweView);


    }
}
