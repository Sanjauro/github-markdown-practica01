/**
 * PanelDatosReserva.java
 * 23 nov 2021 10:55:53
 *
 * @author Santiago Martínez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelDatosReserva extends JPanel implements ChangeListener, ActionListener {

    private final String[] PROVINCIAS_ARRAY = {"Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz",
        "Barcelona", "Burgos", "CÃ¡ceres", "CÃ¡diz", "Cantabria", "CastellÃ³n", "Ciudad Real", "CÃ³rdoba", "La CoruÃ±a",
        "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipüzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén",
        "León", "Lérida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Palencia", "Las Palmas",
        "Pontevedra", "La Rioja", "Salamanca", "Segovia", "Sevilla", "Soria", "Tarragona", "Santa Cruz de Tenerife",
        "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza"};
    final String[] TIPO_CAMAS = {"Simple", "Doble", "Sofá Cama"};

    static String fechaActualES = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            fechaFinES = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    JLabel labelDireccion = new JLabel("Dirección:"), labelFechaAlta = new JLabel("Fecha Alta:"), labelFechaBaja = new JLabel("Fecha Baja:");
    static JTextField cajaProvincia = new JTextField(12), cajaFechaAlta = new JTextField(fechaActualES),
            cajaFechaFin = new JTextField(fechaFinES);
    
    JComboBox<String> provincias = new JComboBox<String>(PROVINCIAS_ARRAY);

    JComboBox<String> comboTipoCamas1 = new JComboBox<String>(TIPO_CAMAS);
    JComboBox<String> comboTipoCamas2 = new JComboBox<String>(TIPO_CAMAS);
    JComboBox<String> comboTipoCamas3 = new JComboBox<String>(TIPO_CAMAS);
    JComboBox<String> comboTipoCamas4 = new JComboBox<String>(TIPO_CAMAS);

    static List<JComboBox<String>> listaCombosTiposCamas = new ArrayList<JComboBox<String>>();

    JLabel labelHuespedes = new JLabel("Número de personas:"), labelDormitorios = new JLabel("Número de dormitorios:"),
            labelBannios = new JLabel("Número de baños:"), labelCamas = new JLabel("Número de camas:");
    static JSpinner spinnerHuespedes = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1)),
            spinnerDormitorios = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1)),
            spinnerBannios = new JSpinner(new SpinnerNumberModel(0, 0, 2, 1)),
            spinnerCamas = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1));

    static JCheckBox ninnos = new JCheckBox("¿Niños?");
    JLabel labelCalculos = new JLabel("Precio Mínimo:"), labelEdadNinnos = new JLabel("Edad Niño:");
    static JTextField cajaCalculos = new JTextField(12);
    
    //Componentes extra niños.
    static JPanel extraNinno = new JPanel();
    static JSpinner edadNinno = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
    static JTextField extras = new JTextField("Cuna",18);
    
    //Componentes panel imágenes
    JPanel panelImagenes = new JPanel();
    JLabel imgPiso1 = new JLabel(new ImageIcon(getClass().getResource("/recursos/fotopiso1.jpg")));
    JLabel imgPiso2 = new JLabel(new ImageIcon(getClass().getResource("/recursos/fotopiso2.jpg")));
    JLabel imgPiso3 = new JLabel(new ImageIcon(getClass().getResource("/recursos/fotopiso3.jpg")));
    
    
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    
    public PanelDatosReserva() {
        this.setBackground(new Color(209, 141, 173));
        listaCombosTiposCamas.add(comboTipoCamas1);
        listaCombosTiposCamas.add(comboTipoCamas2);
        listaCombosTiposCamas.add(comboTipoCamas3);
        listaCombosTiposCamas.add(comboTipoCamas4);

        spinnerCamas.addChangeListener(this);
        comboTipoCamas1.setEnabled(false);
        comboTipoCamas2.setEnabled(false);
        comboTipoCamas3.setEnabled(false);
        comboTipoCamas4.setEnabled(false);
        
        //Panel exraNinno
        ninnos.addActionListener(this);
        extraNinno.add(labelEdadNinnos);
        extraNinno.add(edadNinno);
        extras.setEditable(false);
        extraNinno.add(extras);
        extras.addFocusListener(new Foco());
        extraNinno.setVisible(false);
        
        //Precio
        cajaCalculos.setText("0 €");
        cajaCalculos.addFocusListener(new Foco());
        cajaCalculos.setEditable(false);
        
        //Panel imágenes
        panelImagenes.add(imgPiso1);
        panelImagenes.add(imgPiso3);
        panelImagenes.add(imgPiso2);

        //AÃ±ado nombres a mis Spinners.
        cajaProvincia.setName("Dirección");
        cajaFechaAlta.setName("Fecha Alta");
        cajaFechaFin.setName("Fecha Fin");
        spinnerHuespedes.setName("personas");
        spinnerDormitorios.setName("dormitorios");
        spinnerBannios.setName("baños");
        spinnerCamas.setName("camas");
        cajaCalculos.setName("Precio Mánimo");
        
        //Layout
        layout = new GridBagLayout();
        this.setLayout(layout);
        constraints = new GridBagConstraints();
        
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.addComponent(labelDireccion,0,0,1,1);
        this.addComponent(cajaProvincia,1,0,1,1);
        this.addComponent(provincias,2,0,1,1);
        
        this.addComponent(labelFechaAlta,0,1,1,1);
        this.addComponent(cajaFechaAlta,1,1,1,1);
        this.addComponent(labelFechaBaja,2,1,1,1);
        this.addComponent(cajaFechaFin,3,1,1,1);
        
        this.addComponent(labelHuespedes,0,2,1,1);
        this.addComponent(spinnerHuespedes,1,2,1,1);
        this.addComponent(labelDormitorios,2,2,1,1);
        this.addComponent(spinnerDormitorios,3,2,1,1);
        this.addComponent(labelBannios,4,2,1,1);
        this.addComponent(spinnerBannios,5,2,1,1);
        this.addComponent(labelCamas,6,2,1,1);
        this.addComponent(spinnerCamas,7,2,1,1);
        
        this.addComponent(comboTipoCamas1,0,3,1,1);
        this.addComponent(comboTipoCamas2,1,3,1,1);
        this.addComponent(comboTipoCamas3,2,3,1,1);
        this.addComponent(comboTipoCamas4,3,3,1,1);
        
        this.addComponent(ninnos,0,4,1,1);
        this.addComponent(extraNinno,1,4,1,1);
        this.addComponent(labelCalculos,2,4,1,1);
        this.addComponent(cajaCalculos,3,4,1,1);
        this.addComponent(panelImagenes,0,5,8,1);
    }
    private void addComponent(Component component, int column, int row, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        this.add(component);
    }
    //Clase para los focos.
    class Foco extends FocusAdapter{
        @Override
        public void focusGained(FocusEvent f){
            //Para el campo extras.
            if (f.getSource() == extras){
                if ((Integer)edadNinno.getValue() < 3)
                    extras.setText("Cuna");
                else
                    extras.setText("Cama supletoria pequeña");
            }
            //Para el precio final.
            if (f.getSource() == cajaCalculos){
                //Precios
                final int PRECIO_BANNIOS = 25;
                final int PRECIO_CAMA_SIMPLE=15;
                final int PRECIO_CAMA_DOBLE=20;
                final int PRECIO_SOFA_CAMA=15;
                final int PRECIO_CUNA_SUPLETORIA=12;
                
                int numBannios = (int) spinnerBannios.getValue();
                int numCamas = (int) spinnerCamas.getValue();
                
                int resultado = numBannios*PRECIO_BANNIOS;
                
                for (int i = 0; i < numCamas; i++) {
                    String tipoCama = listaCombosTiposCamas.get(i).getSelectedItem().toString();
                    
                    if (tipoCama.equals(TIPO_CAMAS[0]))
                        resultado += PRECIO_CAMA_SIMPLE;
                    else if (tipoCama.equals(TIPO_CAMAS[1]))
                        resultado += PRECIO_CAMA_DOBLE;
                    else if (tipoCama.equals(TIPO_CAMAS[2]))
                        resultado += PRECIO_SOFA_CAMA;
                }
                if (ninnos.isSelected())
                    resultado += PRECIO_CUNA_SUPLETORIA;
                
                cajaCalculos.setText(String.valueOf(resultado)+" €");
            }
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        //Acción para habilitar las camas según el número de camas pedidas.
        if (e.getSource() == spinnerCamas) {
            int valorCombo = (Integer) spinnerCamas.getValue();

            for (JComboBox<String> comboTipoCama : listaCombosTiposCamas) {
                comboTipoCama.setEnabled(false);
            }

            for (int i = 0; i < valorCombo; i++) {
                listaCombosTiposCamas.get(i).setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ninnos && ninnos.isSelected()) {
            extraNinno.setVisible(true);
        } else if (e.getSource() == ninnos && !ninnos.isSelected()) {
            extraNinno.setVisible(false);
        }

    }
}
