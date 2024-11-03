package com.testproy.movil1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class User_Activity extends AppCompatActivity {
    LinearLayout linearlayout;
    List<String> list;
    List<String> listid;
    String idselected;
    EditText txtuser;
    EditText txtpass;
    Button btnaccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        linearlayout=findViewById(R.id.linearlayout);
        txtuser=findViewById(R.id.txtuser3);
        txtpass=findViewById(R.id.txtclave3);
        btnaccion=findViewById(R.id.btnaccion);
        fillintoLinearLayout(list);

        //Set Evento Click Index o Posicion
        for (int i = 0; i < linearlayout.getChildCount(); i++) {
            View childView = linearlayout.getChildAt(i);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the index of the clicked child view
                    int index = linearlayout.indexOfChild(view);
                    // Use the index as needed
                    System.out.println("Clicked index: " + index);
                    llenarCampos(index);
                    btnaccion.setText("MODIFICAR");
                }
            });
        }
    }
    private void llenarCampos(int index)
    {
        idselected=listid.get(index).toString();
        try
        {
            Connection con=new Conexion().getConexion();
            Statement s1=con.createStatement();
            ResultSet rs=s1.executeQuery("select * from tbl_user where user_id='"+idselected+"'");
            while(rs.next())
            {
                txtuser.setText(rs.getString(2));
                txtpass.setText(rs.getString(3));
            }
            rs.close();
            con.close();
        }catch(Exception exp)
        {
            //Insertar en un Archivo los Errores.
            Log.e("Error", exp.getMessage());
        }
    }
    private void fillintoLinearLayout(List<String> list)
    {
        list=BDManager.getList("select concat(user_nombre,'') from tbl_user order by user_nombre");
        listid=BDManager.getList("select concat(user_id,'') from tbl_user order by user_nombre");
        linearlayout.removeAllViews();
        for(int i=0;i<list.size();i++)
        {
            TextView tv=new TextView(this);
            tv.setText(list.get(i).toString());
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24); // 24sp

            GradientDrawable border = new GradientDrawable();
            border.setColor(Color.TRANSPARENT); // Background color
            border.setStroke(2, Color.RED); // Border color and width
            border.setCornerRadius(4f); // Corner radius

            tv.setBackground(border);
            tv.setPadding(8, 8, 8, 8); // Add padding to text


            linearlayout.addView(tv);
        }
    }
}