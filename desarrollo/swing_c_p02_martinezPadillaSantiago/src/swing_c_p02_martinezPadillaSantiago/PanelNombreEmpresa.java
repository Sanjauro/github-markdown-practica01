/**
 * PanelNombreEmpresa.java
   23 nov 2021 9:31:16
   @author Santiago Martínez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class PanelNombreEmpresa extends JPanel{
	public PanelNombreEmpresa() {
		JLabel nombre = new JLabel("Libring");
		
		this.setBackground(new Color(6, 31, 57));
		nombre.setFont(new Font("Source Sans Pro Black",Font.PLAIN,45));
		nombre.setForeground(Color.WHITE);
		this.add(nombre);
	}
}
