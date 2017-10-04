package com.example.junio.v1.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.junio.v1.DialogUtil;
import com.example.junio.v1.R;
import com.example.junio.v1.adapter.CategoryAdapter;
import com.example.junio.v1.dao.CategoryDAO;
import com.example.junio.v1.db.DataBase;
import com.example.junio.v1.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junio on 04/10/17.
 */

public class AllCategoryActivity extends AppCompatActivity {
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;

    private DataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_all_category);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        dataBase = new DataBase(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getAllCars());

        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(categoryAdapter);
    }

    private List<Category> getAllCars() {
        SQLiteDatabase db = null;
        try {
            db = dataBase.getWritableDatabase();

            CategoryDAO categoryDAO = new CategoryDAO(db);
            return categoryDAO.listAll();
        } catch (Exception ex) {
            DialogUtil.showMessage(AllCategoryActivity.this, "ERRO!!!", ex.getMessage());

        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return null;
    }


    @OnClick(R.id.btBack)
    public void onClickBtNewCar() {
        finish();
    }
}
