package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodigoPostal implements Comparable<CodigoPostal>, Serializable {
	private String codigo;
	private List<String> colonia;
	private List<String> ciudad;
	private List<String> estado;

	public CodigoPostal() {
		super();
		colonia = new ArrayList<>();
		ciudad = new ArrayList<>();
		estado = new ArrayList<>();
	}

	public CodigoPostal(String codigo) {
		this();
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void agregarColonia(String colonia) {
		this.colonia.add(colonia);

	}

	public void agregarCiudad(String ciudad) {
		this.ciudad.add(ciudad);

	}

	public void agregarEstado(String estado) {
		this.estado.add(estado);

	}

	@Override
	public boolean equals(Object obj) {
		CodigoPostal codigoPostal = (CodigoPostal) obj;
		return codigo.equals(codigoPostal.getCodigo());
	}

	@Override
	public String toString() {
		return "\nCodigoPostal [codigo=" + codigo + ", colonia=" + colonia.toString() + ", ciudad=" + ciudad.toString()
				+ ", estado=" + estado.toString() + "]";
	}

	public List<String> getColonia() {
		return colonia;
	}

	public void setColonia(List<String> colonia) {
		this.colonia = colonia;
	}

	public List<String> getCiudad() {
		return ciudad;
	}

	public void setCiudad(List<String> ciudad) {
		this.ciudad = ciudad;
	}

	public List<String> getEstado() {
		return estado;
	}

	public void setEstado(List<String> estado) {
		this.estado = estado;
	}

	@Override
	public int compareTo(CodigoPostal cp) {
		return codigo.compareTo(cp.codigo);
	}

}
