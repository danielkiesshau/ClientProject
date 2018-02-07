package exceptions;

import javax.swing.JOptionPane;

public class ResponseInvalid extends Exception{
	
	public void toString(String text) {
		JOptionPane.showMessageDialog(null, text); 
	}
}
