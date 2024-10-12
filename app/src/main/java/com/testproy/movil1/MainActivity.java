package com.testproy.movil1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtstatus;
    Button btnentrar;
    EditText txtuser;
    EditText txtclave;
    Conexion con;
    //test2

    @SuppressLint("MissingInflatedId")
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

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtuser=(EditText)findViewById(R.id.txtuser);
                txtclave=(EditText)findViewById(R.id.txtclave);
                if(txtuser.getText().toString().trim().length()<=0) Toast.makeText(MainActivity.this,"Ingrese el Usuario",Toast.LENGTH_LONG).show();
                else if(txtclave.getText().toString().trim().length()<=0) Toast.makeText(MainActivity.this,"Ingrese la Clave",Toast.LENGTH_LONG).show();
                else
                {
                    String sql="select count(*) from tbl_user where usuario_user='"+Utils.preparar(txtuser.getText().toString())+
                            "' and usuario_clave='"+Utils.preparar(txtclave.getText().toString())+"'";
                    //Utils.mostrar(MainActivity.this,sql);
                    String info=BDManager.getData(1,sql);
                    if(Utils.convertInt(info)>0)
                    {

                        Utils.usuario=txtuser.getText().toString();
                        BDManager.insertar("insert into tbl_bitacora values(0,'"+Utils.usuario+" entro al sistema')");
                        Intent pral=new Intent(MainActivity.this,PantallaPral.class);
                        MainActivity.this.startActivity(pral);

                    }
                    else
                    {
                        //Toast.makeText(MainActivity.this,"Credenciales Incorrectas",Toast.LENGTH_LONG).show();
                        Utils.mostrar(MainActivity.this,"Credenciales Incorrectas");
                        txtuser.setText("");
                        txtclave.setText("");
                    }
                }

            }
        });

    }
}