package com.example.career;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  {
    FirebaseAuth auth;
//    Button button;
    FirebaseUser user;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


       //MAIN MENU//



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.rateus) {
            showToast("Rateus Clicked");
            return true;
        } else if (itemId == R.id.about) {
            showToast("About Clicked");
            return true;
        } else if (itemId == R.id.login) {
            showToast("Log In Clicked");
            return true;
        } else if (itemId == R.id.profile1) {
            showToast("Profile Clicked");
            return true;
        } else if (itemId == R.id.settings) {
            showToast("settings clicked");
            onClick();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(){
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i("gfh","shaaa");
                Fragment fragment = null;
                int itemId=item.getItemId();
                if(R.id.home==itemId){
                    fragment=new HomeFragment();
                } else if (R.id.dashboard==itemId) {
                    fragment = new DashboardFragment();
                } else if (R.id.profile==itemId) {
                    fragment = new ProfileFragment();
                }

                return loadFragment(fragment);
            }
        });

        //FIREBASE//

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        // textView=findViewById(R.id.user_details);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }
    }


    public boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;

    }
}