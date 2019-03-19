package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prestamos implements Serializable {
	private List<Prestamo> colecciones;

	public Prestamos() {
		colecciones = new ArrayList<>();
	}

	public void agregar(Prestamo prestamo) {
		colecciones.add(prestamo);
	}

	public String list() {
		String salida = "Listado general de libros prestados\n";
		for (int i = 0; i < colecciones.size(); i++)
			salida += colecciones.get(i) + "\n";
		return salida;
	}

	public List<Prestamo> getList() {
		return colecciones;
	}

	public void eliminar(int p) {
		colecciones.remove(p);
	}

	public int cantLibrosPrestados(Alumno a) {
		int i = 0;
		for (Prestamo prestamo : colecciones) {
			if (prestamo.getAlumno().getNoControl().equals(a.getNoControl())) {
				i++;
			}
		}
		return i;
	}

	public boolean existeNumControl(Alumno a) {
		ComparadorPrestamos cP = new ComparadorPrestamos(ComparadorPrestamos.NUMERO_DE_CONTROL);
		Prestamo ps = new Prestamo();
		ps.setAlumno(a);
		int p = Utileria.linealSearch(colecciones, ps, cP);
		return p != -1 ? true : false;
	}

	public boolean existeLbro(Libro l) {
		ComparadorPrestamos cP = new ComparadorPrestamos(ComparadorPrestamos.ISBN);
		Prestamo ps = new Prestamo();
		ps.setLibro(l);
		int p = Utileria.linealSearch(colecciones, ps, cP);
		return p != -1 ? true : false;
	}

	public Prestamo getPrestamo(int posicion) {
		return colecciones.get(posicion);

	}

	public void agregarColeccion(List<Prestamo> prestamos) {
		for (Prestamo prestamo : prestamos) {
			colecciones.add(prestamo);
		}

	}

}
