package lt.vgtu.isk.psp;

import lt.vgtu.isk.psp.GameStageSerializer.LinesArray;

public class PacmanSerializer {
	
	private static final String keyword = "Pacman:";
	
	 public String serializePacman (Pacman pacman) {
			String result = "";

			result += keyword;
			result += pacman.x + "," + pacman.y;
			result += "\n";

			return result;
		 }
	 
	 
	 public Pacman deserializePacman(LinesArray linesArray) {
			if (!linesArray.lines[linesArray.lineNumber].startsWith(keyword))
				throw new RuntimeException("Invalid saved state: should start with 'Pacman:'");
			
			String coords[] = linesArray.lines[linesArray.lineNumber].substring(keyword.length()).split(",");
			if (coords.length != 2)
				throw new RuntimeException("Invalid saved state: should have 2 pacman coordinates (actual "+coords.length+")");
			
			Pacman pacman = new Pacman(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
			return pacman;
		}

}
