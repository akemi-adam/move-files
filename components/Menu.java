package components;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import helpers.HandleFile;

import javax.swing.JFileChooser;

import java.io.File;
import java.io.IOException;

public class Menu extends JMenuBar
{
	private JFileChooser chooserBaseField;

	private JFileChooser chooserRelativeField;

	private JMenu menuFile;

	private JMenuItem selectPathOption;

	private JMenuItem selectRelativePathOption;

	public Menu()
	{
		this.chooserBaseField = new JFileChooser();

		this.menuFile = new JMenu("File");

		add(this.menuFile);

		this.selectPathOption = new JMenuItem("Diretório das imagens");

		this.selectRelativePathOption = new JMenuItem("Diretório para mover as imagens");

		this.menuFile.add(this.selectPathOption);

		this.menuFile.add(this.selectRelativePathOption);

		this.selectPathOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				chooserBaseField = new JFileChooser();

				chooserBaseField.setCurrentDirectory(new File("."));

				chooserBaseField.setDialogTitle("Escolha o diretório");

				chooserBaseField.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				chooserBaseField.setAcceptAllFileFilterUsed(false);

				if (chooserBaseField.showOpenDialog(chooserBaseField) == JFileChooser.APPROVE_OPTION) {

					try {
						HandleFile.setBasePath(chooserBaseField.getSelectedFile().getAbsolutePath());
					} catch (IOException exception) {
						exception.printStackTrace();
					}

				}
			}

		});

		this.selectRelativePathOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				chooserRelativeField = new JFileChooser();

				chooserRelativeField.setCurrentDirectory(new File("."));

				chooserRelativeField.setDialogTitle("Escolha o diretório");

				chooserRelativeField.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				chooserRelativeField.setAcceptAllFileFilterUsed(false);

				if (chooserRelativeField.showOpenDialog(chooserRelativeField) == JFileChooser.APPROVE_OPTION) {

					try {
						HandleFile.setRelativePath(chooserRelativeField.getSelectedFile().getAbsolutePath());
					} catch (IOException exception) {
						exception.printStackTrace();
					}

				}
			}
			
		});
	}
}