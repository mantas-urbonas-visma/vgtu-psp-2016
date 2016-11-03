package lt.vgtu.isk.psp;
public class GameMapRenderer {
	
	GameStage gameStage;
	
	public GameMapRenderer(GameStage gameStage){
		this.gameStage = gameStage;
	}
	
	public void render() {
		GameMap gameMap = gameStage.gameMap;
		
		for (int y=0; y<gameMap.getHeight(); y++){
			for (int x=0; x<gameMap.getWidth(); x++){
				if (gameMap.isWall(y, x)){
					drawWall();
				}else
				if (y==gameStage.pacman.y && x == gameStage.pacman.x){
					drawPacman();
				}
				else
				if (isGhost(x, y)){
					drawGhost();
				}
				else{
					drawEmptySpace();
				}
			}
			drawNewLine();
		}
	}
	
	private boolean isGhost(int x, int y) {
		for (Ghost ghost: gameStage.ghosts)
			if (ghost.x == x && ghost.y == y)
				return true;
		
		return false;
	}

	private static void drawNewLine() {
		System.out.println();
	}

	private static void drawEmptySpace() {
		System.out.print(" ");
	}

	private static void drawGhost() {
		System.out.print("$");
	}

	private static void drawPacman() {
		System.out.print("C");
	}

	private static void drawWall() {
		System.out.print("#");
	}
}