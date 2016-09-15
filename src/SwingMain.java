import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingMain extends JFrame implements KeyListener{

	GameMap gameMap = new GameMap();
	Pacman pacman = new Pacman(5, 5);
	Ghost ghosts[] = { new Ghost(20, 7, 0, 1), new Ghost(14, 10, 1, 0) };
	GameRules gameRules = new GameRules(gameMap, pacman, ghosts);
	SwingRenderer renderer;
		
	private BufferedImage pacmanImage;
	private BufferedImage ghostImage;
	
	public SwingMain() throws FileNotFoundException, IOException{
		super.setPreferredSize(new Dimension(1200,  600));
		super.pack();
		super.setVisible(true);
		
		pacmanImage = ImageIO.read(new FileInputStream("pacman-open.png"));
		ghostImage = ImageIO.read(new FileInputStream("ghost.png"));
		
		renderer = new SwingRenderer(gameMap, pacman, ghosts, pacmanImage, ghostImage);
		
		this.addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		if (renderer != null)
			renderer.render(g);
	}
	
	public static void main(String args[]) throws InvocationTargetException, InterruptedException{
		
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

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()){
		case 'a':
			tryMovePacman(gameMap, pacman, 0, -1);
		break;
		case 'd':
			tryMovePacman(gameMap, pacman, 0, +1);
			break;
		case 'w':
			tryMovePacman(gameMap, pacman, -1, 0);
			break;
		case 's':
			tryMovePacman(gameMap, pacman, +1, 0);
			break;
		case 'q':
			System.exit(1);;
		}
		
		gameRules.moveGhosts();
		
		if (gameRules.isGameOver())
			System.exit(1);
		
		this.repaint();
	}

	private static void tryMovePacman(GameMap gameMap, Pacman pacman, int dy, int dx) {
		if (!gameMap.isWall(pacman.y + dy,  pacman.x + dx)){
			pacman.x += dx;
			pacman.y += dy;
		}		
	}
}