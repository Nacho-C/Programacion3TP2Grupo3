package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import Persistencia.IPersistencia;
import Persistencia.PersistenciaBIN;

/**
 * @author Grupo 3. <br>
 *         Clase Torneo. <br>
 *         Descripcion: Clase que emplea Patron Singleton y encargada de
 *         gestionar los Entrenadores y las rondas. Implementa la interfaz
 *         Serializable
 */
public class Torneo implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * numeroEntrenadores Numero de Entrenadores que representa la cantidad de
	 * participantes del Torneo. <br>
	 * maxHechizos Numero que representa la cantidad maxima de hechizos utilizables
	 * por los Entrenadores en el Torneo. <br>
	 * arenas ArrayList que contiene todas las rondas, para un posterior informe
	 * general. <br>
	 * entrenadores ArrayList que contiene todos los entrenadores participantes del
	 * Torneo.<br>
	 */
	private static Torneo instance = null;

	public final static int numeroEntrenadores = 16, maxHechizos = 2, cantArenasInicial = 4;

	private ArrayList<Arena> arenas = new ArrayList<Arena>();

	private ArrayList<Enfrentamiento> enfrentamientos = new ArrayList<Enfrentamiento>();

	private ArrayList<Entrenador> entrenadores = new ArrayList<Entrenador>(), entrenadoresClon;

	private int cantPeleando = 0, etapa, cantArenasAnterior;

	private String enfrentamientosTotal = "";

	private Torneo()
	{
	}

	/**
	 * Descripcion: Metodo para obtener la instance del Torneo. Necesario para
	 * operar con sus atributos. Si no existe una instancia de torneo, intenta
	 * recuperar el estado de un torneo persistido en el archivo Torneo.bin. Si el
	 * archivo no existe o no puede ser leido, se crea una nueva instancia. <br>
	 * 
	 * @return Instancia de Torneo.<br>
	 */
	public static Torneo getInstance()
	{
		Torneo leido;
		IPersistencia persistencia = new PersistenciaBIN();
		if (Torneo.instance == null)
		{
			try
			{
				persistencia.abrirInput("Torneo.bin");
				leido = (Torneo) persistencia.leer();
				Torneo.instance = leido;
				persistencia.cerrarInput();
			}
			catch (IOException | ClassNotFoundException e)
			{
				Torneo.instance = new Torneo();
				Torneo.instance.etapa = 1;
				for (int i = 0; i < Torneo.cantArenasInicial; i++)
					Torneo.instance.arenas.add(new Arena());
			}
		}
		return Torneo.instance;
	}

	public synchronized void addEntrenador(Entrenador entrenador)
	{
		this.entrenadores.add(entrenador);
	}

	public synchronized void removeEntrenador(Entrenador entrenador)
	{
		this.entrenadores.remove(entrenador);
	}

	/**
	 * Descripcion: Ejecuta una etapa del torneo, determinada por el atributo etapa.
	 * En la etapa 2 clona el conjunto de entrenadores para no perder la referencia
	 * a ellos a medida que se van eliminando. En las etapas de batalla elige pares
	 * de entrenadores y crea un enfrentamiento para cada uno. Al finalizar los
	 * enfrentamientos elimina las arenas sobrantes y chequea si es necesario
	 * realizar otra etapa de batalla.<br>
	 */
	public void correrTorneo()
	{
		Iterator<Enfrentamiento> itEnfrentamientos;
		double random;
		Entrenador entrenador1 = null, entrenador2 = null;

		if (this.etapa == 2)
			entrenadoresClon = (ArrayList<Entrenador>) entrenadores.clone();

		else
			if (this.etapa >= 3)
			{
				this.cantArenasAnterior = this.getCantArenas();
				while (this.entrenadores.size() >= 2)
				{
					// Seleccion de entrenadores y creacion de enfrentamientos
					while (this.entrenadores.size() >= 2 && entrenador1 == null)
					{
						random = (Math.random() * 0.99 * (this.entrenadores.size()));
						entrenador1 = this.entrenadores.get((int) random);
						this.entrenadores.remove(entrenador1);
						if (!entrenador1.tienePokemones())
							entrenador1 = null;
					}
					while (this.entrenadores.size() >= 1 && entrenador2 == null)
					{
						random = (Math.random() * 0.99 * (this.entrenadores.size()));
						entrenador2 = this.entrenadores.get((int) random);
						this.entrenadores.remove(entrenador2);
						if (!entrenador2.tienePokemones())
							entrenador2 = null;
					}
					if (entrenador1 != null && entrenador2 != null)
					{
						this.enfrentamientos.add(new Enfrentamiento(entrenador1, entrenador2));
					}
					entrenador1 = entrenador2 = null;
				}
				this.cantPeleando = this.enfrentamientos.size() * 2;
				itEnfrentamientos = this.enfrentamientos.iterator();
				while (itEnfrentamientos.hasNext())
				{
					itEnfrentamientos.next().start();
				}
				while (this.cantPeleando > 0)
					try
					{
						Thread.sleep(500);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				this.enfrentamientos.clear();
				while (this.sobraArena() && this.arenas.size() != 1)
				{
					this.arenas.remove(this.arenas.size() - 1);
				}
				if (this.getCantEntrenadores() == 1)
					this.etapa = -1;
			}
	}

	/**
	 * Descripcion: Devuelve un conjunto ordenado por clasificacion de los
	 * entrenadores que entraron al torneo.<br>
	 */
	public TreeSet<Entrenador> getClasificaciones()
	{
		TreeSet<Entrenador> aux2 = new TreeSet<Entrenador>();
		Iterator<Entrenador> itEntrenadoresClon = entrenadoresClon.iterator();
		while (itEntrenadoresClon.hasNext())
			aux2.add(itEntrenadoresClon.next());
		return aux2;
	}

	public Iterator<Arena> getItArenas()
	{
		return this.arenas.iterator();
	}

	public int getCantArenas()
	{
		return this.arenas.size();
	}

	public boolean sobraArena()
	{
		return arenas.size() > (this.entrenadores.size() + this.cantPeleando) / 2;
	}

	public synchronized void removeArena(Arena arena)
	{
		arenas.remove(arena);
	}

	public Arena getArena(int indice)
	{
		return this.arenas.get(indice);
	}

	public synchronized void restaurarPeleando()
	{
		this.cantPeleando -= 2;
	}

	public int getCantEntrenadores()
	{
		return (this.entrenadores.size() + this.cantPeleando);
	}

	public int getEtapa()
	{
		return this.etapa;
	}

	public void setEtapa(int etapa)
	{
		this.etapa = etapa;
	}

	public Iterator<Entrenador> getItEntrenadores()
	{
		return this.entrenadores.iterator();
	}

	public int getCantArenasAnterior()
	{
		return this.cantArenasAnterior;
	}

	public void agregarEnfrentamiento(String mensaje)
	{
		this.enfrentamientosTotal += mensaje;
	}

	public String getEnfrentamientosTotal()
	{
		return this.enfrentamientosTotal;
	}
}
