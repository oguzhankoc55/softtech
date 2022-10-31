package com.oguzhan.softtech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterThird extends RecyclerView.Adapter<AdapterThird.MyViewClass>{
    ArrayList<String> buttonArray;
    Context context;
    public AdapterThird(ArrayList<String> buttonArray , Context context) {
        this.buttonArray = buttonArray;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowthird, parent, false);
            MyViewClass myViewClass = new MyViewClass(view);

            return myViewClass;
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.button.setText(buttonArray.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Item Clicked",Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return  buttonArray.size();
    }
    public class MyViewClass extends RecyclerView.ViewHolder {
        Button button;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            button=(Button) itemView.findViewById(R.id.button);

        }
    }
}
