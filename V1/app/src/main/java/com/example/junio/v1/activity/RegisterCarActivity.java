package com.example.junio.v1.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.junio.v1.DialogUtil;
import com.example.junio.v1.R;
import com.example.junio.v1.dao.CarDAO;
import com.example.junio.v1.dao.CategoryDAO;
import com.example.junio.v1.db.DataBase;
import com.example.junio.v1.model.Car;
import com.example.junio.v1.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junio on 03/10/17.
 */

public class RegisterCarActivity extends AppCompatActivity {
    @BindView(R.id.spCategory)
    AppCompatSpinner spCategory;
    @BindView(R.id.etNumberChassi)
    EditText etNumberChassi;
    @BindView(R.id.etBrand)
    EditText etBrand;
    @BindView(R.id.etModel)
    EditText etModel;

    private DataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register_car);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        dataBase = new DataBase(this);

        setupSpinner();
    }

    private void setupSpinner() {
        List<Category> categories = getCategories();

        if (categories != null) {
            ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
            spCategory.setAdapter(categoryArrayAdapter);
        }
    }

    private List<Category> getCategories() {
        SQLiteDatabase db = null;
        try {
            db = dataBase.getWritableDatabase();

            CategoryDAO categoryDAO = new CategoryDAO(db);
            return categoryDAO.listAll();
        } catch (Exception ex) {
            DialogUtil.showMessage(RegisterCarActivity.this, "ERRO!!!", ex.getMessage());

        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return null;
    }

    private void saveCar() {
        SQLiteDatabase db = null;
        try {
            db = dataBase.getWritableDatabase();

            CarDAO carDAO = new CarDAO(db);
            carDAO.insert(getPayloadRegister());
            DialogUtil.showMessage(RegisterCarActivity.this, "OK!", "Salvou!");

        } catch (Exception ex) {
            DialogUtil.showMessage(RegisterCarActivity.this, "ERRO!!!", ex.getMessage());

        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    private Car getPayloadRegister() {
        Car car = new Car();
        car.setNroChassi(Integer.parseInt(etNumberChassi.getText().toString()));
        car.setIdCategory(1);
        car.setBrand(etBrand.getText().toString());
        car.setModel(etModel.getText().toString());

        return car;
    }

    @OnClick(R.id.btSave)
    public void onClickSave() {
        saveCar();
    }

    @OnClick(R.id.btBack)
    public void onClickBtNewCar() {
        finish();
    }
}
