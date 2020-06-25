package modelo;
/**
* @author Grupo 3.
* <br>
* Clase PokemonFactory.
* <br>
* Descripcion: Clase encargada de realizar la creación de Pokemones siguiendo el Patron Factory.<br>
*/
public class PokemonFactory
{
	/**
	 * Descripcion: Devuelve un nuevo Pokemon.
	 * <br>
	 * <b>Post:</b> Si el tipo no es ninguno de los predefinidos el Pokemon devuelto es null.
	 * <br>
	 * @param nombre Nombre del Pokemon a crear.
	 * @param tipo Tipo del pokemon a crear (Hielo, Fuego, etc.)
	 */
	public static Pokemon getPokemon(String nombre,String tipo)
	{
		Pokemon retorno = null;
		if (tipo != null && nombre != null)
		{
			if (tipo.equalsIgnoreCase("Aire"))
				retorno = new Aire(nombre);
			else if (tipo.equalsIgnoreCase("Agua"))
				retorno = new Agua(nombre);
			else if (tipo.equalsIgnoreCase("Electrico"))
				retorno = new Electrico(nombre);
			else if (tipo.equalsIgnoreCase("Fuego"))
				retorno = new Fuego(nombre);
			else if (tipo.equalsIgnoreCase("Tierra"))
				retorno = new Tierra(nombre);
			else if (tipo.equalsIgnoreCase("Hielo"))
			{
				double r = Math.random();
				if (r < 0.5)
					retorno = new Hielo(nombre,true);
				else
					retorno = new Hielo(nombre,false);
			}
		}
		return retorno;
	}
}
