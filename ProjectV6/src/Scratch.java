import java.io.*;
import java.util.Scanner;

public class Scratch {

	public static void main(String[] args) throws IOException {
		final int numofPlayers = 3;
		String[] highNames = new String[numofPlayers];
		int[] highScores = new int[numofPlayers];
		
		readInHighScore(highNames,highScores);
		
	}
	public static void readInHighScore(String[] highNames, int[] highScores) throws IOException {
		 //creates a file and scanner object for the high score
	    File highScoreFile = new File("highscore.txt");
	    Scanner highScoreReader = new Scanner(highScoreFile);
	    
	    //stores the the names of the high scorers to memory
	    for (int i = 0; i < 3; i++)
	    {
	    	highNames[i] = highScoreReader.next();
	    	highScores[i] = highScoreReader.nextInt();
	    }
	    
	    //closes file
	    highScoreReader.close();
	}
}
