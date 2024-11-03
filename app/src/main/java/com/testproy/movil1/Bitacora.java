package com.testproy.movil1;

import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.os.Build;
import android.util.Log;

public class Bitacora
{
    public String getSQL(String descrip, String hoy)
    {
        String devicename=android.os.Build.MODEL;
        String sql="insert into tbl_bitacora values(0,'"+getMarca()+"','"+getModelo()+"','"+devicename+"','"+
                getIP()+"','"+getMacAddress()+"','"+hoy+"',"+Utils.iduser+",'"+descrip+"',0)";
        return sql;
    }
    public String getMarca()
    {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return model;
        } else {
            return manufacturer;
        }
    }
    public String getModelo()
    {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return model;
        } else {
            return model;
        }
    }
    public String getHostName()
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            // Get IP Address
            byte[] ipAddr = addr.getAddress();
            // Get hostname
            String hostname = addr.getHostName();
            return hostname;
        }
        catch (UnknownHostException e){}
        return null;
    }
    public String getIPPlan()
    {
        List<InetAddress> addrs;
        String address = "";
        try{
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                addrs = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addrs){
                    if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
                        address = addr.getHostAddress().toUpperCase(new Locale("es", "MX"));
                    }
                }
            }
        }catch (Exception e){
            Log.e("Error","Ex getting IP value " + e.getMessage());
        }
        return address;
    }
    public String getIP()
    {
        String id="wlan";

        try {

            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (intf.getName().contains(id)) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {

                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            if (addr instanceof Inet4Address) {
                                return sAddr;
                            }
                        }

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /*public String getHostNameIpAdress()
    {
        try
        {
            // Get hostname by textual representation of IP address
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            // Get hostname by a byte array containing the IP address
            byte[] ipAddr = new byte[]{127, 0, 0, 1};
            addr = InetAddress.getByAddress(ipAddr);

            // Get the host name
            String hostname = addr.getHostName();

            // Get canonical host name
            String hostnameCanonical = addr.getCanonicalHostName();
            return hostnameCanonical;
        } catch (UnknownHostException e) {}
        return null;
    }*/
    public String getMacAddress()
    {
        try {
            // Get the list of network interfaces
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements())
            {
                NetworkInterface networkInterface = interfaces.nextElement();

                // Get the MAC address
                byte[] mac = networkInterface.getHardwareAddress();

                if (mac != null)
                {
                    StringBuilder macAddress = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    //System.out.println("Interface: " + networkInterface.getDisplayName() + " MAC: " + macAddress.toString());
                    return macAddress.toString();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }
}