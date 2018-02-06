package exceptions;

import javax.swing.JOptionPane;

public class numberInvalid extends Exception{
	
	public void toString(String text) {
		JOptionPane.showMessageDialog(null, "The value for the active client can only be one or zero!"); 
	}
}
