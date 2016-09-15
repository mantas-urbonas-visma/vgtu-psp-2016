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
		for (int i=0; i<ghosts.length; i++)
			moveGhost(ghosts[i]);
	}

	public boolean isGameOver() {
		for (int i=0 ; i<ghosts.length; i++)
			if (pacman.x == ghosts[i].x && pacman.y == ghosts[i].y)
				return true;
		
		return false;
	}

	private void moveGhost(Ghost ghost) {
		if (randomChangeHappened())
			ghost.swapMovementVectors();

		if (gameMap.isWall(ghost.y+ghost.dy, ghost.x+ghost.dx))
			ghost.revertMovementVectors();
		else
			ghost.move();
	}
	
	private static boolean randomChangeHappened() {
		return Math.random()*10 > 8;
	}
}