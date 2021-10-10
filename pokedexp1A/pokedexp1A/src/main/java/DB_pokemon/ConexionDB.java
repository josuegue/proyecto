/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_pokemon;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIOTC
 */
public class ConexionDB {
    private PreparedStatement stm;
    private Connection cn;
    private Result rs;
    

    private static final String  CONEXION ="jdbc:mysql://localhost:3306/db_comisiones?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="12345678";


    
    public static Connection conexion() throws SQLException{
            return DriverManager.getConnection(CONEXION, USER, PASSWORD);
 
    }
    public static void close(ResultSet rs, PreparedStatement ppSt, Connection cnn){
        try {
            rs.close();
            ppSt.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
    }
    public static void close (ResultSet resul){
        try {
            resul.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void close (PreparedStatement st){
        try {
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void close (Connection cn) throws SQLException{
        cn.close();
    }
    
}

