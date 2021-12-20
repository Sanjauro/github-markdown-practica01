/**
 * VentanaPrincipal.java
   22 nov 2021 10:21:56
   @author Santiago Mart�nez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements ActionListener {
	final Toolkit MI_PANTALLA = Toolkit.getDefaultToolkit();
	final Dimension TAMANNO_PANTALLA = MI_PANTALLA.getScreenSize();
	final Image MI_ICONO = MI_PANTALLA.getImage(getClass().getResource("/recursos/logoLibring.png"));
	final int ANCHO_PANTALLA = TAMANNO_PANTALLA.width;
	final int ALTO_PANTALLA = TAMANNO_PANTALLA.height;

	JMenuBar miBarra = new JMenuBar();

	JMenu archivo = new JMenu("Archivo");
	JMenuItem salir = new JMenuItem("Salir");

	JMenu registro = new JMenu("Registro");
	JMenuItem alta = new JMenuItem("Alta Pisos"), baja = new JMenuItem("Baja Pisos");

	JMenu ayuda = new JMenu("Ayuda");
	JMenuItem acerca = new JMenuItem("Acerca de...");

	JButton botonAlta = new JButton(new ImageIcon(getClass().getResource("/recursos/Add.png"))),
			botonBaja = new JButton(new ImageIcon(getClass().getResource("/recursos/Remove.png")));

	public VentanaPrincipal() {
		// Establezco el tama�oo de la ventana a la mitad de las dimensiones del monitor.
		this.setSize(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2);
		// Le agrego detener el programa al cerrar.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Establezco mi icono.
		this.setIconImage(MI_ICONO);

		// A�ado los eventos a los items.
		salir.addActionListener(this);
		acerca.addActionListener(this);
		alta.addActionListener(this);
		botonAlta.addActionListener(this);
		baja.addActionListener(this);
		botonBaja.addActionListener(this);

		// A��ado los items a los men�s.
		archivo.add(salir);

		registro.add(alta);
		registro.addSeparator();
		registro.add(baja);

		ayuda.add(acerca);

		// A�ado los men�s a las barras.
		miBarra.add(archivo);
		
		// Asigno el atajo alr + R a registro.
		registro.setMnemonic('R');
		miBarra.add(registro);
		miBarra.add(ayuda);

		// Establezco mi barra como mi barra men� para esta ventana.
		this.setJMenuBar(miBarra);

		// Posicionar los botones.
		this.setLayout(new GridLayout(2, 1, 20, 20));
		botonAlta.setMnemonic('A');
		botonAlta.setToolTipText("Bot�n para reservar un hotel.");
		this.add(botonAlta);
		botonBaja.setMnemonic('B');
		botonBaja.setToolTipText("Bot�n para cancelar una reserva de un hotel");
		this.add(botonBaja);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == salir)
			System.exit(EXIT_ON_CLOSE);

		if (e.getSource() == acerca)
			JOptionPane.showMessageDialog(null, "Nombre de la empresa: Libring\n"
					+ "Libring, fundada en Almer�a en 2021, ha pasado de ser una peque�a startup almeriense, "
					+ "a una de las mayores compa��as digitales de viajes de todo el mundo.\n"
					+ "Libring forma parte de Libring Holdings Inc. (NASDAQ: LBNG) y tiene como misi�n hacer que descubrir el mundo sea m�s f�cil para todos.");

		if (e.getSource() == alta || e.getSource() == botonAlta) {
			VentanaDialogo miVentanaDialogo = new VentanaDialogo(this, true);
			miVentanaDialogo.setVisible(true);
		}
		if (e.getSource() == baja || e.getSource() == botonBaja)
			JOptionPane.showMessageDialog(null, "Opci�n a�n no desarrollada.");
	}
}
