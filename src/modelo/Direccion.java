package modelo;

import java.io.Serializable;

public class Direccion implements Serializable {
	private String calle;
	private String noCasa;
	private String colonia;
	private String municipio;
	private String estado;
	private String cP;

	public Direccion(String calle, String noCasa, String colonia, String municipio, String estado, String cP) {
		super();
		this.calle = calle;
		this.noCasa = noCasa;
		this.colonia = colonia;
		this.municipio = municipio;
		this.estado = estado;
		this.cP = cP;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoCasa() {
		return noCasa;
	}

	public void setNoCasa(String noCasa) {
		this.noCasa = noCasa;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getcP() {
		return cP;
	}

	public void setcP(String cP) {
		this.cP = cP;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Calle: " + calle + ", No. de Casa: " + noCasa + ", Colonia: " + colonia + ", Municipio: " + municipio
				+ ", Estado:" + estado + ", CP:" + cP;
	}

}
