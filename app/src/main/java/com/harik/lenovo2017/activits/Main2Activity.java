package com.harik.lenovo2017.activits;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main2Activity extends Activity {
Button b =null;
 RadioButton rb$,rb€;
    EditText eprix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        eprix=(EditText)findViewById(R.id.eprix$);
        rb$= (RadioButton) findViewById(R.id.rb$);

        rb€= (RadioButton) findViewById(R.id.rb€);
        rb$.setChecked(true);
    }


   public void  convertir(View v){
       RadioButton rb=null;
       double taux=10,prix=0;
       if(rb$.isChecked()){
           rb=rb$;
           taux=8;
       }else {
           rb=rb€;
       }
       try {

           prix =Double.parseDouble(eprix.getText().toString());
eprix.setText(""+prix/taux);
       }catch (Exception e){
Log.e("erreur_conversion",e.getMessage());
       }
       Toast.makeText(this, " convertir dh en ..."+prix, Toast.LENGTH_SHORT).show();

    }
}
