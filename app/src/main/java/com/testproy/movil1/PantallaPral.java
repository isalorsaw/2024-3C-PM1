package com.testproy.movil1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaPral extends AppCompatActivity {
    TextView txtsaludo;
    Button btnsalir;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_pral);

        txtsaludo=(TextView)findViewById(R.id.txtbien);
        btnsalir=(Button)findViewById(R.id.btnsalir);

        txtsaludo.setText("Bienvenido "+Utils.usuario);

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


    }
}