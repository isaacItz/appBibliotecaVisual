package modelo;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class PanelPrestamos extends JPanel {
	private JTextField textFieldNumControl;
	private JTextField textFieldIsbn;

	/**
	 * Create the panel.
	 */
	public PanelPrestamos() {

		JPanel panelCentro = new JPanel(new GridLayout(1, 2));

		JPanel panelIzq = new JPanel();
		JPanel panelDer = new JPanel();
		setLayout(new BorderLayout(0, 0));

		panelCentro.add(panelIzq);
		panelCentro.add(panelDer);
		add(panelCentro, BorderLayout.CENTER);
		panelDer.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Datos del Libro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDer.add(panel_2, BorderLayout.NORTH);

		JLabel label_1 = new JLabel("ISBN del libro:");
		panel_2.add(label_1);

		textFieldIsbn = new JTextField();
		textFieldIsbn.setColumns(10);
		panel_2.add(textFieldIsbn);

		JPanel panel_3 = new JPanel();
		panelDer.add(panel_3, BorderLayout.CENTER);
		panelIzq.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Datos del Alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelIzq.add(panel_1, BorderLayout.NORTH);

		JLabel lblNumeroDeControl = new JLabel("Numero de Control:");
		panel_1.add(lblNumeroDeControl);

		textFieldNumControl = new JTextField();
		textFieldNumControl.setColumns(10);
		panel_1.add(textFieldNumControl);

		JPanel panel = new JPanel();
		panelIzq.add(panel, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnOk = new JButton("OK");
		panel_4.add(btnOk);

		JButton btnCancelar = new JButton("Cancelar");
		panel_4.add(btnCancelar);

	}

}
