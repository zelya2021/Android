package com.example.listcars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listcars.models.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static int ITEM_NOT_SELECTED = -1;
    ListView listCars;
    EditText editMark,editModel,editPrice;
    Button btnAdd,btnUpdate,btnDelete;
    List<Car> cars = new ArrayList<>();
    {
        cars.add(new Car("BMW","BMW 2 Series Gran Coupe 2020","700000"));

    }
    int selectedItemIdx = -1;       //индекс выбранного элемента в списке
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCars = findViewById(R.id.listCars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        editMark = findViewById(R.id.editMark);
        editModel = findViewById(R.id.editModel);
        editPrice = findViewById(R.id.editPrice);

        final ArrayAdapter<Car> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, cars);
        listCars.setAdapter(adapter);

        //выбор элемента в списке
        listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //подстановка текста в 'EditText' для редактирования
                editMark.setText(adapter.getItem(position).getMark());
                editModel.setText(adapter.getItem(position).getModel());
                editPrice.setText((String)adapter.getItem(position).getPrice());
                //запоминае позиции выбранного элемента в списке
                selectedItemIdx = position;
                Toast.makeText(MainActivity.this, String.format("You selected '%s'", cars.get(position)), Toast.LENGTH_SHORT).show();
            }
        });
        //клик по кнопке 'Add'
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //получение введенного текста в поле ввода
                String mark = editMark.getText().toString();
                String model = editModel.getText().toString();
                String price = editPrice.getText().toString();
                if (!model.isEmpty() && !cars.contains(model)&&!mark.isEmpty() && !cars.contains(mark)&&!price.isEmpty() && !cars.contains(price)) {
                    //добавление нового элемента
                    adapter.add(new Car(mark,model,price));
                    adapter.notifyDataSetChanged();
                    editMark.setText("");
                    editPrice.setText("");
                    editModel.setText("");
                }
            }
        });
        //клик по кнопке 'Update'
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //получение введенного текста в поле ввода
                String mark = editMark.getText().toString();
                String model = editModel.getText().toString();
                String price = editPrice.getText().toString();
                if (!mark.isEmpty() && selectedItemIdx != ITEM_NOT_SELECTED&&!model.isEmpty()&&!price.isEmpty()){
                    //сохранение изменений
                    adapter.remove(adapter.getItem(selectedItemIdx));
                    adapter.insert(new Car(mark,model,price), selectedItemIdx);
                    adapter.notifyDataSetChanged();
                    editMark.setText("");
                    editPrice.setText("");
                    editModel.setText("");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mark = editMark.getText().toString();
                String model = editModel.getText().toString();
                String price = editPrice.getText().toString();
                for (Car item: cars) {
                    if(item.getMark().equals(mark)&&item.getModel().equals(model)&&item.getPrice().equals(price))
                    {
                        adapter.remove(adapter.getItem(selectedItemIdx));
                        adapter.notifyDataSetChanged();
                        editMark.setText("");
                        editPrice.setText("");
                        editModel.setText("");
                    }
                }
            }
        });
    }
}
