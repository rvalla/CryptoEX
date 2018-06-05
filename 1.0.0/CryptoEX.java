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

/*/////////////////////////////////////////
CryptoEX is a set of little software tools
to explore some alternatives for encryption.
It is not a profesional software, is intended
to be used in the classroom to study and
explore some aspects of cryptography.
/////////////////////////////////////////*/
class CryptoEX implements ActionListener {
	
	//Variables globales
	Color black = new Color(45, 45, 45);
	Color white = new Color(255, 255, 255);
	Color red = new Color(190, 40, 40);
	Color blue = new Color(90, 140, 190);
	
	JLabel coment = new JLabel("", JLabel.CENTER);
	JButton cexprime = new JButton();
	JButton cexromano = new JButton();
	JButton cexkey = new JButton();
	JButton cexmirror = new JButton();

	Font frameFont;
	Font buttonFont;
	
	String language[] = new String[6];
	
	public static void main(String[] args) throws Exception{
		CryptoEX c = new CryptoEX();	
	}

	CryptoEX(){
		getLanguage(System.getProperty("user.language"));
		buildFrame(System.getProperty("os.name"));
	}

	public void actionPerformed (ActionEvent ae) {
	
		if(ae.getSource() == cexprime) {
			
			CEXPrime cexp = new CEXPrime();
			
		}
	
	}
	
	/*////////////
	GUI management
	////////////*/
	//Building the frame...
	void buildFrame(String os) {
		
		JFrame v = new JFrame(language[0]);
		v.setDefaultCloseOperation(3);
		v.setResizable(false);
		
		frameFont = new Font("monospace", Font.PLAIN, 14);
		buttonFont = new Font("monospace", Font.BOLD, 15);
		
        //Icono para windows y linux
        if (os.startsWith("Windows") || os.startsWith("Linux")){
	    	URL iconUrl = getClass().getResource("Icono.png");
			if (iconUrl != null){
				ImageIcon icon = new ImageIcon(iconUrl);
				v.setIconImage(icon.getImage());
			}
		}
				
		v.setSize(400, 325);		
		v.setLocationRelativeTo(null);
		v.add(builPanel());
		v.setVisible(true);

	}
	
	//Building the frame's panels...
	JPanel builPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));		
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(hPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		
		return p;

	}
	
	JPanel hPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(buttonsPanel());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		
		return p;

	}
	
	JPanel buttonsPanel(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		Dimension d = new Dimension(0, 10);
		Dimension dd = new Dimension(0, 5);
		Dimension ddd = new Dimension(0, 22);
		
		cexmirror.setText(language[2]);
		cexmirror.setFont(frameFont);
		cexmirror.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexmirror.setVisible(true);
		cexmirror.setEnabled(false);
		cexmirror.addActionListener(this);
		
		cexromano.setText(language[3]);
		cexromano.setFont(frameFont);
		cexromano.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexromano.setVisible(true);
		cexromano.setEnabled(false);
		cexromano.addActionListener(this);
		
		cexkey.setText(language[4]);
		cexkey.setFont(frameFont);
		cexkey.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexkey.setVisible(true);
		cexkey.setEnabled(false);
		cexkey.addActionListener(this);
		
		cexprime.setText(language[5]);
		cexprime.setFont(frameFont);
		cexprime.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexprime.setVisible(true);
		cexprime.setEnabled(true);
		cexprime.addActionListener(this);
		
		p.add(Box.createRigidArea(ddd));
		p.add(comentPanel());
		p.add(Box.createRigidArea(ddd));
		p.add(cexmirror);
		p.add(Box.createRigidArea(dd));
		p.add(cexromano);
		p.add(Box.createRigidArea(dd));
		p.add(cexkey);
		p.add(Box.createRigidArea(dd));
		p.add(cexprime);
		p.add(Box.createRigidArea(ddd));
		p.add(classInfoPanel());
		p.add(Box.createRigidArea(d));
	
		return p;
	
	}
	
	JPanel comentPanel(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));	
		
		coment.setText(language[1]);
		coment.setFont(frameFont);
		coment.setVisible(true);
		
		coment.setFont(frameFont);
		p.add(coment);
	
		return p;
	
	}
	
	JPanel classInfoPanel(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));	
		JLabel classInfo = new JLabel("<html><div align='center'>github.com/rvalla/CryptoEX"
									+ "<br>Rodrigo Valla - 2018</div><html>", JLabel.CENTER);
		classInfo.setFont(frameFont);
		p.add(classInfo);
	
		return p;
	
	}
	
	/*///////////////////////
	Loading language strings
	///////////////////////*/
	void getLanguage(String s){
	
		if (s.equals("es")){
			language[0] = "CryptoEX";
			language[1] = "<html><div align='center'>Bienvenidos a <b>CryptoEX</b><br>"
						+ "Aquí pueden decidir que algoritmo de cifrado usar</div><html>";
			language[2] = "Escribiendo al revés";
			language[3] = "Con giro de abecedario";
			language[4] = "Con clave secreta";
			language[5] = "Con números primos";
		} else {
			language[0] = "CryptoEX";
			language[1] = "<html><div align='center'>Welcome to <b>CryptoEX</b><br>"
						+ "Here you can decide which encryption algorithm to use</div><html>";
			language[2] = "writing backwards";
			language[3] = "With alphabet twist";
			language[4] = "With secret key";
			language[5] = "With prime numbers";
		}	
		
	}
	
}