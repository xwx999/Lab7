import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {
	Map map = new Map(40,40);
	
	@Before
	public void setUp() throws Exception {
		map.clearMap();
	}

	@Test
	public void testClearMap() {
			boolean flag=true;
			Cell[][] test = new Cell[40][40];			
			for(int i =0;i<40;i++)
				for(int j =0;j<40;j++) {
					test[i][j] = new Cell(0);
				}
			map.clearMap();
			for(int i=0; i<40; i++)
				for(int j=0; j<40; j++){
					if(test[i][j].isLiving!=map.getMap()[i][j].isLiving) {
						flag=false;
						break;	
					}
				}		
				assertEquals(true,flag);
	}

	@Test
	public void testGetNeighbors() {
		Cell[][] a = map.getMap();
		//左上角
		a[1][0].isLiving=1;
		a[1][1].isLiving=1;
		a[0][1].isLiving=1;
		map.setMap(a);
		int num = map.getNeighbors(0,0);
		assertEquals(3,num);
		map.clearMap();
		//左下角
		a[39][1].isLiving=1;
		num=map.getNeighbors(39, 0);
		assertEquals(1,num);
		map.clearMap();
		//右上角
		a[1][39].isLiving=1;
		num=map.getNeighbors(0, 39);
		assertEquals(1,num);
		map.clearMap();
		//右下角
		a[39][38].isLiving=1;
		num=map.getNeighbors(39,39);
		assertEquals(1,num);
		map.clearMap();
		//第一行
		a[0][10].isLiving=1;
		a[0][9].isLiving =1;
		a[1][11].isLiving=1;
		num=map.getNeighbors(0, 10);
		assertEquals(2,num);
		map.clearMap();
		//最后一行
		a[38][10].isLiving=1;
		a[39][9].isLiving =1;
		a[39][11].isLiving=1;
		num=map.getNeighbors(39, 10);
		assertEquals(3,num);
		map.clearMap();
		//第一列
		a[25][1].isLiving=1;
		a[24][0].isLiving=1;
		num=map.getNeighbors(25, 0);
		assertEquals(2,num);
		map.clearMap();
		//最后一列
		a[25][38].isLiving=1;
		a[24][39].isLiving=1;
		num=map.getNeighbors(25, 39);
		assertEquals(2,num);
		map.clearMap();
					
		//一般情况
		a[20][20].isLiving=1;
		a[19][20].isLiving=1;
		a[21][20].isLiving=1;
		a[20][19].isLiving=1;
		a[20][21].isLiving=1;
		a[21][21].isLiving=1;
		a[19][19].isLiving=1;
		a[19][21].isLiving=1;
		a[21][19].isLiving=1;
		num = map.getNeighbors(20, 20);
		assertEquals(8,num);
		
	}

	@Test
	public void testUpdate() {
		
		Cell[][] cells = map.getMap();
		cells[10][10].setIsLiving(1);
		cells[10][11].setIsLiving(1);
		cells[10][12].setIsLiving(1);
		cells[11][10].setIsLiving(1);
		cells[11][11].setIsLiving(1);
		cells[11][12].setIsLiving(1);
		cells[12][10].setIsLiving(1);
		cells[12][11].setIsLiving(1);
		cells[12][12].setIsLiving(1);
		map.setMap(cells);
		Cell[][] newCells = map.update();		
		Cell[][] test = new Cell[40][40];				
		//创建对象，不然会报空指针异常
		for(int i =0;i<40;i++)
			for(int j =0;j<40;j++) {
				test[i][j] = new Cell(0);
			}
		test[10][10].setIsLiving(1);
		test[9][11].setIsLiving(1);
		test[10][12].setIsLiving(1);
		test[11][9].setIsLiving(1);
		test[11][13].setIsLiving(1);
		test[12][10].setIsLiving(1);
		test[13][11].setIsLiving(1);
		test[12][12].setIsLiving(1);		
		boolean flag = true;	
		for(int i =0;i<40;i++)
			for(int j =0;j<40;j++) {
				if(test[i][j].isLiving!=newCells[i][j].isLiving) {
					flag=false;
					break;
				}
			}		
		assertEquals(true,flag);
		
		
		/*
		//边界情况
		Cell[][] cells = map.getMap();
		cells[0][1].setIsLiving(1);
		cells[1][0].setIsLiving(1);
		cells[0][0].setIsLiving(1);

		map.setMap(cells);

		Cell[][] newCells = map.update();
		
		Cell[][] test = new Cell[40][40];
				
		for(int i =0;i<40;i++)
			for(int j =0;j<40;j++) {
				test[i][j] = new Cell(0);
			}
		test[1][1].setIsLiving(1);
		test[0][0].setIsLiving(1);
		test[0][1].setIsLiving(1);
		test[1][0].setIsLiving(1);
		
		boolean flag = true;
		
		for(int i =0;i<40;i++)
			for(int j =0;j<40;j++) {
				if(test[i][j].isLiving!=newCells[i][j].isLiving) {
					flag=false;
					break;
				}
			}
		
		assertEquals(true,flag);
		*/
	}

}
