package com.example.vilmarferreira.triagem_avc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vilmarferreira.triagem_avc.R;
import com.example.vilmarferreira.triagem_avc.model.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vilmarferreira on 26/10/2017.
 */

public class adapter extends BaseAdapter {

    private final ArrayList<model> models;
    private  final Activity activity;

    public adapter(ArrayList<model> models, Activity activity) {
        this.models = models;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View celula, ViewGroup parent) {
        if (celula==null)
        {
            celula = this.activity.getLayoutInflater().inflate(R.layout.celula_adapter, null);
        }
        model copia = models.get(position);


        TextView nome = celula.findViewById(R.id.txtnome);
        TextView latitude= celula.findViewById(R.id.txtLat);
        TextView longitude= celula.findViewById(R.id.txtLong);



        nome.setText(copia.getNome());
        latitude.setText("Latitude: "+copia.getLag());
        longitude.setText("Longitude: "+copia.getLongi());
        return null;
    }

}