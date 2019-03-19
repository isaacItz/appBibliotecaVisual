package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Alumno implements Comparable<Alumno>, Serializable {
	private String noControl;
	private String nombre;
	private String paterno;
	private String materno;
	private LocalDate fechaNac;
	private char genero;
	private int semestre;
	private String carrera;
	private Direccion direccion;

	public Alumno(String noControl, String nombre, String paterno, String materno, LocalDate edad, char genero,
			int semestre, String carrera, Direccion direccion) {
		super();
		this.noControl = noControl;
		this.nombre = nombre;
		this.paterno = paterno;
		this.materno = materno;
		this.fechaNac = edad;
		this.genero = genero;
		this.semestre = semestre;
		this.carrera = carrera;
		this.direccion = direccion;
	}

	public Alumno() {
	}

	public String getNombreCompleto() {
		return nombre.concat(" ").concat(paterno).concat(" ").concat(materno);
	}

	public Alumno(String noControl) {
		this.noControl = noControl;
	}

	public String getNoControl() {
		return noControl;
	}

	public void setNoControl(String noControl) {
		this.noControl = noControl;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Alumno [noControl=" + noControl + ", nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno
				+ ", edad=" + fechaNac.toString() + ", genero=" + genero + ", semestre=" + semestre + ", carrera="
				+ carrera + ", direccion=" + direccion + "]";
	}

	@Override
	public int compareTo(Alumno a) {
		return noControl.compareTo(a.noControl);
	}

}
