package com.testproy.movil1;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class BDManager
{
    static Conexion conn;
    public static void insertar(String sql)
    {
        try
        {
            Connection con=conn.getConexion();
            Statement s1=con.createStatement();
            s1.executeQuery(sql);
            con.close();
        }catch(Exception exp)
        {
            //Insertar en un Archivo los Errores.
            Log.e("Error", exp.getMessage());
        }
    }
    public static String getData(int campo,String sql)
    {
        String columna="";
        String info="";
        try
        {
            Connection con=conn.getConexion();
            Statement s1=con.createStatement();
            ResultSet rs=s1.executeQuery(sql);
            ResultSetMetaData md=rs.getMetaData();
            columna=md.getColumnTypeName(campo);
            while(rs.next())
            {
                info = tratar_rs(columna, campo, rs);
            }
            rs.close();
            con.close();
        }catch(Exception exp)
        {
            //Insertar en un Archivo los Errores.
            Log.e("Error", exp.getMessage());
        }
        return info;
    }
    private static String tratar_rs(String columna, int campo, ResultSet rs){
        try{
            if (columna.equals("INTEGER"))return ""+rs.getInt(campo);
            else if (columna.equals("INT"))return ""+rs.getInt(campo);
            else if (columna.equals("BIGINT"))return ""+rs.getInt(campo);
            else if (columna.equals("INTEGER UNSIGNED"))return ""+rs.getInt(campo);
            else if (columna.equals("VARCHAR"))return ""+rs.getString(campo);
            else if (columna.equals("DOUBLE"))return ""+rs.getDouble(campo);
            else if (columna.equals("CURRENCY"))return ""+rs.getDouble(campo);
            else if (columna.equals("COUNTER"))return ""+rs.getInt(campo);
            else if (columna.equals("TINYINT"))return ""+rs.getBoolean(campo);
            else if (columna.equals("TINYINT UNSIGNED"))return ""+rs.getBoolean(campo);
            else if (columna.equals("DECIMAL"))return ""+rs.getDouble(campo);
            else if (columna.equals("DATE"))return ""+rs.getString(campo);
            else if (columna.equals("DATETIME"))return ""+rs.getString(campo);
            else if (columna.equals("BIGINT UNSIGNED"))return ""+rs.getInt(campo);
            System.out.println("tipo"+columna);
        }
        catch(Exception exp){}
        return "";
    }
}