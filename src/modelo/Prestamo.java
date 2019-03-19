package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Prestamo implements Serializable {
	private Alumno alumno;
	private Libro libro;
	private LocalDate fechaDePrestamo;
	private LocalDate fechaDeEntrega;

	public Prestamo() {

	}

	public Prestamo(Alumno alumno, Libro libro, LocalDate fechaDePrestamo, LocalDate fechaDeEntrega) {
		super();
		this.alumno = alumno;
		this.libro = libro;
		this.fechaDePrestamo = fechaDePrestamo;
		this.fechaDeEntrega = fechaDeEntrega;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public LocalDate getFechaDePrestamo() {
		return fechaDePrestamo;
	}

	public void setFechaDePrestamo(LocalDate fechaDePrestamo) {
		this.fechaDePrestamo = fechaDePrestamo;
	}

	public LocalDate getFechaDeEntrega() {
		return fechaDeEntrega;
	}

	public void setFechaDeEntrega(LocalDate fechaDeEntrega) {
		this.fechaDeEntrega = fechaDeEntrega;
	}

}
