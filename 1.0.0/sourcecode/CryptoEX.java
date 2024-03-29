/*/////////////////////////////////////////
CryptoEX is a set of little software tools
to explore some alternatives for encryption.
It is not a profesional software, is intended
to be used in the classroom to study and
explore some aspects of cryptography.
/////////////////////////////////////////*/
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import cex.*;

class CryptoEX implements ActionListener {

	JLabel coment = new JLabel("", JLabel.CENTER);
	JButton cexprime = new JButton();
	JButton cexromano = new JButton();
	JButton cexkey = new JButton();
	JButton cexmirror = new JButton();

	Font frameFont;
	Font buttonFont;

	static ImageIcon ic;
	static String os;
	static String ln;

	String language[] = new String[6];

	public static void main(String[] args) throws Exception{
		ln = System.getProperty("user.language");
		os = System.getProperty("os.name");
		CryptoEX c = new CryptoEX(os, ln);
	}

	/*/////////
	Constructor
	/////////*/
	CryptoEX (String os, String ln){
		getLanguage(ln);
		buildFrame(os);
	}

	/*////////////////
	Actions management
	////////////////*/
	public void actionPerformed (ActionEvent ae) {

		if(ae.getSource() == cexprime) {
			CEXPrime cexP = new CEXPrime(os, ln, ic);
		}

		if(ae.getSource() == cexmirror) {
			CEXMirror cexM = new CEXMirror(os, ln, ic);
		}

		if(ae.getSource() == cexromano) {
			CEXCaesarCipher cexR = new CEXCaesarCipher(os, ln, ic);
		}

		if(ae.getSource() == cexkey) {
			CEXKey cexK = new CEXKey(os, ln, ic);
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

        if (os.startsWith("Windows") || os.startsWith("Linux")){
	    	URL iconUrl = getClass().getResource("img/Icono.png");
			if (iconUrl != null){
				ic = new ImageIcon(iconUrl);
				v.setIconImage(ic.getImage());
			}
		}

		v.setSize(400, 340);
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
		cexmirror.setEnabled(true);
		cexmirror.addActionListener(this);

		cexromano.setText(language[3]);
		cexromano.setFont(frameFont);
		cexromano.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexromano.setVisible(true);
		cexromano.setEnabled(true);
		cexromano.addActionListener(this);

		cexkey.setText(language[4]);
		cexkey.setFont(frameFont);
		cexkey.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexkey.setVisible(true);
		cexkey.setEnabled(true);
		cexkey.addActionListener(this);

		cexprime.setText(language[5]);
		cexprime.setFont(frameFont);
		cexprime.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cexprime.setVisible(true);
		cexprime.setEnabled(true);
		cexprime.addActionListener(this);

		p.add(Box.createRigidArea(d));
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
						+ "Aquí pueden decidir que algoritmo<br>de cifrado usar</div><html>";
			language[2] = "Escribiendo al revés";
			language[3] = "Con Cifrado del César";
			language[4] = "Con clave secreta";
			language[5] = "Con números primos";
		} else {
			language[0] = "CryptoEX";
			language[1] = "<html><div align='center'>Welcome to <b>CryptoEX</b><br>"
						+ "Here you can decide which encryption<br>algorithm to use</div><html>";
			language[2] = "Writing backwards";
			language[3] = "With Caesar Cipher";
			language[4] = "With secret key";
			language[5] = "With prime numbers";
		}

	}

}
