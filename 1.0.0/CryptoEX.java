import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;

class CryptoEX implements ActionListener {
	
	//Variables globales
	Color negro = new Color(45, 45, 45);
	Color blanco = new Color(255, 255, 255);
	Color rojo = new Color(190, 40, 40);
	Color azul = new Color(90, 140, 190);
	
	JLabel comentario = new JLabel("", JLabel.CENTER);
	JButton cexprimo = new JButton();
	JButton cexromano = new JButton();
	JButton cexclave = new JButton();
	JButton cexespejo = new JButton();

	Font ventanaFont;
	Font botonesFont;
	
	String idioma[] = new String[6];
	
	public static void main(String[] args) throws Exception{
		CryptoEX c = new CryptoEX();	
	}

	CryptoEX(){
		getIdioma(System.getProperty("user.language"));
		construirVentana(System.getProperty("os.name"));
	}

	public void actionPerformed (ActionEvent ae) {
	
		if(ae.getSource() == cexprimo) {
			
			CEXPrime cexp = new CEXPrime();
			
		}
	
	}
	
	/*///////////////////////////////////////////////////////
	Construcción y métodos de gestión de la interfaz gráfica.
	///////////////////////////////////////////////////////*/
	
	//Construcción de la ventana, agregado del panel principal.
	void construirVentana(String os) {
		
		JFrame v = new JFrame(idioma[0]);
		v.setDefaultCloseOperation(3);
		v.setResizable(false);
		
		ventanaFont = new Font("monospace", Font.PLAIN, 14);
		botonesFont = new Font("monospace", Font.BOLD, 15);
		
        //Icono para windows y linux
        if (os.startsWith("Windows") || os.startsWith("Linux")){
	    	URL iconoUrl = getClass().getResource("Icono.png");
			if (iconoUrl != null){
				ImageIcon icono = new ImageIcon(iconoUrl);
				v.setIconImage(icono.getImage());
			}
		}
				
		v.setSize(400, 325);		
		v.setLocationRelativeTo(null);
		v.add(construirPanel());
		v.setVisible(true);

	}
	
	//Construcción del panel principal.
	JPanel construirPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));		
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(horizontalPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		
		return p;

	}
	
	//Construcción de paneles arriba y abajo
	JPanel horizontalPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(botonesPanel());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		
		return p;

	}
	
	
	//Construcción de dos paneles para los botones
	JPanel botonesPanel(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		Dimension d = new Dimension(0, 10);
		Dimension dd = new Dimension(0, 5);
		Dimension ddd = new Dimension(0, 22);
		
		cexromano.setText(idioma[3]);
		cexromano.setFont(ventanaFont);
		cexromano.setVisible(true);
		cexromano.setEnabled(false);
		cexromano.addActionListener(this);
		
		cexclave.setText(idioma[4]);
		cexclave.setFont(ventanaFont);
		cexclave.setVisible(true);
		cexclave.setEnabled(false);
		cexclave.addActionListener(this);
		
		cexprimo.setText(idioma[5]);
		cexprimo.setFont(ventanaFont);
		cexprimo.setVisible(true);
		cexprimo.setEnabled(true);
		cexprimo.addActionListener(this);
		
		p.add(Box.createRigidArea(ddd));
		p.add(panelWC());
		p.add(Box.createRigidArea(ddd));
		p.add(cexespejoP());
		p.add(Box.createRigidArea(dd));
		p.add(cexromano);
		p.add(Box.createRigidArea(dd));
		p.add(cexclave);
		p.add(Box.createRigidArea(dd));
		p.add(cexprimo);
		p.add(Box.createRigidArea(ddd));
		p.add(panelCI());
		p.add(Box.createRigidArea(d));
	
		return p;
	
	}
	
	JPanel cexespejoP() {
		
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		cexespejo.setText(idioma[2]);
		cexespejo.setFont(ventanaFont);
		cexespejo.setVisible(true);
		cexespejo.setEnabled(false);
		cexespejo.addActionListener(this);
		
		p.add(cexespejo);
		
		return p;
	
	}
	
	//Mensaje de bienvenida
	JPanel panelWC(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));	
		
		comentario.setText(idioma[1]);
		comentario.setFont(ventanaFont);
		comentario.setVisible(true);
		
		comentario.setFont(ventanaFont);
		p.add(comentario);
	
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
			idioma[0] = "CryptoEX";
			idioma[1] = "<html><div align='center'>Bienvenidos a <b>CryptoEX</b><br>"
						+ "Aquí pueden decidir que algoritmo de cifrado usar</div><html>";
			idioma[2] = "Escribiendo al revés";
			idioma[3] = "Con giro de abecedario";
			idioma[4] = "Con clave secreta";
			idioma[5] = "Con números primos";
		} else {
			idioma[0] = "CryptoEX";
			idioma[1] = "<html><div align='center'>Welcome to <b>CryptoEX</b><br>"
						+ "Here you can decide which encryption algorithm to use</div><html>";
			idioma[2] = "writing backwards";
			idioma[3] = "With alphabet twist";
			idioma[4] = "With secret key";
			idioma[5] = "With prime numbers";
		}	
		
	}
	
}