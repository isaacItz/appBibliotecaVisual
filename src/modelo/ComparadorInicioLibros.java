package modelo;

import java.util.ArrayList;
import java.util.List;

public class ComparadorInicioLibros {

	public static final String ISBN = "Isbn";
	public static final String TITULO = "Titulo";
	public static final String AUTOR = "Autor";
	public static final String EDITORIAL = "Editorial";
	public static final String NO_EDICION = "No de Edicion";
	public static final String AÑO_EDICION = "Año de Edicion";
	public static final String IDIOMA = "Idioma";
	public static final String PAIS = "Pais";

	public static Libro[] concidencias(List<Libro> list, String criterio, String prefix) {

		List<Libro> libros = new ArrayList<>();

		switch (criterio) {
		case ISBN:
			for (Libro libro : list)
				if (libro.getIsbn().startsWith(prefix))
					libros.add(libro);
			break;
		case TITULO:
			for (Libro libro : list)
				if (libro.getTitulo().startsWith(prefix))
					libros.add(libro);
			break;
		case AUTOR:
			for (Libro libro : list)
				if (libro.getAutor().startsWith(prefix))
					libros.add(libro);
			break;
		case EDITORIAL:
			for (Libro libro : list)
				if (libro.getEditoria().startsWith(prefix))
					libros.add(libro);
			break;
		case NO_EDICION:
			for (Libro libro : list)
				if (libro.getNumeroEdicion().startsWith(prefix))
					libros.add(libro);
			break;
		case AÑO_EDICION:
			for (Libro libro : list)
				if (libro.getAnioEdicion().startsWith(prefix))
					libros.add(libro);
			break;
		case IDIOMA:
			for (Libro libro : list)
				if (libro.getIdioma().startsWith(prefix))
					libros.add(libro);
			break;
		case PAIS:
			for (Libro libro : list)
				if (libro.getPais().startsWith(prefix))
					libros.add(libro);
			break;
		}

		return (Libro[]) libros.toArray(new Libro[libros.size()]);
	}



}
