/*/////////////////////////////////////////////////////////////////////////////////////
CifroConMiPrimo puede encriptar cadenas de cantidades primas de caracteres. Utiliza
la multiplicación para desordenar los caracteres de la cadena.
Como ustedes quizás sepan, en un conjunto de p elementos, la multiplicación es una
función biyectiva y nos va a permitir recuperar la información.
/////////////////////////////////////////////////////////////////////////////////////*/
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Math;
import java.net.URL;
import javax.swing.ImageIcon;


class CifroConMiPrimo implements ActionListener {

	//Variables globales
	Color negro = new Color(45, 45, 45);
	Color blanco = new Color(255, 255, 255);
	Color rojo = new Color(190, 40, 40);
	Color azul = new Color(90, 140, 190);
	JTextArea mensaje = new JTextArea("");
	JTextArea cifrado = new JTextArea("");
	JLabel factor = new JLabel("0");
	
	JButton borrar = new JButton();
	JButton encriptar = new JButton();
	JButton sumar = new JButton();
	JButton restar = new JButton();

	Font ventanaFont;
	Font mensajeFont;
	Font botonesFont;
	
	Dimension tamañoAreasTexto = new Dimension(650, 220);
	
	static int[] primos = new int[5];
	static int primoenuso = 0;
		
	public static void main(String[] args) throws Exception{
		
		cargarPrimos();
		primoenuso = primos[0];
		CifroConMiPrimo c = new CifroConMiPrimo();
		
	}
		
	/*//////////
	Constructor
	/////////*/
	CifroConMiPrimo(){
		
		construirVentana(System.getProperty("os.name"));
      	
	}
	
	
	/*///////////////////////////////////////////////////////
	Construcción y métodos de gestión de la interfaz gráfica.
	///////////////////////////////////////////////////////*/
	
	//Construcción de la ventana, agregado del panel principal.
	void construirVentana(String os) {
		
		JFrame v = new JFrame("Cifrando con mi primo");
		v.setDefaultCloseOperation(3);
		
		v.setResizable(false);
		v.setLocationRelativeTo(null);
		
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
		p.add(panelB());
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
		
		return p;

	}
	
	JPanel abajoPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(construirpCifrado());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		
		return p;

	}
	
	//Construcción del panel que aloja el mensaje.
	JPanel construirpMensaje(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createTitledBorder("Mensaje"));
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		mensaje.setLineWrap(true);
		mensaje.setWrapStyleWord(true);
		mensaje.setFont(mensajeFont);
		mensaje.setForeground(azul);
		mensaje.setPreferredSize(tamañoAreasTexto);
		mensaje.setMaximumSize(tamañoAreasTexto);
		p.add(mensaje);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
	
		return p;
		
	}
	
	//Construcción del panel que mostrará los resultados.
	JPanel construirpCifrado(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createTitledBorder("Cifrado"));
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		cifrado.setLineWrap(true);
		cifrado.setWrapStyleWord(true);
		cifrado.setFont(mensajeFont);
		cifrado.setForeground(rojo);
		cifrado.setPreferredSize(tamañoAreasTexto);
		cifrado.setMaximumSize(tamañoAreasTexto);
		p.add(cifrado);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
	
		return p;
		
	}
	
	JPanel panelB(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		borrar.setText("Borrar");
		borrar.setFont(botonesFont);
		borrar.setVisible(true);
		borrar.addActionListener(this);

		encriptar.setText("Encriptar");
		encriptar.setFont(botonesFont);
		encriptar.setVisible(true);
		encriptar.addActionListener(this);
		
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

		Dimension d = new Dimension(10, 0);
		
		p.add(Box.createRigidArea(d));
		p.add(factor);
		p.add(Box.createRigidArea(new Dimension(30, 0)));
		p.add(sumar);
		p.add(restar);
		p.add(Box.createRigidArea(new Dimension(100, 0)));
		p.add(encriptar);
		p.add(Box.createRigidArea(d));
		p.add(borrar);
		p.add(Box.createRigidArea(d));
	
		return p;
	
	}
	
	JPanel panelCI(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));	
		JLabel classInfo = new JLabel("<html><div align='center'>http://github.com/rvalla/cifrajes"
									+ " - Rodrigo Valla - 2018</div><html>", JLabel.CENTER);
		classInfo.setFont(ventanaFont);
		p.add(classInfo);
	
		return p;
	
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
	
	}
	
	/*//////////////////////////////////////////
	Cargamos algunos números primos (limitan el 
	tamaño del mensaje que se puede encriptar)
	//////////////////////////////////////////*/
	static void cargarPrimos () {
		
		primos[0]=331;
		primos[1]=449;
		primos[2]=709;
		primos[3]=991;
		primos[4]=1117;
	
	}

}