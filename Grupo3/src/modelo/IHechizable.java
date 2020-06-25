package modelo;

public interface IHechizable
{
	/**
	 * Descripcion: Metodo que implementan las subclases para actuar frente a carta Niebla proveniente del adversario.<br>
	 */
	void recibeNiebla();
	
	/**
	 * Descripcion: Metodo que implementan las subclases para actuar frente a carta Viento proveniente del adversario.<br>
	 */
	void recibeViento();
	
	/**
	 * Descripcion: Metodo que implementan las subclases para actuar frente a carta Tormenta proveniente del adversario.<br>
	 */
	void recibeTormenta();
}
