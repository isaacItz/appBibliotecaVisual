package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grupo implements Serializable {

	private List<Alumno> grupo;

	public Grupo() {
		grupo = new ArrayList<Alumno>(500);
	}

	public List<Alumno> getList() {
		return grupo;
	}

	public void agregar(Alumno Alumno) {
		grupo.add(Alumno);
	}

	public void agregar(Alumno[] Alumno) {
		for (Alumno alumno2 : Alumno) {
			if (buscar(alumno2.getNoControl()) == null) {
				grupo.add(alumno2);
			}

		}

	}

	public Alumno getAlumno(int i) {
		return grupo.get(i);
	}

	public Alumno buscar(String clave) {
		for (Alumno Alumno : grupo) {
			if (Alumno.getNoControl().equals(clave))
				return Alumno;
		}
		return null;
	}

	public boolean hayPersonas() {
		return grupo.size() > 0;
	}

	public boolean eliminar(Alumno p) {
		return grupo.remove(p);
	}

	public void ordenarGrupo() {
		for (int i = 0; i < grupo.size() - 1; i++) {
			for (int j = i + 1; j < grupo.size(); j++) {
				if (!(grupo.get(i).getNombre().compareToIgnoreCase(grupo.get(j).getNombre()) < 0)) {
					Alumno aux = grupo.get(i);
					grupo.set(i, grupo.get(j));
					grupo.set(j, aux);
				}

			}
		}
	}

	public String listar() {
		String salida = "Listado General de Alumnos\n";

		for (int i = 0; i < grupo.size(); i++) {
			String aux = "\f" + grupo.get(i) + "\n";
			salida += aux;
		}
		return salida;

	}

	public void copiar(List<Alumno> grupo) {
		this.grupo = grupo;
	}

}
