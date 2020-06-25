package modelo;

/**
 * @author Grupo 3. <br>
 *         Clase Definicion. <br>
 *         Descripcion: Clase que implementa la interfaz IState para el uso del
 *         patron State. Se encarga de decidir los resultados de la batalla y
 *         asignar las respectivas recompensas.<br>
 */
public class Definicion implements IState {
	private Arena arena;
	private String mensaje;

	public Definicion(Arena arena) {
		this.arena = arena;
	}

	/**
	 * Descripcion: Metodo que genera en la variable String mensaje los logs para
	 * informar en la ventana y que calcula el pokemon ganador, ademas de asignar
	 * las ganancias de experiencia.<br>
	 */
	@Override
	public void correr() {
		mensaje = "Arena en estado de definición: \n";
		double puntaje1, puntaje2;
		Pokemon pokemon1 = this.arena.getPokemon1(), pokemon2 = this.arena.getPokemon2(), pokemonPerdedor;
		puntaje1 = pokemon1.getVitalidad() + 2 * pokemon1.getFuerza() + 0.5 * pokemon1.getEscudo();
		puntaje2 = pokemon2.getVitalidad() + 2 * pokemon2.getFuerza() + 0.5 * pokemon2.getEscudo();
		if (puntaje1 > puntaje2) {
			this.arena.setPokemonGanador(pokemon1);
			this.arena.setGanador(this.arena.getEntrenador1());
			pokemonPerdedor = pokemon2;
			this.arena.setPerdedor(this.arena.getEntrenador2());
			this.arena.setCartaGanada(this.arena.getCarta2());
		} else if (puntaje2 > puntaje1) {
			this.arena.setPokemonGanador(pokemon2);
			this.arena.setGanador(this.arena.getEntrenador2());
			pokemonPerdedor = pokemon1;
			this.arena.setPerdedor(this.arena.getEntrenador1());
			this.arena.setCartaGanada(this.arena.getCarta1());
		} else

		if (this.arena.getEntrenador1().getCategoria() <= this.arena.getEntrenador2().getCategoria()) {
			this.arena.setPokemonGanador(pokemon1);
			this.arena.setGanador(this.arena.getEntrenador1());
			pokemonPerdedor = pokemon2;
			this.arena.setPerdedor(this.arena.getEntrenador2());
			this.arena.setCartaGanada(this.arena.getCarta2());
		} else {
			this.arena.setPokemonGanador(pokemon2);
			this.arena.setGanador(this.arena.getEntrenador2());
			pokemonPerdedor = pokemon1;
			this.arena.setPerdedor(this.arena.getEntrenador1());
			this.arena.setCartaGanada(this.arena.getCarta1());
		}
		mensaje += this.arena.getGanador().getNombre() + " gana.\n";
		mensaje += this.arena.getPerdedor().getNombre() + " queda eliminado del torneo con "
				+ this.arena.getPerdedor().getNombre() + " rondas ganadas.\n";
		this.arena.getPokemonGanador().addXP(3);
		pokemonPerdedor.addXP(1);
		this.arena.getGanador().ganarCreditos(this.arena.getPerdedor().getCategoria() * 0.4);
		mensaje += this.arena.getGanador().getNombre() + " tiene " + this.arena.getGanador().getCreditos()
				+ " créditos.\n";
		if (this.arena.getCartaGanada() != null) {
			this.arena.getGanador().addCarta(this.arena.getCartaGanada());
			mensaje += this.arena.getGanador().getNombre() + " recibe la carta " + this.arena.getCartaGanada()
					+ " usada por " + this.arena.getPerdedor().getNombre() + ".\n";
		}
		this.arena.setEstado(new Limpieza(this.arena));
	}

	public String getMensaje() {
		return mensaje;
	}
}
