package modelo;

import java.io.Serializable;
import java.util.Observable;

/**
 * @author Grupo 3. <br>
 *         Clase Arena. <br>
 *         Descripcion: Recurso compartido encargado de administrar los hilos
 *         entrantes para los enfrentamientos<br>
 */
public class Arena extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	private IState estado, estadoAnterior;
	private boolean disponible;
	private Entrenador entrenador1, entrenador2, ganador, perdedor;
	private Pokemon pokemon1, pokemon2, pokemonGanador;
	private CartaHechizo carta1 = null, carta2 = null, cartaGanada = null;
	private int numeroArena;
	private static int cantArena = 0;

	public Arena() {
		this.numeroArena = Arena.cantArena;
		Arena.cantArena++;
		this.estado = new Preliminar(this);
		this.disponible = true;
	}

	/**
	 * Descripcion: Metodo encargado de setear el estado de la arena.<br>
	 */
	public void setEstado(IState estado) {
		this.estado = estado;
	}

	/**
	 * Descripcion: Metodo sincronizado encargado de bloquear el recurso compartido
	 * cuando un hilo esta utilizandolo.<br>
	 */
	public synchronized void iniciar(Entrenador ent1, Entrenador ent2) {
		this.disponible = false;
		this.entrenador1 = ent1;
		this.entrenador2 = ent2;
		this.carta1 = this.carta2 = this.cartaGanada = null;
		for (int i = 0; i < 4; i++) {
			estadoAnterior = estado;
			estado.correr();
			this.setChanged();
			this.notifyObservers(estadoAnterior);
			estadoAnterior = null;
		}
		notify();
	}

	public Entrenador getEntrenador1() {
		return this.entrenador1;
	}

	public Entrenador getEntrenador2() {
		return this.entrenador2;
	}

	public Entrenador getGanador() {
		return this.ganador;
	}

	public Entrenador getPerdedor() {
		return this.perdedor;
	}

	public Pokemon getPokemon1() {
		return this.pokemon1;
	}

	public Pokemon getPokemon2() {
		return this.pokemon2;
	}

	public Pokemon getPokemonGanador() {
		return this.pokemonGanador;
	}

	public void setGanador(Entrenador ganador) {
		this.ganador = ganador;
	}

	public void setPerdedor(Entrenador perdedor) {
		this.perdedor = perdedor;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public void setPokemonGanador(Pokemon pokemonGanador) {
		this.pokemonGanador = pokemonGanador;
	}

	public CartaHechizo getCarta1() {
		return this.carta1;
	}

	public void setCarta1(CartaHechizo carta1) {
		this.carta1 = carta1;
	}

	public CartaHechizo getCarta2() {
		return this.carta2;
	}

	public void setCarta2(CartaHechizo carta2) {
		this.carta2 = carta2;
	}

	public CartaHechizo getCartaGanada() {
		return this.cartaGanada;
	}

	public void setCartaGanada(CartaHechizo cartaGanada) {
		this.cartaGanada = cartaGanada;
	}

	public boolean isDisponible() {
		return this.disponible;
	}

	public void setDisponible(boolean disp) {
		this.disponible = disp;
	}

	public int getNumeroArena() {
		return this.numeroArena;
	}
}
