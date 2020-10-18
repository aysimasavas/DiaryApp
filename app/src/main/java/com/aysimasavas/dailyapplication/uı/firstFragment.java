package com.aysimasavas.dailyapplication.uı;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;

import com.aysimasavas.dailyapplication.DailyAdapter;
import com.aysimasavas.dailyapplication.DailyModel;
import com.aysimasavas.dailyapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class firstFragment extends Fragment {

    FloatingActionButton fab;

    private ArrayList<DailyModel> dailyModelList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    DailyAdapter dailyAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_first,container,false);

        sharedPreferences=getActivity().getSharedPreferences("DAILY_APP", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        gson=new Gson();
        fab=v.findViewById(R.id.floatingActionButton);

        dailyModelList=getList();

        RecyclerView recyclerView=v.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(layoutManager);
        dailyAdapter=new DailyAdapter(dailyModelList,container,getActivity());
        recyclerView.setAdapter(dailyAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragment secondFragment=new secondFragment();

                FragmentManager fragmentManager=getFragmentManager();

                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                fragmentTransaction.replace(container.getId(),secondFragment).addToBackStack("first").commit();

            }
        });

        return v;
    }

    private ArrayList<DailyModel> getList()
    {
        String file=sharedPreferences.getString("LIST","");

        if(file.equals(""))
        {
            return new ArrayList<DailyModel>();
        }

        Type type =  new TypeToken<ArrayList<DailyModel>>(){}.getType();
        return gson.fromJson(file,type);

    }
    private void setList(ArrayList<DailyModel> dailyModelList)
    {
        String file=gson.toJson(dailyModelList);
        editor.putString("LIST",file);
        editor.apply();
    }



}