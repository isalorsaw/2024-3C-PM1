package com.testproy.movil1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtstatus;
    Button btnentrar;
    Conexion con;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtstatus=(TextView)findViewById(R.id.textView);
        btnentrar=(Button)findViewById(R.id.button2);

        con=new Conexion();
        if(con.confirm())txtstatus.setText(con.ConnError());
        else
        {
            txtstatus.setText(con.ConnError());
            btnentrar.setVisibility(View.INVISIBLE);
        }

    }
}