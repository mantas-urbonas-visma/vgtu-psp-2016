

public class GameMapRenderer {

	
	GameMap gameMap;
	Pacman pacman;
	Ghost[] ghosts;
	
	public GameMapRenderer(GameMap gameMap, Pacman pacman, Ghost[] ghosts){
		this.gameMap = gameMap;
		this.pacman = pacman;
		this.ghosts = ghosts;
	}
	
	public void render() {
		for (int y=0; y<gameMap.getHeight(); y++){
			for (int x=0; x<gameMap.getWidth(); x++){
				if (gameMap.isWall(y, x)){
					drawWall();
				}else
				if (y==pacman.y && x == pacman.x){
					drawPacman();
				}
				else
				if (y == ghosts[0].y && x == ghosts[0].x){
					drawGhost();
				}
				else
				if (y == ghosts[1].y && x == ghosts[1].x){
					drawGhost();
				}
				else{
					drawEmptySpace();
				}
			}
			drawNewLine();
		}
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
