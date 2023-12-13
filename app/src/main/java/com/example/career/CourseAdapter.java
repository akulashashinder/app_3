package com.example.career;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courseList;
    private OnItemClickListener listener;

    public CourseAdapter(List<Course> courseList, OnItemClickListener listener) {
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.textCourseName.setText(course.getName());
        holder.textCourseDescription.setText(course.getDescription());
        holder.btnView.setOnClickListener(v -> listener.onItemClick(course));
//        holder.btnping.setOnClickListener(v -> listener.onItemClick());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textCourseName;
        public TextView textCourseDescription;
        public Button btnView;
        public Button btnping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.textCourseName);
            textCourseDescription = itemView.findViewById(R.id.textCourseDescription);
            btnView = itemView.findViewById(R.id.btnView);
            btnping = itemView.findViewById(R.id.btnping);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }
}

