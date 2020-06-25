package modelo;

/**
 * @author Grupo 3. <br>
 *         Interface IState. <br>
 *         Descripcion: Interfaz encargada de contener los metodos necesarios
 *         para llevar a cabo el Patrón State<br>
 */
public interface IState {
	void correr();

	/**
	 * Descripción: Retorna al observador el mensaje del estado actual.<br>
	 * 
	 * @return String .<br>
	 */
	String getMensaje();
}
