package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Persistencia.IPersistencia;
import Persistencia.PersistenciaBIN;
import modelo.Arena;
import modelo.Entrenador;
import modelo.IState;
import modelo.Niebla;
import modelo.Pokemon;
import modelo.PokemonFactory;
import modelo.Tormenta;
import modelo.Torneo;
import modelo.Viento;
import vista.Ventana;

/**
 * @author Grupo 3. <br>
 *         Clase Controlador. <br>
 *         Descripcion: Funciona como nexo entre el modelo y la vista.
 * @param persistencia
 */
public class Controlador implements ActionListener, ListSelectionListener, Observer
{
	IPersistencia<Serializable> persistencia = new PersistenciaBIN();
	private Torneo torneo;
	private Ventana ventana;
	private ArrayList<Arena> observadas = new ArrayList<Arena>();

	/**
	 * Descripcion: Construye el controlador, crea una nueva ventana y obtiene la
	 * instancia de Torneo del patron Singleton. Si carga un torneo persistido
	 * anteriormente corre por primera vez su metodo correrTorneo() <br>
	 */
	public Controlador()
	{
		this.torneo = Torneo.getInstance();
		this.observarArenas();
		this.ventana = new Ventana(this.torneo.getEtapa());
		this.ventana.setActionListener(this);
		this.ventana.setListSelectionListener(this);
		this.ventana.refrescarEtapa();
		if (this.torneo.getEtapa() == 1)
		{
			this.ventana.getTextoSalidaGeneral()
					.setText(this.ventana.getTextoSalidaGeneral().getText()
							+ "No se encontró el archivo Torneo.bin. \nSe necesitan " + Torneo.numeroEntrenadores
							+ " entrenadores para poder iniciar.\n");
		}
		else
		{
			this.refrescarListaEntrenadores();
			if (this.torneo.getEtapa() == -1)
			{
				this.ventana.setEtapa(this.torneo.getEtapa());
				DefaultListModel modeloListaClasificaciones = (DefaultListModel) this.ventana.getListaClasificiones()
						.getModel();
				modeloListaClasificaciones.addAll(torneo.getClasificaciones());
				this.ventana.getTextoReporte().setText(torneo.getEnfrentamientosTotal());
				this.ventana.getBotonSigEtapa().setVisible(false);
				this.ventana.getTextoSalidaGeneral().setText(this.ventana.getTextoSalidaGeneral().getText()
						+ this.torneo.getItEntrenadores().next().getNombre() + " gana el torneo.\n");
			}
			else
			{
				this.ventana.getTextoSalidaGeneral().setText(
						this.ventana.getTextoSalidaGeneral().getText() + "Se cargó un estado anterior del torneo.\n");
				this.torneo.correrTorneo();
				this.ventana.getBotonSigEtapa().setEnabled(true);
			}
		}
	}

	/**
	 * Descripcion: Se encarga de llevar a cabo las acciones de cada boton. <br>
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JButton boton = (JButton) arg0.getSource();
		String comando = arg0.getActionCommand();

		if (comando.equalsIgnoreCase("AGREGARENTRENADOR"))
		{
			Entrenador entrenador = new Entrenador(this.ventana.getTextoNombreEntrenador().getText());
			if (this.ventana.getCheckNiebla().isSelected())
				entrenador.addCarta(new Niebla());
			if (this.ventana.getCheckTormenta().isSelected())
				entrenador.addCarta(new Tormenta());
			if (this.ventana.getCheckViento().isSelected())
				entrenador.addCarta(new Viento());
			this.torneo.addEntrenador(entrenador);
			this.refrescarListaEntrenadores();
			this.ventana.getCheckNiebla().setSelected(false);
			this.ventana.getCheckTormenta().setSelected(false);
			this.ventana.getCheckViento().setSelected(false);
			this.ventana.getTextoNombreEntrenador().setText("");
			boton.setEnabled(false);
			this.ventana.getTextoSalidaGeneral().setText(this.ventana.getTextoSalidaGeneral().getText()
					+ "Se agregó correctamente al entrenador " + entrenador.getNombre() + ".\n");
			if (this.torneo.getCantEntrenadores() == Torneo.numeroEntrenadores)
				this.ventana.getBotonSigEtapa().setEnabled(true);
			else
				this.ventana.getBotonSigEtapa().setEnabled(false);
		}

		if (comando.equalsIgnoreCase("ELIMINARENTRENADOR"))
		{
			Entrenador entrenador = (Entrenador) this.ventana.getListaEntrenadoresVivos().getSelectedValue();
			this.torneo.removeEntrenador(entrenador);
			this.refrescarListaEntrenadores();
			this.ventana.getTextoSalidaGeneral().setText(this.ventana.getTextoSalidaGeneral().getText()
					+ "Se eliminó correctamente al entrenador " + entrenador.getNombre() + ".\n");
			if (this.torneo.getCantEntrenadores() == Torneo.numeroEntrenadores)
				this.ventana.getBotonSigEtapa().setEnabled(true);
			else
				this.ventana.getBotonSigEtapa().setEnabled(false);
		}

		if (comando.equalsIgnoreCase("AGREGARPOKEMON"))
		{
			Pokemon pokemon = PokemonFactory.getPokemon(this.ventana.getTextoNombrePokemon().getText(),
					this.ventana.getButtonGroup().getSelection().getActionCommand());
			Entrenador entrenador = (Entrenador) this.ventana.getListaEntrenadoresVivos().getSelectedValue();
			entrenador.addPokemon(pokemon);
			this.ventana.getTextoNombrePokemon().setText("");
			this.refrescarListaPokemones();
			this.ventana.getTextoSalidaGeneral()
					.setText(this.ventana.getTextoSalidaGeneral().getText() + "Se agregó correctamente al Pokemon "
							+ pokemon.getNombre() + " de " + entrenador.getNombre() + ".\n");
			boton.setEnabled(false);
		}

		if (comando.equalsIgnoreCase("ELIMINARPOKEMON"))
		{
			Pokemon pokemon = (Pokemon) this.ventana.getListaPokemones().getSelectedValue();
			Entrenador entrenador = (Entrenador) this.ventana.getListaEntrenadoresVivos().getSelectedValue();
			entrenador.removePokemon(pokemon);
			this.refrescarListaPokemones();
			this.ventana.getTextoSalidaGeneral()
					.setText(this.ventana.getTextoSalidaGeneral().getText() + "Se eliminó correctamente al Pokemon "
							+ pokemon.getNombre() + " de " + entrenador.getNombre() + ".\n");
			boton.setEnabled(false);
		}

		if (comando.equalsIgnoreCase("SIGUIENTEETAPA"))
		{
			if (this.torneo.getEtapa() != -1)
			{
				this.torneo.setEtapa(this.torneo.getEtapa() + 1);
				this.ventana.setEtapa(this.torneo.getEtapa());
				if (this.torneo.getCantArenas() < this.torneo.getCantArenasAnterior())
					this.ventana.getTextoSalidaGeneral()
							.setText(this.ventana.getTextoSalidaGeneral().getText() + "Se cerraron "
									+ (this.torneo.getCantArenasAnterior() - this.torneo.getCantArenas())
									+ " arenas ya que no son necesarias.\n");
				this.persitirTorneo();
				this.ventana.refrescarEtapa();
				this.refrescarListaEntrenadores();
				DefaultListModel modelo = (DefaultListModel) this.ventana.getListaPokemones().getModel();
				modelo.clear();
				this.torneo.correrTorneo();
				this.ventana.getBotonSigEtapa().setEnabled(true);
			}
			else
			{
				this.ventana.setEtapa(this.torneo.getEtapa());
				this.persitirTorneo();
				this.refrescarListaEntrenadores();
				this.ventana.refrescarEtapa();
				DefaultListModel modeloListaClasificaciones = (DefaultListModel) this.ventana.getListaClasificiones()
						.getModel();
				modeloListaClasificaciones.addAll(torneo.getClasificaciones());
				this.ventana.getTextoReporte().setText(torneo.getEnfrentamientosTotal());
				this.ventana.getBotonSigEtapa().setVisible(false);
				this.ventana.getTextoSalidaGeneral().setText(this.ventana.getTextoSalidaGeneral().getText()
						+ this.torneo.getItEntrenadores().next().getNombre() + " gana el torneo.\n");
			}
		}
	}

	/**
	 * Descripcion: Metodo necesario para la implementacion del patron Observer. Se
	 * encarga de llevar la informacion del estado de cada arena a sus respectivos
	 * paneles en la vista <br>
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		Arena arena = (Arena) arg0;
		IState estado = (IState) arg1;
		if (arena.getNumeroArena() == 0)
		{
			this.ventana.getTextoArena1().setText(this.ventana.getTextoArena1().getText() + estado.getMensaje() + "\n");
		}
		else
			if (arena.getNumeroArena() == 1)
			{
				this.ventana.getTextoArena2()
						.setText(this.ventana.getTextoArena2().getText() + estado.getMensaje() + "\n");
			}
			else
				if (arena.getNumeroArena() == 2)
				{
					this.ventana.getTextoArena3()
							.setText(this.ventana.getTextoArena3().getText() + estado.getMensaje() + "\n");
				}
				else
					if (arena.getNumeroArena() == 3)
					{
						this.ventana.getTextoArena4()
								.setText(this.ventana.getTextoArena4().getText() + estado.getMensaje() + "\n");
					}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
	}

	/**
	 * Descripcion: Agrega todas las arenas del conjunto presente en la instancia de
	 * Torneo a la lista de observables del controlador, para la implementacion del
	 * patron Observer <br>
	 */
	private void observarArenas()
	{
		Arena aux;
		Iterator<Arena> it = this.torneo.getItArenas();
		while (it.hasNext())
		{
			aux = it.next();
			aux.addObserver(this);
			this.observadas.add(aux);
		}
	}

	/**
	 * Descripcion: Refresca la lista de entrenadores de la vista.<br>
	 */
	private void refrescarListaEntrenadores()
	{
		this.ventana.getTextoVitalidad().setText("");
		this.ventana.getTextoFuerza().setText("");
		this.ventana.getTextoEscudo().setText("");
		Iterator<Entrenador> itEntrenadores = this.torneo.getItEntrenadores();
		DefaultListModel modeloListaEntrenadores = (DefaultListModel) this.ventana.getListaEntrenadoresVivos()
				.getModel();
		modeloListaEntrenadores.clear();
		while (itEntrenadores.hasNext())
			modeloListaEntrenadores.addElement(itEntrenadores.next());
	}

	/**
	 * Descripcion: Refresca el panel de pokemones de la vista. <br>
	 * <b>Pre:</b> Se debe tener un elemento no nulo seleccionado en la lista de
	 * entrenadores.
	 */
	private void refrescarListaPokemones()
	{
		this.ventana.getTextoVitalidad().setText("");
		this.ventana.getTextoFuerza().setText("");
		this.ventana.getTextoEscudo().setText("");
		JList listaPokemones = this.ventana.getListaPokemones();
		DefaultListModel modeloListaPokemones = (DefaultListModel) listaPokemones.getModel();
		Entrenador entrenador = (Entrenador) this.ventana.getListaEntrenadoresVivos().getSelectedValue();
		Iterator<Pokemon> itPokemones = entrenador.getItPokemones();
		DefaultListModel modeloListaEntrenadores = (DefaultListModel) this.ventana.getListaEntrenadoresVivos()
				.getModel();
		modeloListaPokemones.clear();
		itPokemones = entrenador.getItPokemones();
		while (itPokemones.hasNext())
			modeloListaPokemones.addElement(itPokemones.next());
	}

	/**
	 * Descripcion: Guarda el estado actual del torneo en el archivo Torneo.bin.<br>
	 */
	private void persitirTorneo()
	{
		try
		{
			persistencia.abrirOutput("Torneo.bin");
			persistencia.escribir(Torneo.getInstance());
			persistencia.cerrarOutput();
			this.ventana.getTextoSalidaGeneral()
					.setText(this.ventana.getTextoSalidaGeneral().getText() + "Se persistió el estado del torneo.\n");
		}
		catch (IOException e)
		{
			this.ventana.getTextoSalidaGeneral().setText(this.ventana.getTextoSalidaGeneral().getText()
					+ "No se puede persistir. Exception: " + e.getMessage() + "\n");
		}
	}
}
