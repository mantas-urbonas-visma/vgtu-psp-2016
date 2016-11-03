package lt.vgtu.isk.psp;

import org.junit.Assert;
import org.junit.Test;

public class TestGameStageSerializer {

	@Test
	public void shouldLoadExactState() {
		
		// given
		GameStage gameStage = createGameStage();
		GameStageSerializer gameSerializer = new GameStageSerializer();
		
		// when
		String serialized = gameSerializer.serialize(gameStage);		
		GameStage loadedStage = gameSerializer.deserialize(serialized);
		
		// then
		Assert.assertEquals(gameStage.pacman.x, loadedStage.pacman.x);
		Assert.assertEquals(gameStage.pacman.y, loadedStage.pacman.y);
		Assert.assertEquals(gameStage.ghosts.get(1).x, loadedStage.ghosts.get(1).x);		
		Assert.assertArrayEquals(gameStage.gameMap.matrix, loadedStage.gameMap.matrix);
	}
	
	@Test
	public void shouldHaveIdenticalSaveFiles(){
		
		// given
		GameStage gameStage = createGameStage();	
		GameStageSerializer serializer = new GameStageSerializer();
		
		// when
		String savedGame1 = serializer.serialize(gameStage);
		GameStage loadedStage = serializer.deserialize(savedGame1);
		String savedGame2 = serializer.serialize(loadedStage);
		
		// then
		Assert.assertEquals(savedGame1, savedGame2);
	}

	
	private GameStage createGameStage() {
		GameMap gameMap = new GameMap();
		Pacman pacman = new Pacman(5, 5);
		Ghost ghosts[] = { new Ghost(20, 7, 0, 1), new Ghost(14, 10, 1, 0) };

		GameStage gameStage = new GameStage(gameMap, pacman, ghosts);
		return gameStage;
	}

}
