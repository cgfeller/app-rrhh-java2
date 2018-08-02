/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;

/**
 *
 * @author cgfe
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    private static final String USUARIO = "root";
    private static final String PASSWORD = "newinset";
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/app-rrhh";
    private static Connection _CONEXION;


    public static Connection getConexion() throws SQLException {
        if (_CONEXION == null) {
        _CONEXION = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);
        }
        return _CONEXION;
    }
    
    public static void liberarConexion() throws SQLException {
        if (_CONEXION != null) {
            _CONEXION.close();
            _CONEXION = null;
        }
    }
    
}
