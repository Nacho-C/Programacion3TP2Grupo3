package modelo;

/**
 * @author Grupo 3. <br>
 *         Clase Batalla. <br>
 *         Descripcion: Clase que implementa la interfaz IState para el uso del
 *         patron State. Se encarga de manejar el uso de cartas y el ciclo
 *         ataque-contraataque de una batalla.<br>
 */
public class Batalla implements IState {
	private Arena arena;
	private String mensaje;

	public Batalla(Arena arena) {
		this.arena = arena;
	}

	/**
	 * Descripcion: Metodo que genera en la variable String mensaje los logs para
	 * informar en la ventana, y donde se generan los ataques de los pokemones y la
	 * utilizacion de las cartas hechizo.<br>
	 */
	public void correr() {
		String nombre1 = this.arena.getEntrenador1().getNombre(), nombre2 = this.arena.getEntrenador2().getNombre();
		mensaje = "Arena en estado de batalla: \n";
		Pokemon pokemon1 = this.arena.getPokemon1(), pokemon2 = this.arena.getPokemon2();
		double random = Math.random();
		if (this.arena.getCarta1() != null) {
			this.arena.getCarta1().hechizar(pokemon2);
			mensaje += "El entrenador " + nombre1 + " usó su carta " + this.arena.getCarta1() + " en el "
					+ pokemon2.getNombre() + " enemigo.\n";
		}
		if (this.arena.getCarta2() != null) {
			this.arena.getCarta2().hechizar(pokemon1);
			mensaje += "El entrenador " + nombre2 + " usó su carta " + this.arena.getCarta2() + " en el "
					+ pokemon1.getNombre() + " enemigo.\n";
		}
		if (random > 0.5) {
			pokemon2.ataque(pokemon1);
			mensaje += "El " + pokemon1.getNombre() + " de " + nombre1 + " ataca al " + pokemon2.getNombre() + " de "
					+ nombre2 + ".\n";
			pokemon1.ataque(pokemon2);
			mensaje += "El " + pokemon2.getNombre() + " de " + nombre2 + " ataca al " + pokemon1.getNombre() + " de "
					+ nombre1 + ".\n";
		} else {
			pokemon1.ataque(pokemon2);
			mensaje += "El " + pokemon2.getNombre() + " de " + nombre2 + " ataca al " + pokemon1.getNombre() + " de "
					+ nombre1 + ".\n";
			pokemon2.ataque(pokemon1);
			mensaje += "El " + pokemon1.getNombre() + " de " + nombre1 + " ataca al " + pokemon2.getNombre() + " de "
					+ nombre2 + ".\n";

		}
		this.arena.setEstado(new Definicion(this.arena));
	}

	public String getMensaje() {
		return mensaje;
	}
}
