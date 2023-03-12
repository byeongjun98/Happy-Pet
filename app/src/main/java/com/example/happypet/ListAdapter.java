package com.example.happypet;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[], data5[], data6[];
    int images[], symptomimages[];
    Context context;

    public ListAdapter(Context ct, String s1[], String s2[], String s3[], String s4[], String s5[], String s6[], int img[], int symimg[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        data5 = s5;
        data6 = s6;
        images = img;
        symptomimages = symimg;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText(data1[position]);
        holder.textView2.setText(data2[position]);
        holder.textView3.setText(data3[position]);
        holder.textView4.setText(data4[position]);
        holder.imageView.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FragmentListDetail.class);
                i.putExtra("의사이름", data2[position]);
                i.putExtra("병원이름", data3[position]);
                i.putExtra("내 증상", data5[position]);
                i.putExtra("진료비", data6[position]);
                i.putExtra("진료사진", symptomimages[position]);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1, textView2, textView3, textView4;
        ImageView imageView;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.list_animal_name);
            textView2 = itemView.findViewById(R.id.list_doctor_name);
            textView3 = itemView.findViewById(R.id.list_hospital_name);
            textView4 = itemView.findViewById(R.id.list_date);
            imageView = itemView.findViewById(R.id.imageIcon);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
