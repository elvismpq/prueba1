package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Crear extends AppCompatActivity {
    DatabaseReference DataBase;
    FirebaseDatabase fData;
    EditText nombre,descripcion,precio;
    Button breg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        precio=findViewById(R.id.preciotv);
        fData= FirebaseDatabase.getInstance();
        DataBase=fData.getReference("articulos");

        precio=findViewById(R.id.preciotv);
        descripcion=findViewById(R.id.descripcionet);
        nombre=findViewById(R.id.nombreet);
        breg=findViewById(R.id.bReg);

        breg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double p=Double.parseDouble(precio.getText().toString());
                String d=descripcion.getText().toString();
                String n=nombre.getText().toString();
                Articulo articulo = new Articulo(n,d,p);
                DataBase.setValue(articulo);
            }
        });



    }
}