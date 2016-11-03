package lt.vgtu.isk.psp;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class SwingRenderer {

	private BufferedImage pacmanImage;
	private BufferedImage ghostImage;

	public SwingRenderer(BufferedImage pacmanImage, BufferedImage ghostImage){
		this.pacmanImage = pacmanImage;
		this.ghostImage = ghostImage;
	}
		
	public void render(GameStage game, Graphics where){
		drawAllWalls(game.gameMap, where);		
		
		drawPacman(game.pacman, where);
				
		drawGhosts(game.ghosts, where);
	}

	private void drawAllWalls(GameMap gameMap, Graphics g) {
		for (int y = 0; y<gameMap.getHeight(); y++)
			for (int x = 0; x<gameMap.getWidth(); x++)
				if (gameMap.isWall(y, x))
					drawWall(g, y, x);
	}

	private void drawWall(Graphics g, int y, int x) {
		g.fillRect(x*20+20, y*20+30, 20, 20);
	}
	
	private void drawPacman(Pacman pacman, Graphics g) {
		g.drawImage(pacmanImage, pacman.x*20+20, pacman.y*20+30, 20, 20, null);
	}

	private void drawGhosts(List<Ghost> ghosts, Graphics g) {
		for (Ghost ghost: ghosts)
			drawGhost(g, ghost);
	}
	
	private void drawGhost(Graphics g, Ghost ghost) {
		g.drawImage(ghostImage, ghost.x*20+20, ghost.y*20+30, 20, 20, null);
	}
}
