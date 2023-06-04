package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;

import Model.Board;
import Model.Tiles.*;
import junit.framework.TestCase;

public class BoardTest extends TestCase {
	@Test
	public void test1() {
		Board brd = new Board();
		assertNotNull(brd);
		ArrayList<Tile> brdArray = brd.getBoard();
		assertNotNull(brdArray);
		assertEquals(brdArray.size(), 32);
		assertTrue(brd.getTile(0) instanceof StartTile);
		assertTrue(brd.getTile(31) instanceof PaydayTile);
		
		int fstartTile = 0, fpaydayTile = 0, fdealTile = 0, ffamilyTile = 0, flotteryTile = 0,
				fmessageTile = 0, fradioTile = 0, fsweepTile = 0, fyardTile = 0, fbuyerTile = 0;
		for(int i=0; i<32; i++) {
			Tile tile = brd.getTile(i);
			if(tile instanceof StartTile) fstartTile++; 
			if(tile instanceof PaydayTile) fpaydayTile++;
			if(tile instanceof DealTile) fdealTile++;
			if(tile instanceof FamilyCasinoTile) ffamilyTile++;
			if(tile instanceof LotteryTile) flotteryTile++;
			if(tile instanceof MessageTile) fmessageTile++;
			if(tile instanceof RadioTile) fradioTile++;
			if(tile instanceof SweepTakesTile) fsweepTile++;
			if(tile instanceof YardSaleTile) fyardTile++;
			if(tile instanceof BuyerTile) fbuyerTile++;
		}
		assertEquals(fstartTile, 1);
		assertEquals(fpaydayTile, 1);
		assertEquals(fdealTile, 5);
		assertEquals(flotteryTile, 3);
		assertEquals(fmessageTile, 8);
		assertEquals(ffamilyTile, 2);
		assertEquals(fradioTile, 2);
		assertEquals(fsweepTile, 2);
		assertEquals(fyardTile, 2);
		assertEquals(fbuyerTile, 6);
	}
}
