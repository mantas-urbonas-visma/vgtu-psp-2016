package lt.vgtu.isk.psp;

import org.junit.Test;

import org.junit.Assert;

public class TestGameRules {

	public static final int TEST_MAP[][] = 
		{
			{1,1,1,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,1,1,1}
	    };
	
	@Test
	public void shouldNewGameBeNotOver() {
		
		// given
		GameMap gameMap = new GameMap(TEST_MAP);
		Pacman  pacman  = new Pacman(1,1);
		GameStage gameStage = new GameStage(gameMap, pacman);
		GameRules gameRules = new GameRules();

		// then
		Assert.assertFalse(gameRules.isGameOver(gameStage));
	}
	
	@Test
	public void shouldGhostEatPacman(){

		// given
		GameMap gameMap = new GameMap(TEST_MAP);
		Pacman  pacman  = new Pacman(1,1);
		Ghost   ghost   = new Ghost(pacman.x, pacman.y, +1, 0);
		GameStage gameStage = new GameStage(gameMap, pacman, ghost);
		GameRules gameRules = new GameRules();

		// then
		Assert.assertTrue(gameRules.isGameOver(gameStage));
	}

	@Test
	public void shouldMoveGhostToAvailableCell() {

		// given
		GameMap gameMap = new GameMap(TEST_MAP);
		Pacman  pacman  = new Pacman(1,1);
		Ghost   ghost   = new Ghost(1, 2, +1, 0); 
		GameStage gameStage = new GameStage(gameMap, pacman, ghost);
		GameRules gameRules = new GameRules();
		
		// when
		gameRules.moveGhosts(gameStage);
		
		// then
		//Assert.assertEquals(2, ghost.x);
		//Assert.assertEquals(2, ghost.y);
	}
	
	@Test
	public void shouldNotMoveGhostToTheWallCell() {

		// given
		GameMap gameMap = new GameMap(TEST_MAP);
		Pacman  pacman  = new Pacman(1,1);
		Ghost   ghost   = new Ghost(2, 2, +1, 0); 
		GameStage gameStage = new GameStage(gameMap, pacman, ghost);
		GameRules gameRules = new GameRules();
		
		// when
		gameRules.moveGhosts(gameStage);
		
		// then
		//Assert.assertEquals(2, ghost.x);
		//Assert.assertEquals(2, ghost.y);
	}

	@Test
	public void shouldChangeMoveDirection() {

		// given
		GameMap gameMap = new GameMap(TEST_MAP);
		Pacman  pacman  = new Pacman(2,2);
		Ghost   ghost   = new Ghost(1, 1, +1, 0); 
		GameStage gameStage = new GameStage(gameMap, pacman, ghost);
		GameRules gameRules = new GameRules();
		
		// when
		// TODO: fake random event to make ghost switch direction
		gameRules.moveGhosts(gameStage);
		
		// then
		//Assert.assertEquals(1, ghost.x);
		//Assert.assertEquals(2, ghost.y);
	}
}
