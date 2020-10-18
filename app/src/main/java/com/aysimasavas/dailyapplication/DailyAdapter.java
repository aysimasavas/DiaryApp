package com.aysimasavas.dailyapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.aysimasavas.dailyapplication.uı.firstFragment;
import com.aysimasavas.dailyapplication.uı.historyFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ListHolder> {

    private ArrayList<DailyModel> dailyModelArrayList;
    private ViewGroup container;
    FragmentActivity fragmentActivity;

    public DailyAdapter(ArrayList<DailyModel> dailyModelArrayList, ViewGroup container,FragmentActivity fragmentActivity) {
        this.dailyModelArrayList = dailyModelArrayList;
        this.container = container;
        this.fragmentActivity=fragmentActivity;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View customView=layoutInflater.inflate(R.layout.recycler_row,parent,false);


        return new ListHolder(customView);
    }


    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, final int position) {

        SimpleDateFormat simpleDateFormat;
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy EEEE");

        final TextView rowTextView = holder.itemView.findViewById(R.id.rowTextView);
        rowTextView.setText(simpleDateFormat.format(dailyModelArrayList.get(position).getDate()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText editText=v.findViewById(R.id.editTextTextMultiLine);

                historyFragment historyFragment=new historyFragment(dailyModelArrayList.get(position).getNote());
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(container.getId(),new historyFragment(dailyModelArrayList.get(position).getNote())).addToBackStack("note").commit();


            }
        });

    }


    @Override
    public int getItemCount() {
        return dailyModelArrayList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder
    {
        public ListHolder(View itemView)
        {
            super(itemView);
        }
    }


}
