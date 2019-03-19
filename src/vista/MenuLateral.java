package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java.awt.event.KeyEvent;

public class MenuLateral extends JPanel {
	private JButton botonRegistrar;
	private JButton botonConsultar;
	private JButton botonModificar;
	private JButton botonEliminar;
	private JButton botonOrdenar;

	/**
	 * Create the panel.
	 */
	public MenuLateral() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(9, 1, 0, 0));

		botonRegistrar = new JButton("Registrar");
		botonRegistrar.setMnemonic(KeyEvent.VK_R);
		botonRegistrar.setPressedIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonVerde.gif")));
		botonRegistrar.setRolloverIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonRojo.gif")));
		botonRegistrar.setToolTipText("Captura de Datos de Libros");
		botonRegistrar.setContentAreaFilled(false);
		botonRegistrar.setBorderPainted(false);
		botonRegistrar.setFocusPainted(false);
		botonRegistrar.setForeground(Color.YELLOW);
		botonRegistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonRegistrar.setIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonAzul.gif")));
		add(botonRegistrar);

		botonConsultar = new JButton("Consultar");
		botonConsultar.setPressedIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonVerde.gif")));
		botonConsultar.setRolloverIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonRojo.gif")));
		botonConsultar.setContentAreaFilled(false);
		botonConsultar.setBorderPainted(false);
		botonConsultar.setFocusPainted(false);
		botonConsultar.setForeground(Color.YELLOW);
		botonConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonConsultar.setIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonAzul.gif")));
		add(botonConsultar);

		botonModificar = new JButton("Modificar");
		botonModificar.setPressedIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonVerde.gif")));
		botonModificar.setRolloverIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonRojo.gif")));
		botonModificar.setContentAreaFilled(false);
		botonModificar.setBorderPainted(false);
		botonModificar.setFocusPainted(false);
		botonModificar.setForeground(Color.YELLOW);
		botonModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonModificar.setIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonAzul.gif")));
		add(botonModificar);

		botonEliminar = new JButton("Eliminar");
		botonEliminar.setPressedIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonVerde.gif")));
		botonEliminar.setRolloverIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonRojo.gif")));
		botonEliminar.setContentAreaFilled(false);
		botonEliminar.setBorderPainted(false);
		botonEliminar.setFocusPainted(false);
		botonEliminar.setForeground(Color.YELLOW);
		botonEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonEliminar.setIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonAzul.gif")));
		add(botonEliminar);

		botonOrdenar = new JButton("Ordenar");
		botonOrdenar.setPressedIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonVerde.gif")));
		botonOrdenar.setRolloverIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonRojo.gif")));
		botonOrdenar.setContentAreaFilled(false);
		botonOrdenar.setBorderPainted(false);
		botonOrdenar.setFocusPainted(false);
		botonOrdenar.setForeground(Color.YELLOW);
		botonOrdenar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonOrdenar.setIcon(new ImageIcon(MenuLateral.class.getResource("/Recursos/botonAzul.gif")));
		add(botonOrdenar);

		setBorder(new TitledBorder(null, "Menu:", TitledBorder.LEADING, TitledBorder.TOP, null, null));

	}

	public JButton getBotonRegistrar() {
		return botonRegistrar;
	}

	public JButton getBotonConsultar() {
		return botonConsultar;
	}

	public JButton getBotonModificar() {
		return botonModificar;
	}

	public JButton getBotonEliminar() {
		return botonEliminar;
	}

	public JButton getBotonOrdenar() {
		return botonOrdenar;
	}

}
