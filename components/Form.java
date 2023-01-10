package components;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;

import helpers.HandleFile;

import javax.swing.JButton;

public class Form extends JPanel implements ActionListener
{
	private JButton button;

	private JTextField textField;

	public Form()
	{
		this.button = new JButton("Enviar");
		
		this.textField = new JTextField("Nome dos arquivos", 40);

		this.button.addActionListener(this);

		add(this.textField);
		
		add(this.button);
	}

	/**
	 * Calls the function to move the files under the new name. It also resets the value of the text field
	 * 
	 * @param e | Object of the event
	 * @return void
	 */
	public void actionPerformed(ActionEvent e)
	{
		try {
			HandleFile.moveFiles(this.textField.getText());
			this.textField.setText("");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}