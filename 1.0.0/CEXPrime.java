/*/////////////////////////////////////////////////////////////////////////////////////
CifroConMiPrimo puede encriptar cadenas de cantidades primas de caracteres. Utiliza
la multiplicación para desordenar los caracteres de la cadena.
Como ustedes quizás sepan, en un conjunto de p elementos, la multiplicación es una
función biyectiva y nos va a permitir recuperar la información.
/////////////////////////////////////////////////////////////////////////////////////*/
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;

class CEXPrime implements ActionListener {

	//Variables globales
	Color negro = new Color(45, 45, 45);
	Color blanco = new Color(255, 255, 255);
	Color rojo = new Color(190, 40, 40);
	Color azul = new Color(90, 140, 190);
	
	JTextArea mensaje = new JTextArea("");
	JTextArea cifrado = new JTextArea("");
	JLabel factor = new JLabel("47");
	JButton borrar = new JButton();
	JButton encriptar = new JButton();
	JButton descifrar = new JButton();
	JButton sumar = new JButton();
	JButton restar = new JButton();
	JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);

	Font ventanaFont;
	Font mensajeFont;
	Font botonesFont;
	
	Dimension tamañoAreasTexto = new Dimension(650, 180);
	
	static int[] primos = new int[8];
	String idioma[] = new String[6];
	
		
	/*//////////
	Constructor
	/////////*/
	CEXPrime(){
		getIdioma(System.getProperty("user.language"));
		cargarPrimos();
		construirVentana(System.getProperty("os.name"));
	}
	
	/*////////////////////////////////
	Métodos para manipular el mensaje 
	////////////////////////////////*/
	char[] cadenaCifrada (char[] c, int p, int f){
		char[] r = obtenerMensaje(c, p, f);
		return r;
	}
	
	char[] obtenerMensaje (char[] c, int p, int f){
		char[] m = new char[p];
		iniciarCadena(m);
		for (int i = 0; i < c.length; i++){
			m[(i * f) % p] = c[i];
		}
		return m;
	}
	
	
	/*////////////////////////////////
	Métodos para manipular el texto 
	////////////////////////////////*/
	String mostrarMensaje(char[] c){
		String m = new String(c);
		return m;
	}
	
	void mostrarCifrado(char[] c){
		String e = new String(c);
		cifrado.setText(e);
	}
	
	char[] cadenaTexto (String m){
		char[] cadena = m.toCharArray();
		return cadena;
	}
	
	void iniciarCadena(char[] c){
		for (int i = 0; i < c.length; i++){
			c[i]=' ';
		}
	}
	
	boolean mensajeValido(char c[], int p){
		boolean v;
		if (c.length > p){
			v = false;
		} else {
			v = true;
		}
		return v;
	}
	
	void mensajeGrande(){
		JOptionPane.showMessageDialog(new JFrame(),
   				"<html>El mensaje es demasiado largo.<br>Pruebe ajustando el tamaño.</html>",
  				"Error",
		JOptionPane.ERROR_MESSAGE);
	}
	
	void mensajeNulo(){
		JOptionPane.showMessageDialog(new JFrame(),
   				"<html>No encuentro ningún mensaje.<br>¡Escriba un mensaje!</html>",
  				"Error",
		JOptionPane.ERROR_MESSAGE);
	}
	
	void factorGrande(){
		JOptionPane.showMessageDialog(new JFrame(),
   				"<html>El factor es demasiado grande.<br>¡Ajuste el factor o el tamaño del mensaje!</html>",
  				"Error",
		JOptionPane.ERROR_MESSAGE);
	}
	
	
	/*//////////////////////////////////////////
	Cargamos algunos números primos (limitan el 
	tamaño del mensaje que se puede encriptar)
	//////////////////////////////////////////*/
	static void cargarPrimos () {
		
		primos[0]=53;
		primos[1]=107;
		primos[2]=163;
		primos[3]=223;
		primos[4]=307;
		primos[5]=560;
		primos[6]=709;
		primos[7]=1117;
	
	}
	
	/*//////////////////////////////////////////
	Métodos para el funcionamiento del programa
	//////////////////////////////////////////*/
	public void actionPerformed (ActionEvent ae) {
		
		if(ae.getSource() == sumar) {
			
			String t = factor.getText();
			int n = Integer.parseInt(t);
			n = n + 1;
			factor.setText(String.valueOf(n));
			
		}
		
		if(ae.getSource() == restar) {
			
			String t = factor.getText();
			int n = Integer.parseInt(t);
			n = n - 1;
			if (n<0){n=0;}
			factor.setText(String.valueOf(n));
			
		}
		
		if(ae.getSource() == encriptar) {
			int p = primos[slider.getValue() - 1];
			String m = mensaje.getText();
			if (m != null && !m.isEmpty()){
				char[] c = cadenaTexto(m);
				int f = Integer.parseInt(factor.getText());
				if (f < p){
					if (mensajeValido(c, p)){	
						cifrado.setText(mostrarMensaje(cadenaCifrada(c, p, f)));
					} else {
						mensajeGrande();
					}
				} else {
					factorGrande();
				}
			} else {
				mensajeNulo();
			}		
		}
		
		if(ae.getSource() == borrar) {
			
			mensaje.setText("");
			cifrado.setText("");
			
		}
	
	}
	
	/*///////////////////////////////////////////////////////
	Construcción y métodos de gestión de la interfaz gráfica.
	///////////////////////////////////////////////////////*/
	
	//Construcción de la ventana, agregado del panel principal.
	void construirVentana(String os) {
		
		JFrame v = new JFrame("Cifrando con mi primo");
		v.setDefaultCloseOperation(2);
		v.setResizable(true);
		
		mensajeFont = new Font("sansserif", Font.PLAIN, 14);
		ventanaFont = new Font("monospace", Font.PLAIN, 12);
		botonesFont = new Font("monospace", Font.BOLD, 13);
		
        //Icono para windows y linux
        if (os.startsWith("Windows") || os.startsWith("Linux")){
	    	URL iconoUrl = getClass().getResource("Icono.png");
			if (iconoUrl != null){
				ImageIcon icono = new ImageIcon(iconoUrl);
				v.setIconImage(icono.getImage());
			}
		}
				
		v.setSize(700, 600);		
		v.setLocationRelativeTo(null);
		v.add(construirPanel());
		v.setVisible(true);

	}
	
	//Construcción del panel principal.
	JPanel construirPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));		
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(arribaPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(panelB1());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(panelB2());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(abajoPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(panelCI());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		
		return p;

	}
	
	//Construcción de paneles arriba y abajo
	JPanel arribaPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(construirpMensaje());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.setPreferredSize(tamañoAreasTexto);
		
		return p;

	}
	
	JPanel abajoPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(construirpCifrado());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.setPreferredSize(tamañoAreasTexto);
		
		return p;

	}
	
	//Construcción del panel que aloja el mensaje.
	JPanel construirpMensaje(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createTitledBorder(idioma[1]));
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		mensaje.setLineWrap(true);
		mensaje.setWrapStyleWord(true);
		mensaje.setFont(mensajeFont);
		mensaje.setForeground(azul);
		p.add(mensaje);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
	
		return p;
		
	}
	
	//Construcción del panel que mostrará el mensaje cifrado.
	JPanel construirpCifrado(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createTitledBorder(idioma[2]));
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		cifrado.setLineWrap(true);
		cifrado.setWrapStyleWord(true);
		cifrado.setFont(mensajeFont);
		cifrado.setForeground(rojo);
		p.add(cifrado);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
	
		return p;
		
	}
	
	//Construcción de dos paneles para los botones
	JPanel panelB1(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		sumar.setText("+");
		sumar.setFont(botonesFont);
		sumar.setVisible(true);
		sumar.addActionListener(this);
		
		restar.setText("-");
		restar.setFont(botonesFont);
		restar.setVisible(true);
		restar.addActionListener(this);
		
		factor.setFont(botonesFont);
		factor.setVisible(true);
		
		slider.setVisible(true);
		slider.setPaintTicks(false);
		slider.setPaintLabels(false);
		slider.setMaximumSize(new Dimension(200, 40));

		Dimension d = new Dimension(10, 0);
		
		p.add(Box.createRigidArea(d));
		p.add(factor);
		p.add(Box.createRigidArea(new Dimension(30, 0)));
		p.add(sumar);
		p.add(restar);
		p.add(Box.createRigidArea(new Dimension(30, 0)));
		p.add(slider);
		p.add(Box.createRigidArea(d));
	
		p.setPreferredSize(new Dimension(650, 35));
	
		return p;
	
	}
	
	JPanel panelB2(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		borrar.setText(idioma[5]);
		borrar.setFont(botonesFont);
		borrar.setVisible(true);
		borrar.addActionListener(this);

		encriptar.setText(idioma[3]);
		encriptar.setFont(botonesFont);
		encriptar.setVisible(true);
		encriptar.addActionListener(this);
		
		descifrar.setText(idioma[4]);
		descifrar.setFont(botonesFont);
		descifrar.setVisible(true);
		descifrar.addActionListener(this);
		
		Dimension d = new Dimension(25, 0);
		
		p.add(Box.createRigidArea(d));
		p.add(encriptar);
		p.add(Box.createRigidArea(d));
		p.add(descifrar);
		p.add(Box.createRigidArea(d));
		p.add(borrar);
		p.add(Box.createRigidArea(d));
	
		p.setPreferredSize(new Dimension(650, 35));
	
		return p;
	
	}
	
	//Información acerca del programa
	JPanel panelCI(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));	
		JLabel classInfo = new JLabel("<html><div align='center'>github.com/rvalla/CryptoEX"
									+ "<br>Rodrigo Valla - 2018</div><html>", JLabel.CENTER);
		classInfo.setFont(ventanaFont);
		p.add(classInfo);
	
		return p;
	
	}
	
	/*///////////////////////////////////
	Decidiendo el idioma para los botones
	///////////////////////////////////*/
	void getIdioma(String s){
	
		if (s.equals("es")){
			idioma[0] = "CryptoEX con primo";
			idioma[1] = "Mensaje";
			idioma[2] = "Cifrado";
			idioma[3] = "Encriptar";
			idioma[4] = "Descifrar";
			idioma[5] = "Borrar";
		} else {
			idioma[0] = "CryptoEX Prime";
			idioma[1] = "Message";
			idioma[2] = "Encrypted";
			idioma[3] = "Encrypt";
			idioma[4] = "decrypt";
			idioma[5] = "Erase";
		}	
		
	}
	
}