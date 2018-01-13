/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author ALUCI
 */
public class Persona {
	private int id_persona;
	private String nombre;
	private String apellido;

	
	public int getId_persona(){
		return id_persona; 		
	}
	
	public void setId_persona(int unaPersona){
		id_persona = unaPersona;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String unNombre) {
		nombre = unNombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String unapellido) {
		apellido = unapellido;
	}
	
	@Override
	public String toString() {
		return "Persona{" + "id´persona=" + id_persona + ","
				+ "nombre=" + nombre + ", apellido=" + apellido + '}';
	}
	
}

