package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText autName,autClave,autEmail,autPhone;

    Button bReg;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        autName=findViewById(R.id.nombre);
        autClave=findViewById(R.id.passwd);
        autEmail=findViewById(R.id.correo);
        bReg=findViewById(R.id.bReg);

        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Productos.class));
            Toast.makeText(Register.this, "Su sesión esta inicializada",Toast.LENGTH_SHORT).show();
            finish();
        }
        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=autEmail.getText().toString().trim();
                String password=autClave.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    autEmail.setError("Por favor ingrese su correo");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    autClave.setError("Por favor ingrese su contraseña");
                    return;
                }
                if(password.length()<8){
                    autClave.setError("La clave debe tener al menos 8 caracteres");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "Usuario creado exitósamente",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "No se pudo crear el usuario",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}