package com.example.peoplecrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.peoplecrud.adapters.PeopleAdapter;
import com.example.peoplecrud.models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvPeople;
    Button btnAddNewPerson,btnViewNewPerson, btnUpdatePerson, btnDeletePerson;
    private int Activity_Add_Person = 0;
    private int Activity_Update_Person = 0;
    ArrayList<Person> people =new ArrayList<>();
    Person curr_person;
    PeopleAdapter peopleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPeople = findViewById(R.id.lvPeople);

        btnAddNewPerson=findViewById(R.id.btnAddNewPerson);
        btnViewNewPerson = findViewById(R.id.btnViewNewPerson);
        btnUpdatePerson = findViewById(R.id.btnUpdatePerson);
        btnDeletePerson = findViewById(R.id.btnDeletePerson);

        people.add(new Person("Masha","Petrovskaya","menager"));

        peopleAdapter = new PeopleAdapter(this, R.layout.people_layout, people);
        lvPeople.setAdapter(peopleAdapter);

        btnAddNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_Add_Person=1;
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
                intent.putExtra(Person.class.getSimpleName(), curr_person);
                startActivity(intent);
            }
        });

        //для передачи в ViewActivity, UpdateActivity
        lvPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                curr_person =new Person(
                        peopleAdapter.getItem(position).getName(),
                        peopleAdapter.getItem(position).getLastName(),
                        peopleAdapter.getItem(position).getPosition()
                );
            }
        });

        btnUpdatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_Update_Person=1;
                Intent intent = new Intent(MainActivity.this, UpdatePerson.class);
                //передача данных
                intent.putExtra(Person.class.getSimpleName(), curr_person);
                startActivityForResult(intent, Activity_Update_Person);
            }
        });

        btnDeletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPerson=people.indexOf(curr_person);
                people.remove(currentPerson);
                lvPeople.setAdapter(peopleAdapter);
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

                lvPeople.setAdapter(peopleAdapter);
            }
        }
        if (requestCode == Activity_Update_Person) {
            if (resultCode == RESULT_OK) {
                //получение переданных данных
                int currentPerson=people.indexOf(curr_person);
                Person person = (Person)data.getExtras().getSerializable(Person.class.getSimpleName());

                people.set(currentPerson,person);

                lvPeople.setAdapter(peopleAdapter);
            }
        }
        Activity_Add_Person=0;
        Activity_Update_Person=0;
    }
}
