/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app.rrhh;
import java.util.Date;

/**
 *
 * @author cgfeller
 */
class Contratado extends Empleado {
    
    private Double  costoHora;

    public Contratado(String nombre, String correo, String cuil, Date fIngreso, int hsTrab) {
        super(nombre, correo, cuil, fIngreso, hsTrab);
    }
    
    public Contratado(String nombre, String correo, String cuil, Date fIngreso, int hsTrab, Double costoHora){
        super(nombre, correo, cuil, fIngreso, hsTrab);
        this.costoHora = costoHora;
    }

    public Contratado() {
    }

    public Double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(Double costoHora) {
        this.costoHora = costoHora;
    }
    

    @Override
    public Double salario() {
        Double calculo = this.costoHora * getHorasTrabajadas();
        return calculo;
    }

    
}
