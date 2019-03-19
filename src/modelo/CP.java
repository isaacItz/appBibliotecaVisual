package modelo;

public class CP implements Comparable<CP> {

	private int cP;
	private String colonia;
	private String ciudad;
	private String estado;

	public CP(int cP, String colonia, String ciudad, String estado) {
		this.cP = cP;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;
	}

	public CP(int cP) {
		this.cP = cP;
	}

	public int getcP() {
		return cP;
	}

	public void setcP(int cP) {
		this.cP = cP;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CP [cP=" + cP + ", colonia=" + colonia + ", ciudad=" + ciudad + ", estado=" + estado + "]";
	}

	@Override
	public int compareTo(CP cp) {
		return new Integer(cP).compareTo(cp.cP);
	}

}
