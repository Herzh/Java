/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import domain.Persona;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ALUCI
 */
public class PersonasJDBC {
	
	private java.sql.Connection userConn;
	
	private final String SQL_INSERT = "INSERT INTO persona(NOMBRE, APELLIDO) values(?,?)";
	private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";
	private final String SQL_DELETE = "DELETE FROM persona WHERE id_persona =?";
	private final String SQL_SELECT = "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";
	
	
	/*
     * Agregamos el constructor vacio
     */
    public PersonasJDBC() {
    }

    /**
     * Constructor que asigna una conexion existente para ser utilizada en los
     * queries de esta clase
     *
     * @param conn Conexion a la BD previamente creada
     */
    public PersonasJDBC(Connection conn) {
        this.userConn = conn;
    }
	
	 /**
     * Metodo que inserta un registro en la tabla de Persona
     *
     * @param nombre
     * @param apellido
     */
	public int insert(String nombre, String apellido) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs =null;		// se utilizara después
		int rows = 0;
		
		try{
			//Si la conexion a reutilizar es distinto de nulo se utiliza, sino se
            //crea una nueva conexion
            conn = (this.userConn != null) ? this.userConn : 
					Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			int index = 1;						// contador de columnas
			stmt.setString(index++, nombre);		//param 1 => ?
			stmt.setString(index++, apellido);		//param 2 => ?
			System.out.println("Ejecutando query:" + SQL_INSERT);
			rows = stmt.executeUpdate();			//No. registros afectados
            System.out.println("Registros afectados:" + rows);
			
        } finally {
            Conexion.close(stmt);
             //Unicamente cerramos la conexión si fue creada en este metodo
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
	}
	
	/**
     * Metodo que actualiza un registro existente
     *
     * @param id_persona Es la llave primaria
     * @param nombre Nuevo Valor
     * @param apellido Nuevo Valor
     * @return int No. de registros modificados
     */
    public int update(int id_persona, String nombre, String apellido) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, nombre);
            stmt.setString(index++, apellido);
            stmt.setInt(index, id_persona);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }
	
	/**
     * Metodo que elimina un registro existente
     *
     * @param id_persona Es la llave primaria
     * @return int No. registros afectados
     */
    public int delete (int id_persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);;
            stmt.setInt(1, id_persona);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

	
	 /**
     * Metodo que regresa el contenido de la tabla de personas
     */
    public List<Persona> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_persona = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                /*System.out.print(" " + id_persona);
                 System.out.print(" " + nombre);
                 System.out.print(" " + apellido);
                 System.out.println();
                 */
                persona = new Persona();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                personas.add(persona);
            }

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return personas;
    }
	
	
}
