package lt.vgtu.isk.psp;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingMain extends JFrame implements KeyListener{

	GameStage gameStage;
	GameRules gameRules;
	SwingRenderer renderer;
	GameSaver gameSaver = new GameSaver();
		
	private BufferedImage pacmanImage;
	private BufferedImage ghostImage;
	
	Map<Character, AbstractCommand> commands = new HashMap<>();
	
	public SwingMain() throws FileNotFoundException, IOException{
		super.setPreferredSize(new Dimension(1200,  600));
		super.pack();
		super.setVisible(true);
		
		this.pacmanImage = ImageIO.read(new FileInputStream("pacman-open.png"));
		this.ghostImage = ImageIO.read(new FileInputStream("ghost.png"));
		this.renderer = new SwingRenderer(pacmanImage, ghostImage);
		
		this.gameRules = new GameRules();
	
		this.gameStage = gameSaver.loadGameStage();
		if (this.gameStage == null)	
			this.gameStage = gameSaver.createNewGameStage();
	
		commands.put('a', new PacmanMoveComand(gameStage, -1, 0));
		commands.put('d', new  PacmanMoveComand(gameStage, 1, 0));
		commands.put('w', new  PacmanMoveComand(gameStage, 0, -1));
		commands.put('s', new  PacmanMoveComand(gameStage, 0, 1));
		commands.put('q', new ExitCommand());
		
		this.addKeyListener(this);
	}

	@Override
	public void paint(Graphics graphics){
		super.paint(graphics);
		
		if (renderer != null)
			renderer.render(gameStage, graphics);
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String keyText = KeyEvent.getKeyText(e.getKeyCode());
		if (keyText == "F2")
			gameSaver.saveGame(gameStage);
		else
		if (keyText == "F3")
			loadGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		AbstractCommand command = getCommand(e);
		if (command != null)
			command.execute();
		
		if (gameRules.isGameOver(gameStage))
			System.exit(1);
		
		gameRules.moveGhosts(gameStage);
				
		this.repaint();
	}

	private AbstractCommand getCommand(KeyEvent e) {
		return commands.get(e.getKeyChar());
	}		
	
	public void loadGame(){
		GameStage loadedGameStage = gameSaver.loadGameStage();
		if (loadedGameStage != null)
			this.gameStage = loadedGameStage;
		
		repaint();
	}
	
	
	public static void main(String args[]) throws Exception{
		
		SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				try {
					new SwingMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}