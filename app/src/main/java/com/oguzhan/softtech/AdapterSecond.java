package com.oguzhan.softtech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterSecond extends RecyclerView.Adapter<AdapterSecond.MyViewClass>{
    ArrayList<String> textArray;
    Context context;
    public AdapterSecond(ArrayList<String> textArray , Context context) {
        this.textArray = textArray;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowsecond, parent, false);
            MyViewClass myViewClass = new MyViewClass(view);

            return myViewClass;
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.textView.setText(textArray.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Item Clicked",Toast.LENGTH_LONG).show();
            }

        });
    }
    @Override
    public int getItemCount() {
        return  textArray.size();
    }
    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.textView);
        }
    }
}
