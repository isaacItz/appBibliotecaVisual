package pruebas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLista extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList listaUno;
	private JPanel panel;
	private JButton botonIzquierdo;
	private JButton botonDerecho;
	private JButton botonTodoIzquierdo;
	private JButton botonTodoDerecho;
	private JScrollPane scrollPane_1;
	private JList listaDos;
	private DefaultListModel<String> defaultListModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLista frame = new VentanaLista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLista() {
		defaultListModel = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		listaUno = new JList();
		listaUno.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaUno.setModel(new AbstractListModel() {
			String[] values = new String[] { "Mexico", "Argentina", "Urguay", "Paraguay", "Brasil", "venezuela",
					"Colombia", "Nicaragua", "Dinamarca", "Holanda", "Alemania", "Belgica", "Noriega", "Rumania",
					"Finlandia", "Australia", "Nueva Zelanda", "Etiopia" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listaUno.addListSelectionListener(new ListSelectionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList lista = ((JList) e.getSource());
				// JOptionPane.showMessageDialog(null,lista.getSelectedValue()+", Posicion
				// "+lista.getSelectedIndex());

			}
		});
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		// listaUno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(listaUno);
		contentPane.add(scrollPane);

		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 1, 0, 0));

		botonIzquierdo = new JButton("<");
		botonIzquierdo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});
		panel.add(botonIzquierdo);

		botonDerecho = new JButton(">");
		botonDerecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selecciones = listaUno.getSelectedIndices();
				Object[] opcionesElegidas = listaUno.getSelectedValues();
				// String salida="";
				for (Object opcion : opcionesElegidas) {
					// salida+="\n"+opcion;
					defaultListModel.addElement((String) opcion);

				}
				// JOptionPane.showMessageDialog(null, salida);
				listaDos.setModel(defaultListModel);

			}
		});
		panel.add(botonDerecho);

		botonTodoIzquierdo = new JButton("|<<");
		panel.add(botonTodoIzquierdo);

		botonTodoDerecho = new JButton(">>|");
		panel.add(botonTodoDerecho);

		listaDos = new JList();
		listaDos.setModel(new AbstractListModel() {
			String[] values = new String[] { "", "" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_1 = new JScrollPane(listaDos);
		scrollPane_1.setPreferredSize(new Dimension(100, 100));
		contentPane.add(scrollPane_1);

		contentPane.add(scrollPane_1);

	}

}
