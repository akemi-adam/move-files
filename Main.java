import components.App;
import components.Form;
import components.Menu;

public class Main
{
	public static void main(String[] args)
	{
		App app = new App();
		
		app.setJMenuBar(new Menu());

		app.add(new Form());

		app.setVisible(true);
	}
}