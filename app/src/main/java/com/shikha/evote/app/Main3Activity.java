package com.shikha.evote.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Main3Activity extends AppCompatActivity {
TextView a1,a2,a3,a4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        a1=(TextView)findViewById(R.id.textView11);
        a2=(TextView)findViewById(R.id.textView12);
        a3=(TextView)findViewById(R.id.textView13);
        a4=(TextView)findViewById(R.id.textView14);
        abc();
    }

    public void abc()
    {
        String tag_json_obj = "json_obj_req";
        String url = "https://evote9.herokuapp.com/result";

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

                        try {
                            String e=response.getString("emma");
                            String r=response.getString("racheal");
                            String sh=response.getString("shane");
                            String j=response.getString("james");
                            a1.setText(e);
                            a2.setText(r);
                            a3.setText(sh);
                            a4.setText(j);

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

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
}
