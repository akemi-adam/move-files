package components;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

import java.io.File;

import java.util.function.Consumer;

import helpers.HandleFile;

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
				chooserBaseField = setupChooser("Escolha o diretório");

				chooserAction(chooserBaseField, HandleFile::setBasePath);
			}

		});

		this.selectRelativePathOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				chooserRelativeField = setupChooser("Escolha o diretório");

				chooserAction(chooserRelativeField, HandleFile::setRelativePath);
			}
			
		});
	}

	/**
	 * Returns an entire instance of chooser already configured to receive only directories
	 * 
	 * @param title | Chooser title
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return JFileChooser instance
	 */
	protected JFileChooser setupChooser(String title)
	{
		JFileChooser chooser = new JFileChooser();

		chooser.setCurrentDirectory(new File("."));

		chooser.setDialogTitle(title);

		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		chooser.setAcceptAllFileFilterUsed(false);

		return chooser;
	}

	/**
	 * Calls a certain function passed by parameter if a directory is chosen in the chooser
	 * 
	 * @param chooser | Object that represents the chooser to determine the directory
	 * @param method | Method that will be called if a directory is chosen
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return void
	 */
	protected void chooserAction(JFileChooser chooser, Consumer<String> method)
	{
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION)
			method.accept(chooser.getSelectedFile().getAbsolutePath());
	}
}