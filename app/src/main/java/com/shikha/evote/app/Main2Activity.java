package com.shikha.evote.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shikha.evote.app.R;

import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView i, i1, i2, i3;
    int val = 0;//counter
    // String pre="Mypref";
    //SharedPreferences sp =PreferenceManager.getDefaultSharedPreferences(this);
    //Context context;
   SharedPreferences sp;
    //sp=context.getSharedPreferences("com.example.aj07.gmai",Context.MODE_PRIVATE);
     //sp = getBaseContext().getSharedPreferences(
    //        "com.example.aj07.gmai", Context.MODE_PRIVATE);
    //SharedPreferences.Editor editor = sp.edit();

    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        i = (ImageView) findViewById(R.id.imageView3);
        i1 = (ImageView) findViewById(R.id.imageView2);
        i2 = (ImageView) findViewById(R.id.imageView4);
        i3 = (ImageView) findViewById(R.id.imageView5);
        i.setOnClickListener(this);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);
        //shared
        sp = getSharedPreferences("com.shikha.evote.app", Context.MODE_PRIVATE);
       // SharedPreferences.Editor editor=sp.edit();
          // editor.putInt("k",0);
         //editor.apply();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView3:
                 val = sp.getInt("k", 0);
                //       val = sp.getInt("k", -1);
                if (val == 0) {
                    Toast.makeText(getBaseContext(), "+1 Emma", Toast.LENGTH_SHORT).show();
                    abc("e");
                }
                break;
            case R.id.imageView2:
                     val = sp.getInt("k", 0);

                if (val == 0) {
                    Toast.makeText(getBaseContext(), "+1 Racheal", Toast.LENGTH_SHORT).show();
                    abc("r");
                }
                break;
            case R.id.imageView4:
                   val = sp.getInt("k", 0);

                if (val == 0) {
                    Toast.makeText(getBaseContext(), "+1 Shane", Toast.LENGTH_SHORT).show();
                    abc("s");
                }
                break;
            case R.id.imageView5:
                 val = sp.getInt("k", 0);

                if (val == 0) {
                    Toast.makeText(getBaseContext(), "+1 James", Toast.LENGTH_SHORT).show();
                    abc("j");
                }
                break;
            case R.id.button:
                Toast.makeText(getBaseContext(), "RESULTS", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Main3Activity.class);
                startActivity(i);
                break;
        }
    }

    public void abc(String s) {
        String tag_json_obj = "json_obj_req";
        String url = "https://evote9.herokuapp.com/" + s;

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("abc", response.toString());
                        pDialog.hide();
                        //k=1;
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putInt("k",1);
                        editor.apply();
                        //       editor.putInt("k",1);
                        //     editor.commit();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("abc", "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      // MainActivity.this.finish();
                        //android.os.Process.killProcess(android.os.Process.myPid());
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                    }
                })
                .setNegativeButton("No", null)
                .show();
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("k",0);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("k",0);
        editor.apply();
    }

}
