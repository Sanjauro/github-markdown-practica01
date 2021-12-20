/**
 * PanelImprimirDatos.java
 * 23 nov 2021 11:57:37
 *
 * @author Santiago Martínez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelImprimirDatos extends JPanel implements ActionListener {

    private static final Color COLOR_ERROR = new Color(255, 111, 111);
    private static final Color COLOR_EXITO = new Color(111, 255, 111);

    JTextArea textoDatosPersonales = new JTextArea(""), textoDatosInmueble = new JTextArea("");
    JPanel mostrarDatosPersonales = new JPanel();
    JPanel mostrarDatosInmueble = new JPanel();
    JTabbedPane pestannas = new JTabbedPane();

    Image imagenImprimir = new ImageIcon(getClass().getResource("/recursos/imprimirImprimir.jpg")).getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
    Image imagenNuevo = new ImageIcon(getClass().getResource("/recursos/nuevoImprimir.png")).getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
    Image imagenGuardar = new ImageIcon(getClass().getResource("/recursos/guardarImprimir.png")).getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);

    JButton imprimir = new JButton("Imprimir");
    JButton nuevo = new JButton("Nuevo");
    JButton guardar = new JButton("Guardar");

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    public PanelImprimirDatos() {
        this.setBackground(new Color(154, 191, 97));
        imprimir.setIcon(new ImageIcon(imagenImprimir));
        imprimir.addActionListener(this);

        nuevo.setIcon(new ImageIcon(imagenNuevo));
        nuevo.addActionListener(this);

        guardar.setIcon(new ImageIcon(imagenGuardar));
        guardar.addActionListener(this);

        textoDatosPersonales.setVisible(false);
        textoDatosInmueble.setVisible(false);
        mostrarDatosPersonales.add(textoDatosPersonales);
        mostrarDatosInmueble.add(textoDatosInmueble);

        pestannas.addTab("Datos Personales", mostrarDatosPersonales);
        pestannas.addTab("Datos Inmueble", mostrarDatosInmueble);

        //Layout
        layout = new GridBagLayout();
        this.setLayout(layout);
        constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 1;
        
        
        this.addComponent(imprimir,0,0,1,1);
        this.addComponent(nuevo,1,0,1,1);
        this.addComponent(guardar,2,0,1,1);
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.addComponent(pestannas,0,1,3,15);

    }

    private void addComponent(Component component, int column, int row, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        this.add(component);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imprimir && comprobarFormulario()) {
            String cadenaPersonal = "";
            String cadenaInmueble = "";
            boolean ninnos = false;

            //Datos Personales.
            for (Component componente : VentanaDialogo.panelDatosPersonales.getComponents()) {
                if (componente instanceof JTextField) {
                    cadenaPersonal += componente.getName() + ": " + ((JTextField) componente).getText() + ".\n";
                }
            }
            //Datos Inmueble.
            int cont = 0, numCamas = 0;
            for (Component componente : VentanaDialogo.panelDatosReserva.getComponents()) {
                if (componente instanceof JTextField && !componente.getName().equals("Precio Mínimo")) {
                    cadenaInmueble += componente.getName() + ": " + ((JTextField) componente).getText() + ".\n";
                }
                if (componente instanceof JSpinner) {
                    cadenaInmueble += componente.getName().substring(0, 1).toUpperCase() + componente.getName().substring(1) + ": " + ((JSpinner) componente).getValue() + ".\n";
                    if (componente.getName().equals("camas")) {
                        numCamas = (int) ((JSpinner) componente).getValue();
                    }
                }
                if (componente instanceof JComboBox && numCamas >= cont++) {
                    if (cont == 2) {
                        cadenaInmueble += "Tipos de Camas Pedidas\n-------------------------------------\n";
                    }
                    cadenaInmueble += ((JComboBox) componente).getSelectedItem().toString() + "\n";
                }
                if (componente instanceof JCheckBox) {
                    cadenaInmueble += ((JCheckBox) componente).isSelected() ? "Con niños.\n" : "Sin niños.\n";
                    ninnos = ((JCheckBox) componente).isSelected();
                }
            }

            if (ninnos) {
                cadenaInmueble += "Edad niño: " + PanelDatosReserva.edadNinno.getValue() + "\n";
                cadenaInmueble += "Tipo de cama: " + PanelDatosReserva.extras.getText() + "\n";
            }

            cadenaInmueble += "Precio Mínimo: " + PanelDatosReserva.cajaCalculos.getText()+" €";

            textoDatosPersonales.setText(cadenaPersonal);
            textoDatosInmueble.setText(cadenaInmueble);
            textoDatosPersonales.setVisible(true);
            textoDatosInmueble.setVisible(true);
        }

        if (e.getSource() == nuevo) {
            //Datos Personales.
            PanelDatosPersonales.cajaNombre.requestFocus();
            for (Component componente : VentanaDialogo.panelDatosPersonales.getComponents()) {
                if (componente instanceof JTextField) {
                    ((JTextField) componente).setText("");
                    componente.setBackground(Color.WHITE);
                }
            }

            //Datos Inmueble
            PanelDatosReserva.cajaProvincia.setText("");
            PanelDatosReserva.cajaProvincia.setBackground(Color.WHITE);
            PanelDatosReserva.cajaFechaAlta.setText(PanelDatosReserva.fechaActualES);
            PanelDatosReserva.cajaFechaAlta.setBackground(Color.WHITE);
            PanelDatosReserva.cajaFechaFin.setText(PanelDatosReserva.fechaFinES);
            PanelDatosReserva.cajaFechaFin.setBackground(Color.WHITE);

            PanelDatosReserva.spinnerHuespedes.setValue(0);
            PanelDatosReserva.spinnerHuespedes.getEditor().getComponent(0).setBackground(Color.WHITE);
            PanelDatosReserva.spinnerDormitorios.setValue(0);
            PanelDatosReserva.spinnerDormitorios.getEditor().getComponent(0).setBackground(Color.WHITE);
            PanelDatosReserva.spinnerBannios.setValue(0);
            PanelDatosReserva.spinnerBannios.getEditor().getComponent(0).setBackground(Color.WHITE);
            PanelDatosReserva.spinnerCamas.setValue(0);
            PanelDatosReserva.spinnerCamas.getEditor().getComponent(0).setBackground(Color.WHITE);

            for (JComboBox<String> comboTipoCama : PanelDatosReserva.listaCombosTiposCamas) {
                comboTipoCama.setSelectedIndex(0);
            }

            PanelDatosReserva.ninnos.setSelected(false);
            PanelDatosReserva.extraNinno.setVisible(false);
            PanelDatosReserva.edadNinno.setValue(0);
            PanelDatosReserva.extras.setText("Cuna");
            
            //Datos Imprimir
            textoDatosPersonales.setText("");
            textoDatosInmueble.setText("");
        }

        if (e.getSource() == guardar) {
            if (comprobarFormulario()) {
                JOptionPane.showMessageDialog(null, "¡Registro guardado!");
            }
        }
    }

    private boolean comprobarFormulario() {
        //Compruebo Datos Personales.
        if (PanelDatosPersonales.cajaNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Se ha olvidado rellenar el campo nombre.", "Campo nombre vacío", JOptionPane.ERROR_MESSAGE);
            PanelDatosPersonales.cajaNombre.requestFocus();
            PanelDatosPersonales.cajaNombre.setBackground(COLOR_ERROR);
            return false;
        } else {
            PanelDatosPersonales.cajaNombre.setBackground(COLOR_EXITO);
        }

        if (PanelDatosPersonales.cajaApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Se ha olvidado rellenar el campo apellidos.", "Campo apellidos vacío", JOptionPane.ERROR_MESSAGE);
            PanelDatosPersonales.cajaApellidos.requestFocus();
            PanelDatosPersonales.cajaApellidos.setBackground(COLOR_ERROR);
            return false;
        } else {
            PanelDatosPersonales.cajaApellidos.setBackground(COLOR_EXITO);
        }

        if (PanelDatosPersonales.cajaDNI.getText().trim().length() != 9) {
            JOptionPane.showMessageDialog(null, "Error: El campo DNI no contiene 8 números y 1 letra.", "Campo DNI inválido", JOptionPane.ERROR_MESSAGE);
            PanelDatosPersonales.cajaDNI.requestFocus();
            PanelDatosPersonales.cajaDNI.setBackground(COLOR_ERROR);
            return false;
        } else {
            PanelDatosPersonales.cajaDNI.setBackground(COLOR_EXITO);
        }

        if (PanelDatosPersonales.cajaTelefono.getText().trim().length() != 9) {
            JOptionPane.showMessageDialog(null, "Error: El campo Teléfono no contiene 9 números.", "Campo Teléfono inválido", JOptionPane.ERROR_MESSAGE);
            PanelDatosPersonales.cajaTelefono.requestFocus();
            PanelDatosPersonales.cajaTelefono.setBackground(COLOR_ERROR);
            return false;
        } else {
            PanelDatosPersonales.cajaTelefono.setBackground(COLOR_EXITO);
        }

        //Compruebo Datos Reserva.
        if (PanelDatosReserva.cajaProvincia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El campo Dirección está vacío.", "Campo Dirección inválido", JOptionPane.ERROR_MESSAGE);
            PanelDatosReserva.cajaProvincia.requestFocus();
            PanelDatosReserva.cajaProvincia.setBackground(COLOR_ERROR);
            return false;
        } else {
            PanelDatosReserva.cajaProvincia.setBackground(COLOR_EXITO);
        }

        try {
            LocalDate valorFechaAlta = LocalDate.parse(PanelDatosReserva.cajaFechaAlta.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate valorFechaFin = LocalDate.parse(PanelDatosReserva.cajaFechaFin.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            if (valorFechaAlta.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, "Error: El campo Fecha Alta está en el pasado.", "Campo Fecha Alta inválido", JOptionPane.ERROR_MESSAGE);
                PanelDatosReserva.cajaFechaAlta.requestFocus();
                PanelDatosReserva.cajaFechaAlta.setBackground(COLOR_ERROR);
                return false;
            } else {
                PanelDatosReserva.cajaFechaAlta.setBackground(COLOR_EXITO);
            }

            if (valorFechaFin.isBefore(valorFechaAlta)) {
                JOptionPane.showMessageDialog(null, "Error: El campo Fecha Baja es anterior a Fecha Alta.", "Campo Fecha Baja inválido", JOptionPane.ERROR_MESSAGE);
                PanelDatosReserva.cajaFechaFin.requestFocus();
                PanelDatosReserva.cajaFechaFin.setBackground(COLOR_ERROR);
                return false;
            } else {
                PanelDatosReserva.cajaFechaFin.setBackground(COLOR_EXITO);
            }
        } catch (DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(null, "Error:Revisa el formato de las fechas (día/mes/año).", "Formato Fechas", JOptionPane.ERROR_MESSAGE);
            PanelDatosReserva.cajaFechaAlta.requestFocus();
            PanelDatosReserva.cajaFechaAlta.setBackground(COLOR_ERROR);
            PanelDatosReserva.cajaFechaFin.setBackground(COLOR_ERROR);
            return false;
        }

        //Spinners
        for (Component componente : VentanaDialogo.panelDatosReserva.getComponents()) {
            if (componente instanceof JSpinner) {
                if ((int) ((JSpinner) componente).getValue() < 1) {
                    JOptionPane.showMessageDialog(null, "Error: El número de " + componente.getName() + " debe ser al mayor que 0.", "Error número de " + componente.getName(), JOptionPane.ERROR_MESSAGE);
                    componente.requestFocus();
                    ((JSpinner) componente).getEditor().getComponent(0).setBackground(COLOR_ERROR);
                    return false;
                }
                ((JSpinner) componente).getEditor().getComponent(0).setBackground(COLOR_EXITO);
            }
        }
        return true;
    }
}
