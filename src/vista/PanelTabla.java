package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.Alumno;
import modelo.ComparadorInicioAlumnos;
import modelo.ComparadorInicioPrestamos;
import modelo.Devoluciones;
import modelo.Prestamo;
import modelo.Prestamos;

public class PanelTabla extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private Devoluciones devoluciones;

	public PanelTabla(Devoluciones d) {

		this.devoluciones = d;

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Ingrese los Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);

		JLabel lblCriterio = new JLabel("Criterio:");
		panel_1.add(lblCriterio);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Datos del Alumno:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTablaDeAlumnos = new JLabel("Tabla de Alumnos");
		panel_3.add(lblTablaDeAlumnos);

		modeloTabla = new DefaultTableModel();
		table = new JTable(modeloTabla);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel_2.add(scrollPane, BorderLayout.CENTER);

	}

	public String getNumControlSeleccionado() {
		int fila = table.getSelectedRow();
		String dato = (String) table.getValueAt(fila, 0);
		return dato;
	}

	public boolean hayFilaSeleccionada() {
		return table.getSelectedRow() > -1;
	}

	public void setTitulos(Object[] cols) {
		modeloTabla.setColumnIdentifiers(cols);
	}

	public void agregarFila(Object[] fila) {
		modeloTabla.addRow(fila);
	}

	// String noControl, String nombre, String paterno, String materno,
	// LocalDate
	// edad, char genero,
	// int semestre, String carrera, Direccion direccion

	public void agregarPrestamos(List<Prestamo> lista) {
		Object arr[][] = new Object[lista.size()][];
		for (int i = 0; i < lista.size(); i++) {
			Object[] linea = new Object[6];
			Prestamo a = lista.get(i);
			linea[0] = a.getAlumno().getNoControl();
			linea[1] = a.getAlumno().getNombreCompleto();
			linea[2] = a.getLibro().getIsbn();
			linea[3] = a.getLibro().getTitulo();
			linea[4] = a.getFechaDePrestamo().toString();
			linea[5] = a.getFechaDeEntrega().toString();
			arr[i] = linea;
		}
	}

	public void agregarPrestamos(Prestamo[] lista) {
		for (int i = 0; i < lista.length; i++) {
			Object[] linea = new Object[6];
			Prestamo a = lista[i];
			linea[0] = a.getAlumno().getNoControl();
			linea[1] = a.getAlumno().getNombreCompleto();
			linea[2] = a.getLibro().getIsbn();
			linea[3] = a.getLibro().getTitulo();
			linea[4] = a.getFechaDePrestamo().toString();
			linea[5] = a.getFechaDeEntrega().toString();
			modeloTabla.addRow(linea);
		}
	}

	public void actualizarTabla() {
		modeloTabla.setRowCount(0);
		agregarPrestamos(devoluciones.getList());
	}

	public <T> void agregarFilas(T[][] filas) {

		for (int i = 0; i < filas.length; i++)
			modeloTabla.addRow(filas[i]);

	}

}
