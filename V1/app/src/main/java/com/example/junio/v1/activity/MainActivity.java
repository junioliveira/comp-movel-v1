package com.example.junio.v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.junio.v1.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btNewCar)
    public void onClickBtNewCar() {
        Intent intent = new Intent(MainActivity.this, RegisterCarActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btNewCategory)
    public void onClickBtNewCategory() {
        Intent intent = new Intent(MainActivity.this, RegisterCategory.class);
        startActivity(intent);
    }

    @OnClick(R.id.btListCars)
    public void onClickBtListCars() {
        Intent intent = new Intent(MainActivity.this, AllCarsActivity.class);
        startActivity(intent);
    }
}
