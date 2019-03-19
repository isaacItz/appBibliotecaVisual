package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import modelo.Alumno;
import modelo.CodigoPostal;
import modelo.CodigosPostales;
import modelo.Direccion;
import modelo.Utileria;

public class ModificacionAlumno extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField textFieldNC;
	private JTextField textFieldNombre;
	private JTextField textFieldPaterno;
	private JTextField textFieldMaterno;
	private JTextField textFieldCarrera;
	private JTextField textFieldNombreCalle;
	private JTextField textFieldNumeroCasa;
	private JTextField textFieldCP;
	private JTextField textFieldEstado;
	private JTextField textFieldMunicipio;
	private Alumno a;
	private JList<String> list;
	private String[] lista;
	private DefaultListModel<String> modeloLista;
	private JRadioButton radioButtonF;
	private JRadioButton radioButtonM;
	private ButtonGroup grupo;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBox;
	private Utileria uti;
	private CodigosPostales codigosPostales;

	/**
	 * Create the dialog.
	 */
	public ModificacionAlumno(Alumno alumno, String[] lista, CodigosPostales codigosPostales) {
		this.codigosPostales = codigosPostales;
		uti = new Utileria();
		if (lista != null)
			this.lista = lista;
		else
			this.lista = new String[0];
		a = alumno;
		setBounds(100, 100, 683, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Datos Escolares", TitledBorder.LEADING, TitledBorder.TOP,
						null, Color.BLUE));
				panel_1.setBackground(Color.GRAY);
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(9, 2, 0, 0));
				{
					JLabel label = new JLabel("Ingrese el numero de control:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					label.setBackground(Color.WHITE);
					panel_1.add(label);
				}
				{
					textFieldNC = new JTextField();
					textFieldNC.setColumns(10);
					panel_1.add(textFieldNC);
				}
				{
					JLabel label = new JLabel("Ingrese su nombre(s):");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldNombre = new JTextField();
					textFieldNombre.setColumns(10);
					panel_1.add(textFieldNombre);
				}
				{
					JLabel label = new JLabel("Ingrese su apellido paterno:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldPaterno = new JTextField();
					textFieldPaterno.setColumns(10);
					panel_1.add(textFieldPaterno);
				}
				{
					JLabel label = new JLabel("Ingrese su apellido materno:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldMaterno = new JTextField();
					textFieldMaterno.setColumns(10);
					panel_1.add(textFieldMaterno);
				}
				{
					JLabel label = new JLabel("Ingrese su fecha de nacimiento:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					dateChooser = new JDateChooser();
					panel_1.add(dateChooser);
				}
				{
					JLabel label = new JLabel("Seleccione su genero:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}

				radioButtonM = new JRadioButton("Masculino");
				panel_1.add(radioButtonM);
				grupo = new ButtonGroup();
				grupo.add(radioButtonM);
				{
					JLabel label = new JLabel("");
					panel_1.add(label);
				}
				radioButtonF = new JRadioButton("Femenino");
				panel_1.add(radioButtonF);
				grupo.add(radioButtonF);
				{
					JLabel label = new JLabel("Seleccione su semestre:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					comboBox = new JComboBox<>(
							new String[] { "Seleccione una opcion", "1", "2", "3", "4", "5", "6", "7", "8", "9" });
					panel_1.add(comboBox);
				}
				{
					JLabel label = new JLabel("Ingrese la carrera que cursa:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldCarrera = new JTextField();
					textFieldCarrera.setColumns(10);
					panel_1.add(textFieldCarrera);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP,
						null, Color.BLUE));
				panel_1.setBackground(Color.GRAY);
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(6, 2, 0, 0));
				{
					JLabel label = new JLabel("Ingrese el nombre de la calle:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldNombreCalle = new JTextField();
					textFieldNombreCalle.setColumns(10);
					panel_1.add(textFieldNombreCalle);
				}
				{
					JLabel label = new JLabel("Ingrese el numero de la casa:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldNumeroCasa = new JTextField();
					textFieldNumeroCasa.setColumns(10);
					panel_1.add(textFieldNumeroCasa);
				}
				{
					JLabel label = new JLabel("Ingrese el Codigo Postal:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);

				}
				{
					textFieldCP = new JTextField();
					textFieldCP.setColumns(10);
					panel_1.add(textFieldCP);
					textFieldCP.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (!uti.estaVacio(textFieldCP) && textFieldCP.getText().length() == 5) {
								CodigoPostal cp = codigosPostales.existe(textFieldCP.getText());
								textFieldEstado.setText(cp.getEstado().get(0));
								if (modeloLista != null) {
									modeloLista.removeAllElements();
									agregarColonias(textFieldCP.getText());
								}
								textFieldMunicipio.setText(cp.getCiudad().get(0));
							} else {
								uti.escribir("Ingrese el dato solicitado la longitud debe ser 5 caracteres");

							}
						}
					});
				}
				{
					JLabel label = new JLabel("Ingrese el nombre de la colonia");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					modeloLista = new DefaultListModel<>();
					list = new JList<>(modeloLista);
					JScrollPane scrollPane = new JScrollPane(list);
					panel_1.add(scrollPane);
				}
				{
					JLabel label = new JLabel("Ingrese el Estado:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldEstado = new JTextField();
					textFieldEstado.setColumns(10);
					panel_1.add(textFieldEstado);
				}
				{
					JLabel label = new JLabel("Ingrese el nombre del municipio:");
					label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.ITALIC, 13));
					panel_1.add(label);
				}
				{
					textFieldMunicipio = new JTextField();
					textFieldMunicipio.setColumns(10);
					panel_1.add(textFieldMunicipio);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						alumno.setNoControl(textFieldNC.getText());
						alumno.setCarrera(textFieldCarrera.getText());
						if (radioButtonM.isSelected()) {
							alumno.setGenero('M');
						} else
							alumno.setGenero('F');

						alumno.setMaterno(textFieldMaterno.getText());
						alumno.setPaterno(textFieldPaterno.getText());
						alumno.setNombre(textFieldNombre.getText());
						Direccion d = new Direccion(textFieldNombreCalle.getText(), textFieldNumeroCasa.getText(),
								list.getSelectedValue(), textFieldMunicipio.getText(), textFieldEstado.getText(),
								textFieldCP.getText());
						alumno.setDireccion(d);
						JOptionPane.showMessageDialog(null, "Alumno Actualizado Con Exito");
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				// getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setTextos();
		textFieldNC.setEditable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);

	}

	private void agregarColonias(String codigo) {
		List<String> colonias = codigosPostales.getColonias(codigo);

		for (String colonia : colonias) {
			if (!modeloLista.contains(colonia))
				modeloLista.addElement(colonia);
		}
	}

	private void setTextos() {
		textFieldNC.setText(a.getNoControl());
		textFieldCarrera.setText(a.getCarrera());
		textFieldCP.setText(a.getDireccion().getcP());
		textFieldEstado.setText(a.getDireccion().getEstado());
		textFieldMaterno.setText(a.getMaterno());
		textFieldNombre.setText(a.getNombre());
		textFieldPaterno.setText(a.getPaterno());
		textFieldNumeroCasa.setText(a.getDireccion().getNoCasa());
		textFieldMunicipio.setText(a.getDireccion().getMunicipio());
		textFieldNombreCalle.setText(a.getDireccion().getCalle());
		for (String string : lista)
			modeloLista.addElement(string);
		list.setSelectedValue(a.getDireccion().getColonia(), true);
		if (a.getGenero() == 'M')
			radioButtonM.setSelected(true);
		else
			radioButtonM.setSelected(true);

		dateChooser.setDate(convertToDateViaSqlDate(a.getFechaNac()));
		comboBox.setSelectedIndex(a.getSemestre());

	}

	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
