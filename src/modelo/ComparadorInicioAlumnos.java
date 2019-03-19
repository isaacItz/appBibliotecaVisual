package modelo;

import java.util.ArrayList;
import java.util.List;

public class ComparadorInicioAlumnos {

	public final static String NOCONTROL = "Numero de Control";
	public final static String NOMBRE = "Nombre";
	public final static String FECHANAC = "Fecha de Nacimiento";
	public final static String GENERO = "Genero";
	public final static String SEMESTRE = "Semestre";
	public final static String CARRERA = "Carrera";

	public static Alumno[] concidenciasA(List<Alumno> list, String criterio, String prefix) {

		List<Alumno> alumnos = new ArrayList<>();

		switch (criterio) {
		case NOCONTROL:
			for (Alumno alumno : list)
				if (alumno.getNoControl().startsWith(prefix))
					alumnos.add(alumno);
			break;
		case NOMBRE:
			for (Alumno a : list)
				if (a.getNombre().concat(" ").concat(a.getPaterno().concat(" ").concat(a.getMaterno()))
						.startsWith(prefix))
					alumnos.add(a);
			break;
		case FECHANAC:
			for (Alumno alumno : list)
				if (alumno.getFechaNac().toString().startsWith(prefix))
					alumnos.add(alumno);
			break;
		case GENERO:
			for (Alumno alumno : list)
				if (String.valueOf(alumno.getGenero()).startsWith(prefix))
					alumnos.add(alumno);
			break;
		case SEMESTRE:
			for (Alumno alumno : list)
				if (String.valueOf(alumno.getSemestre()).startsWith(prefix))
					alumnos.add(alumno);
			break;
		case CARRERA:
			for (Alumno alumno : list)
				if (alumno.getCarrera().startsWith(prefix))
					alumnos.add(alumno);
			break;

		}

		return (Alumno[]) alumnos.toArray(new Alumno[alumnos.size()]);
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

}
