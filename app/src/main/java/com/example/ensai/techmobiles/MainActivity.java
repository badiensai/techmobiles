package com.example.ensai.techmobiles;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .protocols(Arrays.asList(Protocol.HTTP_1_1))
            .build();

    void run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(
                        new Callback() {
                            @Override public void onFailure(Call call, IOException e) {
                                Log.e("Erreur","Erreur",e);
                            }
                            @Override public void onResponse(Call call, Response response) throws IOException {
                                try (ResponseBody responseBody = response.body()) {
                                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                                    String body = responseBody.string();
                                    Log.i("Resultat", body);

                                    try {
                                        JSONObject json = new JSONObject(body);
                                        JSONArray jsonArray json.getJSONArray("records");
                                    } catch (JSONException e) {
                                        Log.e("Erreur","Erreur",e);
                                    }

                                    JSONArray
                                }
                            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url="https://data.explore.star.fr/api/records/1.0/search/?dat" +
                "aset=tco-bus-vehicules-position-tr&facet=numerobus&facet=etat&" +
                "facet=nomcourtligne&facet=sens&facet=destination&refine.nomcourtligne=57";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.startMap();
        try{
           runString(url);
        }catch (IOException e){
            Log.e("Erreur", "Erreur",e);
        }
    }

    public void startMap() {
        Intent intent = new Intent(this,MapsActivity.class);
        /*
        EditText editText = (EditText) findViewById(R.id.map);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        */
        startActivity(intent);
    }

}
