package com.example.dbcurdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton, viewAllButton, deleteBtn, updateBtn;
    TextView nameTxtView, rollTxtView;
    Switch switchState;
    ListView listViewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        updateBtn = findViewById(R.id.updateBtn);
        viewAllButton =  findViewById(R.id.viewAllBtn);
        nameTxtView = findViewById(R.id.studentTxtView);
        rollTxtView = findViewById(R.id.RollNoTxtView);
        switchState = findViewById(R.id.switchState);
        listViewStudent = findViewById(R.id.studentListView);

        addButton.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;

            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(nameTxtView.getText().toString(), Integer.parseInt(rollTxtView.getText().toString()), switchState.isChecked());
                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                    DBHelper dbHelper  = new DBHelper(MainActivity.this);
                    dbHelper.addStudent(studentModel);
                    Toast.makeText(MainActivity.this, "Added Student", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error while Adding", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBHelper dbHelper  = new DBHelper(MainActivity.this);
                    dbHelper.deleteStudent(rollTxtView.getText().toString());
                    Toast.makeText(MainActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBHelper dbHelper  = new DBHelper(MainActivity.this);
                    dbHelper.updateStudent(rollTxtView.getText().toString(), nameTxtView.getText().toString(), switchState.isChecked());
                    Toast.makeText(MainActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
                        (MainActivity.this, android.R.layout.simple_list_item_1,list);
                listViewStudent.setAdapter(arrayAdapter);
                Toast.makeText(MainActivity.this, "List Refreshed", Toast.LENGTH_SHORT).show();

            }


        });
    }


}