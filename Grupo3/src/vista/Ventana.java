package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Entrenador;
import modelo.Pokemon;
/**
 * @author Grupo 3.
 * <br>
 * Clase Ventana.
 * <br>
 * Descripcion: La clase Ventana se extiende de JFrame e implementa las interfaces KeyListener y ListSelectionListener. Esta clase conecta el controlador con el usuario.<br>
 */

public class Ventana extends JFrame implements KeyListener, ListSelectionListener
{

	private JPanel panelTodo;
	private JPanel panelInferior;
	private JButton botonSigEtapa;
	private JScrollPane scrollSalidaGeneral;
	private JPanel panelDerecho;
	private JPanel panelEntrenadoresVivos;
	private JPanel panelPokemones;
	private JScrollPane scrollEntrenadoresVivos;
	private JScrollPane scrollPokemones;
	private JList listaEntrenadoresVivos;
	private JList listaPokemones;
	private JPanel switchEtapa;
	private JPanel panelEtapa1;
	private JLabel labelSalidaGeneral;
	private JLabel labelNombreEntrenador;
	private JLabel labelNiebla;
	private JLabel labelTormenta;
	private JCheckBox checkNiebla;
	private JCheckBox checkTormenta;
	private JCheckBox checkViento;
	private JLabel labelViento;
	private JButton botonAltaEntrenador;
	private JTextField textoNombreEntrenador;
	private ActionListener actionListener;
	private ListSelectionListener listSelectionListener;
	private int etapa;
	private DefaultListModel modeloListaEntrenadores = new DefaultListModel();
	private DefaultListModel modeloListaPokemones = new DefaultListModel();
	private DefaultListModel modeloListaClasificaciones = new DefaultListModel();
	private JButton botonBajaEntrenador;
	private JTextArea textoSalidaGeneral;
	private JPanel panelEtapa2;
	private JPanel panelEtapa;
	private JLabel labelEtapa;
	private JLabel lblNewLabel;
	private JTextField textoNombrePokemon;
	private JRadioButton radioAgua;
	private JRadioButton radioAire;
	private JRadioButton radioHielo;
	private JRadioButton radioElectrico;
	private JRadioButton radioFuego;
	private JRadioButton radioTierra;
	private JButton botonBajaPokemon;
	private JButton botonAltaPokemon;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelEtapaBatalla;
	private JPanel panelArena1;
	private JPanel panelArena2;
	private JPanel panelArena3;
	private JPanel panelArena4;
	private JLabel labelArena4;
	private JLabel labelArena3;
	private JLabel labelArena1;
	private JLabel labelArena2;
	private JScrollPane scrollArena1;
	private JScrollPane scrollArena2;
	private JScrollPane scrollArena3;
	private JScrollPane scrollArena4;
	private JTextArea textoArena1;
	private JTextArea textoArena2;
	private JTextArea textoArena3;
	private JTextArea textoArena4;
	private JPanel panelEtapaFinal;
	private JScrollPane scrollClasificaciones;
	private JScrollPane scrollReporte;
	private JList listaClasificaciones;
	private JLabel labelClasificaciones;
	private JLabel labelReporte;
	private JLabel labelEntrenadoresVivos;
	private JLabel labelPokemones;
	private JTextArea textoReporte;
	private JPanel panelDetallePokemones;
	private JLabel labelVitalidad;
	private JLabel labelFuerza;
	private JLabel labelEscudo;
	private JTextPane textoVitalidad;
	private JTextPane textoFuerza;
	private JTextPane textoEscudo;
	
	/**
	 * Descripcion: Constructor encargado de generar todas las componentes.<br>
	 */
	public Ventana(int etapa)
	{
		setResizable(false);
		this.etapa = etapa;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1305, 604);
		this.panelTodo = new JPanel();
		this.panelTodo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.panelTodo);
		this.panelTodo.setLayout(null);

		this.panelInferior = new JPanel();
		this.panelInferior.setBounds(5, 476, 1281, 91);
		this.panelTodo.add(this.panelInferior);
		this.panelInferior.setLayout(null);

		this.botonSigEtapa = new JButton("Siguiente");
		this.botonSigEtapa.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.botonSigEtapa.setActionCommand("SIGUIENTEETAPA");
		this.botonSigEtapa.setBounds(1159, 0, 122, 91);
		this.botonSigEtapa.setEnabled(false);
		this.panelInferior.add(this.botonSigEtapa);

		this.scrollSalidaGeneral = new JScrollPane();
		this.scrollSalidaGeneral.setBounds(0, 0, 1149, 91);
		this.panelInferior.add(this.scrollSalidaGeneral);

		this.labelSalidaGeneral = new JLabel("Anuncios");
		this.labelSalidaGeneral.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.scrollSalidaGeneral.setColumnHeaderView(this.labelSalidaGeneral);

		this.textoSalidaGeneral = new JTextArea();
		this.textoSalidaGeneral.setEditable(false);
		this.scrollSalidaGeneral.setViewportView(this.textoSalidaGeneral);

		this.panelDerecho = new JPanel();
		this.panelDerecho.setBounds(886, 17, 400, 449);
		this.panelTodo.add(this.panelDerecho);
		this.panelDerecho.setLayout(null);

		this.panelEntrenadoresVivos = new JPanel();
		this.panelEntrenadoresVivos.setBounds(0, 0, 200, 449);
		this.panelDerecho.add(this.panelEntrenadoresVivos);
		this.panelEntrenadoresVivos.setLayout(new BorderLayout(0, 0));

		this.scrollEntrenadoresVivos = new JScrollPane();
		this.panelEntrenadoresVivos.add(this.scrollEntrenadoresVivos);

		this.listaEntrenadoresVivos = new JList();
		this.listaEntrenadoresVivos.addListSelectionListener(this);
		this.listaEntrenadoresVivos.setModel(this.modeloListaEntrenadores);
		this.scrollEntrenadoresVivos.setViewportView(this.listaEntrenadoresVivos);

		this.labelEntrenadoresVivos = new JLabel("Entrenadores de la etapa");
		this.labelEntrenadoresVivos.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelEntrenadoresVivos.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.scrollEntrenadoresVivos.setColumnHeaderView(this.labelEntrenadoresVivos);

		this.panelPokemones = new JPanel();
		this.panelPokemones.setBounds(200, 0, 200, 449);
		this.panelDerecho.add(this.panelPokemones);
		this.panelPokemones.setLayout(new BorderLayout(0, 0));

		this.scrollPokemones = new JScrollPane();
		this.panelPokemones.add(this.scrollPokemones);

		this.listaPokemones = new JList();
		this.listaPokemones.addListSelectionListener(this);
		this.listaPokemones.setModel(this.modeloListaPokemones);
		this.scrollPokemones.setViewportView(this.listaPokemones);

		this.labelPokemones = new JLabel("Pokemones");
		this.labelPokemones.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.labelPokemones.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPokemones.setColumnHeaderView(this.labelPokemones);
		
		this.panelDetallePokemones = new JPanel();
		this.panelPokemones.add(this.panelDetallePokemones, BorderLayout.SOUTH);
		this.panelDetallePokemones.setLayout(new GridLayout(3, 2, 0, 3));
		
		this.labelVitalidad = new JLabel("Vitalidad: ");
		this.labelVitalidad.setHorizontalAlignment(SwingConstants.TRAILING);
		this.labelVitalidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panelDetallePokemones.add(this.labelVitalidad);
		
		this.textoVitalidad = new JTextPane();
		this.textoVitalidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textoVitalidad.setEditable(false);
		this.panelDetallePokemones.add(this.textoVitalidad);
		
		this.labelFuerza = new JLabel("Fuerza: ");
		this.labelFuerza.setHorizontalAlignment(SwingConstants.TRAILING);
		this.labelFuerza.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panelDetallePokemones.add(this.labelFuerza);
		
		this.textoFuerza = new JTextPane();
		this.textoFuerza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textoFuerza.setEditable(false);
		this.panelDetallePokemones.add(this.textoFuerza);
		
		this.labelEscudo = new JLabel("Escudo: ");
		this.labelEscudo.setHorizontalAlignment(SwingConstants.TRAILING);
		this.labelEscudo.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panelDetallePokemones.add(this.labelEscudo);
		
		this.textoEscudo = new JTextPane();
		this.textoEscudo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textoEscudo.setEditable(false);
		this.panelDetallePokemones.add(this.textoEscudo);

		this.panelEtapa = new JPanel();
		this.panelEtapa.setBounds(5, 15, 871, 451);
		this.panelTodo.add(this.panelEtapa);
		this.panelEtapa.setLayout(new BorderLayout(0, 0));

		this.switchEtapa = new JPanel();
		this.panelEtapa.add(this.switchEtapa);
		this.switchEtapa.setToolTipText("");
		this.switchEtapa.setLayout(new CardLayout(0, 0));

		this.panelEtapa2 = new JPanel();
		this.switchEtapa.add(this.panelEtapa2, "ETAPA2");
		this.panelEtapa2.setLayout(new GridLayout(5, 2, 0, 0));

		this.lblNewLabel = new JLabel("Nombre: ");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		this.panelEtapa2.add(this.lblNewLabel);

		this.textoNombrePokemon = new JTextField();
		this.textoNombrePokemon.addKeyListener(this);
		this.textoNombrePokemon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panelEtapa2.add(this.textoNombrePokemon);
		this.textoNombrePokemon.setColumns(10);

		this.radioAgua = new JRadioButton("Agua");
		this.radioAgua.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.radioAgua.setSelected(true);
		this.radioAgua.setActionCommand("AGUA");
		buttonGroup.add(this.radioAgua);
		this.radioAgua.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelEtapa2.add(this.radioAgua);

		this.radioAire = new JRadioButton("Aire");
		this.radioAire.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.radioAire.setActionCommand("AIRE");
		buttonGroup.add(this.radioAire);
		this.radioAire.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelEtapa2.add(this.radioAire);

		this.radioElectrico = new JRadioButton("Eléctrico");
		this.radioElectrico.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.radioElectrico.setActionCommand("ELECTRICO");
		buttonGroup.add(this.radioElectrico);
		this.radioElectrico.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelEtapa2.add(this.radioElectrico);

		this.radioFuego = new JRadioButton("Fuego");
		this.radioFuego.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.radioFuego.setActionCommand("FUEGO");
		buttonGroup.add(this.radioFuego);
		this.radioFuego.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelEtapa2.add(this.radioFuego);

		this.radioHielo = new JRadioButton("Hielo");
		this.radioHielo.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.radioHielo.setActionCommand("HIELO");
		buttonGroup.add(this.radioHielo);
		this.radioHielo.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelEtapa2.add(this.radioHielo);

		this.radioTierra = new JRadioButton("Tierra");
		this.radioTierra.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.radioTierra.setActionCommand("TIERRA");
		buttonGroup.add(this.radioTierra);
		this.radioTierra.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelEtapa2.add(this.radioTierra);

		this.botonBajaPokemon = new JButton("Eliminar Pokemon");
		this.botonBajaPokemon.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.botonBajaPokemon.setActionCommand("ELIMINARPOKEMON");
		this.botonBajaPokemon.setEnabled(false);
		this.panelEtapa2.add(this.botonBajaPokemon);

		this.botonAltaPokemon = new JButton("Agregar Pokemon");
		this.botonAltaPokemon.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.botonAltaPokemon.setActionCommand("AGREGARPOKEMON");
		this.botonAltaPokemon.setEnabled(false);
		this.panelEtapa2.add(this.botonAltaPokemon);

		this.panelEtapa1 = new JPanel();
		this.panelEtapa1.setLayout(new GridLayout(5, 2, 0, 0));
		this.switchEtapa.add(this.panelEtapa1, "ETAPA1");

		this.labelNombreEntrenador = new JLabel("Nombre: ");
		this.labelNombreEntrenador.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.labelNombreEntrenador.setHorizontalAlignment(SwingConstants.TRAILING);
		this.panelEtapa1.add(this.labelNombreEntrenador);

		this.textoNombreEntrenador = new JTextField();
		this.textoNombreEntrenador.addKeyListener(this);
		this.textoNombreEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textoNombreEntrenador.setColumns(10);
		this.panelEtapa1.add(this.textoNombreEntrenador);

		this.labelNiebla = new JLabel("Carta Hechizo Niebla ");
		this.labelNiebla.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.labelNiebla.setHorizontalAlignment(SwingConstants.TRAILING);
		this.panelEtapa1.add(this.labelNiebla);

		this.checkNiebla = new JCheckBox("");
		this.panelEtapa1.add(this.checkNiebla);

		this.labelTormenta = new JLabel("Carta Hechizo Tormenta ");
		this.labelTormenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.labelTormenta.setHorizontalAlignment(SwingConstants.TRAILING);
		this.panelEtapa1.add(this.labelTormenta);

		this.checkTormenta = new JCheckBox("");
		this.panelEtapa1.add(this.checkTormenta);

		this.labelViento = new JLabel("Carta Hechizo Viento ");
		this.labelViento.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.labelViento.setHorizontalAlignment(SwingConstants.TRAILING);
		this.panelEtapa1.add(this.labelViento);

		this.checkViento = new JCheckBox("");
		this.panelEtapa1.add(this.checkViento);

		this.botonBajaEntrenador = new JButton("Eliminar entrenador seleccionado");
		this.botonBajaEntrenador.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.botonBajaEntrenador.setActionCommand("ELIMINARENTRENADOR");
		this.botonBajaEntrenador.setEnabled(false);
		this.panelEtapa1.add(this.botonBajaEntrenador);

		this.botonAltaEntrenador = new JButton("Agregar entrenador");
		this.botonAltaEntrenador.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.botonAltaEntrenador.setActionCommand("AGREGARENTRENADOR");
		this.botonAltaEntrenador.setEnabled(false);
		this.panelEtapa1.add(this.botonAltaEntrenador);

		this.panelEtapaBatalla = new JPanel();
		this.switchEtapa.add(this.panelEtapaBatalla, "ETAPABATALLA");
		this.panelEtapaBatalla.setLayout(new GridLayout(0, 4, 0, 0));

		this.panelArena1 = new JPanel();
		this.panelEtapaBatalla.add(this.panelArena1);
		this.panelArena1.setLayout(new BorderLayout(0, 0));

		this.labelArena1 = new JLabel("Arena 1");
		this.labelArena1.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelArena1.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.panelArena1.add(this.labelArena1, BorderLayout.NORTH);

		this.scrollArena1 = new JScrollPane();
		this.panelArena1.add(this.scrollArena1, BorderLayout.CENTER);

		this.textoArena1 = new JTextArea();
		this.textoArena1.setEditable(false);
		this.scrollArena1.setViewportView(this.textoArena1);

		this.panelArena2 = new JPanel();
		this.panelEtapaBatalla.add(this.panelArena2);
		this.panelArena2.setLayout(new BorderLayout(0, 0));

		this.labelArena2 = new JLabel("Arena 2");
		this.labelArena2.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.labelArena2.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelArena2.add(this.labelArena2, BorderLayout.NORTH);

		this.scrollArena2 = new JScrollPane();
		this.panelArena2.add(this.scrollArena2, BorderLayout.CENTER);

		this.textoArena2 = new JTextArea();
		this.textoArena2.setEditable(false);
		this.scrollArena2.setViewportView(this.textoArena2);

		this.panelArena3 = new JPanel();
		this.panelEtapaBatalla.add(this.panelArena3);
		this.panelArena3.setLayout(new BorderLayout(0, 0));

		this.labelArena3 = new JLabel("Arena 3");
		this.labelArena3.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelArena3.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.panelArena3.add(this.labelArena3, BorderLayout.NORTH);

		this.scrollArena3 = new JScrollPane();
		this.panelArena3.add(this.scrollArena3, BorderLayout.CENTER);

		this.textoArena3 = new JTextArea();
		this.textoArena3.setEditable(false);
		this.scrollArena3.setViewportView(this.textoArena3);

		this.panelArena4 = new JPanel();
		this.panelEtapaBatalla.add(this.panelArena4);
		this.panelArena4.setLayout(new BorderLayout(0, 0));

		this.labelArena4 = new JLabel("Arena 4");
		this.labelArena4.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.labelArena4.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelArena4.add(this.labelArena4, BorderLayout.NORTH);

		this.scrollArena4 = new JScrollPane();
		this.panelArena4.add(this.scrollArena4, BorderLayout.CENTER);

		this.textoArena4 = new JTextArea();
		this.textoArena4.setEditable(false);
		this.scrollArena4.setViewportView(this.textoArena4);

		this.panelEtapaFinal = new JPanel();
		this.switchEtapa.add(this.panelEtapaFinal, "ETAPAFINAL");
		this.panelEtapaFinal.setLayout(new GridLayout(1, 2, 0, 0));

		this.scrollClasificaciones = new JScrollPane();
		this.panelEtapaFinal.add(this.scrollClasificaciones);

		this.listaClasificaciones = new JList();
		this.listaClasificaciones.setModel(this.modeloListaClasificaciones);
		this.scrollClasificaciones.setViewportView(this.listaClasificaciones);

		this.labelClasificaciones = new JLabel("Clasificaciones");
		this.labelClasificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelClasificaciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.scrollClasificaciones.setColumnHeaderView(this.labelClasificaciones);

		this.scrollReporte = new JScrollPane();
		this.panelEtapaFinal.add(this.scrollReporte);

		this.labelReporte = new JLabel("Reporte de enfrentamientos");
		this.labelReporte.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelReporte.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.scrollReporte.setColumnHeaderView(this.labelReporte);

		this.textoReporte = new JTextArea();
		this.textoReporte.setEditable(false);
		this.scrollReporte.setViewportView(this.textoReporte);

		this.labelEtapa = new JLabel("ETAPA X");
		this.labelEtapa.setBounds(5, 0, 755, 15);
		this.panelTodo.add(this.labelEtapa);
		this.labelEtapa.setFont(new Font("Tahoma", Font.BOLD, 12));

		this.setVisible(true);
	}

	public void keyPressed(KeyEvent arg0)
	{
	}
	
	/**
	 * Descripcion: Validacion de condiciones para habilitar o no el boton de AltaPokemon y de AltaEntrenador.<br>
	 * @param KeyEvent arg0 evento de la vista a evaluar.
	 */
	public void keyReleased(KeyEvent arg0)
	{
		JTextField texto = (JTextField) arg0.getSource();
		if (this.etapa == 1)
			if (!texto.getText().isEmpty() && !texto.getText().isBlank())
				this.botonAltaEntrenador.setEnabled(true);
			else
				this.botonAltaEntrenador.setEnabled(false);
		if (this.etapa == 2)
			if (!texto.getText().isEmpty() && !texto.getText().isBlank()
					&& this.listaEntrenadoresVivos.getSelectedValue() != null)
				this.botonAltaPokemon.setEnabled(true);
			else
				this.botonAltaPokemon.setEnabled(false);
	}

	public void keyTyped(KeyEvent arg0)
	{
	}

	public void setActionListener(ActionListener a)
	{
		this.botonAltaEntrenador.addActionListener(a);
		this.botonBajaEntrenador.addActionListener(a);
		this.botonAltaPokemon.addActionListener(a);
		this.botonBajaPokemon.addActionListener(a);
		this.botonSigEtapa.addActionListener(a);
		this.actionListener = a;
	}

	public void setListSelectionListener(ListSelectionListener l)
	{
		this.listaEntrenadoresVivos.addListSelectionListener(l);
		this.listaPokemones.addListSelectionListener(l);
		this.listaClasificaciones.addListSelectionListener(l);
	}

	public JCheckBox getCheckNiebla()
	{
		return this.checkNiebla;
	}

	public JCheckBox getCheckTormenta()
	{
		return this.checkTormenta;
	}

	public JCheckBox getCheckViento()
	{
		return this.checkViento;
	}

	public JTextField getTextoNombreEntrenador()
	{
		return this.textoNombreEntrenador;
	}

	public JList getListaEntrenadoresVivos()
	{
		return this.listaEntrenadoresVivos;
	}
	/**
	 * Descripcion: Validacion de condiciones para habilitar o no el boton de BajaEntrenador y de BajaPokemon , y refrezca las listas al seleccionar un entrenador<br>
	 * @param ListSelectionEvent arg0 evento de la vista a evaluar.
	 */
	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
		JList lista = (JList) arg0.getSource();
		if (lista == this.listaEntrenadoresVivos)
			if (this.etapa == 1)
				if (lista.getSelectedValue() != null)
					this.botonBajaEntrenador.setEnabled(true);
				else
					this.botonBajaEntrenador.setEnabled(false);
			else
			{
				this.modeloListaPokemones.clear();
				if (lista.getSelectedValue() != null)
				{
					Entrenador entrenador = (Entrenador) lista.getSelectedValue();
					Iterator<Pokemon> it = entrenador.getItPokemones();
					while (it.hasNext())
						this.modeloListaPokemones.addElement(it.next());
				}
				if (this.etapa == 2)
				{
					if (!this.textoNombrePokemon.getText().isEmpty() && !textoNombrePokemon.getText().isBlank()
							&& lista.getSelectedValue() != null)
						this.botonAltaPokemon.setEnabled(true);
					else
						this.botonAltaPokemon.setEnabled(false);
				}
			}
		else
			if (lista.getSelectedValue() != null)
			{
				Pokemon pokemon = (Pokemon) lista.getSelectedValue();
				this.botonBajaPokemon.setEnabled(true);
				this.textoVitalidad.setText("" + pokemon.getVitalidad());
				this.textoFuerza.setText("" + pokemon.getFuerza());
				this.textoEscudo.setText("" + pokemon.getEscudo());
			}
			else
			{
				this.botonBajaPokemon.setEnabled(false);
				this.textoVitalidad.setText("");
				this.textoFuerza.setText("");
				this.textoEscudo.setText("");
			}
	}

	public JList getListaPokemones()
	{
		return this.listaPokemones;
	}

	public JTextArea getTextoSalidaGeneral()
	{
		return this.textoSalidaGeneral;
	}

	public JButton getBotonSigEtapa()
	{
		return this.botonSigEtapa;
	}

	public void setEtapa(int etapa)
	{
		this.etapa = etapa;
	}

	/**
	 * Descripcion: Metodo encargado de actualizar el panel segun la etapa.<br>
	 */
	public void refrescarEtapa()
	{
		CardLayout layout = (CardLayout) this.switchEtapa.getLayout();
		this.botonSigEtapa.setEnabled(false);
		if (this.etapa == -1)
		{
			this.labelEtapa.setText("Última etapa - Finalización");
			this.textoSalidaGeneral.setText(this.textoSalidaGeneral.getText() + "Comienza la etapa de Finalización.\n");
			layout.show(this.switchEtapa, "ETAPAFINAL");
		}
		if (this.etapa == 1)
		{
			this.labelEtapa.setText("Etapa: " + this.etapa + " - Alta de Entrenadores");
			this.textoSalidaGeneral.setText(this.textoSalidaGeneral.getText() + "Comienza la etapa de Alta de Entrenadores.\n");
			layout.show(this.switchEtapa, "ETAPA1");
		}
		else
			if (this.etapa == 2)
			{
				this.botonSigEtapa.setEnabled(true);
				this.labelEtapa.setText("Etapa: " + this.etapa + " - Alta de Pokemones");
				this.textoSalidaGeneral.setText(this.textoSalidaGeneral.getText() + "Comienza la etapa de Alta de Pokemones.\n");
				layout.show(this.switchEtapa, "ETAPA2");
			}
			else
				if (this.etapa >= 3)
				{
					this.labelEtapa.setText("Etapa: " + this.etapa + " - Batalla");
					this.textoSalidaGeneral.setText(this.textoSalidaGeneral.getText() + "Comienza la etapa de batalla  " + (this.etapa - 2) + ".\n");
					layout.show(this.switchEtapa, "ETAPABATALLA");
					this.textoArena1.setText("");
					this.textoArena2.setText("");
					this.textoArena3.setText("");
					this.textoArena4.setText("");
				}
	}

	public ButtonGroup getButtonGroup()
	{
		return this.buttonGroup;
	}

	public JTextField getTextoNombrePokemon()
	{
		return this.textoNombrePokemon;
	}

	public JTextArea getTextoArena1()
	{
		return this.textoArena1;
	}

	public JTextArea getTextoArena2()
	{
		return this.textoArena2;
	}

	public JTextArea getTextoArena3()
	{
		return this.textoArena3;
	}

	public JTextArea getTextoArena4()
	{
		return this.textoArena4;
	}

	public JList getListaClasificiones()
	{
		return this.listaClasificaciones;
	}

	public JTextArea getTextoReporte()
	{
		return this.textoReporte;
	}
	
	public JTextPane getTextoVitalidad()
	{
		return this.textoVitalidad;
	}
	
	public JTextPane getTextoFuerza()
	{
		return this.textoFuerza;
	}
	
	public JTextPane getTextoEscudo()
	{
		return this.textoEscudo;
	}
}
