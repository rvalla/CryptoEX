package cex;
/*//////////////////////////////////////////////////////////////////////////////
CEXCaesarCipher uses a very simple encryption technique known as "Caesar Cipher"
(yes, it was used by the Roman Empire). The technique consists in replacing each
letter of the message by a another letter at a constant distance in the alphabet.
For example, shifting 2 places the A becomes C, the B becomes D and so on...
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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.net.URL;
import java.util.Hashtable;
import tools.TextMethods;

public class CEXCaesarCipher implements ActionListener {

	//Global variables
	Color black = new Color(35, 35, 35);
	Color white = new Color(255, 255, 255);
	Color red = new Color(190, 40, 40);
	Color green = new Color(0, 255, 95);
	
	JTextArea message = new JTextArea("");
	JTextArea encrypted = new JTextArea("");
	JButton erase = new JButton();
	JButton encrypt = new JButton();
	JButton decrypt = new JButton();
	JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 27, 1);

	Font frameFont;
	Font messageFont;
	Font buttonsFont;
	
	Dimension textAreaSize = new Dimension(650, 180);
	
	String language[] = new String[7];
	char aUC[] = new char[27];
	char alc[] = new char[27];
	
	TextMethods tM;
	
	/*//////////
	Constructor
	/////////*/
	public CEXCaesarCipher (String os, String ln, ImageIcon ic){
		getLanguage(ln);
		getAlphabets();
		tM = new TextMethods();
		buildFrame(os, ic);
	}
	
	/*///////////////////////////////////
	Methods for encryption and decryption 
	///////////////////////////////////*/
	char[] encryptChain (char[] c, int s){
		for (int i = 0; i < c.length; i++){
			for (int e = 0; e < aUC.length; e++){
				if (c[i] == aUC[e]){
					c[i] = aUC[(e + s)%27];
					break;
				} else if (c[i] == alc[e]){
					c[i] = alc[(e + s)%27];
					break;
				}
			}
		}
		return c;
	}
	
	int getInverse (int t){
		int i = 27 - t;
		return i;
	}
	
	/*////////////////
	Action management
	////////////////*/
	public void actionPerformed (ActionEvent ae) {
		
		if(ae.getSource() == encrypt) {
			int t = slider.getValue();
			String m = message.getText();
			if (m != null && !m.isEmpty()){
				char[] c = tM.textChain(m);
				c = tM.simplifyChain(c);
				c = encryptChain(c, t);
				encrypted.setText(tM.getMessage(c));
			} else {
				nullMessage();
			}		
		}
		
		if(ae.getSource() == decrypt) {
			int t = slider.getValue();
			String m = encrypted.getText();
			if (m != null && !m.isEmpty()){
				char[] c = tM.textChain(m);
				c = tM.simplifyChain(c);
				c = encryptChain(c, getInverse(t));
				message.setText(tM.getMessage(c));
			} else {
				nullMessage();
			}
		}
		
		if(ae.getSource() == erase) {	
			message.setText("");
			encrypted.setText("");
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
		
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			table.put (1, new JLabel("1"));
    		table.put (14, new JLabel("14"));
    		table.put (27, new JLabel("27"));
		
		slider.setVisible(true);
		slider.setValue(14);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(table);
		slider.setMinorTickSpacing(1);

		Dimension d = new Dimension(10, 0);
		
		p.add(Box.createRigidArea(d));
		p.add(slider);
		p.add(Box.createRigidArea(d));
	
		p.setMaximumSize(new Dimension(450, 40));
	
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
	
	/*///////////////
	Loading alphabets
	///////////////*/
	void getAlphabets() {
	
		aUC = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
		alc = "abcdefghijklmnñopqrstuvwxyz".toCharArray();
	
	}
	
	/*///////////////////////
	Loading language strings
	///////////////////////*/
	void getLanguage(String s){
	
		if (s.equals("es")){
			language[0] = "CryptoEX Cifrado del César";
			language[1] = "Mensaje";
			language[2] = "Cifrado";
			language[3] = "Encriptar";
			language[4] = "Descifrar";
			language[5] = "Borrar";
			language[6] = "<html>No encuentro ningún mensaje.<br>¡Escriba un mensaje!</html>";
		} else {
			language[0] = "CryptoEX Caesar Cipher";
			language[1] = "Message";
			language[2] = "Encrypted";
			language[3] = "Encrypt";
			language[4] = "Decrypt";
			language[5] = "Erase";
			language[6] = "<html>I don't find any messages.<br>Write a message!</html>";
		}	
		
	}
	
	/*////////////
	Error handling
	////////////*/
	void nullMessage(){
		JOptionPane.showMessageDialog(new JFrame(),
   				language[6],
  				"Ups",
			JOptionPane.WARNING_MESSAGE);
	}
	
}