package modelo;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Grupo 3. <br>
 *         Clase Arena. <br>
 *         Descripcion: Clase que se encarga de realizar un enfrentamiento entre
 *         dos entrenadores de manera concurrente. Elige una arena libre y la
 *         reserva. Si no hay arenas libres se elige una al azar.<br>
 */

public class Enfrentamiento extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	private Entrenador entrenador1, entrenador2;

	/**
	 * Descripción: Crea un nuevo enfrentamiento con los entrenadores pasados por
	 * parametro. <br>
	 * <b>Pre:</b> Ninguno de los entrenadores pasados por parametro puede ser null.
	 * <br>
	 */
	public Enfrentamiento(Entrenador entrenador1, Entrenador entrenador2) {
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
	}

	/**
	 * Descripción: Metodo run propio de la clase Thread que se encarga de manejar
	 * el comportamiento del hilo<br>
	 */
	@Override
	public void run() {
		Arena arena = null;
		Iterator<Arena> it = Torneo.getInstance().getItArenas();
		arena = it.next();
		while (it.hasNext() && arena != null && !arena.isDisponible()) {
			arena = it.next();
		}
		if (!it.hasNext() && !arena.isDisponible()) {
			int r = (int) (0.99 * Math.random() * Torneo.getInstance().getCantArenas());
			arena = Torneo.getInstance().getArena(r);
		}
		arena.iniciar(this.entrenador1, this.entrenador2);
	}
}