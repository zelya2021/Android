package com.example.peoplecrud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.peoplecrud.models.Person;

import java.util.List;

public class PeopleAdapter extends ArrayAdapter<Person> {
    private List<Person> informationList;
    private int resource;
    private LayoutInflater inflater;
    private Context ctx;

    public PeopleAdapter(Context  context, int resource, List<Person> informationList) {
        super(context, resource, informationList);
        ctx = context;
        this.informationList = informationList;               //список
        this.resource = resource;       //view(layout)
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //создание представления для одного объекта
        View view = inflater.inflate(resource, parent, false);

        return view;
    }
    public interface OnViewClickListener {
        void view(Person person);
    }
}
