package lt.vgtu.isk.psp;
public class Main {

	public static void main(String args[]) throws Exception{
		GameMap gameMap = new GameMap();
		Pacman pacman = new Pacman(5, 5);
		Ghost ghosts[] = { new Ghost(20,7, 0,1), new Ghost(14,10,1,0) };
		
		GameStage gameStage = new GameStage(gameMap, pacman, ghosts);
		
		GameMapRenderer renderer = new GameMapRenderer(gameStage);
		GameRules gameRules = new GameRules();
		
		while(true){
			renderer.render();

			if (gameRules.isGameOver(gameStage))
				return;
			
			int n = System.in.read();
			switch(n){
			case 'a':
				tryMovePacman(gameStage, 0, -1);
			break;
			case 'd':
				tryMovePacman(gameStage, 0, +1);
				break;
			case 'w':
				tryMovePacman(gameStage, -1, 0);
				break;
			case 's':
				tryMovePacman(gameStage, +1, 0);
				break;
			case 'q':
				return;
			}
			gameRules.moveGhosts(gameStage);
		}
	}

	private static void tryMovePacman(GameStage gameStage, int dy, int dx) {
		if (!gameStage.gameMap.isWall(gameStage.pacman.y + dy, gameStage.pacman.x + dx)){
			gameStage.pacman.x += dx;
			gameStage.pacman.y += dy;
		}		
	}

}