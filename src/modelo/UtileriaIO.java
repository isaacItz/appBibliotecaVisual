package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class UtileriaIO {

	private static Scanner entrada;

	// public static List<CP> getCPS(List<String> lista) {
	//
	// List<CP> cps = new ArrayList<>();
	// for (int i = 0; i < lista.size(); i++) {
	// String[] subcadena = lista.get(i).split("\t");
	// if (subcadena.length > 3) {
	// CP cp = new CP(Integer.parseInt(subcadena[0]), subcadena[1], subcadena[2],
	// subcadena[3]);
	// cps.add(cp);
	// }
	//
	// }
	//
	// return cps;
	//
	// }

	public static <T extends Comparable<? super T>> int busquedaBinaria(List<T> vector, T dato) {
		int n = vector.size();
		int centro, inf = 0, sup = n - 1;
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			if (vector.get(centro).compareTo(dato) == 0)
				return centro;
			else if (dato.compareTo(vector.get(centro)) < 0) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}
		}
		return -1;
	}

	public static ArrayList<String> getDatosIO(File archivo) {

		ArrayList<String> cadenas = new ArrayList<>();
		try {
			entrada = new Scanner(archivo);
			while (entrada.hasNextLine()) {
				String aux = entrada.nextLine();
				cadenas.add(aux);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return cadenas;
	}

}