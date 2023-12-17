package com.example.career;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CourseAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private DatabaseReference database;
    private FirebaseUser user;
    List<Course> courseList;
    private DatabaseReference userDatabase;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.home_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        user= FirebaseAuth.getInstance().getCurrentUser();
        database= FirebaseDatabase.getInstance().getReference();
        userDatabase=database.child("pinned").child(user.getUid());
        getCourseList();
        courseAdapter = new CourseAdapter(courseList, this);
        recyclerView.setAdapter(courseAdapter);
        return view;
    }
    private void getCourseList() {
        // Add sample courses
        courseList= new ArrayList<>();
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String courseId = dataSnapshot.getKey();
                    String standard=dataSnapshot.getValue(String.class);
                    fetchUserCourse(courseId,standard);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void fetchUserCourse(String courseId,String standard) {
        DatabaseReference courseDatabase=database.child("courses/"+standard).child(courseId);
        courseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Course course=snapshot.getValue(Course.class);
                    Log.i("course pinned name",course.getName());
                    courseList.add(course);

                }

                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(Course course) {
        Intent intent=new Intent(getActivity(),CourseViewActivity.class);
        startActivity(intent);
        Toast.makeText(getActivity(), "Viewing: " + course.getName(), Toast.LENGTH_SHORT).show();

    }

}
