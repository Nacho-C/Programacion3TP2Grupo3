package modelo;

/**
 * @author Grupo 3.
 * <br>
 * Clase NotieneCartasException
 * <br>
 * Descripcion: Excepcion que se lanza cuando un Entrenador quiere usar un hechizo y no tiene cartas.<br>
 */

public class NoTieneCartasException extends Exception
{
	private static final long serialVersionUID = 1L;

	public NoTieneCartasException(String mensaje)
	{
		super(mensaje);
	}
}
