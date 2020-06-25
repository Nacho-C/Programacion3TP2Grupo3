package modelo;
/**
 * @author Grupo 3.
 * <br>
 * Clase Viento.
 * <br>
 * Descripcion: Clase que se extiende de la clase CartaHechizo y la interfaz Cloneable, y que es utilizada por un Entrenador para debilitar al Pokemon enemigo.
 */

public class Viento extends CartaHechizo implements Cloneable
{
	private static final long serialVersionUID = 1L;

	public void hechizar(Pokemon pokemon)
	{
		pokemon.recibeViento();
	}

	@Override
	public String toString()
	{
		return "Viento";
	}
}
