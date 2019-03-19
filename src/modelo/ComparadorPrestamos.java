package modelo;

import java.util.Comparator;

public class ComparadorPrestamos implements Comparator<Prestamo> {

	private String criterio;
	public static final String NUMERO_DE_CONTROL = "Numero de Control";
	public static final String ISBN = "ISBN";
	public static final String FECHA_PRESTAMO = "Fecha del Prestamo";
	public static final String FECHA_DEVOLUCION = "Fecha de Devolucion";

	public ComparadorPrestamos(String cr) {
		criterio = cr;
	}

	public static String[] getCriterios() {
		String lista[] = new String[4];
		lista[1] = ISBN;
		lista[0] = NUMERO_DE_CONTROL;
		lista[2] = FECHA_PRESTAMO;
		lista[3] = FECHA_DEVOLUCION;

		return lista;

	}

	@Override
	public int compare(Prestamo p1, Prestamo p2) {
		switch (criterio) {
		case NUMERO_DE_CONTROL:
			return p1.getAlumno().getNoControl().compareTo(p2.getAlumno().getNoControl());
		case ISBN:
			return p1.getLibro().getIsbn().compareTo(p2.getLibro().getIsbn());
		case FECHA_PRESTAMO:
			return p1.getFechaDePrestamo().compareTo(p2.getFechaDeEntrega());
		case FECHA_DEVOLUCION:
			return p1.getFechaDeEntrega().compareTo(p2.getFechaDeEntrega());
		}

		return 0;
	}

}
