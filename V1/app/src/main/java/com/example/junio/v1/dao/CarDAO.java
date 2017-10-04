package com.example.junio.v1.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.junio.v1.model.Car;
import com.example.junio.v1.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junio on 03/10/17.
 */

public class CarDAO {
    private SQLiteDatabase db;

    public CarDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(Car car) {
        String sql = "insert into car (nro_chassi, id_category, brand, model) values (?, ?, ?, ?)";
        Object bindArgs[] = new Object[]{
                car.getNroChassi(), car.getCategory().getIdCategory(), car.getBrand(), car.getModel()};
        db.execSQL(sql, bindArgs);
    }

    public void delete(Car car) {
        String sql = "delete from car where nro_chassi = ?";
        Object bindArgs[] = new Object[]{car.getNroChassi()};
        db.execSQL(sql, bindArgs);
    }

    public void delete(Integer nroChassi) {
        String sql = "delete from car where nro_chassi = ?";
        Object bindArgs[] = new Object[]{nroChassi};
        db.execSQL(sql, bindArgs);
    }

    public void update(Car car) {
        String sql = "update carro set id_category = ?, brand = ? , model = ? where nro_chassi = ?";
        Object bindArgs[] = new Object[]{
                car.getCategory().getIdCategory(),
                car.getBrand(),
                car.getModel(),
                car.getNroChassi()};
        db.execSQL(sql, bindArgs);
    }

    public List<Car> listAll() {
        List<Car> cars = new ArrayList<>();
        Cursor cursor = db.rawQuery("select car.nro_chassi, car.brand, car.model, category.id_category, category.name" +
                " from car inner join category on car.id_category = category.id_category", null);
//        Cursor cursor = db.query("car", new String[]{"nro_chassi", "id_category", "brand", "model"},
//                null, null, null, null, "nro_chassi");

        while (cursor != null && cursor.moveToNext()) {
            Car car = new Car();
            Category category = new Category();

            car.setNroChassi(cursor.getInt(0));
            car.setBrand(cursor.getString(1));
            car.setModel(cursor.getString(2));

            category.setIdCategory(cursor.getInt(3));
            category.setName(cursor.getString(4));

            car.setCategory(category);

            cars.add(car);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return cars;
    }

    public Car getById(Integer nroChassi) {
        Car car = null;
        Cursor cursor = db.query("car", new String[]{"nro_chassi", "id_category", "brand", "model"},
                "nro_chassi=" + nroChassi, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            car = new Car();
            Category category = new Category();

            car.setNroChassi(cursor.getInt(0));
            car.setBrand(cursor.getString(1));
            car.setModel(cursor.getString(2));

            category.setIdCategory(cursor.getInt(3));
            category.setName(cursor.getString(4));

            car.setCategory(category);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return car;
    }
}
