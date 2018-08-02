/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EfectivoTest {
    
    public EfectivoTest() {
    }
    
    @Test 
    public void testSalarioSinHorasExtras() { 
        System.out.println("salario"); 
        Efectivo empleado = new Efectivo(); 
        empleado.setHorasTrabajadas(40); 
        empleado.setHorasMinimasObligatorias(40); 
        empleado.setComisiones(2000.0); 
        empleado.setSueldoBasico(30000.0); 
        Double expResult = 30000.0+2000.0; 
        Double result = empleado.salario(); 
        assertEquals(expResult, result); 
        System.out.println(result);
    } /** * Test of salario method, of class Efectivo. */ 
    
    
    @Test 
    public void testSalario2HorasExtras() 
    { 
        System.out.println("salario"); 
        Efectivo empleado = new Efectivo(); 
        empleado.setHorasTrabajadas(42); 
        empleado.setHorasMinimasObligatorias(40); 
        empleado.setComisiones(2000.0); 
        empleado.setSueldoBasico(30000.0); 
        Double expResult = 30000.0+2000.0+3000.0; 
        Double result = empleado.salario(); 
        assertEquals(expResult, result); 
        System.out.println(result);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testSomeMethod() {
    }
    
}
