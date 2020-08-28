package com.example.peoplecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peoplecrud.models.Person;

public class AddPerson extends AppCompatActivity {
    EditText tvNameAdd, tvLastNameAdd, tvPositionAdd;
    Button btnAddPersonNewAct,btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        tvNameAdd=findViewById(R.id.tvName);
        tvLastNameAdd=findViewById(R.id.tvLastName);
        tvPositionAdd=findViewById(R.id.tvPosition);
        btnAddPersonNewAct=findViewById(R.id.btnAddPersonNewAct);
        btnClose=findViewById(R.id.btnClose);

        btnAddPersonNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Person.class.getSimpleName(), new Person(tvNameAdd.getText().toString(), tvLastNameAdd.getText().toString(), tvPositionAdd.getText().toString()));
                //intent.putExtra(KEY_RESULT_STR, "Okey!");

                setResult(RESULT_OK, intent);
                //закрытие текущего Activity
                finish();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
