package lt.vgtu.isk.psp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameSaver {

	public void saveGame(GameStage gameStage){
		try {
			Files.write(Paths.get("game.save"), new GameStageSerializer().serialize(gameStage).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GameStage createNewGameStage(){		
		GameMap gameMap = new GameMap();
		Pacman pacman = new Pacman(5, 5);
		Ghost ghosts[] = { new Ghost(20, 7, 0, 1), new Ghost(14, 10, 1, 0) };

		return new GameStage(gameMap, pacman, ghosts);
	}
	
	public GameStage loadGameStage(){
		try {
			String fileContent = new String(Files.readAllBytes(Paths.get("game.save")));
			return new GameStageSerializer().deserialize(fileContent);
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
