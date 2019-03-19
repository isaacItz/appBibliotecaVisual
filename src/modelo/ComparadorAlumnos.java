package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparadorAlumnos implements Comparator<Alumno> {
	public final static String NOCONTROL = "Numero de Control";
	public final static String NOMBRE = "Nombre";
	public final static String FECHANAC = "Fecha de Nacimiento";
	public final static String GENERO = "Genero";
	public final static String SEMESTRE = "Semestre";
	public final static String CARRERA = "Carrera";
	private String criterio;

	public ComparadorAlumnos(String c) {
		criterio = c;
	}

	public static String[] getCriterios() {
		String[] c = new String[6];
		c[0] = NOCONTROL;
		c[1] = NOMBRE;
		c[2] = FECHANAC;
		c[3] = GENERO;
		c[4] = SEMESTRE;
		c[5] = CARRERA;
		return c;
	}

	@Override
	public int compare(Alumno a1, Alumno a2) {
		int v = 0;
		switch (criterio) {

		case NOCONTROL:
			v = a1.getNoControl().compareTo(a2.getNoControl());
			break;
		case NOMBRE:
			v = a1.getNombre().compareTo(a2.getNombre());
			break;
		case FECHANAC:
			v = a1.getFechaNac().compareTo(a2.getFechaNac());
			break;
		case GENERO:
			v = String.valueOf(a1.getGenero()).compareTo(String.valueOf(a2.getGenero()));
			break;
		case SEMESTRE:
			v = String.valueOf(a1.getSemestre()).compareTo(String.valueOf(a2.getSemestre()));
			break;
		case CARRERA:
			v = a1.getCarrera().compareTo(a2.getCarrera());
			break;
		}
		return v;
	}
}
