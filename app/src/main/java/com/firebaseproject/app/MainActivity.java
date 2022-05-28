package com.firebaseproject.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    FirebaseAuth auth;


    TextView tvResult;
    private CardView addStudentFirebase, addStudentSqlite, viewStudentFirebase, viewStudentSqlite,deleteStudentFirebase,weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //init Views
        addStudentFirebase = (CardView)findViewById(R.id.addStudentFirebase);
        addStudentSqlite = (CardView) findViewById(R.id.addStudentSqlite);
        viewStudentFirebase = (CardView) findViewById(R.id.viewStudentFirebase);
        viewStudentSqlite = (CardView) findViewById(R.id.viewStudentSqlite);
        deleteStudentFirebase = (CardView) findViewById(R.id.deleteStudentFirebase);
        weather = (CardView) findViewById(R.id.weather);
        tvResult =  findViewById(R.id.tvResult);

        //add click listener
        addStudentFirebase.setOnClickListener(this);
        addStudentSqlite.setOnClickListener(this);
        viewStudentFirebase.setOnClickListener(this);
        viewStudentSqlite.setOnClickListener(this);
        deleteStudentFirebase.setOnClickListener(this);
        weather.setOnClickListener(this);


        getWeatherDetails();
    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            // on clicking buttons move to respective screen
            case R.id.addStudentFirebase:  i = new Intent(this, AddStudentFirebaseActivity.class); startActivity(i); break;
            case R.id.addStudentSqlite:  i = new Intent(this, AddStudentSQActivity.class);startActivity(i); break;
            case R.id.viewStudentFirebase:  i = new Intent(this, AllStudentsFirebaseActivity.class);startActivity(i); break;
            case R.id.viewStudentSqlite:  i = new Intent(this, SQliteViewAllStudentsActivity.class);startActivity(i); break;
            case R.id.deleteStudentFirebase:  i = new Intent(this, DeleteStudentFirebaseActivity.class);startActivity(i); break;
            case R.id.weather:  i = new Intent(this, WeatherActivity.class);startActivity(i); break;

            default: break;
        }


    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu

        return true;
    }
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "a45c61be8d836e072e21863dc9e8dcdd";
    DecimalFormat df = new DecimalFormat("#.##");
    public void getWeatherDetails() {
        String tempUrl = "";


        tempUrl = url + "?q=" + "berlin" + "&appid=" + appid;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");

                    String icon = jsonObjectWeather.getString("icon");


                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonResponse.getString("name");
                    tvResult.setTextColor(Color.rgb(68,134,199));


                    output += "Current weather of " + cityName + " (" + countryName + ")"
                            + "\n Temp: " + df.format(temp) + " °C"
                            + "\n Humidity: " + humidity + "%"
                            + "\n Cloudiness: " + clouds + "%";


                    // Including the weather image created from the icon - use glide for this
//                    Glide.with(WeatherActivity.this).load("https://openweathermap.org/img/w/" + icon + ".png")
//                            .placeholder(R.mipmap.ic_launcher)
//                            .error(R.mipmap.ic_launcher)
//                            .into(image);
                    tvResult.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }



}
