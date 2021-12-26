/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lengu
 */
public class SQLServerDataProvider {
     private Connection connection;
    public void open(){
        String strServer = "PHATTAI";
        String strDatabase = "QL_ShopThuCung";
        String strUsername="sa";
        String strPassword="123";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase
                                + ";user=" + strUsername + ";password=" + strPassword;
            connection = DriverManager.getConnection(connectionURL);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void close(){
        try{
            this.connection.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try{
            Statement sm=connection.createStatement();
            rs= sm.executeQuery(sql);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return rs;
    }
    
    public int executeUpdate(String sql){
        int n = -1;
        try{
            Statement sm = connection.createStatement();
            n=sm.executeUpdate(sql);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return n;
    }
}
