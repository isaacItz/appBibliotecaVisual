package modelo;

import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Utileria {

	public boolean estaVacio(JTextField caja) {
		return caja.getText().isEmpty();
	}

	public boolean validarCaracter(JTextField caja, int numero) {
		return (caja.getText().length() == numero);
	}

	public void escribir(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean verificarRadioBottom(JRadioButton boton) {
		return boton.isSelected();
	}

	public static String leerString(String mensaje) {
		return JOptionPane.showInputDialog(mensaje);
	}

	public boolean isMujer(JTextField caja) {
		String nombre = caja.getText();
		nombre = nombre.toUpperCase();
		int letraF = nombre.length() - 1;
		char l = nombre.charAt(letraF);
		if (l == 'A') {
			return true;
		} else {
			return false;
		}
	}

	public static <T> int linealSearch(List<T> coleccion, T dato, Comparator<T> c) {
		int i = 0;
		while (i < coleccion.size() && c.compare(coleccion.get(i), dato) != 0)
			i++;

		return i == coleccion.size() ? -1 : i;
	}

	public boolean isHombre(JTextField caja) {
		String nombre = caja.getText();
		nombre = nombre.toUpperCase();
		int letraF = nombre.length() - 1;
		char l = nombre.charAt(letraF);
		if (l == 'O') {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarCaja(JComboBox<String> combo) {
		if (combo.getSelectedIndex() != 0) {
			return true;
		} else {
			return false;
		}
	}

}
