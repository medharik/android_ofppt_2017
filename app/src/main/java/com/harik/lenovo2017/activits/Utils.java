package com.harik.lenovo2017.activits;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 12/31/2017.
 */

public class Utils {

   public static  String HttpGetReponse(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
    }
public static String [] cols={"formatted_address"};
    public static List<Map<String,String>> getList(String jsonString , String jsonRootObjectname){
        List<Map<String,String>> data=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        try {
            JSONObject jo=new JSONObject(jsonString);
            JSONArray ja=jo.getJSONArray(jsonRootObjectname);
            for (int i=0;i<ja.length();i++){
map=new HashMap<>();
                map.put(cols[0],ja.getJSONObject(i).getString(cols[0]));
      //  System.out.println("jsonss "+i+ " est "+ja.getJSONObject(i).toString());
data.add(map);
            }
           // System.out.println("formatted_addresss final  est "+ ja.getJSONObject(0).getString("formatted_address"));

        } catch (JSONException e) {
            System.out.println("Erreur getlist"+e.getMessage());
          e.printStackTrace();
        }

        return data;
    }
    public static void alertSetting(String title , String message, final Context context ){
        AlertDialog a=new AlertDialog.Builder(context)
                .setTitle(title)
                . setMessage(message)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setPositiveButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
