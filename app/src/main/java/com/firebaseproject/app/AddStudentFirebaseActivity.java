package com.firebaseproject.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class AddStudentFirebaseActivity extends AppCompatActivity {

//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
    private FirebaseAuth firebaseAuth;
    Button  additemtodatabase;
    DatabaseReference databaseReference;
    ProgressDialog pd;
    Context context;
    FirebaseStorage storage;
    FirebaseAuth auth;

    TextView tvResult;

    EditText nameTxt,studentIdEditTxt,surnameEditTxt,fatherNameEditTxt,nationalIdEditTxt,dobEditTxt,genderEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_firebase);
        firebaseAuth = FirebaseAuth.getInstance();


        //Top action bar
        ActionBar a = getSupportActionBar();
        a.setTitle("Add Student");
        a.setDisplayHomeAsUpEnabled(true);

        //Firebase Storage referenece initialization
        FirebaseApp.initializeApp(context);


        //Firebase authentication
        auth = FirebaseAuth.getInstance();
        //Progress bar
        pd = new ProgressDialog(this);
        pd.setTitle("Saving Data");
        pd.setMessage("Please Wait...");

        //Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        tvResult=  findViewById(R.id.tvResult);

        studentIdEditTxt= (EditText) findViewById(R.id.studentIdEditTxt);

        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        surnameEditTxt= (EditText) findViewById(R.id.surnameEditTxt);
        fatherNameEditTxt= (EditText) findViewById(R.id.fatherNameEditTxt);
        nationalIdEditTxt= (EditText) findViewById(R.id.nationalIdEditTxt);
        dobEditTxt= (EditText) findViewById(R.id.dobEditTxt);
        genderEditTxt= (EditText) findViewById(R.id.genderEditTxt);

        //init views
        additemtodatabase = findViewById(R.id.additembuttontodatabase);





        //add product to database
        additemtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        getWeatherDetails();
    }




    public  void addProduct(){
        // all fields must be filled
        if(!TextUtils.isEmpty(studentIdEditTxt.getText().toString())&&!TextUtils.isEmpty(nameTxt.getText().toString())&&!TextUtils.isEmpty(nationalIdEditTxt.getText().toString())){


            //upload product to database
            StudentsFirebase items = new StudentsFirebase(studentIdEditTxt.getText().toString(), nameTxt.getText().toString(),surnameEditTxt.getText().toString(),fatherNameEditTxt.getText().toString(),nationalIdEditTxt.getText().toString(),dobEditTxt.getText().toString(),genderEditTxt.getText().toString());
            databaseReference.child("students").child(studentIdEditTxt.getText().toString()).setValue(items);
            studentIdEditTxt.setText("");
            nameTxt.setText("");
            surnameEditTxt.setText("");
            fatherNameEditTxt.setText("");
            nationalIdEditTxt.setText("");
            dobEditTxt.setText("");
            genderEditTxt.setText("");

            Toast.makeText(AddStudentFirebaseActivity.this,"Added",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(AddStudentFirebaseActivity.this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();
        }
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

















    // logout



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu
        return true;
    }


}
