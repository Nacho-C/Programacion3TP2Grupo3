package modelo;
/**
 * @author Grupo 3.
 * <br>
 * Clase Tormenta.
 * <br>
 * Descripcion: Clase que se extiende de la clase CartaHechizo y la interfaz Cloneable, y que es utilizada por un Entrenador para debilitar al Pokemon enemigo.
 */

public class Tormenta extends CartaHechizo implements Cloneable
{
	private static final long serialVersionUID = 1L;

	public void hechizar(Pokemon pokemon)
	{
		pokemon.recibeTormenta();
	}

	@Override
	public String toString()
	{
		return "Tormenta";
	}
}
