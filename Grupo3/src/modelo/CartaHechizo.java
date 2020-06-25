package modelo;

import java.io.Serializable;

/**
 * @author Grupo 3.
 * <br>
 * Clase CartaHechizo.
 * <br>
 * Descripcion: Clase abstracta que contiene el metodo para realizar el doble dispatch, entre los tipos de pokemones y de cartas.<br>
 */
public abstract class CartaHechizo implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Descripcion: Metodo para hechizar a un Pokemon. Este es implementado en cada una de las clases que extienden esta clase (Niebla, Tormenta y Viento).
	 * <br>
	 * @param pokemon Pokemon que sera hechizado.
	 */
	protected abstract void hechizar(Pokemon pokemon);
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
