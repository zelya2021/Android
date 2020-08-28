package com.example.peoplecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.peoplecrud.adapters.PeopleAdapter;
import com.example.peoplecrud.models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvPeople;
    Button btnAddNewPerson;
    ArrayList<Person> people = new ArrayList<>();
    {
        for (int i = 1; i <= 10; i++) {
            people.add(new Person(i, String.format("Name #%s", i), String.format("LastName #%s", i), String.format("Position #%s", i)));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPeople = findViewById(R.id.lvPeople);
        PeopleAdapter peopleAdapter = new PeopleAdapter(this, R.layout.people_layout, people);
        lvPeople.setAdapter(peopleAdapter);
        btnAddNewPerson=findViewById(R.id.btnAddNewPerson);

        btnAddNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                //запуск нового Activity
                startActivity(intent);
            }
        });
    }
}
