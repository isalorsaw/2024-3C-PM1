package com.testproy.movil1;

import android.content.Context;
import android.widget.Toast;

public class Utils
{
    static String usuario="";
    public static String preparar(String inputString)
    {
        final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","%","\\;"};
        for (int i = 0 ; i < metaCharacters.length ; i++)
        {
            if(inputString.contains(metaCharacters[i]))
                inputString = inputString.replace(metaCharacters[i],"\\"+metaCharacters[i]);
        }
        return inputString;
    }
    public static void mostrar(Context c, String msg)
    {
        Toast.makeText(c,msg,Toast.LENGTH_LONG).show();
    }
    public static int convertInt(String s)
    {
        return Integer.parseInt(s);
    }
}
