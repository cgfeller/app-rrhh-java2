/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author cgfeller
 */
public class EmpleadoDaoJdbcTest {
    
    public EmpleadoDaoJdbcTest() {
    }
    
    @Test
    public void testCrear() throws ParseException, SQLException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("NUEVOS EMPLEADOS");

        EmpleadoDaoJdbc eDao = new EmpleadoDaoJdbc();
        Empleado e = new Efectivo("DENISE", "DENU@GM.COM", "22-11222333-8", formatter.parse("10/04/2000"), 15, 15030.00, 500.00, 10);
 
        assertTrue("Hubo un error al ingresar el Efectivo" + e.getNombre(), eDao.crear(e)>0);
        
        Empleado c = new Contratado("EDUARDO", "EDU@GM.COM", "22-22558887-4", formatter.parse("12/02/2000"), 15, 420.00);
        assertTrue("Hubo un error al ingresar el Contratado " + c.getNombre(), eDao.crear(c)>0);
    }

    @Test
    public void testActualizar() throws ParseException, SQLException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("ACTUALIZO Empleado 12");

        EmpleadoDaoJdbc eDao = new EmpleadoDaoJdbc();
        Empleado e = eDao.buscarPorId(12);
        
        e.setNombre("MARIELA");
        assertTrue("Hubo un error al actualizar el Empleado " + e.getNombre(), eDao.actualizar(e)>0);
        
    }  
    
    @Test
    public void testEliminar() {
        System.out.println("ELIMINO EMPLEADO 18");
        try {
                EmpleadoDaoJdbc eDao = new EmpleadoDaoJdbc();
                Empleado e = eDao.buscarPorId(18);
                int elimine = eDao.eliminar(e);
        } catch (Exception ex) {
            ex.printStackTrace();     
            System.out.println("NO SE PUDO ELIMINAR .. NO ENCONTRADO");
        }
    }

    @Test
    public void testBuscarPorId() {
        System.out.println("BuscarPorId EL EMPLEADO 10");
        EmpleadoDaoJdbc instance = new EmpleadoDaoJdbc();
        assertTrue ("No se encuentra el empleado 10", instance.buscarPorId(10)!=null);
    }

    @Test
    public void testBuscarTodos() {
        System.out.println("BUSQUEDA DE TODOS LOS EMPLEADOS");
        EmpleadoDaoJdbc eDao = new EmpleadoDaoJdbc();
         assertTrue("No se encontraron empleados", eDao.buscarTodos().size() > 0);  
    }
    
}
