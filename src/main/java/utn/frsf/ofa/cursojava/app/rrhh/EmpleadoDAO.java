/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;

import java.util.List;

/**
 *
 * @author cgfe
 */
public interface EmpleadoDAO {
    public int crear(Empleado e);
    public int actualizar(Empleado e);
    public int eliminar(Empleado e);
    public Empleado buscarPorId(Integer id);
    public List<Empleado> buscarTodos();
    
}
