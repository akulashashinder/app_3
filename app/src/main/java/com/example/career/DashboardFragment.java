package com.example.career;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;



public class DashboardFragment extends Fragment {

    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button button1 = view.findViewById(R.id.after_10th);
        Button button2 = view.findViewById(R.id.after_12th);
        Button button3 = view.findViewById(R.id.after_grad);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton1Click();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton2Click();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton3Click();
            }
        });


        return view;
    }

    private void onButton1Click() {
        Intent intent = new Intent(getContext(),CourseListActivity.class);
        getActivity().startActivity(intent);

        // Implement functionality for Button 1
        Toast.makeText(getActivity(), "after 10th clicked", Toast.LENGTH_SHORT).show();
    }

    private void onButton2Click() {

        Intent intent = new Intent(getContext(), CourseListActivity.class);
        getActivity().startActivity(intent);

        // Implement functionality for Button 2
        Toast.makeText(getActivity(), "after 12th Clicked!", Toast.LENGTH_SHORT).show();
    }

    private void onButton3Click() {
        Intent intent = new Intent(getContext(), CourseListActivity.class);
        getActivity().startActivity(intent);
        // Implement functionality for Button 2
        Toast.makeText(getActivity(), "after graduation Clicked!", Toast.LENGTH_SHORT).show();
    }
}
