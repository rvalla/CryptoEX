package cex;
/*/////////////////////////////////////////////////////////////////////////////
CEXMirror encrypt the message showing the symbols in reverse order. It offers
two options: 1) reverse all the string or 2) divide the message using a
character selected by the user and then reverse each "word".
/////////////////////////////////////////////////////////////////////////////*/
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.net.URL;
import tools.TextMethods;

public class CEXMirror implements ActionListener {

	//Global variables
	Color black = new Color(35, 35, 35);
	Color white = new Color(255, 255, 255);
	Color red = new Color(190, 40, 40);
	Color green = new Color(0, 255, 95);
	
	JTextArea message = new JTextArea("");
	JTextArea encrypted = new JTextArea("");
	JTextField character = new JTextField("");
	JButton erase = new JButton();
	JButton encrypt = new JButton();
	JButton decrypt = new JButton();
	JRadioButton option[] = new JRadioButton[2];

	Font frameFont;
	Font messageFont;
	Font buttonsFont;
	
	Dimension textAreaSize = new Dimension(650, 180);
	
	String language[] = new String[14];
	
	TextMethods tM;
	
	/*//////////
	Constructor
	/////////*/
	public CEXMirror (String os, String ln, ImageIcon ic){
		getLanguage(ln);
		tM = new TextMethods();
		buildFrame(os, ic);		
	}
	
	/*///////////////////////////////////
	Methods for encryption and decryption 
	///////////////////////////////////*/
	String invertedString (String s){
		String i = new String();
		i = new StringBuilder(s).reverse().toString();
		return i;
	}
	
	String[] invertedStrings (String[] s){
		String i[] = new String[s.length];
		for (int e = 0; e < i.length; e++){
			i[e] = new StringBuilder(s[e]).reverse().toString();
		}
		return i;
	}
	
	void encryptSplit(String s, JTextArea a){
		String selectedCharacter = character.getText();
		int sChLength = selectedCharacter.length();
		if (anoyingCharacters(selectedCharacter) == true){
			errorSelectedCharacter();
			return;
		}
		String r = new String("");
		if (sChLength > 0 && sChLength < 4){
			String m[];
			try {
       			m = s.split(selectedCharacter);
           	} catch (Exception e) {
          		errorSelectedCharacter();
          		return;
           	} 			
			String e[] = invertedStrings(m);
			r = tM.catString(e, selectedCharacter);
			if (s.endsWith(selectedCharacter)){
				a.setText(r.substring(0, r.length() - sChLength));				
			} else {
				a.setText(r);
			}
		} else {
			errorCharacter();
		}
	}
	
	/*////////////////
	Action management
	////////////////*/
	public void actionPerformed (ActionEvent ae) {
		
		if(ae.getSource() == encrypt) {
			String m = message.getText();
			String c = character.getText();
			if (m != null && !m.isEmpty())
				if (option[0].isSelected() == true){
					encrypted.setText(invertedString(m));
				} else {
					if (m.length() > c.length()){
						encryptSplit(m, encrypted);
					} else {
						shortMessage();
					}
				}
			else {
				nullMessage();
			}
		}
		
		if(ae.getSource() == decrypt) {
			String m = encrypted.getText();
			String c = character.getText();
			if (m != null && !m.isEmpty())
				if (option[0].isSelected() == true){
					message.setText(invertedString(m));
				} else {
					if (m.length() > c.length()){
						encryptSplit(m, message);
					} else {
						shortMessage();
					}
				}
			else {
				nullMessage();
			}
		}
		
		if(ae.getSource() == erase) {
			message.setText("");
			encrypted.setText("");
		}
		
		if(ae.getSource() == option[0]) {
			if (option[0].isSelected() == true) {
				option[1].setSelected(false);
			} else {
				option[0].setSelected(true);
			}
		}
	
		if(ae.getSource() == option[1]) {
			if (option[1].isSelected() == true){
				option[0].setSelected(false);
			} else {
				option[1].setSelected(true);
			}
		}
		
	}
	
	/*////////////
	GUI management
	////////////*/
	//Building the frame...
	void buildFrame(String os, ImageIcon ic) {
		
		JFrame v = new JFrame(language[0]);
		v.setDefaultCloseOperation(2);
		v.setResizable(true);
		
		messageFont = new Font("sansserif", Font.PLAIN, 14);
		frameFont = new Font("monospace", Font.PLAIN, 12);
		buttonsFont = new Font("monospace", Font.BOLD, 13);
		
		if (os.startsWith("Windows") || os.startsWith("Linux")){
       		v.setIconImage(ic.getImage());
		}
				
		v.setSize(700, 650);		
		v.setLocationRelativeTo(null);
		v.add(builPanel());
		v.setVisible(true);

	}
	
	//Building the frame's panels...
	JPanel builPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));		
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(upPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(buttonsPanel1());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(buttonsPanel2());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(downPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		p.add(classInfoPanel());
		p.add(Box.createRigidArea(new Dimension (0, 10)));
		
		return p;

	}
	
	JPanel upPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(buildMessagePanel());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.setPreferredSize(textAreaSize);
		
		return p;

	}
	
	JPanel downPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));		
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.add(buildEncryptionPanel());
		p.add(Box.createRigidArea(new Dimension (10, 0)));
		p.setPreferredSize(textAreaSize);
		
		return p;

	}
	
	JPanel buildMessagePanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createTitledBorder(language[1]));
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setFont(messageFont);
		message.setMargin(new Insets(15,15,15,15));
		message.setBackground(white);
		message.setForeground(red);
		p.add(message);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
	
		return p;
		
	}
	
	JPanel buildEncryptionPanel(){
	
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createTitledBorder(language[2]));
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		encrypted.setLineWrap(true);
		encrypted.setWrapStyleWord(true);
		encrypted.setFont(messageFont);
		encrypted.setMargin(new Insets(15,15,15,15));
		encrypted.setForeground(green);
		encrypted.setBackground(black);
		p.add(encrypted);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
	
		return p;
		
	}
	
	JPanel buttonsPanel1(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		JLabel mode = new JLabel(language[6]);
		mode.setFont(buttonsFont);
		mode.setVisible(true);
		
		option[0] = new JRadioButton();
		option[0].setText(language[7]);
		option[0].setFont(buttonsFont);
		option[0].setVisible(true);
		option[0].setSelected(true);
		option[0].addActionListener(this);
		
		option[1] = new JRadioButton();
		option[1].setText(language[8]);
		option[1].setFont(buttonsFont);
		option[1].setVisible(true);
		option[1].addActionListener(this);

		Dimension d = new Dimension(10, 0);
		
		p.add(Box.createRigidArea(d));
		p.add(mode);
		p.add(Box.createRigidArea(new Dimension(30, 0)));
		p.add(option[0]);
		p.add(Box.createRigidArea(d));
		p.add(option[1]);
		p.add(characterPanel());
		p.add(Box.createRigidArea(d));
	
		p.setPreferredSize(new Dimension(650, 35));
	
		return p;
	
	}
	
	JPanel characterPanel(){
		
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setMaximumSize(new Dimension(60, 30));
	
		character.setFont(buttonsFont);
		character.setHorizontalAlignment(SwingConstants.CENTER);
		character.setVisible(true);
		
		p.add(character);
		
		return p;
		
	}
	
	JPanel buttonsPanel2(){
	
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		erase.setText(language[5]);
		erase.setFont(buttonsFont);
		erase.setVisible(true);
		erase.addActionListener(this);

		encrypt.setText(language[3]);
		encrypt.setFont(buttonsFont);
		encrypt.setVisible(true);
		encrypt.addActionListener(this);
		
		decrypt.setText(language[4]);
		decrypt.setFont(buttonsFont);
		decrypt.setVisible(true);
		decrypt.addActionListener(this);
		
		Dimension d = new Dimension(25, 0);
		
		p.add(Box.createRigidArea(d));
		p.add(encrypt);
		p.add(Box.createRigidArea(d));
		p.add(decrypt);
		p.add(Box.createRigidArea(d));
		p.add(erase);
		p.add(Box.createRigidArea(d));
	
		p.setPreferredSize(new Dimension(650, 35));
	
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
			language[0] = "CryptoEX en espejo";
			language[1] = "Mensaje";
			language[2] = "Cifrado";
			language[3] = "Encriptar";
			language[4] = "Descifrar";
			language[5] = "Borrar";
			language[6] = "Modo:";
			language[7] = "Completo";			
			language[8] = "Con caracteres:";
			language[9] = "<html>No me siento cómodo con estos<br>" + 
							"caracteres especiales<br>¡Elija otro caracter!</html>";
			language[10] = "<html>Elija entre uno y tres caracteres.<br>Ni más ni menos.</html>";
			language[11] = "<html>No hay ningún mensaje.<br>¡Escriba un mensaje!</html>";
			language[12] = "<html>¡El mensaje es muy corto!</html>";
			language[13] = "<html>¡Es imposible recuperar los datos!</html>";
		} else {
			language[0] = "CryptoEX Mirror";
			language[1] = "Message";
			language[2] = "Encrypted";
			language[3] = "Encrypt";
			language[4] = "Decrypt";
			language[5] = "Erase";
			language[6] = "Mode:";
			language[7] = "Complete";			
			language[8] = "With characters";
			language[9] = "<html>I don'n feel safe with <br>special characters." +
							"<br>Try another one!</html>";
			language[10] = "<html>Please, choose between one and 3 characters.<br>No more, no less.</html>";
			language[11] = "<html>There is not any message.<br>Write a message!</html>";
			language[12] = "<html>The message is so short!</html>";
			language[13] = "<html>It's imposible to recover the data!</html>";
		}	
		
	}
	
	/*////////////
	Error handling
	////////////*/
	void errorSelectedCharacter(){
		JOptionPane.showMessageDialog(new JFrame(),
   				language[9],
  				"Ups",
			JOptionPane.WARNING_MESSAGE);
	}
	
	void errorCharacter(){
		JOptionPane.showMessageDialog(new JFrame(),
   				language[10],
  				"Ups",
			JOptionPane.WARNING_MESSAGE);
	}
	
	void nullMessage(){
		JOptionPane.showMessageDialog(new JFrame(),
   				language[11],
  				"Ups",
			JOptionPane.WARNING_MESSAGE);
	}
	
	void shortMessage(){
		JOptionPane.showMessageDialog(new JFrame(),
   				language[12],
  				"Ups",
			JOptionPane.WARNING_MESSAGE);
	}
	
	void outOfBound(){
		JOptionPane.showMessageDialog(new JFrame(),
   				language[13],
  				"Ups",
			JOptionPane.WARNING_MESSAGE);
	}
	
	boolean anoyingCharacters(String s){
		boolean a = false;
		if (s.indexOf('.') > (-1)){
			a = true;
		}
		if (s.indexOf('|') > (-1)){
			a = true;
		}
		if (s.indexOf('$') > (-1)){
			a = true;
		}
		if (s.indexOf('^') > (-1)){
			a = true;
		}
		return a;
	}
}