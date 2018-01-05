package com.harik.lenovo2017.activits;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListViewActivity extends Activity {
ListView lv;
    ArrayAdapter adapter;//adapter (manager)les données d'une collections
    // pour les afficher dans la listView
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lv= (ListView) findViewById(R.id.listsimple);
        data=new ArrayList();

        for (int i=0;i<100;i++){
            Random r=new Random(1000);

            data.add("tdi  "+r.nextInt());
        }
        adapter=new ArrayAdapter(ListViewActivity.this,android.R.layout.simple_list_item_2,data);

        lv.setAdapter(adapter);

lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListViewActivity.this,"click sur "+data.get(position),Toast.LENGTH_LONG).show();
    }
});
    }
}
