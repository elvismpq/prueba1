package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Productos extends AppCompatActivity {
    private List<Articulo> lista=new ArrayList<Articulo>();
    ArrayAdapter<Articulo> adapterProductos;
    Button bt,bt2;
    ListView listView;
    FirebaseAuth fAuth;
    FirebaseUser User;
    DatabaseReference bdd;
    Articulo articuloSeleccionado;

    @Override
    public void onStart() {
        super.onStart();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        fAuth=FirebaseAuth.getInstance();
        User = fAuth.getCurrentUser();
        bdd= FirebaseDatabase.getInstance().getReference();
        bt2=findViewById(R.id.bt2);
        bt=findViewById(R.id.bt);
        listView=findViewById(R.id.lista);

        //Presentación de datos
        listarDatos();
        listView.getOnItemClickListener (new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id) {
                articuloSeleccionado=(Articulo) parent.getItemAtPosition(position);
                articuloSeleccionado.getId();

            }
        });

        if(fAuth.getCurrentUser()==null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            Toast.makeText(Productos.this, "No exite sesión inicializada",Toast.LENGTH_SHORT).show();
            finish();
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fAuth.signOut();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(Productos.this, "Su sesión ha finalizado",Toast.LENGTH_SHORT).show();
                    finish();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Crear.class));
                finish();
            }
        });
    }
    private void listarDatos(){
    bdd.child("Artículos").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            lista.clear();
            for(DataSnapshot objSnapShot:dataSnapshot.getChildren()){
                Articulo art= objSnapShot.getValue(Articulo.class);
                lista.add(art);
                adapterProductos=new ArrayAdapter<Articulo>(Productos.this,android.R.layout.simple_list_item_1,lista);
                listView.setAdapter(adapterProductos);
                Toast.makeText(Productos.this, "Usuario creado exitósamente",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }
}