package helpers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.IOException;
import java.io.File;

import java.util.List;

public class HandleFile
{
	/**
	 * Returns the value of a configuration variable from the settings.txt file
	 * 
	 * @param index | File Line Index
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 *
	 * @return String
	 */
	protected static String getSetting(int index) throws IOException
	{
		Path file = Paths.get(new File("settings.txt").getAbsolutePath());

		List<String> lines = Files.readAllLines(file);

		String line = lines.get(index);

		return line.split("=")[1];
	}

	/**
	 * Sets the value of a configuration variable in the settings.txt file
	 * 
	 * @param path | Path of the directory to be saved
	 * @param var | Variable name related to this path
	 * @param index | Line position
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return void
	 */
	protected static void setSetting(String path, String var, int index) throws IOException
	{
		Path file = Paths.get(new File("settings.txt").getAbsolutePath());

		List<String> lines = Files.readAllLines(file);

		lines.remove(index);

		lines.add(index, var + "=" + path);

		Files.write(file, lines);
	}

	/**
	 * Sets the value of the base path where the files are located
	 * 
	 * @param path | Path of the directory to be saved
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return void
	 */
	public static void setBasePath(String path)
	{
		try {
			setSetting(path, "BASE_PATH", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the value of the base path where the files are located
	 * 
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return String
	 */
	public static String getBasePath() throws IOException
	{
		return getSetting(0);
	}

	/**
	 * Defines the path where files will be moved to
	 * 
	 * @param path | Path of the directory to be saved
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return void
	 */
	public static void setRelativePath(String path)
	{
		try {
			setSetting(path, "RELATIVE_PATH", 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the path where the files will be moved to
	 * 
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return String
	 */
	public static String getRelativePath() throws IOException
	{
		return getSetting(1);
	}

	/**
	 * Create a new directory
	 * 
	 * @param folderName | Name of the new directory
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return File
	 */
	public static File createNewDirectory(String folderName) throws IOException
	{
		File newFolder = new File(getRelativePath() + "\\" + folderName);

		newFolder.mkdir();

		return newFolder;
	}

	/**
	 * Searches for and returns all files in the base directory
	 * 
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return File[]
	 */
	public static File[] setup() throws IOException
	{
		File currentPath = new File(getBasePath());

		File[] files = currentPath.listFiles();

		return files;
	}

	/**
	 * Moves and renames all files from the base directory to the relative directory
	 * 
	 * @param name | New folder name and base file name
	 * @throws IOException
	 * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
	 * 
	 * @return void
	 */
	public static void moveFiles(String name) throws IOException
	{
		File[] files = setup();

		File newFolder = createNewDirectory(name);

		int i = 0;
		
		for (File file : files)
		{
			if (file.getName().contains("Imagem")) {
				try {
					
					i++;

					Path fileMovePath = Paths.get(file.getAbsolutePath());

					Path targetPath = Paths.get(newFolder.getAbsolutePath() + "\\" + name + " " + i + ".jpg");

					Files.move(fileMovePath, targetPath);

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}
	
}