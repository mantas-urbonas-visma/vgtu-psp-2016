package lt.vgtu.isk.psp;

public class PacmanMoveComand extends AbstractCommand {

	private GameStage gameStage;
	private int dy;
	private int dx;

	public PacmanMoveComand(GameStage gamestage, int dx, int dy){
		this.gameStage = gamestage;
		this.dy=dy;
		this.dx=dx;
	}
	
	@Override
	public void execute() {
		if (!gameStage.gameMap.isWall(gameStage.pacman.y + dy,  gameStage.pacman.x + dx)){
			gameStage.pacman.x += dx;
			gameStage.pacman.y += dy;
		}	
	}
	
}
