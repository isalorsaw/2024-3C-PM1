package com.testproy.movil1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class activity_accesos extends AppCompatActivity {
    TableLayout tabla;
    Spinner cmbuser;
    EditText txtuseri;
    List<String> list,listid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesos);
        cmbuser=findViewById(R.id.spinner);
        txtuseri=findViewById(R.id.txtuseri);
        tabla=findViewById(R.id.tabla);
        txtuseri.setText("");
        cmbuser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
               //int selectedIndex=(int)parent.getItemAtPosition(i);//
                if(i!=0)
                {
                    txtuseri.setText(listid.get(i).toString());
                    //Toast.makeText(activity_accesos.this,"Index"+i,Toast.LENGTH_LONG).show();
                    fillTable();

                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        fillCmbBox();
    }
    public void fillTable()
    {
        //idselected=listid.get(index).toString();
        tabla.removeAllViews();
        try
        {
            Connection con=new Conexion().getConexion();
            Statement s1=con.createStatement();
            ResultSet rs=s1.executeQuery("SELECT\n" +
                    "m.modulo_nombre,\n" +
                    "case (ifnull(ac.acceso_estado,FALSE)) \n" +
                    "when 0 then 'INACTIVO' else 'ACTIVO' end AS estadoac,\n" +
                    "ac.user_id,\n" +
                    "m.modulo_codigo,\n" +
                    "IFNULL(ac.user_id,0)AS newmod\n" +
                    "FROM\n" +
                    "tbl_modulo m\n" +
                    "LEFT OUTER JOIN tbl_acceso ac ON ac.modulo_codigo=m.modulo_codigo AND ac.user_id="+
                    txtuseri.getText().toString()+"\n" +
                    "LEFT OUTER JOIN tbl_user us ON us.user_id=ac.user_id\n" +
                    "WHERE m.modulo_estado='ACTIVO'");
            int i=0;
            while(rs.next())
            {
                TableRow row=new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,100));
                TextView tv=new TextView(this);
                //tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                //TableRow.LayoutParams.WRAP_CONTENT));
                tv.setTextSize(14);
                tv.setText(rs.getString(1));
                tv.setPadding(16,16,16,10);

                Button btnestado=new Button(this);
                btnestado.setText(rs.getString(2));
                final String idm=rs.getString(4);
                final String nm=rs.getString(1);
                final int ui=rs.getInt(5);

                btnestado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actualizar(btnestado.getText().toString(),idm,nm,ui);
                    }
                });

                row.addView(tv);
                row.addView(btnestado);
                //row.addView(tv);
                tabla.addView(row);
                i++;
            }
            rs.close();
            con.close();
        }catch(Exception exp)
        {
            //Insertar en un Archivo los Errores.
            Log.e("Error", exp.getMessage());
        }
    }
    public void actualizar(String text,String idm,String nm, int userid)
    {
        //Toast.makeText(activity_accesos.this,"Index"+text+"IDM"+idm,Toast.LENGTH_LONG).show();

        String preg=(text.equals("INACTIVO"))?"Desea cambiar el Acceso <"+nm+"> a Activo":
                "Desea cambiar el Acceso <"+nm+"> a Inactivo";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Cambio de Acceso")
                .setMessage(preg)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Yes button

                        int estado=(text.equals("INACTIVO")?1:0);
                        String sql="";
                        if(userid==0)
                        {
                            sql="insert into tbl_acceso values('"+txtuseri.getText().toString()+
                                    "','"+idm+"',"+estado+")";
                        }
                        else
                        {
                            sql="update tbl_acceso set acceso_estado='"+estado+"' where user_id="+
                                    txtuseri.getText().toString()+
                                    " and modulo_codigo='"+idm+"'";
                        }
                        //Toast.makeText(activity_accesos.this, sql, Toast.LENGTH_SHORT).show();
                        String s=BDManager.insertarMsg(sql);
                        //Toast.makeText(activity_accesos.this, s, Toast.LENGTH_SHORT).show();
                        Toast.makeText(activity_accesos.this, "Actualizacion Satisfactoria", Toast.LENGTH_SHORT).show();
                        fillTable();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        //Toast.makeText(activity_accesos.this, "", Toast.LENGTH_SHORT).show();
                    }
                });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void fillCmbBox()
    {
        list=BDManager.getList("select concat(user_nombre,'') from tbl_user order by user_nombre");
        listid=BDManager.getList("select concat(user_id,'') from tbl_user order by user_nombre");

        list.add(0,"<SELECCIONE UN USUARIO>");
        listid.add(0,"<SELECCIONE UN USUARIO>");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        cmbuser.setAdapter(adapter);

    }
}