package com.rishav.retrofitexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishav.retrofitexample.R;
import com.rishav.retrofitexample.model.SpiritualTeacher;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context c;
    SpiritualTeacher[] teachers;
    public ArrayList<SpiritualTeacher> checkedTeachers = new ArrayList<>();

    public MyAdapter(Context c, SpiritualTeacher[] teachers) {
        this.c = c;
        this.teachers = teachers;
    }

    //VIEWHOLDER IS INITIALIZED
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo, null);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    //DATA IS BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final SpiritualTeacher teacher = teachers[position];
        holder.nameTxt.setText(teacher.getName());
        holder.posTxt.setText(teacher.getQuote());
        holder.myCheckBox.setChecked(teacher.isSelected());
        holder.img.setImageResource(teacher.getImage());

        holder.setItemClickListener(new MyHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox myCheckBox = (CheckBox) v;
                SpiritualTeacher currentTeacher = teachers[pos];

                if (myCheckBox.isChecked()) {
                    currentTeacher.setSelected(true);
                    checkedTeachers.add(currentTeacher);
                } else if (!myCheckBox.isChecked()) {
                    currentTeacher.setSelected(false);
                    checkedTeachers.remove(currentTeacher);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teachers.length;
    }

    //Viewholder
    static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView nameTxt, posTxt;
        CheckBox myCheckBox;

        ItemClickListener itemClickListener;

        public MyHolder(View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameTextView);
            posTxt = itemView.findViewById(R.id.descritionTextView);
            img = itemView.findViewById(R.id.teacherImageView);
            myCheckBox = itemView.findViewById(R.id.myCheckBox);

            myCheckBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }

        public interface ItemClickListener {

            void onItemClick(View v, int pos);
        }
    }

}