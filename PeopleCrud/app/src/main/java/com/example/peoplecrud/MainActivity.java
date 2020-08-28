package com.example.peoplecrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.peoplecrud.adapters.PeopleAdapter;
import com.example.peoplecrud.models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvPeople;
    Button btnAddNewPerson;
    private final static int Activity_Add_Person = 1;
    ArrayList<Person> people =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPeople = findViewById(R.id.lvPeople);
        btnAddNewPerson=findViewById(R.id.btnAddNewPerson);

        btnAddNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                //запуск нового Activity
                startActivityForResult(intent, Activity_Add_Person);
                //startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //определение от какого Activity пришел результат
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity_Add_Person) {
            if (resultCode == RESULT_OK) {
                //получение переданных данных
                Person person = (Person)data.getExtras().getSerializable(Person.class.getSimpleName());
                people.add(person);
                PeopleAdapter peopleAdapter = new PeopleAdapter(this, R.layout.people_layout, people);
                lvPeople.setAdapter(peopleAdapter);
            }
        }

    }
}
