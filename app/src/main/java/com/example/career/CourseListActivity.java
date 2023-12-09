package com.example.career;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        // Assuming you have a list of courses
        List<Course> courseList = getCourseList();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter(courseList, this);
        recyclerView.setAdapter(courseAdapter);
    }

    private List<Course> getCourseList() {
        // Add sample courses
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Course 1", "Description 1"));
        courses.add(new Course("Course 2", "Description 2"));
        // Add more courses as needed
        return courses;
    }

    @Override
    public void onItemClick(Course course) {
        // Handle the "View" button click for the selected course
        Intent intent=new Intent(CourseListActivity.this,CourseViewActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Viewing: " + course.getName(), Toast.LENGTH_SHORT).show();
        // Add your logic to navigate to the course details page or perform any action
    }
}