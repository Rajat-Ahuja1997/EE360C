import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestAssignment3 {
	private static int totalClasses;//total number of classes
	private static int totalHours;//total number of hours available
	private static int [][]gradearray;//hold values of grade for each hour spent for every class
	//This main function is used to test and call Program3
	public static void main(String[] args) {
		fileToMap("test1.txt");//loads test case1
		int maxgrade = totalClasses*10;
		Assignment3 program = new Assignment3();
		program.initialize(totalClasses, maxgrade, gradearray);
		result[] studentoutput=new result[totalClasses];
		for(int i=0;i<totalClasses;i++)
		{
			studentoutput[i]=new result(0,0);//initializing arrays of object type result

		}
		result[] expected=new result[totalClasses];
		expected[0]=new result(9,9);
		expected[1]=new result(4,5);
		expected[2]=new result(4,8);
		expected[3]=new result(0,1);
		expected[4]=new result(0,4);
		expected[5]=new result(0,3);
		expected[6]=new result(0,1);
		expected[7]=new result(3,7);
		expected[8]=new result(0,2);
		expected[9]=new result(0,3);
		studentoutput=program.compute(totalHours,studentoutput);//Returns computed Values from Assignment3.java

		if(Arrays.deepEquals(expected, studentoutput)){
			System.out.println("Test Case 1 Passed Successfully");
		}
		else{
			System.out.println("Test Case 1 Failed");
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		fileToMap("test2.txt");//Loads Test case 2
		maxgrade = totalClasses*10;
		program.initialize(totalClasses, maxgrade, gradearray);
		studentoutput = program.compute(totalHours,studentoutput);//Returns computed Values from Assignment3.java

		expected[0]=new result(9,10);
		expected[1]=new result(9,10);
		expected[2]=new result(2,3);
		expected[3]=new result(0,1);
		expected[4]=new result(0,1);
		expected[5]=new result(0,1);
		expected[6]=new result(0,1);
		expected[7]=new result(0,1);
		expected[8]=new result(0,1);
		expected[9]=new result(0,1);
		if(Arrays.deepEquals(expected, studentoutput)){
			System.out.println("Test Case 2 Passed Successfully");
		}
		else{
			System.out.println("Test Case 2 Failed");
		}
	}
	//This method fills the gradearray from the given file name. It also stores total number of classes in variable classes and total number of available hours inside variable hours
	private static void fileToMap(String fileName) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String[] sizes = br.readLine().split(" ");
			totalClasses = Integer.parseInt(sizes[0]);
			totalHours = Integer.parseInt(sizes[1]);

			gradearray = new int[totalClasses][10];
			for(int i = 0; i < totalClasses; i++) {
				sizes = br.readLine().split(" ");

				for(int j = 0; j < 10; j++) {
					gradearray[i][j] = Integer.parseInt(sizes[j]);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
