package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.ComparadorInicioLibros;
import modelo.Estante;
import modelo.Libro;

public class PanelBusquedaLibros extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JComboBox<String> comboBox;
	private Estante estante;
	private Object[] titulos;

	public PanelBusquedaLibros(Estante gru, Object[] titulo, String[] crterios) {

		this.estante = gru;
		this.titulos = titulo;

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

		comboBox = new JComboBox<>();
		panel_1.add(comboBox);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				Libro[] libros = ComparadorInicioLibros.concidencias(estante.getList(),
						comboBox.getSelectedItem().toString(), textField.getText());
				modeloTabla.setRowCount(0);
				agregarLibro(libros);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Libro[] libros = ComparadorInicioLibros.concidencias(estante.getList(),
						comboBox.getSelectedItem().toString(), textField.getText());
				modeloTabla.setRowCount(0);
				agregarLibro(libros);
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				Libro[] libros = ComparadorInicioLibros.concidencias(estante.getList(),
						comboBox.getSelectedItem().toString(), textField.getText());
				modeloTabla.setRowCount(0);
				agregarLibro(libros);
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTablaDeAlumnos = new JLabel("Tabla de Libros");
		panel_3.add(lblTablaDeAlumnos);

		modeloTabla = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modeloTabla);
		table.getActionMap().put("copy", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cellValue = table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn())
						.toString();
				StringSelection stringSelection = new StringSelection(cellValue);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel_2.add(scrollPane, BorderLayout.CENTER);

		if (titulos != null) {
			setTitulos(titulos);
		}
		setCriterio(crterios);
		setEspacioRegistros();

	}

	private void setEspacioRegistros() {
		TableColumn columna;
		int i;
		for (i = 0; i < titulos.length - 1; i++) {
			columna = table.getColumn(titulos[i]);
			columna.setMinWidth(150);
		}
		columna = table.getColumn(titulos[i]);
		columna.setMinWidth(150);
	}

	public String getClaveSeleccionada() {
		int fila = table.getSelectedRow();
		String dato = (String) table.getValueAt(fila, 0);
		return dato;
	}

	public boolean hayFilaSeleccionada() {
		return table.getSelectedRow() > -1;
	}

	public void setCriterio(Object[] lista) {
		for (int i = 0; i < lista.length; i++) {
			comboBox.addItem(lista[i].toString());
		}

	}

	public void setTitulos(Object[] cols) {
		modeloTabla.setColumnIdentifiers(cols);
	}

	public void agregarFila(Object[] fila) {
		modeloTabla.addRow(fila);
	}

	// String noControl, String nombre, String paterno, String materno, LocalDate
	// edad, char genero,
	// int semestre, String carrera, Direccion direccion

	public void agregarLibros(List<Libro> lista) {
		for (int i = 0; i < lista.size(); i++) {
			Object[] linea = new Object[8];
			Libro l = lista.get(i);
			linea[0] = l.getIsbn();
			linea[1] = l.getTitulo();
			linea[2] = l.getAutor();
			linea[3] = l.getEditoria();
			linea[4] = l.getNumeroEdicion();
			linea[5] = l.getAnioEdicion();
			linea[6] = l.getIdioma();
			linea[7] = l.getPais();
			modeloTabla.addRow(linea);
		}
	}

	public void agregarLibro(Libro[] lista) {
		for (int i = 0; i < lista.length; i++) {
			Object[] linea = new Object[8];
			Libro l = lista[i];
			linea[0] = l.getIsbn();
			linea[1] = l.getTitulo();
			linea[2] = l.getAutor();
			linea[3] = l.getEditoria();
			linea[4] = l.getNumeroEdicion();
			linea[5] = l.getAnioEdicion();
			linea[6] = l.getIdioma();
			linea[7] = l.getPais();
			modeloTabla.addRow(linea);
		}
	}

	public void pedirFoco() {
		textField.requestFocus();
	}

	public void actualizarTabla() {
		// modeloTabla.fireTableDataChanged();
		// repaint();
		// table.repaint();
		// table.setVisible(true);
		// setVisible(true);
		modeloTabla.setRowCount(0);
		agregarLibros(estante.getList());
	}

	public <T> void agregarFilas(T[][] filas) {

		for (int i = 0; i < filas.length; i++)
			modeloTabla.addRow(filas[i]);

	}

}
