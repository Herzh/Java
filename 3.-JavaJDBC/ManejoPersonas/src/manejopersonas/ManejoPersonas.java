/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejopersonas;
import datos.PersonasJDBC;
import domain.Persona;
import java.util.List;

/**
 *
 * @author ALUCI
 */
public class ManejoPersonas {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		PersonasJDBC personasJDBC = new PersonasJDBC();
		//Prueba del metodo insert
		personasJDBC.insert("Juan", "Cuerdas");
		personasJDBC.insert("Pedro", "Lopez");
        personasJDBC.insert("Alberto", "Juarez");
		
		//Prueba del metodo update
        //personasJDBC.update(1, "Juan", "Cuerdas");
		
		//Prueba del metodo delete
        //personasJDBC.delete(1);
		
		
		//Prueba del metodo select
        //Uso de un objeto persona para encapsular la informacion
        //de un registro de base de datos
        List<Persona> personas = personasJDBC.select();
        for (Persona persona : personas) {
            System.out.print(persona);
            System.out.println("");
        }
	}
	
}
