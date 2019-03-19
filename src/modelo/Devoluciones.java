package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Devoluciones implements Serializable {

	private List<Prestamo> devoluciones;

	public Devoluciones() {
		devoluciones = new ArrayList<Prestamo>();
	}

	public void agregar(Prestamo p) {
		devoluciones.add(p);
	}

	public List<Prestamo> getList() {
		return devoluciones;
	}

}
