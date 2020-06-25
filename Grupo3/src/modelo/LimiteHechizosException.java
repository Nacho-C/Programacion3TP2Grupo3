package modelo;

import java.io.Serializable;

/**
 * @author Grupo 3.
 * <br>
 * Clase LimiteHechixosException
 * <br>
 * Descripcion: Excepcion que se lanza cuando un Entrenador ya alcanzo el limite de cartas utilizables en el torneo.<br>
 */

public class LimiteHechizosException extends Exception
{
	private static final long serialVersionUID = 1L;

	public LimiteHechizosException(String mensaje)
	{
		super(mensaje);
	}
}
