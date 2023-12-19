package com.example.uf2_pt1_m08_maurocitt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uf2_pt1_m08_maurocitt.model.Coche;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Coche> coches;

    public EditText nombre;
    public EditText apellido;
    public EditText telefono;
    public EditText marca;
    public EditText modelo;
    public EditText matricula;

    private DataManager dataManager = new DataManager(MainActivity.this);

    public Button btnAdd;
    public Button btnConsulta;
    public Button btnLista;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnConsulta = findViewById(R.id.btnConsulta);
        btnLista = findViewById(R.id.btnLista);
        btnAdd.setOnClickListener(this);
        btnConsulta.setOnClickListener(this);
        btnLista.setOnClickListener(this);

        nombre = findViewById(R.id.nom);
        apellido = findViewById(R.id.lastNom);
        telefono = findViewById(R.id.telefono);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        matricula = findViewById(R.id.matricula);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            System.out.println(matricula.getText().toString());
            if (matricula.getText().toString().equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Introduce una matricula", Toast.LENGTH_SHORT);
                toast.show();
            } else if (telefono.getText().toString().length() == 9) {
                try {
                    int parsedTelefono = Integer.parseInt(telefono.getText().toString());
                    Coche coche = dataManager.consultaCoche(matricula.getText().toString());
                    if (coche != null) {
                        Coche coche2 = new Coche(
                                nombre.getText().toString(),
                                apellido.getText().toString(),
                                telefono.getText().toString(),
                                marca.getText().toString(),
                                modelo.getText().toString(),
                                matricula.getText().toString()
                        );
                        dataManager.updateCoche(coche2);
                        Toast toast = Toast.makeText(getApplicationContext(), "Coche modificado", Toast.LENGTH_SHORT);
                        toast.show();

                    } else {
                        Coche coche2 = new Coche();
                        coche2.setNombre(nombre.getText().toString());
                        coche2.setApellido(apellido.getText().toString());
                        coche2.setTelefono(telefono.getText().toString());
                        coche2.setMarca(marca.getText().toString());
                        coche2.setModelo(modelo.getText().toString());
                        coche2.setMatricula(matricula.getText().toString());
                        dataManager.addCoches(coche2);
                        Toast toast = Toast.makeText(getApplicationContext(), "Coche añadido", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } catch (NumberFormatException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Introduce un telefono valido", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Introduce un telefono de 9 digitos", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else if(v.getId() == R.id.btnConsulta){
            Coche coche = dataManager.consultaCoche(matricula.getText().toString());
            if (coche != null) {
                nombre.setText(coche.getNombre());
                apellido.setText(coche.getApellido());
                telefono.setText(coche.getTelefono());
                marca.setText(coche.getMarca());
                modelo.setText(coche.getModelo());
                matricula.setText(coche.getMatricula()
                );
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "No existe el coche", Toast.LENGTH_SHORT);
            }
        } else {
                Intent intent = new Intent(this, ListaCoches.class);
                startActivity(intent);
        }
    }
}
