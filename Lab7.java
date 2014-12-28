import java.awt.*;
import javax.swing.*;

public class Lab7 extends JApplet{
	StartupView startUp;
	EnterView enter;
	DisplayView display;
	ExitView exit;
	Model model;
	ChangeView change;
	
	JPanel gui = new JPanel ();
	
	
	public void init() {
		setLayout(new FlowLayout());
		add(gui);
		model = new Model();
		createStartupView();
	}

	public void createStartupView() {
		startUp = new StartupView(this);
		gui.removeAll();
		gui.add(startUp);
		validate();
	}
	
	public void createEnterView() {
		enter = new EnterView(this, model);
		gui.removeAll();
		gui.add(enter);
		validate();
	}
	
	public void createDisplayView() {
		display = new DisplayView (this, model);
		gui.removeAll();
		gui.add(display);
		validate();
	}

	public void createChangeView() {
		change = new ChangeView (this, model);
		gui.removeAll();
		gui.add(change);
		validate();
	}
	
	public void createExitView() {
		exit = new ExitView (this);
		gui.removeAll();
		gui.add(exit);
		validate();
	}
}
