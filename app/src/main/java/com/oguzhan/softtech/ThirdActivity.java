package com.oguzhan.softtech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ThirdActivity extends AppCompatActivity {
    Button btnDegisken;
    Button btnDegiskensecond;
    ArrayList<String> CustomViewType=new ArrayList<>();
    ArrayList<String> edittxtArray=new ArrayList<>();//edittext
    ArrayList<String> buttonArray=new ArrayList<>();//textView
    String Headertitle;//HeaderTitle


    //RecyclerView recyclerView;
    RecyclerView recyclerViewbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnDegisken=(Button) findViewById (R.id.buttontextInput);
        recyclerViewbutton=findViewById(R.id.recyclerViewbutton);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerViewbutton.setLayoutManager(linearLayoutManager);
        btnDegisken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap= new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(gecisYap);
            }
        });
        btnDegiskensecond=(Button) findViewById (R.id.buttontext);
        btnDegiskensecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap= new Intent(ThirdActivity.this,SecondActivity.class);
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
                //System.out.println(jsonArrayProperties);
                if(Objects.equals(CustomViewType.get(i), "Header")){
                    //Text
                    //System.out.println("new");
                    Headertitle=jsonArrayProperties.getString("Text");
                    Headertitle.replace("'","");

                }


                if(Objects.equals(CustomViewType.get(i), "Button")){
                    buttonArray.add(jsonArrayProperties.getString("Header").replace("'",""));
                    System.out.println("new");
                }


            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        AdapterThird adapterthird =new AdapterThird(buttonArray, ThirdActivity.this);
        recyclerViewbutton.setAdapter(adapterthird);
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
            json =new String(bufferData,"UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}