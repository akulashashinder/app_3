package com.example.career;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private DatabaseReference database;
    private String standard;
    List<Course> courseList;
    private DatabaseReference standardReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        // Assuming you have a list of courses
        Intent intent=getIntent();
        standard=intent.getStringExtra("standard");
        database=FirebaseDatabase.getInstance().getReference();
        standardReference= FirebaseDatabase.getInstance().getReference("courses/"+standard);
         getCourseList();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter(courseList, this);
        recyclerView.setAdapter(courseAdapter);
    }

    private void getCourseList() {
        // Add sample courses
        courseList= new ArrayList<>();
        standardReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String courseId = dataSnapshot.getKey();
                    fetchUserCourse(courseId);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Add more courses as needed
    }
    private void fetchUserCourse(String courseId) {
        DatabaseReference courseDatabase=database.child("courses/"+standard).child(courseId);
        courseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Course course=snapshot.getValue(Course.class);
                    Log.i("coursename",course.getName());
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
        // Handle the "View" button click for the selected course
        Intent intent=new Intent(CourseListActivity.this,CourseViewActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Viewing: " + course.getName(), Toast.LENGTH_SHORT).show();
    }


}