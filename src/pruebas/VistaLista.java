package pruebas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.CodigoPostal;
import modelo.CodigosPostales;
import modelo.DAOCodigoPostal;

public class VistaLista extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpCodigo;
	private JScrollPane scpColonia;
	private JList<String> listaCodigos;
	private JList<String> listaColonias;
	private DefaultListModel<String> modeloCodigo;
	private DefaultListModel<String> modeloColonia;
	private JTextField cajaEstado;
	private JTextField cajaCiudad;
	private CodigosPostales codigosPostales;
	private String codigoPostal;
	private String coloniax;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLista frame = new VistaLista();
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
	public VistaLista() {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(new File(".").getAbsolutePath());
		codigosPostales = DAOCodigoPostal.getCodigos("src\\extras/CPdescarga.txt");
		System.out.println("paso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();

		modeloCodigo = new DefaultListModel<>();

		modeloColonia = new DefaultListModel<>();
		agragarCodigos();
		listaCodigos = new JList<>(modeloCodigo);
		listaCodigos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (modeloColonia != null) {
					modeloColonia.removeAllElements();
					cajaCiudad.setText("");
					cajaEstado.setText("");
				}
				JList listaCodigos = (JList) e.getSource();
				String codigo = (String) listaCodigos.getSelectedValue();
				agragarColonias(codigo);
				codigoPostal = codigo;

			}
		});
		listaColonias = new JList<>(modeloColonia);
		listaColonias.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated met
				JList listaColonias = (JList) e.getSource();
				String col = (String) listaColonias.getSelectedValue();
				coloniax = col;
				CodigoPostal cp = codigosPostales.getElemento(new CodigoPostal(codigoPostal));
				cajaEstado.setText(cp.getEstado().get(0));
				cajaCiudad.setText(cp.getCiudad().get(0));
				repaint();

			}
		});

		scpCodigo = new JScrollPane(listaCodigos);
		scpColonia = new JScrollPane(listaColonias);
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
		cajaEstado = new JTextField();
		cajaEstado.setPreferredSize(new Dimension(200, 50));
		cajaCiudad = new JTextField();
		cajaCiudad.setPreferredSize(new Dimension(200, 50));
		contentPane.add(scpCodigo);
		contentPane.add(scpColonia);
		contentPane.add(cajaEstado);
		contentPane.add(cajaCiudad);

	}

	private void agragarCodigos() {
		for (String codigo : codigosPostales.getCodigosPostales()) {
			modeloCodigo.addElement(codigo);
		}
	}

	private void agragarColonias(String codigo) {
		List<String> colonias = codigosPostales.getColonias(codigo);

		for (String colonia : colonias) {
			if (!modeloColonia.contains(colonia))
				modeloColonia.addElement(colonia);
		}
	}

}
