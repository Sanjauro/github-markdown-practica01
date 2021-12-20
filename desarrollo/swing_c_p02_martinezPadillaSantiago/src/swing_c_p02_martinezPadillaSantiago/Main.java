/**
 * Main.java
   22 nov 2021 10:19:22
   @author Santiago Martínez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

public class Main {

	/**
	 * Main que llama crea la ventana principal y la sitúa en el centro.
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		
		ventanaPrincipal.setTitle("Gestión Apartamentos Turísticos Libring");
		//Centro la ventana.
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setVisible(true);
	}

}
