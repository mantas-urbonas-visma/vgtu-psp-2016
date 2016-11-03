package lt.vgtu.isk.psp;
import java.util.Arrays;
import java.util.List;

/***
 * a collection of all the objects making up a scene for a Pacman game
 */
public class GameStage {

	public GameMap gameMap;
	
	public Pacman pacman;
	
	public List<Ghost> ghosts;
	
	
	public GameStage(GameMap gameMap, Pacman pacman, Ghost... ghosts){
		this(gameMap, pacman, Arrays.asList(ghosts));
	}

	public GameStage(GameMap gameMap, Pacman pacman, List<Ghost> ghosts) {
		this.gameMap = gameMap;
		this.pacman = pacman;
		this.ghosts = ghosts;
	}
	

}
