package com.aysimasavas.dailyapplication.uı;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aysimasavas.dailyapplication.R;

public class historyFragment extends Fragment {

    TextView textView;
    Button button;
    String note;

    public historyFragment(String note) {
        this.note = note;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_history, container, false);
        TextView history=v.findViewById(R.id.history);
        history.setText(note);

        textView=v.findViewById(R.id.history);
        button=v.findViewById(R.id.buttonBack);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack("note", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        return v;



    }
}