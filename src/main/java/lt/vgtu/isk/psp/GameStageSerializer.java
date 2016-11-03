package lt.vgtu.isk.psp;
import java.util.ArrayList;
import java.util.List;

public class GameStageSerializer {
	
	public String serialize(GameStage gameStage){
		String result = "";
		
		result += "Pacman:";
		result += gameStage.pacman.x+","+gameStage.pacman.y;
		result += "\n";

		for (Ghost ghost: gameStage.ghosts){
			result += "Ghost:";
			result += ghost.x+","+ghost.y+","+ghost.dx+","+ghost.dy;
			result += "\n";
		}
		
		result += "Map:";
		for (int row[]: gameStage.gameMap.matrix){
			String joinSymbol = "";
			for (int cell: row){
				result += joinSymbol + cell;
				joinSymbol = ",";
			}
			result+="\n";
		}
			
		return result;
	}
	
	public GameStage deserialize(String savedState){
		if (savedState == null || savedState.trim().isEmpty())
			return null;
		
		String[] lines = savedState.split("\n");
		int lineNumber = 0;
		
		if (!lines[lineNumber].startsWith("Pacman:"))
			throw new RuntimeException("Invalid saved state: should start with 'Pacman:'");
		
		String coords[] = lines[lineNumber].substring("Pacman:".length()).split(",");
		if (coords.length != 2)
			throw new RuntimeException("Invalid saved state: should have 2 pacman coordinates (actual "+coords.length+")");
		
		Pacman pacman = new Pacman(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
		
		List<Ghost> ghosts = new ArrayList<>();
		while( lineNumber < lines.length){
			lineNumber += 1;
			
			if (!lines[lineNumber].startsWith("Ghost:"))
				break;
			
			String params[] = lines[lineNumber].substring("Ghost:".length()).split(",");
			if (params.length != 4)
				throw new RuntimeException("Invalid saved state: should have 4 parameters for a ghost: actual "+params.length);
			
			Ghost ghost = new Ghost(Integer.parseInt(params[0]),Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]));
			ghosts.add(ghost);
		}

		if (!lines[lineNumber].startsWith("Map:"))
			throw new RuntimeException("Invalid saved state: should start with 'Map:'");

		List<List<Integer>> rows = new ArrayList<>();
		lines[lineNumber] = lines[lineNumber].substring("Map:".length());
		while (lineNumber < lines.length){
			String cells[] = lines[lineNumber].split(",");
			if (cells.length<=1)
				break;
			
			ArrayList<Integer> row = new ArrayList<>();
			for (String cell: cells)
				row.add(Integer.parseInt(cell));
			
			rows.add(row);
			
			lineNumber += 1;
		}
		
		int matrix[][] = new int[rows.size()][];
		for (int i=0; i<rows.size(); i++){
			List<Integer> cells = rows.get(i);
			matrix[i] = new int[cells.size()];
			for (int j=0; j<cells.size(); j++)
				matrix[i][j]=cells.get(j);
		}
		
		GameMap gameMap = new GameMap(matrix);
		return new GameStage(gameMap, pacman, ghosts);
	}
}
