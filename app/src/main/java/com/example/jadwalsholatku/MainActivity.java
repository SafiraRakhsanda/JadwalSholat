package com.example.jadwalsholatku;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    String url = "https://muslimsalat.com/tenjolaya/weekly.json?key=45178e26e94d5ad42a6ca0520dea954a";
    String tag_json_obj = "json_obj_req";

    TextView textFajr, textSyuruq, textDzuhur, textAshar, textMaghrib, textIsya, textSepertiga;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFajr = findViewById(R.id.fajr);
        textSyuruq = findViewById(R.id.syuruq);
        textDzuhur = findViewById(R.id.dzuhur);
        textAshar = findViewById(R.id.ashar);
        textMaghrib = findViewById(R.id.maghrib);
        textIsya = findViewById(R.id.isya);
        textSepertiga = findViewById(R.id.sepertiga);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading. . . .");
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String mFajr = response.getJSONArray("items").getJSONObject(0).get("fajr") .toString();
                            String mSyuruq = response.getJSONArray("items").getJSONObject(0).get("shurooq") .toString();
                            String mDzuhur = response.getJSONArray("items").getJSONObject(0).get("dhuhr") .toString();
                            String mAshar = response.getJSONArray("items").getJSONObject(0).get("asr") .toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib") .toString();
                            String mIsya = response.getJSONArray("items").getJSONObject(0).get("isha") .toString();
                            String mSepertiga = response.getJSONArray("items").getJSONObject(0).get("shurooq") .toString();

                            textFajr.setText(mFajr);
                            textSyuruq.setText(mSyuruq);
                            textDzuhur.setText(mDzuhur);
                            textAshar.setText(mAshar);
                            textMaghrib.setText(mMaghrib);
                            textIsya.setText(mIsya);
                            textSepertiga.setText(mSepertiga);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error : " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });

       AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
}