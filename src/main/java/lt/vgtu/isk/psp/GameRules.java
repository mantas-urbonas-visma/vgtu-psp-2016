package lt.vgtu.isk.psp;
public class GameRules {

	public GameRules(){
	}

	public void moveGhosts(GameStage gameStage) {
		for (Ghost ghost: gameStage.ghosts)
			moveGhost(gameStage.gameMap, ghost);
	}

	public boolean isGameOver(GameStage gameStage) {
		for (Ghost ghost: gameStage.ghosts)
			if (gameStage.pacman.x == ghost.x && gameStage.pacman.y == ghost.y)
				return true;
		
		return false;
	}

	private void moveGhost(GameMap gameMap, Ghost ghost) {
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