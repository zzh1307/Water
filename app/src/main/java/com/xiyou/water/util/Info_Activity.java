package com.xiyou.water.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.xiyou.water.R;

import org.json.JSONObject;

public class Info_Activity extends AppCompatActivity {

    RequestQueue mQueue = null;
    JsonObjectRequest jsonObjectRequest = null;
    TextView tv = null;
    String city = null,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tv = (TextView) findViewById(R.id.tv);
        mQueue = Volley.newRequestQueue(getApplicationContext());
        Intent intent = getIntent();
        city = intent.getStringExtra("CITY");

        jsonObjectRequest = new JsonObjectRequest("http://115.28.206.181:8080/water/s-getStanMessage.action", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        info = response.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    tv.setText(info);
                            }
                        });
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

    }
    public void click(View view){
        mQueue.add(jsonObjectRequest);
    }
}
