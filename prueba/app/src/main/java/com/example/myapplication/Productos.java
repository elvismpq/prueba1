package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Productos extends AppCompatActivity {

    Button bt;
    FirebaseAuth fAuth;
    FirebaseUser User;
    TextView tv;

    @Override
    public void onStart() {
        super.onStart();
        fAuth=FirebaseAuth.getInstance();
        User = fAuth.getCurrentUser();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        tv=findViewById(R.id.tv);

        bt=findViewById(R.id.bt);
        fAuth=FirebaseAuth.getInstance();
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
    }
}