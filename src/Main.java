import java.io.IOException;

public class Main {

	
	public static void main(String args[]) throws IOException{
		GameMap gameMap = new GameMap();
		
		Pacman pacman = new Pacman(5, 5);
		
		Ghost ghosts[] = { new Ghost(20,7, 0,1), new Ghost(14,10,1,0) };
		
		GameMapRenderer renderer = new GameMapRenderer(gameMap, pacman, ghosts);
		
		GameRules gameRules = new GameRules(gameMap, pacman, ghosts);
		
		while(true){
			
			renderer.render();

			if (gameRules.isGameOver())
				return;
			
			int n = System.in.read();
			switch(n){
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
				return;
				
			}
			
			gameRules.moveGhosts();

		}
	}


	private static void tryMovePacman(GameMap gameMap, Pacman pacman, int dy, int dx) {
		if (!gameMap.isWall(pacman.y + dy,  pacman.x + dx)){
			pacman.x += dx;
			pacman.y += dy;
		}
		
	}


	
}
