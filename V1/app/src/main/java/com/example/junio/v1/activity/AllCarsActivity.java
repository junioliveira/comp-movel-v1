package com.example.junio.v1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.junio.v1.R;
import com.example.junio.v1.Service.ServiceAllCar;
import com.example.junio.v1.adapter.CarAdapter;
import com.example.junio.v1.db.DataBase;
import com.example.junio.v1.model.Car;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junio on 03/10/17.
 */

public class AllCarsActivity extends AppCompatActivity implements ServiceAllCar.OnListenerGetAllCars {
    @BindView(R.id.rvCars)
    RecyclerView rvCars;

    private DataBase dataBase;
    private ServiceAllCar serviceCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_all_cars);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        dataBase = new DataBase(this);
        serviceCar = new ServiceAllCar(this);

        serviceCar.execute("http://192.168.1.11:8080/veiculo/");
    }

    private void setup(List<Car> cars){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        CarAdapter carAdapter = new CarAdapter(cars);

        rvCars.setLayoutManager(layoutManager);
        rvCars.setAdapter(carAdapter);
    }

//    private List<Car> getAllCars() {
////        SQLiteDatabase db = null;
////        try {
////            db = dataBase.getWritableDatabase();
////
////            CarDAO carDAO = new CarDAO(db);
////            return carDAO.listAll();
////        } catch (Exception ex) {
////            DialogUtil.showMessage(AllCarsActivity.this, "ERRO!!!", ex.getMessage());
////
////        } finally {
////            if (db != null && db.isOpen()) {
////                db.close();
////            }
////        }
//
//        return null;
//    }

    @OnClick(R.id.btBack)
    public void onClickBtNewCar() {
        finish();
    }

    @Override
    public void onGetAllCarsSuccess(List<Car> cars) {
        setup(cars);
    }

    @Override
    public void onGetAllCarsError() {
        // TODO: 06/12/17
    }
}
