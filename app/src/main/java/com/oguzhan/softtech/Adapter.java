package com.oguzhan.softtech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewClass>{
    ArrayList<String> edittxtArray;
    Context context;
    public Adapter(ArrayList<String> edittxtArray , Context context) {
        this.edittxtArray = edittxtArray;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
            MyViewClass myViewClass = new MyViewClass(view);

            return myViewClass;
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.edittxt.setText(edittxtArray.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Item Clicked",Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return  edittxtArray.size();
    }
    public class MyViewClass extends RecyclerView.ViewHolder {
        EditText edittxt;
        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            edittxt= (EditText) itemView.findViewById(R.id.edittxt);

        }
    }
}
