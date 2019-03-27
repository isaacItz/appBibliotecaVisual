package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import modelo.Alumno;
import modelo.Direccion;
import modelo.Libro;

public class Cargador {

	public static Libro[] getLibrosArchivo(String ruta) {

		File archivo = new File(ruta);
		Scanner entrada;
		try {
			entrada = new Scanner(archivo);
			List<String> cadenas = new ArrayList<String>();
			while (entrada.hasNext()) {
				String cadena = entrada.nextLine();
				cadenas.add(cadena);
				System.out.println(cadena);
			}
			entrada.close();
			Libro[] libros = getLibros(cadenas);
			List<Libro> librox = new ArrayList<>();
			for (int i = 0; i < libros.length; i++) {
				librox.add(libros[i]);
			}
			// guardar(librox, "recursos/libros.dat");

			return libros;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());

		}
		return null;

	}

	public static String getSelectorRuta() {
		JFileChooser selector = new JFileChooser();
		int op = selector.showOpenDialog(null);
		if (op == JFileChooser.APPROVE_OPTION)
			return selector.getSelectedFile().getAbsolutePath();
		return null;
	}

	private static Libro[] getLibros(List<String> cadenas) {
		Libro[] libros = new Libro[cadenas.size()];
		int i = 0;

		for (String cadena : cadenas) {
			String[] subCadenas = cadena.split("\t");
			String isbn = subCadenas[0];
			String titulo = subCadenas[1];
			String autor = subCadenas[2];
			String editorial = subCadenas[3];
			String anioEdicion = subCadenas[5];
			String numeroEdicion = subCadenas[4];
			String idioma = subCadenas[6];
			String pais = subCadenas[7];
			Libro libro = new Libro();
			libro.setIsbn(isbn);
			libro.setTitulo(titulo);
			libro.setAutor(autor);
			libro.setEditoria(editorial);
			libro.setAnioEdicion(anioEdicion);
			libro.setNumeroEdicion(numeroEdicion);
			libro.setIdioma(idioma);
			libro.setPais(pais);
			libros[i] = libro;
			i++;
		}
		return libros;
	}

	public static Alumno[] getAlumnosArchivo(String ruta) {
		File archivo = new File(ruta);
		Scanner entrada;
		try {
			entrada = new Scanner(archivo);
			List<String> cadenas = new ArrayList<String>();
			while (entrada.hasNext()) {
				String cadena = entrada.nextLine();
				cadenas.add(cadena);
			}
			entrada.close();
			Alumno[] libros = getAlumnos(cadenas);
			List<Alumno> librox = new ArrayList<>();
			for (int i = 0; i < libros.length; i++) {
				librox.add(libros[i]);
			}
			// guardarA(librox, "recursos/libros.dat");

			return libros;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());

		}
		return null;

	}

	private static Alumno[] getAlumnos(List<String> cadenas) {
		Alumno[] alumnos = new Alumno[cadenas.size()];
		int i = 0;

		for (String cadena : cadenas) {
			String[] subCadenas = cadena.split("\t");
			String numC = subCadenas[0];
			String nombre = subCadenas[1];
			String materno = subCadenas[2];
			String paterno = subCadenas[3];
			String fechaNac = subCadenas[4];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(fechaNac, formatter);
			String genero = subCadenas[5];
			String se = subCadenas[6];
			int semestre = Integer.valueOf(se);
			String carrera = subCadenas[7];
			String calle = subCadenas[8];
			String numeroCasa = subCadenas[9];
			String colonia = subCadenas[10];
			String ciudad = subCadenas[11];
			String estado = subCadenas[12];
			String CP = subCadenas[13];
			Direccion direccion = new Direccion(calle, numeroCasa, colonia, ciudad, estado, CP);
			Alumno a = new Alumno(numC, nombre, materno, paterno, date, genero.charAt(0), semestre, carrera, direccion);

			alumnos[i] = a;
			i++;
		}
		return alumnos;
	}

	public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static boolean existe(String ruta) {
		File archivo = new File(ruta);
		return archivo.exists();

	}

	public static Libro[] cargarLibros(String ruta) {
		File archivo = new File(ruta);
		try {
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream entrada = new ObjectInputStream(fis);
			List<Libro> libros = (List<Libro>) entrada.readObject();
			Libro[] losLibros = new Libro[libros.size()];
			for (int i = 0; i < libros.size(); i++) {
				losLibros[i] = libros.get(i);
			}
			fis.close();
			entrada.close();
			return losLibros;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String guardar(List<Libro> libros, String ruta) {
		File archivo = new File(ruta);
		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream salida = new ObjectOutputStream(fos);
			salida.writeObject(libros);
			return "Libros guardados con exito";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}

	public static String guardarA(List<Alumno> alumnos, String ruta) {
		File archivo = new File(ruta);
		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream salida = new ObjectOutputStream(fos);
			salida.writeObject(alumnos);
			return "Libros guardados con exito";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	// LEER
	// PRESTAMOS***********************************************************************************

	// public static String escribirPrestamos(List<Prestamo> prestamos, String
	// rutaDestinoPrestamos) {
	// File archivo = new File(rutaDestinoPrestamos);
	// try {
	// FileOutputStream fos = new FileOutputStream(archivo);
	// ObjectOutputStream out = new ObjectOutputStream(fos);
	// out.writeObject(prestamos);
	// return "Prestamos registrados con exito";
	// } catch (IOException e) {
	// return e.getMessage();
	// }
	// }
	//
	// public static List<Prestamo> cargarPrestamos(String rutaOrigen) {
	// List<Prestamo> prestamos;
	// File file = new File(rutaOrigen);
	// try {
	// FileInputStream fis = new FileInputStream(file);
	// ObjectInputStream in = new ObjectInputStream(fis);
	// prestamos = (List<Prestamo>) in.readObject();
	// return prestamos;
	// } catch (IOException e) {
	// e.printStackTrace();
	// return null;
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
	//
	// // LEER DEVOLUCIONES
	// //
	// ****************************************************************************************
	//
	// public static String escribirDevoluciones(List<Devolucion> devoluciones,
	// String rutaDestinoDevoluciones) {
	// File archivo = new File(rutaDestinoDevoluciones);
	// try {
	// FileOutputStream fos = new FileOutputStream(archivo);
	// ObjectOutputStream out = new ObjectOutputStream(fos);
	// out.writeObject(devoluciones);
	// return "Devoluciones registradas con exito";
	// } catch (IOException e) {
	// return e.getMessage();
	// }
	// }
	//
	// public static List<Devolucion> cargarDevoluciones(String rutaOrigen) {
	// List<Devolucion> devoluciones;
	// File file = new File(rutaOrigen);
	// try {
	// FileInputStream fis = new FileInputStream(file);
	// ObjectInputStream in = new ObjectInputStream(fis);
	// devoluciones = (List<Devolucion>) in.readObject();
	// return devoluciones;
	// } catch (IOException e) {
	// e.printStackTrace();
	// return null;
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
}