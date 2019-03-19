package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodigosPostales implements Serializable {
	private List<CodigoPostal> codigos;

	public CodigosPostales() {
		codigos = new ArrayList<>();

	}

	public CodigoPostal existe(String codigo) {
		int posCP = UtileriaIO.busquedaBinaria(codigos, new CodigoPostal(codigo));
		return posCP > -1 ? codigos.get(posCP) : null;
	}

	public boolean existe2(String codigo) {
		// return codigos.contains(new CodigoPostal(codigo));
		return UtileriaIO.busquedaBinaria(codigos, new CodigoPostal(codigo)) > -1 ? true : false;
	}

	public void registrar(CodigoPostal codigoPostal) {
		codigos.add(codigoPostal);
	}

	public CodigoPostal getElemento(CodigoPostal codigoPostal) {
		int posicion = codigos.indexOf(codigoPostal);
		return codigos.get(posicion);
	}

	@Override
	public String toString() {
		return "CodigosPostales [codigos=" + codigos.toString() + "]";
	}

	public List<String> getCodigosPostales() {
		List<String> lista = new ArrayList<>();
		for (CodigoPostal codigoPostal : codigos) {
			lista.add(codigoPostal.getCodigo());
		}
		return lista;

	}

	public List<String> getColonias(String codigo) {
		// List<String> listaColonias = new ArrayList<>();
		int pos = codigos.indexOf(new CodigoPostal(codigo));
		if (pos != -1) {
			CodigoPostal cp = codigos.get(pos);
			return cp.getColonia();
		} else
			return null;

	}

}
