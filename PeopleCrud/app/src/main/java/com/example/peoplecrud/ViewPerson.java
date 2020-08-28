package com.example.peoplecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peoplecrud.models.Person;

public class ViewPerson extends AppCompatActivity {
    Button btnCloseViewAct;
    TextView tvNameView,tvLastNameView,tvPositionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        btnCloseViewAct = findViewById(R.id.btnUpdate);
        tvNameView = findViewById(R.id.textViewName);
        tvLastNameView = findViewById(R.id.textViewLastName);
        tvPositionView = findViewById(R.id.textViewPosition);

        btnCloseViewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //получение ссылки на Intent, который запустил это Activity
        Intent intent = getIntent();
        //получение переданных данных
        Person person = (Person)intent.getExtras().getSerializable(Person.class.getSimpleName());
        tvNameView.setText(person.getName());
        tvLastNameView.setText(person.getLastName());
        tvPositionView.setText(person.getPosition());
    }
}
