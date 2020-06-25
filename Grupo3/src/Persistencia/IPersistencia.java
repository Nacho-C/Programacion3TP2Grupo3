package Persistencia;

import java.io.IOException;

public interface IPersistencia<T>
{

	void abrirInput(String nombre) throws IOException;

	void abrirOutput(String nombre) throws IOException;

	void cerrarInput() throws IOException;

	void cerrarOutput() throws IOException;

	void escribir(T objeto) throws IOException;

	T leer() throws IOException, ClassNotFoundException;
}
