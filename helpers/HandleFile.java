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
	 * @param index
	 * @throws IOException
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
	 * @param path
	 * @param var
	 * @param index
	 * @throws IOException
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
	 * @param path
	 * @throws IOException
	 * 
	 * @return void
	 */
	public static void setBasePath(String path) throws IOException
	{
		setSetting(path, "BASE_PATH", 0);
	}

	/**
	 * Returns the value of the base path where the files are located
	 * 
	 * @throws IOException
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
	 * @param path
	 * @throws IOException
	 * 
	 * @return void
	 */
	public static void setRelativePath(String path) throws IOException
	{
		setSetting(path, "RELATIVE_PATH", 1);
	}

	/**
	 * Returns the path where the files will be moved to
	 * 
	 * @throws IOException
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
	 * @param fileName
	 * @throws IOException
	 * 
	 * @return File
	 */
	public static File createNewDirectory(String fileName) throws IOException
	{
		String rootPath = getRelativePath();

		File newFolder = new File(rootPath + "\\" + fileName);

		newFolder.mkdir();

		return newFolder;
	}

	/**
	 * Searches for and returns all files in the base directory
	 * 
	 * @param fileName
	 * @throws IOException
	 * 
	 * @return File[]
	 */
	public static File[] setup(String fileName) throws IOException
	{
		String rootPath = getBasePath();
		
		File currentPath = new File(rootPath);

		File[] files = currentPath.listFiles();

		return files;
	}

	/**
	 * Moves and renames all files from the base directory to the relative directory
	 * 
	 * @param fileName
	 * @throws IOException
	 * 
	 * @return void
	 */
	public static void moveFiles(String fileName) throws IOException
	{
		File[] files = setup(fileName);

		File newFolder = createNewDirectory(fileName);

		int i = 0;
		
		for (File file : files)
		{
			if (file.getName().contains("Imagem")) {
				try {
					
					i++;

					Path fileMovePath = Paths.get(file.getAbsolutePath());

					Path targetPath = Paths.get(newFolder.getAbsolutePath() + "\\" + fileName + " " + i + ".jpg");

					Files.move(fileMovePath, targetPath);

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}
	
}