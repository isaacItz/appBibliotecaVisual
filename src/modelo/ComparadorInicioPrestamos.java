package modelo;

import java.util.ArrayList;
import java.util.List;

public class ComparadorInicioPrestamos {

	public static final String NUMERO_DE_CONTROL = "Numero de Control";
	public static final String ISBN = "ISBN";
	public static final String FECHA_PRESTAMO = "Fecha del Prestamo";
	public static final String FECHA_DEVOLUCION = "Fecha de Devolucion";

	public static Prestamo[] concidencias(List<Prestamo> list, String criterio, String prefix) {

		List<Prestamo> prestamos = new ArrayList<>();

		switch (criterio) {
		case NUMERO_DE_CONTROL:
			for (Prestamo prestamo : list)
				if (prestamo.getAlumno().getNoControl().startsWith(prefix))
					prestamos.add(prestamo);
			break;
		case ISBN:
			for (Prestamo prestamo : list)
				if (prestamo.getLibro().getIsbn().startsWith(prefix))
					prestamos.add(prestamo);
			break;
		case FECHA_PRESTAMO:
			for (Prestamo prestamo : list)
				if (prestamo.getFechaDePrestamo().toString().startsWith(prefix))
					prestamos.add(prestamo);
			break;
		case FECHA_DEVOLUCION:
			for (Prestamo prestamo : list)
				if (prestamo.getFechaDeEntrega().toString().startsWith(prefix))
					prestamos.add(prestamo);
			break;

		}

		return (Prestamo[]) prestamos.toArray(new Prestamo[prestamos.size()]);
	}

	public static String[] getCriterios() {
		String lista[] = new String[4];
		lista[1] = ISBN;
		lista[0] = NUMERO_DE_CONTROL;
		lista[2] = FECHA_PRESTAMO;
		lista[3] = FECHA_DEVOLUCION;

		return lista;

	}

}
