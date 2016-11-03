package lt.vgtu.isk.psp;

import java.util.ArrayList;
import java.util.List;

import lt.vgtu.isk.psp.GameStageSerializer.LinesArray;

public class GhostSerializer {
	
	private static final String keyword = "Ghost:";

	private Ghost deserialzeGhost(LinesArray linesArray) {
		linesArray.lineNumber += 1;
		
		if (!linesArray.lines[linesArray.lineNumber].startsWith(keyword))
			return null;
		
		String params[] = linesArray.lines[linesArray.lineNumber].substring(keyword.length()).split(",");
		if (params.length != 4)
			throw new RuntimeException("Invalid saved state: should have 4 parameters for a ghost: actual "+params.length);
		
		Ghost ghost = new Ghost(Integer.parseInt(params[0]),Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]));
		return ghost;
	}
	
	public List<Ghost> deserializeGhosts(LinesArray linesArray) {
		List<Ghost> ghosts = new ArrayList<>();
		while( linesArray.lineNumber < linesArray.lines.length){
			Ghost ghost = deserialzeGhost(linesArray);
			if (ghost == null)
				break;
			ghosts.add(ghost);
		}
		return ghosts;
	}
	
	public String serializeGhost (Ghost ghost) {
		String result = "";
		result += keyword;
		result += ghost.x+","+ghost.y+","+ghost.dx+","+ghost.dy;
		result += "\n";
		
		return result;
	}
	
	public String serializeGhosts(List<Ghost> ghosts, String result) {
		for (Ghost ghost: ghosts){
			result += serializeGhost(ghost);
		}
		return result;
	}
}
