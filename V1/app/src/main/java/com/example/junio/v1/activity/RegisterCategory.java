package com.example.junio.v1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.junio.v1.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junio on 03/10/17.
 */

public class RegisterCategory extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register_category);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btBack)
    public void onClickBtNewCar() {
        finish();
    }
}