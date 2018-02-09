package com.harik.lenovo2017.activits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

public class curd extends AppCompatActivity {
ListView list ;
    EditText np,loc;
    Adapter ad=null;
    List<Map<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curd);
    np= (EditText) findViewById(R.id.ednomcrud);
    loc= (EditText) findViewById(R.id.edloccrud);
        list= (ListView) findViewById(R.id.listcrud);

    }

    public class recepeteurHttp extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            
        }
    }
    public void ajouter(View v){

    };
    public void supprimer(View v){

    };
    public void modifier(View v){

    };
    public void rechercher(View v){

    };

}
