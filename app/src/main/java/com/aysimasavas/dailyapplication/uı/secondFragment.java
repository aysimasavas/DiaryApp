package com.aysimasavas.dailyapplication.uı;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aysimasavas.dailyapplication.DailyModel;
import com.aysimasavas.dailyapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class secondFragment extends Fragment {

    Button button;
    EditText editText;
    Button saveButton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_second,container,false);

        sharedPreferences=getActivity().getSharedPreferences("DAILY_APP", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        gson=new Gson();

        button=v.findViewById(R.id.goBack);
        editText=v.findViewById(R.id.editTextTextMultiLine);
        saveButton=v.findViewById(R.id.saveButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"Not yazınız",Toast.LENGTH_LONG).show();
                    return;
                }
                addNote(editText.getText().toString());
                getActivity().getSupportFragmentManager().popBackStack("first", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack("first", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        return v;
    }

    private void addNote(String note)
    {
        ArrayList<DailyModel> dailyModels=getList();
        dailyModels.add(new DailyModel(note,new Date()));
        setList(dailyModels);

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