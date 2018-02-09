package com.harik.lenovo2017.activits;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ListeViewPersonnaliee extends Activity {
ListView lv=null;
    List<Map<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_view_personnaliee);
        data=new ArrayList<>();
        for (int i=0;i<10;i++){
            HashMap<String,String> e=new HashMap<>();
            Random r=new Random(10);
            e.put("nom","nom"+r.nextInt());
            e.put("prenom","prenom"+r.nextInt());
            data.add(e);
        }
        lv= (ListView) findViewById(R.id.listPersonnalise);
        lv.setAdapter(new MonAdaper());
    }

public     class MonAdaper extends BaseAdapter{
        MonAdaper(){
        }
MonAdaper(Context c, List<Map<String,String>> l){
c=getApplicationContext();


}        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public Map<String, String> getItem(int position) {
            return data.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
try{

    if (convertView==null){
        convertView=getLayoutInflater().inflate(R.layout.element_list,null);// met parent à null
    }

    TextView tvnom= (TextView) convertView.findViewById(R.id.tvnom);

    TextView tvprenom= (TextView) convertView.findViewById(R.id.tvprenom);
    tvnom.setText(getItem(position).get("nom"));
    tvprenom.setText(getItem(position).get("prenom"));
}catch (Exception e){
    Log.e("erreur  getView",e.getMessage());
}

            return convertView;
        }
    }

}
