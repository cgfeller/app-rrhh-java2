/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;
import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import java.util.Date;

/**
 *
 * @author cgfeller
 */
public abstract class Empleado {
    protected int id;
    protected String nombre;
    protected String correoElectronico;
    protected String cuil;
    protected Date fechaIngreso;
    protected int horasTrabajadas;
    
    /**
     *
     * @param nombre
     * @param correo
     * @param cuil
     * @param fIngreso
     * @param hsTrab
     */
    public Empleado(String nombre, String correo, String cuil, Date fIngreso, int hsTrab){
        this.nombre = nombre;
        this.correoElectronico = correo;
        this.cuil = cuil;
        this.fechaIngreso = fIngreso;
        this.horasTrabajadas = hsTrab;
               
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    

    public abstract Double salario();

    
}

