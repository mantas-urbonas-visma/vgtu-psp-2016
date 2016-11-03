package lt.vgtu.isk.psp;
import java.util.List;

public class GameStageSerializer {

	PacmanSerializer pacmanSerializer = new PacmanSerializer();
	GhostSerializer ghostSerializer = new GhostSerializer();
	GameMapSerializer gameMapSerializer  = new GameMapSerializer();
	
	public String serialize(GameStage gameStage){
		
		String result = pacmanSerializer.serializePacman(gameStage.pacman);
		
		result = ghostSerializer.serializeGhosts(gameStage.ghosts, result);
		
		result += gameMapSerializer.serializeMap(gameStage.gameMap);
			
		return result;
	}
		
	 public static class LinesArray{
		 public String lines[];
		 public int lineNumber;
	 }
	 
	public GameStage deserialize(String savedState){
		if (savedState == null || savedState.trim().isEmpty())
			return null;
		
		LinesArray linesArray = new LinesArray();
		linesArray.lines = savedState.split("\n");
		linesArray.lineNumber = 0;
		
		Pacman pacman = pacmanSerializer.deserializePacman(linesArray);
		
		List<Ghost> ghosts = ghostSerializer.deserializeGhosts(linesArray);

		GameMap gameMap = gameMapSerializer.deserializeMap(linesArray);
		
		
		return new GameStage(gameMap, pacman, ghosts);
		
	}

	

	
	
	
}
