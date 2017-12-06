package com.example.junio.v1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.junio.v1.DialogUtil;
import com.example.junio.v1.R;
import com.example.junio.v1.Service.ServiceInsertCategory;
import com.example.junio.v1.db.DataBase;
import com.example.junio.v1.model.Category;
import com.google.gson.Gson;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junio on 03/10/17.
 */

public class RegisterCategory extends AppCompatActivity implements ServiceInsertCategory.OnListenerInsertCategory {
    @BindView(R.id.etId)
    EditText etId;
    @BindView(R.id.etName)
    EditText etName;

    private DataBase dataBase;
    private ServiceInsertCategory serviceInsertCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register_category);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        dataBase = new DataBase(this);
        serviceInsertCategory = new ServiceInsertCategory(this);
    }

    private void saveCategory() {
//        SQLiteDatabase db = null;
//        try {
//            db = dataBase.getWritableDatabase();
//
//            CategoryDAO categoryDAO = new CategoryDAO(db);
//            categoryDAO.insert(getPayloadRegister());
//
//            DialogUtil.showMessage(RegisterCategory.this, "OK!", "Salvou!");
//            clean();
//
//        } catch (Exception ex) {
//            DialogUtil.showMessage(RegisterCategory.this, "ERRO!!!", ex.getMessage());
//
//        } finally {
//            if (db != null && db.isOpen()) {
//                db.close();
//            }
//        }

        serviceInsertCategory.execute("http://192.168.1.11:8080/categoria/", new Gson().toJson(getPayloadRegister()));
    }


    private Category getPayloadRegister() {
        Category category = new Category();

        category.setIdCategory(Integer.parseInt(etId.getText().toString()));
        category.setName(etName.getText().toString());
        category.setPrice(new BigDecimal(0));

        return category;
    }

    private void clean() {
        etId.setText("");
        etName.setText("");
    }

    @OnClick(R.id.btSave)
    public void onClickSaveCategory() {
        saveCategory();
    }

    @OnClick(R.id.btBack)
    public void onClickBtNewCar() {
        finish();
    }

    @Override
    public void onInsertCategorySuccess() {
        DialogUtil.showMessage(RegisterCategory.this, "OK!", "Salvou!");
        clean();
    }

    @Override
    public void onInsertCategoryError() {
        DialogUtil.showMessage(RegisterCategory.this, "Ops...", "Tente novamente mais tarde!");
    }
}
