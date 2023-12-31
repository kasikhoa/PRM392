package com.example.customlistview_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private Context mContext;
    private int mResource;

    public FruitAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Fruit> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView txtName = convertView.findViewById(R.id.txtName);

        TextView txtDes = convertView.findViewById(R.id.txtDes);

        imageView.setImageResource(getItem(position).getImage());

        txtName.setText((getItem(position).getName()));

        txtDes.setText((getItem(position).getDesc()));

        return convertView;
    }
}
