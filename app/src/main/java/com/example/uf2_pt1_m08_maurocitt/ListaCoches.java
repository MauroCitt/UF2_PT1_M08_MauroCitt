package com.example.uf2_pt1_m08_maurocitt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uf2_pt1_m08_maurocitt.model.Coche;

import java.util.List;

public class ListaCoches extends AppCompatActivity {

    private List<Coche> coches;
    DataManager dataManager = new DataManager(ListaCoches.this);
    public RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coches);

        coches = dataManager.getAllCoches();
        RecyclerViewApp adapter = new RecyclerViewApp(coches);
        recyclerView = (RecyclerView) findViewById(R.id.listaRecycler);
        recyclerView.setAdapter(adapter);
    }
}