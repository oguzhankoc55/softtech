package com.oguzhan.softtech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class SecondActivity extends AppCompatActivity {
    Button btnDegisken;
    Button btnDegiskensecond;
    ArrayList<String> CustomViewType=new ArrayList<>();
    ArrayList<String> textArray=new ArrayList<>();//textView
    String Headertitle;//HeaderTitle
    RecyclerView recyclerViewtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnDegisken= findViewById (R.id.buttontextInput);
        recyclerViewtxt=findViewById(R.id.recyclerViewtxt);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerViewtxt.setLayoutManager(linearLayoutManager);
        btnDegisken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap= new Intent(SecondActivity.this,MainActivity.class);
                startActivity(gecisYap);
            }
        });
        btnDegiskensecond= findViewById (R.id.buttonButton);
        btnDegiskensecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap= new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(gecisYap);
            }
        });
        try {
            JSONObject jsonObject=new JSONObject(jsonfromdata());
            JSONArray jsonArray=jsonObject.getJSONArray("custom");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData=jsonArray.getJSONObject(i);
                CustomViewType.add(userData.getString("CustomViewType"));
                JSONObject jsonArrayProperties=userData.getJSONObject("Properties");
                if(Objects.equals(CustomViewType.get(i), "Header")){
                    Headertitle=jsonArrayProperties.getString("Text");
                    Headertitle.replace("'","");

                }

                if(Objects.equals(CustomViewType.get(i), "Text")){
                    textArray.add(jsonArrayProperties.getString("Header").replace("'",""));
                    System.out.println("new");
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        AdapterSecond adaptersecond =new AdapterSecond(textArray,SecondActivity.this);
        recyclerViewtxt.setAdapter(adaptersecond);
        getSupportActionBar().setTitle(Headertitle.replace("'",""));


    }

    private  String jsonfromdata(){
        String json=null;
        try {
            InputStream inputStream=getAssets().open("customView.json");
            int sizeOfFile=inputStream.available();
            byte[] bufferData=new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json =new String(bufferData, StandardCharsets.UTF_8);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}