package com.example.junio.v1.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.junio.v1.DialogUtil;
import com.example.junio.v1.R;
import com.example.junio.v1.dao.CategoryDAO;
import com.example.junio.v1.db.DataBase;
import com.example.junio.v1.model.Category;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junio on 03/10/17.
 */

public class RegisterCategory extends AppCompatActivity {
    @BindView(R.id.etId)
    EditText etId;
    @BindView(R.id.etName)
    EditText etName;

    private DataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register_category);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        dataBase = new DataBase(this);
    }

    private void saveCategory() {
        SQLiteDatabase db = null;
        try {
            db = dataBase.getWritableDatabase();

            CategoryDAO categoryDAO = new CategoryDAO(db);
            categoryDAO.insert(getPayloadRegister());

            DialogUtil.showMessage(RegisterCategory.this, "OK!", "Salvou!");

        } catch (Exception ex) {
            DialogUtil.showMessage(RegisterCategory.this, "ERRO!!!", ex.getMessage());

        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    @OnClick(R.id.btSave)
    public void onClickSaveCategory() {
        saveCategory();
    }

    private Category getPayloadRegister() {
        Category category = new Category();
        category.setIdCategory(Integer.parseInt(etId.getText().toString()));
        category.setName(etName.getText().toString());

        return category;
    }

    @OnClick(R.id.btBack)
    public void onClickBtNewCar() {
        finish();
    }
}
