package com.example.junio.v1.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.junio.v1.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junio on 04/10/17.
 */

public class CategoryDAO {
    private SQLiteDatabase db;

    public CategoryDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(Category category) {
        String sql = "insert into category (id_category, name) values (?, ?)";
        Object bindArgs[] = new Object[]{category.getIdCategory(), category.getName()};
        db.execSQL(sql, bindArgs);
    }

    public List<Category> listAll() {
        List<Category> categories = new ArrayList<>();
        Cursor cursor = db.query("category", new String[]{"id_category", "name"},
                null, null, null, null, "id_category");

        while (cursor != null && cursor.moveToNext()) {
            Category category = new Category();
            category.setIdCategory(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return categories;
    }
}
