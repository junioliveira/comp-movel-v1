package com.example.junio.v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junio.v1.R;
import com.example.junio.v1.model.Car;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junio on 04/10/17.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<Car> cars;

    public CarAdapter(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(cars.get(position));
    }

    @Override
    public int getItemCount() {
        return cars != null ? cars.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        @BindView(R.id.tvNumberChassi)
        TextView tvNumberChassi;
        @BindView(R.id.tvBrand)
        TextView tvBrand;
        @BindView(R.id.tvModel)
        TextView tvModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Car car) {
            if (car != null) {
                String category = (car.getCategory() != null && car.getCategory().getName() != null) ? car.getCategory().getName().toString() : "";
                tvCategory.setText("Categoria: " + category);

                tvNumberChassi.setText("Nro Chassi: " + car.getNroChassi());
                tvBrand.setText("Marca: " + ((car.getBrand() != null) ? car.getBrand().toString() : ""));
                tvModel.setText("Modelo: " + ((car.getModel() != null) ? car.getModel().toString(): ""));
            }
        }
    }
}
