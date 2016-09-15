
public class GameRules {

	private GameMap gameMap;
	private Ghost[] ghosts;
	private Pacman pacman;

	public GameRules(GameMap gameMap, Pacman pacman, Ghost[] ghosts){
		this.gameMap = gameMap;
		this.ghosts = ghosts;
		this.pacman = pacman;
	}

	public void moveGhosts() {
		for (int i=0; i<ghosts.length; i++){
			if (randomChangeHappened())
				ghosts[i].swapMovementVectors();;

			if (gameMap.isWall(ghosts[i].y+ghosts[i].dy, ghosts[i].x+ghosts[i].dx))
				ghosts[i].revertMovementVectors();
			else
				ghosts[i].move();
		}
	}

	public boolean isGameOver() {
		for (int i=0 ; i<ghosts.length; i++)
			if (pacman.x == ghosts[i].x && pacman.y == ghosts[i].y)
				return true;
		
		return false;
	}

	

	private static boolean randomChangeHappened() {
		return Math.random()*10 > 8;
	}
}
