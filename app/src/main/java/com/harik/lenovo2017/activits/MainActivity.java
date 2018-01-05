package com.harik.lenovo2017.activits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import harik.exo.test.ListViewIntentImplicite;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{
    ListView list=null;
    List  data=null;
    ArrayAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       list= (ListView) findViewById(R.id.listcours);
        list.setOnItemClickListener(this);


      //  adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1);
       // list.setAdapter(adapter);

    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s=list.getItemAtPosition(position).toString();
        Toast.makeText(this, "list "+position+" id  : "+id+" text : "+s, Toast.LENGTH_SHORT).show();

        switch (position){
            case 0:
startActivity(new Intent(this,Main2Activity.class));
                break;
            case 1 : //pour list statique (entries)  et intent implicite
                startActivity(new Intent(this, ListViewIntentImplicite.class));
                break;
            case 2: // intent explicite
                break;
            case 3 : // intent explicite  avec retour
                break;
            case 4 : //intent avec filter
                break;



            case  5 : //pour list view simple
                startActivity(new Intent(this,ListViewActivity.class));

                break;
            case 6 : //pour list view personnalisée (baseAdapter)
                startActivity(new Intent(this,ListeViewPersonnaliee.class));
break;
            case  7 : //pour gps et services
                startActivity(new Intent(this,ServiceGpsWebServiceRest.class));

                break;
default:
    Toast.makeText(this, "choix non valide", Toast.LENGTH_SHORT).show();

        }
    }
}
