package com.testproy.movil1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaPral extends AppCompatActivity {
    TextView txtsaludo;
    Button btnsalir;
    Button btnuser;
    Button btnacceso;
    Button btnproduct;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_pral);

        txtsaludo=(TextView)findViewById(R.id.txtbien);
        btnsalir=(Button)findViewById(R.id.btnsalir);
        btnacceso=(Button)findViewById(R.id.btnmacceso);
        btnuser=findViewById(R.id.btnmuser);
        btnproduct=findViewById(R.id.button3);

        txtsaludo.setText("Bienvenido "+Utils.usuario);
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        btnacceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pral=new Intent(PantallaPral.this,activity_accesos.class);
                PantallaPral.this.startActivity(pral);
            }
        });
        btnproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pral=new Intent(PantallaPral.this,Product_Activity.class);
                PantallaPral.this.startActivity(pral);
            }
        });
    }
}