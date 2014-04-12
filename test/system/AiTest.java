package system;

import junit.framework.Assert;
import org.junit.Test;

import gameObject.Point;
import java.util.LinkedList;

public class AiTest {

	@Test
	public void obtainRandomValidPointsTest(){
		final Ai ai = new Ai();
		boolean[][] map = new boolean[3][3];
		map[1][1] = false;
		try{
			LinkedList<Point> lPoints = ai.obtainRandomValidPoints(map, 1);
		} catch (NullPointerException e){
			Assert.fail();
		}
	}
	
	@Test
	public void nextToPlayerTest(){
		final Ai ai = new Ai();
		Assert.assertEquals(true, ai.nextToPlayer(0, 0, 0, 0));
		Assert.assertEquals(true, ai.nextToPlayer(0, 0, 0, 1));
		Assert.assertEquals(true, ai.nextToPlayer(1, 0, 0, 0));
		Assert.assertEquals(false, ai.nextToPlayer(1, 0, 0, 1));
	}
	
	@Test
	public void onTopOfPlayerTest(){
		final Ai ai = new Ai();
		Assert.assertEquals(true, ai.onTopOfPlayer(0, 0, 0, 0));
		Assert.assertEquals(false, ai.onTopOfPlayer(1, 0, 0, 0));
	}
	
	@Test
	public void isValidStraightLineTest(){
		final Ai ai = new Ai();
		boolean[][] map = new boolean[3][3];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j] = true;
			}
		}
		map[1][1] = false;
		map[0][1] = false;
		map[2][1] = false;
		map[1][0] = false;
		map[1][2] = false;
		
		Assert.assertEquals("stop", ai.isValidStraightLine(map, 0, 0, 0, 0));
		Assert.assertEquals("down", ai.isValidStraightLine(map, 1, 1, 1, 0));
		Assert.assertEquals("up", ai.isValidStraightLine(map, 1, 1, 1, 2));
		Assert.assertEquals("right", ai.isValidStraightLine(map, 1, 1, 0, 1));
		Assert.assertEquals("left", ai.isValidStraightLine(map, 1, 1, 2, 1));
		Assert.assertEquals("stop", ai.isValidStraightLine(map, 1, 0, 0, 1));
	}
	
}
