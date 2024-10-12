package com.testproy.movil1;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    /*protected static String db="movil1";
    protected static String ip="192.168.1.10";
    protected static String port="3306";
    protected static String username="ilsw";
    protected static String password="olimpia";*/

    protected static String db="sql5735262";
    protected static String ip="sql5.freemysqlhosting.net";
    protected static String port="3306";
    protected static String username="sql5735262";
    protected static String password="zxmx3fjS2D";
    public static Connection getConexion()
    {
        Connection conn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String conString="jdbc:mysql://"+ip+":"+port+"/"+db;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            conn= DriverManager.getConnection(conString,username,password);
        }catch(Exception exp)
        {
            Log.e("Error", exp.getMessage());
            //Toast.makeText(null, exp.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conn;
    }
    public static String ConnError()
    {
        String str="";
        Connection conn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String conString="jdbc:mysql://"+ip+":"+port+"/"+db;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            conn= DriverManager.getConnection(conString,username,password);
            str="Conexion Satisfactoria a BD";
        }catch(Exception exp)
        {
            str=exp.getMessage();
            //str="Error de Conexion a BD";
        }
        return str;
    }
    public static boolean confirm()
    {
        String str="";
        Connection conn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String conString="jdbc:mysql://"+ip+":"+port+"/"+db;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            conn= DriverManager.getConnection(conString,username,password);
            return true;
        }catch(Exception exp)
        {
            str=exp.getMessage();
        }
        return false;
    }
}


