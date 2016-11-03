package lt.vgtu.isk.psp;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingMain extends JFrame implements KeyListener{

	GameStage gameStage;
	GameRules gameRules;
	SwingRenderer renderer;
		
	private BufferedImage pacmanImage;
	private BufferedImage ghostImage;
	
	public SwingMain() throws FileNotFoundException, IOException{
		super.setPreferredSize(new Dimension(1200,  600));
		super.pack();
		super.setVisible(true);
		
		this.pacmanImage = ImageIO.read(new FileInputStream("pacman-open.png"));
		this.ghostImage = ImageIO.read(new FileInputStream("ghost.png"));
		this.renderer = new SwingRenderer(pacmanImage, ghostImage);
		
		this.gameRules = new GameRules();
	
		this.gameStage = loadGameStage();
		if (this.gameStage == null)	
			this.gameStage = createNewGameStage();
	
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
			saveGame();
		else
		if (keyText == "F3")
			loadGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()){
		case 'a':
			tryMovePacman(0, -1);
		break;
		case 'd':
			tryMovePacman(0, +1);
			break;
		case 'w':
			tryMovePacman(-1, 0);
			break;
		case 's':
			tryMovePacman(+1, 0);
			break;
		case 'q':
			System.exit(1);
		}
		
		if (gameRules.isGameOver(gameStage))
			System.exit(1);
		
		gameRules.moveGhosts(gameStage);
				
		this.repaint();
	}

	private void tryMovePacman(int dy, int dx) {
		if (!gameStage.gameMap.isWall(gameStage.pacman.y + dy,  gameStage.pacman.x + dx)){
			gameStage.pacman.x += dx;
			gameStage.pacman.y += dy;
		}		
	}
		
	private GameStage createNewGameStage(){		
		GameMap gameMap = new GameMap();
		Pacman pacman = new Pacman(5, 5);
		Ghost ghosts[] = { new Ghost(20, 7, 0, 1), new Ghost(14, 10, 1, 0) };

		return new GameStage(gameMap, pacman, ghosts);
	}
	
	private GameStage loadGameStage(){
		try {
			String fileContent = new String(Files.readAllBytes(Paths.get("game.save")));
			return new GameStageSerializer().deserialize(fileContent);
		} catch (Exception e) {
			return null;
		}
	}
	
	private void saveGame(){
		try {
			Files.write(Paths.get("game.save"), new GameStageSerializer().serialize(gameStage).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadGame(){
		GameStage loadedGameStage = loadGameStage();
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