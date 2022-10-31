package com.oguzhan.softtech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> CustomViewType=new ArrayList<>();
    ArrayList<String> edittxtArray=new ArrayList<>();//edittext
    String Headertitle;//HeaderTitle
    RecyclerView recyclerView;
    Button btnDegisken;
    Button btnDegiskensecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        btnDegisken=(Button) findViewById (R.id.buttontext);

        btnDegisken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap= new Intent(MainActivity.this,SecondActivity.class);
                startActivity(gecisYap);
            }
        });
        btnDegiskensecond=(Button) findViewById (R.id.buttonButton);
        btnDegiskensecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap= new Intent(MainActivity.this,ThirdActivity.class);
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
                if(Objects.equals(CustomViewType.get(i), "TextInput")){
                    edittxtArray.add(jsonArrayProperties.getString("Header").replace("'",""));
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        Adapter adapter =new Adapter(edittxtArray,MainActivity.this);
        recyclerView.setAdapter(adapter);
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