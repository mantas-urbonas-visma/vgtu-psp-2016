import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SwingRenderer {

	private GameMap gameMap;
	private Pacman pacman;
	private BufferedImage pacmanImage;

	public SwingRenderer(GameMap gameMap, Pacman pacman, BufferedImage pacmanImage){
		this.gameMap = gameMap;
		this.pacman = pacman;
		this.pacmanImage = pacmanImage;
	}
	
	public void render(Graphics g){
		for (int y = 0; y<gameMap.getHeight(); y++)
			for (int x = 0; x<gameMap.getWidth(); x++){
				if (gameMap.isWall(y, x))
					g.fillRect(x*20+20, y*20+30, 20, 20);
				if (pacman.x == x && pacman.y == y)
					if (pacmanImage != null)
					g.drawImage(pacmanImage, x*20+20, y*20+30, 20, 20, null);
			}
	}
}
