package modelo;

import java.util.Comparator;

public class ComparadorLibros implements Comparator<Libro> {

	private String criterio;
	public static final String ISBN = "Isbn";
	public static final String TITULO = "Titulo";
	public static final String AUTOR = "Autor";
	public static final String EDITORIAL = "Editorial";
	public static final String NO_EDICION = "No de Edicion";
	public static final String AÑO_EDICION = "Año de Edicion";
	public static final String IDIOMA = "Idioma";
	public static final String PAIS = "Pais";
	private int orden;

	public ComparadorLibros(String criterio, int orden) {
		super();
		this.criterio = criterio;
		this.orden = orden;
	}

	public ComparadorLibros(String criterio) {
		super();
		this.criterio = criterio;
		orden = 1;
	}

	@Override
	public int compare(Libro l1, Libro l2) {

		switch (criterio) {
		case ISBN:
			return l1.getIsbn().compareTo(l2.getIsbn()) * orden;

		case TITULO:
			return l1.getTitulo().compareTo(l2.getTitulo()) * orden;

		case AUTOR:
			return l1.getAutor().compareTo(l2.getAutor()) * orden;

		case EDITORIAL:
			return l1.getEditoria().compareTo(l2.getEditoria()) * orden;

		case NO_EDICION:
			return l1.getNumeroEdicion().compareTo(l2.getNumeroEdicion()) * orden;

		case AÑO_EDICION:
			return l1.getAnioEdicion().compareTo(l2.getAnioEdicion()) * orden;

		case IDIOMA:
			return l1.getIdioma().compareTo(l2.getIdioma()) * orden;

		case PAIS:
			return l1.getIdioma().compareTo(l2.getIdioma()) * orden;
		}

		return 0;
	}

	public static String[] getCriterios() {
		String[] c = new String[8];
		c[0] = ISBN;
		c[1] = TITULO;
		c[2] = AUTOR;
		c[3] = EDITORIAL;
		c[4] = NO_EDICION;
		c[5] = AÑO_EDICION;
		c[6] = IDIOMA;
		c[7] = PAIS;
		return c;
	}

}
