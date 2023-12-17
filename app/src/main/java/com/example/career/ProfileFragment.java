package com.example.career;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    Button signOut;
    TextView email;
    FirebaseUser user;
    Button addcourse;

    public ProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        signOut=view.findViewById(R.id.signout);
        addcourse=view.findViewById(R.id.addcourse);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        email=view.findViewById(R.id.user_email);
        email.setText(user.getEmail());
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent=new Intent(getContext(), login.class);
                getActivity().startActivity(intent);
                getActivity().finish();

            }
        });
        checkAdmin();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        reference.child("roles").child(user.getUid()).setValue("admin");
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AddCourse.class);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).commit();
                getActivity().startActivity(intent);

            }
        });

        return view;
    }
    private void checkAdmin(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("roles");
        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.getValue(String.class).equals("admin")){
                        Log.i("course visible",snapshot.getValue(String.class));
                        addcourse.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
