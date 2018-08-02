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
public class Efectivo extends Empleado {
    private Double sueldoBasico;
    private Double comisiones;
    protected int horasMinimasObligatorias;

    public Efectivo(String nombre, String correo, String cuil, Date fIngreso, int hsTrab) {
        super(nombre, correo, cuil, fIngreso, hsTrab);
    }
    
    public Efectivo(String nombre, String correo, String cuil, Date fIngreso, int hsTrab, Double sueldo, Double comision, int minHoras ){
        super(nombre, correo, cuil, fIngreso, hsTrab);
        this.sueldoBasico = sueldo;
        this.comisiones = comision;
        this.horasMinimasObligatorias = minHoras;
    }

    public Efectivo() {
    }
    
    
    public Double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Double getComisiones() {
        return comisiones;
    }

    public void setComisiones(Double comision) {
        this.comisiones = comision;
    }

    public int getHorasMinimasObligatorias() {
        return horasMinimasObligatorias;
    }

    public void setHorasMinimasObligatorias(int minHoras) {
        this.horasMinimasObligatorias = minHoras;
    }

    /**
     *
     * @return
     */
    @Override
    public Double salario() {
        Double calculo = this.sueldoBasico + this.comisiones;
        int hsextras = getHorasTrabajadas() - this.horasMinimasObligatorias;
        if (hsextras > 0) {
            calculo += hsextras * (this.sueldoBasico / 20);
        }    
        return calculo;
    }
 
}
