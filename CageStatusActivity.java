package com.example.animalcare;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CageStatusActivity extends AppCompatActivity {

    TextView cageStatus;
    String statusString;
    ImageView locked,unlocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cage_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cageStatus = (TextView) findViewById(R.id.cageStatus);

        locked = (ImageView) findViewById(R.id.locked);
        unlocked = (ImageView) findViewById(R.id.unlocked);

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                getCageStatus(0);
                handler.postDelayed(this, delay);
            }
        }, delay);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getCageStatus(int id){

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... params) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.8.100:4000/api/cages/104")
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                   // JSONArray jsonArray = new JSONArray(response.body().string());

                    JSONObject object = new JSONObject(response.body().string());

                        //cageStatus data = new cageStatus(object.getInt("empID"),object.getInt("cageID"),object.getString("status"),object.getInt("_v"));

                        statusString = object.getString("Status");


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                cageStatus.setText(statusString);
                if(statusString.equalsIgnoreCase("locked")){
                    locked.setVisibility(View.VISIBLE);
                    unlocked.setVisibility(View.GONE);
                } else if (statusString.equalsIgnoreCase("unlocked")){
                    locked.setVisibility(View.GONE);
                    unlocked.setVisibility(View.VISIBLE);
                }
            }
        };

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id);
        else
            task.execute(id);
//        task.execute(id);
    }

}
