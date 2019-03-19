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

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
import modelo.Direccion;
import modelo.Libro;
import javax.swing.SwingConstants;

public class ModificacionLibro extends JDialog {

	private JPanel contentPanel = new JPanel();

	private Libro l;
	private JTextField text1;
	private JTextField text2;
	private JTextField text5;
	private JTextField text3;
	private JTextField text6;
	private JTextField text4;
	private JTextField text7;
	private JComboBox comboBox;

	/**
	 * Create the dialog.
	 */
	public ModificacionLibro(Libro libro) {
		l = libro;
		setBounds(100, 100, 683, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(238, 232, 170));
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(4, 4, 0, 25));
			{
				JLabel label = new JLabel("ISBN:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text1 = new JTextField();
				text1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text1.setColumns(10);
				panel.add(text1);
			}
			{
				JLabel label = new JLabel("Numero Edicion:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
				panel.add(label);
			}
			{
				comboBox = new JComboBox();
				panel.add(comboBox);
			}
			{
				JLabel label = new JLabel("Titulo:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text2 = new JTextField();
				text2.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text2.setColumns(10);
				panel.add(text2);
			}
			{
				JLabel label = new JLabel("A\u00F1o Edicion:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text5 = new JTextField();
				text5.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text5.setColumns(10);
				panel.add(text5);
			}
			{
				JLabel label = new JLabel("Autor:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text3 = new JTextField();
				text3.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text3.setColumns(10);
				panel.add(text3);
			}
			{
				JLabel label = new JLabel("Idioma");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text6 = new JTextField();
				text6.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text6.setColumns(10);
				panel.add(text6);
			}
			{
				JLabel label = new JLabel("Editorial:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text4 = new JTextField();
				text4.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text4.setColumns(10);
				panel.add(text4);
			}
			{
				JLabel label = new JLabel("Pais:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
				panel.add(label);
			}
			{
				text7 = new JTextField();
				text7.setFont(new Font("Tahoma", Font.PLAIN, 20));
				text7.setColumns(10);
				panel.add(text7);
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

						l.setTitulo(text2.getText());
						l.setAutor(text3.getText());
						l.setEditoria(text4.getText());
						l.setAnioEdicion(text5.getText());
						l.setIdioma(text6.getText());
						l.setPais(text7.getText());
						l.setNumeroEdicion(comboBox.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null, "Libro Actualizado Con Exito");
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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

		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "1ra", "2da", "3ra", "4ta", "5ta", "6ta", "7ma", "8va", "9na" }));

		setTextos();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);

	}

	private void setTextos() {
		text1.setText(l.getIsbn());
		text1.setEnabled(false);
		text2.setText(l.getTitulo());
		text3.setText(l.getAutor());
		text4.setText(l.getEditoria());
		text5.setText(l.getAnioEdicion());
		text6.setText(l.getIdioma());
		text7.setText(l.getPais());
		comboBox.setSelectedItem(l.getNumeroEdicion());
	}

	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
