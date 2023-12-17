package com.example.career;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courseList;
    private OnItemClickListener listener;
    Context context;

    public CourseAdapter(List<Course> courseList, OnItemClickListener listener) {
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.textCourseName.setText(course.getName());
        holder.textCourseDescription.setText(course.getDescription());
        holder.btnView.setOnClickListener(v -> listener.onItemClick(course));
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        holder.pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean[] proceed = new boolean[1];
                showAlertDialog(context, userClickedYes -> {
                    // Handle user's response
                    proceed[0]=userClickedYes;
                    if(proceed[0]){
                        reference.child("pinned/"+user.getUid()).child(course.getCourseId()).setValue(course.getStandard());
                        Toast.makeText(context, "Pinned Course"+course.getName(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        reference.child("pinned/"+user.getUid()).child(course.getCourseId()).removeValue();
                        Toast.makeText(context, "Removed from pin"+course.getName(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    public interface AlertDialogCallback {
        void onUserResponse(boolean userClickedYes);
    }
    public void showAlertDialog(Context context, final AlertDialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmation");
        builder.setMessage("Do you want to pin or unpin the Course?");
        builder.setPositiveButton("Pin", (dialog, which) -> {
            if (callback != null) {
                callback.onUserResponse(true);
            }
            dialog.dismiss();
        });
        builder.setNegativeButton("Unpin", (dialog, which) -> {
            if (callback != null) {
                callback.onUserResponse(false);
            }
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textCourseName;
        public TextView textCourseDescription;
        public Button btnView;
        public ImageView pin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.textCourseName);
            textCourseDescription = itemView.findViewById(R.id.textCourseDescription);
            btnView = itemView.findViewById(R.id.btnView);
            pin = itemView.findViewById(R.id.logoPin);
        }
    }

}

