package com.example.peoplecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peoplecrud.models.Person;

public class UpdatePerson extends AppCompatActivity {
    TextView tvNamePerson, tvLastNamePerson, tvPositionPerson;
    Button btnUpdate, btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);

        tvNamePerson = findViewById(R.id.tvNamePerson);
        tvLastNamePerson = findViewById(R.id.tvLastNamePerson);
        tvPositionPerson = findViewById(R.id.tvPositionPerson);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnClose = findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Person.class.getSimpleName(),
                        new Person(
                                tvNamePerson.getText().toString(),
                                tvLastNamePerson.getText().toString(),
                                tvPositionPerson.getText().toString()));

                setResult(RESULT_OK, intent);
                //закрытие текущего Activity
                finish();
            }
        });
        //получение ссылки на Intent, который запустил это Activity
        Intent intent = getIntent();
        //получение переданных данных
        Person person = (Person)intent.getExtras().getSerializable(Person.class.getSimpleName());
        tvNamePerson.setText(person.getName());
        tvLastNamePerson.setText(person.getLastName());
        tvPositionPerson.setText(person.getPosition());
    }
}
