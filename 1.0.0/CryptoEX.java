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
import java.lang.Math;
import java.net.URL;

class CryptoEX implements ActionListener {
	
	public static void main(String[] args) throws Exception{
		
		CryptoEX c = new CryptoEX();	
	}

	CryptoEX(){
		construirVentana(System.getProperty("os.name"));
	}

	public void actionPerformed (ActionEvent ae) {
	
	}
	
	
	
}