/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;

import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cgfe
 */
public class EmpleadoDaoJdbc implements EmpleadoDAO {

    private final String INSERT_EMPLEADO = "INSERT INTO EMPLEADOS (NOMBRE, CORREO, CUIL, "
            + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
            + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";

    private final String UPDATE_EMPLEADO = "UPDATE EMPLEADOS SET NOMBRE=?, CORREO=?, CUIL=?, "
            + "FECHA_INGRESO=?, HS_TRABAJADAS=?, SUELDO_BASICO=?, "
            + "COMISIONES=?, HS_MINIMAS=?, COSTO_HORA=? "
            + "WHERE ID=? ";

    private final String DELETE_EMPLEADO = "DELETE FROM EMPLEADOS "
            + "WHERE ID=? ";

    private final String SELECT_EMPLEADO = "SELECT * FROM EMPLEADOS "
            + "WHERE ID=? ";

    private final String SELECT_EMPLEADOS = "SELECT id FROM EMPLEADOS ";

    @Override
    public int crear(Empleado e) {
        int filasInsertadas=0;
        try {
            Connection conn = ConexionJDBC.getConexion();
                PreparedStatement ps = conn.prepareStatement(INSERT_EMPLEADO);
                ps.setString(1, e.getNombre());
                ps.setString(2, e.getCorreoElectronico());
                ps.setString(3, e.getCuil());
                ps.setDate(4, new java.sql.Date(e.getFechaIngreso().getTime()));
                ps.setInt(5, e.getHorasTrabajadas());
                if (e instanceof Efectivo) {
                    Efectivo empEf = (Efectivo) e;
                    ps.setDouble(6, empEf.getSueldoBasico());
                    ps.setDouble(7, empEf.getComisiones());
                    ps.setInt(8, empEf.getHorasMinimasObligatorias());
                    ps.setDouble(9, 0.00);
                    ps.setInt(10, 1);
                }
                if (e instanceof Contratado) {
                    Contratado c;
                    c = (Contratado) e;
                    ps.setDouble(6, 0.00);
                    ps.setDouble(7, 0.00);
                    ps.setInt(8, 0);
                    ps.setDouble(9, c.getCostoHora());
                    ps.setInt(10, 2);
                }
                filasInsertadas = ps.executeUpdate();
                ps.close();
                ConexionJDBC.liberarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasInsertadas;
    }

    @Override
    public int actualizar(Empleado e) {
        int filasInsertadas = 0;
        try {
            Connection conn = ConexionJDBC.getConexion();
            
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLEADO)) {
                ps.setString(1, e.getNombre());
                ps.setString(2, e.getCorreoElectronico());
                ps.setString(3, e.getCuil());
                ps.setDate(4, new java.sql.Date(e.getFechaIngreso().getTime()));
                ps.setInt(5, e.getHorasTrabajadas());
                if (e instanceof Efectivo) {
                    Efectivo empEf = (Efectivo) e;
                    ps.setDouble(6, empEf.getSueldoBasico());
                    ps.setDouble(7, empEf.getComisiones());
                    ps.setInt(8, empEf.getHorasMinimasObligatorias());
                    ps.setDouble(9, 0.00);
                }
                if (e instanceof Contratado){
                    Contratado c;
                    c = (Contratado) e;
                    ps.setDouble(6, 0.00);
                    ps.setDouble(7, 0.00);
                    ps.setInt(8, 0);
                    ps.setDouble(9, c.getCostoHora());
                }
                ps.setInt(10, e.getId());
                filasInsertadas = ps.executeUpdate();
                ps.close();
                ConexionJDBC.liberarConexion();
              
            } catch (SQLException ex) {
                ex.printStackTrace();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasInsertadas;
    }

    @Override
    public int eliminar(Empleado e) {
        int filaEliminada=0;
        try {
            Connection conn = ConexionJDBC.getConexion();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_EMPLEADO)) {
                ps.setInt(1, e.getId());
                filaEliminada = ps.executeUpdate();
                ps.close();
                ConexionJDBC.liberarConexion();
              
            } catch (SQLException ex) {
                ex.printStackTrace();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filaEliminada;
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        Empleado empleado = null;
        int busco = id;
        try {
            Connection conn = ConexionJDBC.getConexion();
            try (PreparedStatement pss = conn.prepareStatement(SELECT_EMPLEADO)) {
                pss.setInt(1, busco);
                
                try (ResultSet rs = pss.executeQuery()) {
                    while (rs.next()) {
                        String nombre = rs.getString("NOMBRE");
                        String correo = rs.getString("CORREO");
                        String cuil = rs.getString("CUIL");
                        Date fIngreso = rs.getDate("FECHA_INGRESO");
                        Integer hsTrabajadas = rs.getInt("HS_TRABAJADAS");

                        if (rs.getInt("tipo_empleado") == 1) {
                            Double sueldoBasico = rs.getDouble("SUELDO_BASICO");
                            Double comisiones = rs.getDouble("COMISIONES");
                            Integer hsMinimas = rs.getInt("HS_MINIMAS");
                            empleado = new Efectivo(nombre, correo, cuil, fIngreso, hsTrabajadas, sueldoBasico, comisiones, hsMinimas);
                            empleado.setId(id);
                        } else {
                            Double costoHora = rs.getDouble("COSTO_HORA");
                            empleado = new Contratado(nombre, correo, cuil, fIngreso, hsTrabajadas, costoHora);
                            empleado.setId(id);
                        }
                        

                    }
                    pss.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (SQLException ex) {
                   ex.printStackTrace(); 
            } 
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
        
    }

    @Override
    public List<Empleado> buscarTodos() {
        List<Empleado> empleados = null;

        try {
            Connection conn = ConexionJDBC.getConexion();
            try (PreparedStatement ps = conn.prepareStatement(SELECT_EMPLEADOS)) {
                try (ResultSet rss = ps.executeQuery()) {
                    while (rss.next()) {
                        if (empleados == null) {
                            empleados = new ArrayList<Empleado>();
                        }
                    
                        Integer id = rss.getInt("id");
                        empleados.add(buscarPorId(id));
                    }
                 }
                   
                
                ps.close();
                ConexionJDBC.liberarConexion();
    
            } catch (SQLException ex) {
                ex.printStackTrace();
            } 
        } catch (SQLException ex) {

        }
        return empleados;
    }

    private void commit() {
    }
    
}

