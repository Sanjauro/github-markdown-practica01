/**
 * VentanaDialogo.java
 * 22 nov 2021 12:33:23
 *
 * @author Santiago Martínez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;

public class VentanaDialogo extends JDialog {

    final Toolkit MI_PANTALLA = Toolkit.getDefaultToolkit();
    final Dimension TAMANNO_PANTALLA = MI_PANTALLA.getScreenSize();
    final Image MI_ICONO = MI_PANTALLA.getImage(getClass().getResource("/recursos/logoLibring.png"));
    final int ANCHO_PANTALLA = TAMANNO_PANTALLA.width;
    final int ALTO_PANTALLA = TAMANNO_PANTALLA.height;

    static PanelNombreEmpresa panelNombreEmpresa = new PanelNombreEmpresa();
    static PanelDatosPersonales panelDatosPersonales = new PanelDatosPersonales();
    static PanelDatosReserva panelDatosReserva = new PanelDatosReserva();
    static PanelImprimirDatos panelImprimirDatos = new PanelImprimirDatos();

    //GridBagLayout
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    
    public VentanaDialogo(VentanaPrincipal miVentanaPrincipal, boolean modal) {
        // Referente a este JDialog.
        super(miVentanaPrincipal, true);
        this.setTitle("Alta Pisos");
        this.setIconImage(MI_ICONO);
        this.setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        
        //Layout
        layout = new GridBagLayout();
        this.setLayout(layout);
        constraints = new GridBagConstraints();
        
        // Añadir los paneles y posicionarlos.
        panelNombreEmpresa.setBorder(BorderFactory.createMatteBorder(3, 3, 5, 5, Color.ORANGE));
        
        /*GRIDBAGLAYOUT*/
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        this.addComponent(panelNombreEmpresa, 0, 0, 3, 1);
        
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.addComponent(panelDatosPersonales, 0, 1, 1, 1);
        
        this.addComponent(panelDatosReserva, 1, 1, 1, 1);
        
        this.addComponent(panelImprimirDatos, 2, 1, 1, 1);
        
    }
    
    private void addComponent(Component component, int column, int row, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        this.add(component);
    }
}
