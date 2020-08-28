package com.example.peoplecrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.peoplecrud.adapters.PeopleAdapter;
import com.example.peoplecrud.models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvPeople;
    Button btnAddNewPerson,btnViewNewPerson;
    private final static int Activity_Add_Person = 1;
    ArrayList<Person> people =new ArrayList<>();
    Person person;
    PeopleAdapter peopleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPeople = findViewById(R.id.lvPeople);
        btnAddNewPerson=findViewById(R.id.btnAddNewPerson);
        btnViewNewPerson = findViewById(R.id.btnViewNewPerson);

        people.add(new Person("Masha","Petrovskaya","menager"));

        peopleAdapter = new PeopleAdapter(this, R.layout.people_layout, people);
        lvPeople.setAdapter(peopleAdapter);

        btnAddNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                //запуск нового Activity
                startActivityForResult(intent, Activity_Add_Person);
                //startActivity(intent);
            }
        });

        btnViewNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPerson.class);
                //передача данных
                intent.putExtra(Person.class.getSimpleName(), person);
                startActivity(intent);
            }
        });

        lvPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                person=new Person(
                        peopleAdapter.getItem(position).getName(),
                        peopleAdapter.getItem(position).getLastName(),
                        peopleAdapter.getItem(position).getPosition()
                );
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
                //peopleAdapter = new PeopleAdapter(this, R.layout.people_layout, people);
                lvPeople.setAdapter(peopleAdapter);
            }
        }

    }
}
