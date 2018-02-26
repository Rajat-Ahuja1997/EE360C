/**
 * Class to implement Stable Matching algorithms
 * Rajat Ahuja
 * RA29697
 * October 2, 2017
 */
import java.util.ArrayList;


public class Assignment1 {
		
    // Part1: Implement a Brute Force Solution
	//can't figure out how to permute all combinations into an array or how to check stability properly...
    public static ArrayList<Integer> stableMatchBruteForce(Preferences preferences) {		
		ArrayList<ArrayList<Integer>> professors = preferences.getProfessors_preference();
		ArrayList<ArrayList<Integer>> students = preferences.getStudents_preference();	//holds our pref lists
    		ArrayList<Integer> matching = new ArrayList<Integer>();	//The ArrayList that will be returned
    		
    		int length = preferences.getNumberOfStudents(); // new variable to hold # of profs/students (which should be equal)
    		int size = matching.size();	//# of profs in our array
    		//Heap's algorithm implementation?
    		return matching;
    }
    

    // Part2: Implement Gale-Shapley Algorithm
    public static ArrayList<Integer> stableMatchGaleShapley(Preferences preferences) {	//Gale-Shapley always return a stable matching, no need to check answer for stability
    		
		ArrayList<ArrayList<Integer>> professors = preferences.getProfessors_preference();
		ArrayList<ArrayList<Integer>> students = preferences.getStudents_preference();	//holds our pref lists
    		ArrayList<Integer> pairing = new ArrayList<Integer>();	//The ArrayList that will be returned
    		int length = preferences.getNumberOfStudents(); // new variable to hold # of profs/students (which should be equal)

    		int size = pairing.size();	//# of profs in our array
    	   	int selection = 0; 
        	int i = 0;
        	
    		for(int j = 0; j < length; j++) {		//initializes our array with all -1s (no matches)
        		pairing.add(-1);
        	}
        	
        	
        	while(i < size && pairing.contains(-1)) {	//~while there exists some unmatched prof and we have not selected every professor
        		if(pairing.get(i) == -1) {			//if the prof is unmatched
        			
        			while(selection < professors.get(i).size()) {	//while there is a student who has not been 'proposed to'
            			int potentialStudentMatch = professors.get(i).get(selection) - 1;	
            			int currentMatch = pairing.indexOf(potentialStudentMatch);	//checks to see what student is matched with  
            			if(currentMatch == -1) {		//if student is unmatched
            				pairing.set(i, potentialStudentMatch);
            				selection = 0; i++;	//'resets' preference list, moves on to next preferences
            				break;
            			}
            			else {	//student is matched
            				int potentialPreference = students.get(potentialStudentMatch).indexOf(i + 1);
            				int currentPreference = students.get(potentialStudentMatch).indexOf(currentMatch + 1);
            				if(currentPreference > potentialPreference) {
            					pairing.set(currentMatch, -1);
            					pairing.set(i, potentialStudentMatch);
            					i = currentMatch; selection = 0;
            					break;
            				}
            			}
            			selection++; //proceed to next student in pref list
            		}
        		}
    			i++;				//proceed to next professor
    			continue;
        		
        	}
        	return pairing; 
    }

    // Part3a: Matching with Costs
    public static ArrayList<Cost> stableMatchCosts(Preferences preferences) {	//professor Optimal
    		ArrayList<Cost> Costs = new ArrayList<Cost>();
    		ArrayList<Integer> pairing = stableMatchGaleShapley(preferences);
    		ArrayList<ArrayList<Integer>> professors = preferences.getProfessors_preference();
    		ArrayList<ArrayList<Integer>> students = preferences.getStudents_preference();	//holds our pref lists
    		
    		int i = 0;
    		while(i < pairing.size()) {	//i is effectively our 'professor index' 
    			int studentIndex = pairing.get(i);
    			int professorCost = professors.get(i).indexOf(studentIndex + 1);	//our cost is the index of where we find the index of our student + an offset to account for zero-indexing
    			int studentCost = students.get(studentIndex).indexOf(i + 1);		// ^same kind of
    			Cost thisCost = new Cost(i, studentIndex, professorCost, studentCost);
    			Costs.add(thisCost);	
    			i++; 
    		}
    		return Costs;
    }
    
    // Part3b: Matching with Costs (student Optimal)
    public static ArrayList<Cost> stableMatchCostsStudent(Preferences preferences) {	//not sure if our array was supposed to be different from professor optimal
		ArrayList<Cost> Costs = new ArrayList<Cost>();								//i.e if the index was supposed to be the student and the element @ that index was the professor
    		int numberOfStudents = preferences.getNumberOfStudents();						//if so, this is incorrect, but I kept it the same as parts 1-3a
    		int numberOfProfessors = preferences.getNumberOfProfessors();
    		ArrayList<ArrayList<Integer>> professorPreferences = preferences.getProfessors_preference();
    		ArrayList<ArrayList<Integer>> studentPreferences = preferences.getStudents_preference();
    		Preferences newPref = new Preferences(numberOfStudents, numberOfProfessors, studentPreferences, professorPreferences);	//numberofstudents/numberofprofessors didn't need to be switched, but just for clarity i did so
   		ArrayList<Integer> studentOptimal = stableMatchGaleShapley(newPref);	//should now contain a student optimal gale shapley; there is probably an easier way to do this - try to figure out
    		
   		int i = 0;
    		while(i < studentOptimal.size()) {
    			int studentIndex = studentOptimal.get(i); 		
    			int studentCost = professorPreferences.get(studentIndex).indexOf(i + 1);	//starting to regret using variables (for prefs) as from the first cost; but in too deep now :(
    			int professorCost = studentPreferences.get(i).indexOf(studentIndex + 1);	//preferences stay the same for both optimizations, however retrieving the data is different
    			Cost thisCost = new Cost(i, studentIndex, professorCost, studentCost);
    			Costs.add(thisCost);
    			i++;
    		}
    		return Costs;
    }
    
}
