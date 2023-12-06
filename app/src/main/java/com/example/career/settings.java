package com.example.career;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class settings extends AppCompatActivity {
    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LV =findViewById(R.id.List_View);
        ArrayList<String> item = new ArrayList<>();
        item.add("Font");
        item.add("about");

        ArrayAdapter<String> pushitem =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,item);
        LV.setAdapter(pushitem);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(settings.this);
                    builder.setTitle("Change Font Colour")
                            .setMessage("Which Font do you want")
                            .setCancelable(false)
                            .setPositiveButton("Red", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton("Green", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            })
                            .setNeutralButton("Black", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }


            }
        });


    }

}