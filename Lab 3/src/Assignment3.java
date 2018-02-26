import java.util.ArrayList;

/**
 * Header
 * <Rajat Ahuja>
 * <RA29697>
 * <16495> (Your section id)
 * Please fill inside < >  and do not remove < >.
 */

public class Assignment3 {

	private int totalClasses;
	private int maxGrade;
	private static int [][]gradearray;
	public Assignment3() {
		this.totalClasses = 0;
		this.maxGrade = 0;
		this.gradearray = null;
	}

	public void initialize(int totalClasses, int maxGrade,  int[][] gradearray) { //These variables are set from TestAssignment3.java
		this.totalClasses = totalClasses;
		this.maxGrade = maxGrade;
		this.gradearray = gradearray;
	}

	public result[] compute(int totalHours, result[] studentoutput) {

			int hours = totalHours;
			int classes = totalClasses; 
			
			int optHour[][] = new int[classes + 1][hours + 1]; // Holds our hour that will maximize our opt
			int opt[][] = new int[classes + 1][hours + 1]; // Holds our grade(s) for hours/classes			
			
			for(int a = 1; a <= classes; a++) {  
				opt[a][0] = gradearray[a - 1][0] + opt[a - 1][0];
				optHour[a][0] = 0;
			}
			
			for(int b = 0; b <= hours; b++) {  
				opt[0][b] = 0;
				optHour[0][b] = b;
			}
			
			int i = 1;
			int h = 1;
			while(i <= classes) {
				while(h <= hours) {
					int maxHour = 0; // The hour that translates to our maxGrade
					int maxG = 0;	//maxGrade for a specific hour 
					for(int x = 0; x <= h; x++) {
						int funcX = 0;
						if(x >= 10) 
							funcX = 0;
						else 
							funcX = gradearray[i - 1][x]; // returns our grade for a specific class i based on hrs
						
						if(funcX + opt[i - 1][h - x] > maxG) { 
							maxG = funcX + opt[i - 1][h - x];	//set our maxGrade for this class
							maxHour = x;							//and its corresponding hour
						}
					}
					opt[i][h] = maxG;  //sets our optimal grade 
					optHour[i][h] = maxHour; //sets our optimalHour
					h++;
				}
				i++;
			}
			
			int tH = totalHours;
			for(int j = totalClasses; j > 0; j--) { 
				int optH = optHour[j][tH];
				studentoutput[j - 1].setHour(optH); 
				studentoutput[j - 1].setGrade(gradearray[j - 1][optH]); 
				tH = tH - optH; 
				
			}
			return studentoutput;
	}

}

/*  WRITE YOUR REPORT INSIDE THIS SECTION AS COMMENTED CODE
 *  * 
 *  We are maximizing n classes, where we 
 * Our solution has an optimal substructure as our solution for each subproblem is an optimal solution that returns a maxGrade for # of hours. So 
 * our final optimal solution is essentially built upon the optimal solutions of these subproblems; our solution is inarguably of an optimal substructure
 * 
 * Our optimal solution is Opt(n,h), which effectively maximizes n classes for h <= H hours of 'studying' (which is a simpler term for completing coursework - 
 * I realize the irony of writing all this out in the parentheses when I could've just put doing coursework initially, but it is late and i am tired). 
 * If we allow a to equal the j of hours spent on
 * a course i, then we have opt(i,h) equaling our maxGrade, where j is some # between 0-h, and this aforementioned sum is added recursively to opt(i-1, h-j)
 * 	-see line 60 for clarification on this. Thus we will reach our optimal solution of Opt(n,h) for n classes.
 * 
 * 
 * As an iterative solution we could start with Opt(n,h) and then store the hour/grade that corresponds for the maximumGrade/its hour. We then 
 * "trace back" by repeating the Opt with Opt(i-1,h-k) (same with OptHour(i-1, h-k)) to get that corresponding output. We would continue for every
 * single possible combination until we had successfully traced back all of the values and ultimately we will reach our solution in an interative fashion.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
