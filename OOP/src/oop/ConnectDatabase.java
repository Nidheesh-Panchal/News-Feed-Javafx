/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.sql.*;
import java.util.Map;

/**
 *
 * @author nilu1
 */
public class ConnectDatabase {
    public static Connection connecrDb() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsfeed", "root", "root");
            return conn;
        } catch (Exception ex) {
            System.out.println("Connection failure");
        }
        return null;
    }
    Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    public boolean loginDb(String username,String password)
    {
        conn=connecrDb();
        String sql = "select * from login where username=?;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            //pst.setString(2,password_txt.getText());
            rs = pst.executeQuery();
            if (rs.next())
            {
                //JOptionPane.showMessageDialog(null, "username and password are correct");
                String pass=password;
                if (rs.getNString("pwd").equals(pass))
                {
                    conn.close();
                    return true;
                }
            }
            else
            {
                System.out.println("no username");
            }
            conn.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }        
        return false;
    }
    public void registerDb(String username,String password,String country,Map<String, Integer> m)
    {
        conn=connecrDb();
        String sql = "insert into login (username,pwd,country,business,entertainment,general,health,science,sports,technology) values (?,?,?,?,?,?,?,?,?,?)";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,country);
            pst.setString(4,Integer.toString(m.get("business")));
            pst.setString(5,Integer.toString(m.get("entertainment")));
            pst.setString(6,Integer.toString(m.get("general")));
            pst.setString(7,Integer.toString(m.get("health")));
            pst.setString(8,Integer.toString(m.get("science")));
            pst.setString(9,Integer.toString(m.get("sports")));
            pst.setString(10,Integer.toString(m.get("technology")));
            pst.execute();
            //conn.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }        
    }
}
