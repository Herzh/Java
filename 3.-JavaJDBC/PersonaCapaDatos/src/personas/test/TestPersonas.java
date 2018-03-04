/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.test;

import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDao;
import personas.jdbc.PersonaDaoJDBC;

/**
 *
 * @author ALUCI
 */
public class TestPersonas {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		//Utilizamos el tipo interface como referencia
        //a una clase concreta
        PersonaDao personaDao = new PersonaDaoJDBC();

        /*Creamos un nuevo registro
        Hacemos uso de la clase persona DTO la cual se usa
        para transferiri la informacion entre las capas
        no es necesario especificar la llave primaria
        ya que en este caso es de tipo autonumerico y la BD se encarga
        de asignarle un nuevo valor*/
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre("new12");
        persona.setApellido("unwee");
        
		//Utilizamos la capa DAO para persistir el objeto DTO
        try {
			
			//Seleccionamos todos los registros
            List<PersonaDTO> personasIni = personaDao.select();
            for (PersonaDTO personaDTO : personasIni) {
                System.out.print( personaDTO );
                System.out.println();
            }
			
            //personaDao.insert(persona);

            //eliminamos un registro, el id 8
            //personaDao.delete( new PersonaDTO(8));
            
            //Actualizamos un registro
             PersonaDTO personaTmp= new PersonaDTO();
             personaTmp.setId_persona(11);			//actualizamos el registro 2
             personaTmp.setNombre("new11");
             personaTmp.setApellido("unwe11");
             personaDao.update(personaTmp);
            
            //Seleccionamos todos los registros
            List<PersonaDTO> personasFin = personaDao.select();
            for (PersonaDTO personaDTO : personasFin) {
                System.out.print( personaDTO );
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Excepcion en la capa de prueba");
            e.printStackTrace();
        }
    }
	
}
