package modelo;

import java.io.Serializable;

/**
 * @author Grupo 3.
 * <br>
 * Clase Niebla.
 * <br>
 * Descripcion: Clase que se extiende de la clase CartaHechizo y la interfaz Cloneable, y que es utilizada por un Entrenador para debilitar al Pokemon enemigo.
 */

public class Niebla extends CartaHechizo implements Cloneable, Serializable
{
	private static final long serialVersionUID = 1L;

	public void hechizar(Pokemon pokemon)
	{
		pokemon.recibeNiebla();
	}
	
	@Override
	public String toString()
	{
		return "Niebla";
	}
}
