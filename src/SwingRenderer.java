import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SwingRenderer {

	private GameMap gameMap;
	private Pacman pacman;
	private BufferedImage pacmanImage;
	private BufferedImage ghostImage;
	private Ghost[] ghosts;

	public SwingRenderer(GameMap gameMap, Pacman pacman, Ghost[] ghosts, BufferedImage pacmanImage, BufferedImage ghostImage){
		this.gameMap = gameMap;
		this.pacman = pacman;
		this.ghosts = ghosts;
		this.pacmanImage = pacmanImage;
		this.ghostImage = ghostImage;
	}
	
	public void render(Graphics g){
		for (int y = 0; y<gameMap.getHeight(); y++)
			for (int x = 0; x<gameMap.getWidth(); x++)
				if (gameMap.isWall(y, x))
					drawWall(g, y, x);		
		
		drawPacman(g);
				
		for (int i=0; i<ghosts.length; i++)
			drawGhost(g, ghosts[i]);
	}

	private void drawWall(Graphics g, int y, int x) {
		g.fillRect(x*20+20, y*20+30, 20, 20);
	}
	
	private void drawPacman(Graphics g) {
		g.drawImage(pacmanImage, pacman.x*20+20, pacman.y*20+30, 20, 20, null);
	}

	private void drawGhost(Graphics g, Ghost ghost) {
		g.drawImage(ghostImage, ghost.x*20+20, ghost.y*20+30, 20, 20, null);
	}
}
