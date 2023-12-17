package com.example.career;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourse extends AppCompatActivity {
    EditText courseName,courseDescription,courseLink;
    Button addCourse;
    DatabaseReference courseDatabase;
    String myStandard="10th";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseName=findViewById(R.id.editTextCourseName);
        courseDescription=findViewById(R.id.editTextCourseDescription);
        courseLink=findViewById(R.id.editTextCourseLink);
        addCourse=findViewById(R.id.add_course_btn);
        spinner=findViewById(R.id.spinnerEducationLevel);
        String [] standards={"10th","12th","grad"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,standards);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        myStandard="10th";
                        break;
                    case 1:
                        myStandard="12th";
                        break;
                    case 2:
                        myStandard="grad";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        courseDatabase= FirebaseDatabase.getInstance().getReference("courses");
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseName.getText().toString().equals("")||
                        courseLink.getText().toString().equals("")||
                courseDescription.getText().toString().equals("")){

                }
                else {

                    String courseIdKey = courseDatabase.push().getKey();
                    Course course = new Course(myStandard,courseIdKey, courseName.getText().toString(), courseDescription.getText().toString(),
                            courseLink.getText().toString());
                    courseDatabase.child(myStandard).child(courseIdKey).setValue(course);
                    finish();

                }

            }
        });
    }
}