package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class editar extends AppCompatActivity {
    DatabaseReference DataBase;
    FirebaseDatabase fData;
    EditText nombre,descripcion,precio;
    Button breg,bAtras;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        precio=findViewById(R.id.preciotv);
        fData= FirebaseDatabase.getInstance();
        DataBase=fData.getReference("Artículos");
        user = FirebaseAuth.getInstance().getCurrentUser();

        precio=findViewById(R.id.preciotv);
        descripcion=findViewById(R.id.descripcionet);
        nombre=findViewById(R.id.nombreet);
        breg=findViewById(R.id.bReg);
        bAtras=findViewById(R.id.bAtras);

        breg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=nombre.getText().toString().trim();
                String d=descripcion.getText().toString().trim();
                String p=precio.getText().toString().trim();
                if(TextUtils.isEmpty(n)){
                    nombre.setError("Campo requerido");
                    return;
                }
                if(TextUtils.isEmpty(d)){
                    nombre.setError("Campo requerido");
                    return;
                }
                if(TextUtils.isEmpty(p)){
                    precio.setError("Campo requerido");
                    return;
                }
                Articulo articulo = new Articulo();
                articulo.setId(UUID.randomUUID().toString());
                articulo.setPrecio(Double.parseDouble(precio.getText().toString()));
                articulo.setDescripcion(descripcion.getText().toString());
                articulo.setNombre(nombre.getText().toString());

                DataBase.child(articulo.getId()).setValue(articulo);
                Toast.makeText(editar.this, "Sus datos has sido modificados",Toast.LENGTH_SHORT).show();
                limpiar();
            }
            private void limpiar() {
                precio.setText("");
                descripcion.setText("");
                nombre.setText("");
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Productos.class));
                finish();
            }
        });
    }
}