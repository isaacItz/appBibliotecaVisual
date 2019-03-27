package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estante implements Serializable {
	private List<Libro> estante;

	public Estante() {
		estante = new ArrayList<>();
	}

	public void agregar(Libro libro) {
		estante.add(libro);
	}

	public void agregar(Libro[] libro) {

		for (Libro libro2 : libro) {
			if (getLibro(libro2.getIsbn()) == null)
				estante.add(libro2);
		}
	}

	public String vizualizar() {
		String salida = "Los libros son: \n";
		for (Libro libro : estante)
			salida += libro + "\n";

		return salida;
	}

	public Libro getLibro(int i) {
		return estante.get(i);
	}

	public boolean existeLibro(String isbn) {

		Libro libro = new Libro(isbn);

		return estante.contains(libro);
	}

	public boolean eliminar(Libro l) {
		return estante.remove(l);
	}

	public Libro getLibro(String isbn) {
		// Libro libroC = new Libro(isbn);
		for (Libro libro : estante) {
			if (libro.getIsbn().compareTo(isbn) == 0)
				return libro;
		}
		return null;
	}

	public Object[][] getMatriz() {
		List<Object[]> lista = new ArrayList<>();

		for (int i = 0; i < estante.size(); i++) {
			Object[] linea = new Object[8];
			Libro l = estante.get(i);
			linea[0] = l.getIsbn();
			linea[1] = l.getTitulo();
			linea[2] = l.getAutor();
			linea[3] = l.getEditoria();
			linea[4] = l.getNumeroEdicion();
			linea[5] = l.getAnioEdicion();
			linea[6] = l.getIdioma();
			linea[7] = l.getPais();
			lista.add(linea);
		}

		return (Object[][]) lista.toArray(new Object[lista.size()][]);

	}

	public ArrayList<Libro> getList() {
		return (ArrayList<Libro>) estante;
	}

}
