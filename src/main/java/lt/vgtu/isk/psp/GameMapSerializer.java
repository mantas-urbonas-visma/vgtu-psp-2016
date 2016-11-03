package lt.vgtu.isk.psp;

import java.util.ArrayList;
import java.util.List;

import lt.vgtu.isk.psp.GameStageSerializer.LinesArray;

public class GameMapSerializer {
	

	public String serializeMap(GameMap gameMap){
		String result = "Map:";
		for (int row[]: gameMap.matrix){
			String joinSymbol = "";
			for (int cell: row){
				result += joinSymbol + cell;
				joinSymbol = ",";
			}
			result+="\n";
		}
		return result;
	}
	
	public GameMap deserializeMap(LinesArray linesArray) {
		if (!linesArray.lines[linesArray.lineNumber].startsWith("Map:"))
			throw new RuntimeException("Invalid saved state: should start with 'Map:'");

		List<List<Integer>> rows = new ArrayList<>();
		linesArray.lines[linesArray.lineNumber] = linesArray.lines[linesArray.lineNumber].substring("Map:".length());
		while (linesArray.lineNumber < linesArray.lines.length){
			String cells[] = linesArray.lines[linesArray.lineNumber].split(",");
			if (cells.length<=1)
				break;
			
			ArrayList<Integer> row = new ArrayList<>();
			for (String cell: cells)
				row.add(Integer.parseInt(cell));
			
			rows.add(row);
			
			linesArray.lineNumber += 1;
		}
		
		int matrix[][] = new int[rows.size()][];
		for (int i=0; i<rows.size(); i++){
			List<Integer> cells = rows.get(i);
			matrix[i] = new int[cells.size()];
			for (int j=0; j<cells.size(); j++)
				matrix[i][j]=cells.get(j);
		}
		
		GameMap gameMap = new GameMap(matrix);
		return gameMap;
	}

}
