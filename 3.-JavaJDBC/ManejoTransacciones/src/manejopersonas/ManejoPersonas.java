/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejopersonas;

import datos.Conexion;
import datos.PersonasJDBC;
import domain.Persona;
import java.sql.*;
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
		
		//Creamos un objeto conexion, se va a compartir
        //para todos los queries que ejecutemos
        Connection conn = null;
		
		 try {
            conn = Conexion.getConnection();
			//Revisamos si la conexion esta en modo autocommit
            //por default es autocommit == true
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
			//Creamos el objeto PersonasJDBC
            //proporcionamos la conexion creada
            PersonasJDBC personas = new PersonasJDBC(conn);
			
			//empezamos a ejecutar sentencias
            //si algo falla no se realizan los cambios en la BD
     
            //cambio correcto
            personas.update(9, "Registro9", "Registo");
			
			//Provocamos un error superando los 45 caracteres
            //del campo de apellido
            //personas.insert("pedro",
                    //"Ayala12341234123412341234123412341234123412341234123412341234123412341234123412341234");
                    //"Trujillo");
								
		
            //guardamos los cambios
            conn.commit();
			
		//Prueba del metodo select
        //Uso de un objeto persona para encapsular la informacion
        //de un registro de base de datos
        List<Persona> personasli = personasJDBC.select();
        for (Persona persona : personasli) {
            System.out.print(persona);
            System.out.println("");
        }
		
        } catch (SQLException e) {
            //Hacemos rollback en caso de error
			 try {
                System.out.println("Entramos al rollback");
                
				//Imprimimos la excepcion a la consola
                e.printStackTrace(System.out);
                
				//Hacemos rollback
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace(System.out);
            }
		}
		 
	
	}
	
}
