/**
 * Header
 * <Rajat Ahuja>
 * <RA29697>
 * <16495> (Your section id)
 * Please fill inside < >  and do not remove < >.
 */

/**
 * Class to implement Assignment2 solution
 * findProgram method should be implemented.
 * Please do not include any main methods.
 */

import java.util.*;

public class Assignment2 {
	
	public static class AirdropComp implements Comparator<Airdrop> {
		@Override
		public int compare(Airdrop a, Airdrop b) {
			int aSize = a.getRow() + a.getColumn();
			int bSize = b.getRow() + b.getColumn();
			int comparison = Integer.compare(aSize, bSize);
			if (comparison == 0) {	//if they're equal then we need to compare our rows to see which is smaller
				int comparingRows = Integer.compare(a.getRow(), b.getRow());
				int comparingColumns = Integer.compare(a.getColumn(), b.getColumn());
				if (comparingRows == 0) {
					return comparingColumns;	 //if our rows are equal then return the columns - can't be the same here because then it'd be a singular coordinate
				}
				else {
					return comparingRows;
				}
			}
			else {	//at this point our rows+cols aren't equal for the two being compared, so we can just return the one with the smallest aggregate
				return comparison;
			}
		}
	}
	
	public static int depth(int [][] map, int rows, int cols) {
		int total = 0;
		int current = map[rows][cols];
		if (rows >= map.length || cols >= map[0].length || current == 0 || 0 > rows || 0 > cols) {
			return 0;
		} 
		else {			
			map[rows][cols] = 0;
			total = current + depth(map, rows, cols-1) + depth(map, rows, cols+1) + depth(map, rows+1, cols) + depth(map, rows-1, cols);
		}
		return total;
	}

	public static ArrayList<Airdrop> findProgram(int[][] map, int row, int column) {
		ArrayList<Airdrop> airdrops = new ArrayList<>();	//our arraylist to be returned
		Comparator<Airdrop> comparisons = new AirdropComp(); 	
		PriorityQueue<Airdrop> sortedDrops = new PriorityQueue<>(comparisons);	//we create a priority queue which will be used to help sort outputs in order
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(map[i][j] > 0) {		//if there's a lion at this spot, we'd like to probe around and find the rest of the region
					int numberOfLions = depth(map, i, j);	//we get the numberOfLions in the region
					sortedDrops.add(new Airdrop(i, j, numberOfLions));	//add this to our priority queue	
				}
			}
		}
		
		while(sortedDrops.size() != 0)	{	//while we have items in the Q 
			airdrops.add(sortedDrops.poll());	
		}			//we "poll" which is how we remove the head of our queue. Note that we don't need to peek into the queue because we know there are elements in it because of our while loop
			
		return airdrops;
	}
	
}