/**
 * PanelDatosPersonales.java
 * 23 nov 2021 9:59:56
 *
 * @author Santiago Mart�nez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class PanelDatosPersonales extends JPanel {

    static JTextField cajaNombre = new JTextField(15), cajaApellidos = new JTextField(25);
    static JFormattedTextField cajaDNI = null, cajaTelefono = null;

    private GridBagLayout layout;
    private GridBagConstraints constraints;
    
    public PanelDatosPersonales() {
        JLabel labelNombre = new JLabel("Nombre"), labelApellidos = new JLabel("Apellidos"), labelDNI = new JLabel("DNI"), labelTelefono = new JLabel("Tel�fono");
        
        this.setBackground(new Color(250, 218, 169));
        
        

        //A�ado las cajas formateadas de DNI y Tel�fono.
        try {
            cajaDNI = new JFormattedTextField(new MaskFormatter("########U"));
            cajaDNI.setFocusLostBehavior(cajaDNI.COMMIT);
            cajaTelefono = new JFormattedTextField(new MaskFormatter("#########"));
            cajaTelefono.setFocusLostBehavior(cajaTelefono.COMMIT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Layout
        layout = new GridBagLayout();
        this.setLayout(layout);
        constraints = new GridBagConstraints();
        
        //A�ado los campos de texto con sus Labels.
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.addComponent(labelNombre, 0, 0, 1, 1);
        this.addComponent(cajaNombre, 0, 1, 1, 1);
        
        this.addComponent(labelApellidos, 0, 2, 1, 1);
        this.addComponent(cajaApellidos, 0, 3, 1, 1);
        
        this.addComponent(labelDNI, 0, 4, 1, 1);
        this.addComponent(cajaDNI, 0, 5, 1, 1);
        
        this.addComponent(labelTelefono, 0, 6, 1, 1);
        this.addComponent(cajaTelefono, 0, 7, 1, 1);
        
        //A�ado nombre a mis campos.
        cajaNombre.setName("Nombre");
        cajaApellidos.setName("Apellidos");
        cajaDNI.setName("DNI");
        cajaTelefono.setName("Tel�fono");
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
