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

	public void actionPerformed(ActionEvent e)
	{
		try {
			HandleFile.moveFiles(this.textField.getText());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}