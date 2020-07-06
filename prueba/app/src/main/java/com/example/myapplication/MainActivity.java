package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView registertv,aux;
    EditText etUser,etPassword;
    Button logInbt;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        registertv= findViewById(R.id.registertv);
        etUser=findViewById(R.id.etUser);
        etPassword=findViewById(R.id.etPassword);
        logInbt=findViewById(R.id.logInbt);
        aux=findViewById(R.id.aux);
        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Productos.class));
            Toast.makeText(MainActivity.this, "Su sesión esta inicializada",Toast.LENGTH_SHORT).show();
            finish();
        }

        registertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class) );
            }
        });
        logInbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etUser.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Sesion inicializada",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Productos.class));
                        }else{
                            Toast.makeText(MainActivity.this, "Usuario y contraseña incorrectos",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}