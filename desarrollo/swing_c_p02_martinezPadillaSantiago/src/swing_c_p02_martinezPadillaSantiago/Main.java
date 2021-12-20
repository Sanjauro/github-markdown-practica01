/**
 * Main.java
   22 nov 2021 10:19:22
   @author Santiago Mart�nez Padilla
 */
package swing_c_p02_martinezPadillaSantiago;

public class Main {

	/**
	 * Main que llama crea la ventana principal y la sit�a en el centro.
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		
		ventanaPrincipal.setTitle("Gesti�n Apartamentos Tur�sticos Libring");
		//Centro la ventana.
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setVisible(true);
	}

}
