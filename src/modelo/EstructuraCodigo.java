package modelo;

public class EstructuraCodigo {
	private String codigo;
	private String colonia;
	private String ciudad;
	private String estado;

	public EstructuraCodigo(String codigo, String colonia, String ciudad, String estado) {
		super();
		this.codigo = codigo;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		return "EstructuraCodigo [codigo=" + codigo + ", colonia=" + colonia + ", ciudad=" + ciudad + ", estado="
				+ estado + "]";
	}

}
