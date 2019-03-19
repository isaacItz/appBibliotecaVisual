package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DAOCodigoPostal {

	public static CodigosPostales getCodigos(String ruta) {
		CodigosPostales codigosPostales = new CodigosPostales();
		File archivo = new File(ruta);
		if (archivo.exists()) {
			try {
				Scanner entrada = new Scanner(archivo);
				List<String> lineas = new ArrayList<>();
				while (entrada.hasNext())
					lineas.add(entrada.nextLine());

				entrada.close();
				EstructuraCodigo estructuraCodigo;
				CodigoPostal codigoPostal;
				for (String cadena : lineas) {
					estructuraCodigo = generarCodigoPostal(cadena);
					codigoPostal = codigosPostales.existe(estructuraCodigo.getCodigo());
					if (codigoPostal == null) {
						codigoPostal = new CodigoPostal();
						codigoPostal.setCodigo(estructuraCodigo.getCodigo());
						codigoPostal.agregarColonia(estructuraCodigo.getColonia());
						codigoPostal.agregarCiudad(estructuraCodigo.getCiudad());
						codigoPostal.agregarEstado(estructuraCodigo.getEstado());
						codigosPostales.registrar(codigoPostal);

					} else {
						codigoPostal.agregarColonia(estructuraCodigo.getColonia());
						codigoPostal.agregarCiudad(estructuraCodigo.getCiudad());
						codigoPostal.agregarEstado(estructuraCodigo.getEstado());

					}

				}

				return codigosPostales;

			} catch (FileNotFoundException e) {
				return null;
			}

		} else {
			System.out.println("archivo no existe");
			return null;

		}

	}

	public static EstructuraCodigo generarCodigoPostal(String cadena) {
		StringTokenizer st = new StringTokenizer(cadena, "|");
		List<String> cadenas = new ArrayList<>();
		while (st.hasMoreElements()) {
			cadenas.add(st.nextToken());
		}
		EstructuraCodigo estructuraCodigo = new EstructuraCodigo(cadenas.get(0), cadenas.get(1), cadenas.get(3),
				cadenas.get(4));
		return estructuraCodigo;

	}

}
