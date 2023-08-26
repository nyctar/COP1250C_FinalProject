/* FileName: ProjectV6.java
 * Name: Yestin Arvin Gochuico
 * Prof.: Michelle Levine
 * Course: COP1250C
 * Date: 05/06/2023
 * Description: This is the 6th version of the quiz game "Who Wants to be a Java Programmer?".
 * Pseudocode:
 * Declare and initialize constants/variables
 * Display initial message
 * Prompt for the player's name
 * Display welcome message
 * Code a method that displays the main menu (See Rules, Play Game, Exit) and prompts player for main menu choice
 * If player chooses a., call displayRules method to display See Rules message, then notify player to return to main menu
 * if player chooses b., display Play Game message, then notify player to proceed to the game
 * if player chooses c., display Exit message, then notify player that the program is exiting
 * if player chooses anything else, display Invalid message, then notify player to return to main menu
 * Read from questions.txt to store String and int data of each questions, choices, and point values
 * Code and call processQuestion method to display all the questions and choices, and prompt user for their answer
 * Display correct if player's answer is right and add the question's point to total score
 * totalScore += questionPoint
 * Display wrong if player's answer is wrong, and display invalid if the input is not in the choices
 * Display invalid if the player's answer is not in the choices
 * Call readInHighScore method to open a text file of top 3 high scoring players and store the contents in a local array
 * Call compareScore method to evaluate if user got a high score
 * Call updateHighScore to overwrite the score inside a text file with the new score, if the player scores a new high score
 * Return player to main menu
 */
/*
 * Version 2 Update
 * fixed the declaration and initialization of variables by setting them at the beginning for readability
 * removed all the print and scanner related statements
 * replaced all messages and prompts to output to a dialog box instead of the console
 * added decision structures for the main menu interface instead of only displaying the string, so
 * the players can choose an option
 * added decision structures for the multiple choice question and answer interface, so the players can
 * be notified if they answered the question wrong or right
 * added a point system that will accumulate when the player answers a right question
 * added a score dependent ending message, where getting a perfect score, no score, or normal score
 * will result to different ending dialog boxes.
 */
/*
 * Version 3 Update
 * made validation on answers to questions for incorrect inputs
 * nested the MC Quiz Interface inside the PLAY_GAME choice for better readability
 * added 2 more question for the quiz
 * added return to main menu choice validation and let the user choose to return to main menu
 * removed exit commands
 */
/*
 * Version 4 Update
 * all quiz content are now stored in a text file called questions.txt
 * the highest score is stored in a text tile called highscore.txt
 * the quiz interface now uses the scanner object to read the questions, answers, and points from the text file
 * instead of coding each question, the questions are now generated using a for loop with the scanner object
 * instead of using a flag variable to validate, while loops are used
 * instead having a play again interface, the game will go back to the main menu after user answers all the questions
 */
/*
 * Version 5 Update
 * another question is added in the questions.txt file
 * rather than having a long and tedious code, functional composition is used to break down the code into smaller parts with methods
 * namely, the code is broken down into 7 methods:
 * 1.) displayMainMenu method that displays, prompts, and returns main menu choice
 * 2.) displayRules method that displays the See Rules message
 * 3.) processQuestion method that displays all the question and choices, prompts user for the answer, evaluates if the answer is correct/incorrect, and returns the point value of the question
 * 4.) readInHighScore method opens the highscore.txt, reads the high score, and stores it in a local variable
 * 5.) compareScore method compares the score in the highscore.txt and the current player's score. If there is a new high score, the score in the file will be replaced
 * 6.) displayScore method displays the cumulative total score after every answered question
 * 7.) main method that holds the general structure of the program and calls all the other methods 
 */
/*
 * Version 6 Update
 * Rather than storing the question values as variables, it will be stored in an array
 * The methods for comparing scores are changed, namely, readInHighScore and compareScore
 * Instead of using variables, arrays are used to store the top 3 high scoring players
 * readInHighScore method now reads the score and player name contents in the highscore.txt and stores it in an array
 * compareScore method evaluates the score of the new players to the top 3 to see if there is a new high score
 * The new method, updateHighScore, updates the array if there is a new score and the name of the player, and
 * overwrites the new top 3 players in highscore.txt
 */
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

public class ProjectV6 {

	public static void main(String[] args) throws IOException {
		//declare and initialize variables
		String INITIAL = "Who Wants to be a Java Programmer? You? Then, what's your name?\n\nEnter your name: ";
		String PLAY_GAME = "Play Game:\nGood luck programmer!";
		String EXIT_MESSAGE = "Exit Game:\nJava isn't for the faint of heart. Come back next time?\nYou have exited the game.";
		
		//create arrays for the questions
		final int numofQ = 10;
		String[] Question = new String[numofQ];
		String[] QuestionChoice_A = new String[numofQ];
		String[] QuestionChoice_B = new String[numofQ];
		String[] QuestionChoice_C = new String[numofQ];
		String[] QuestionChoice_D = new String[numofQ];
		String[] RightAnswer = new String[numofQ];
		int[] QuestionPoint = new int[numofQ];
		String playerName, menuChoice; 
		
		//create arrays for the high scoring players
		final int numofPlayers = 3;
		String[] highNames = new String[numofPlayers];
		int[] highScores = new int[numofPlayers];
		
		//displays the initial message and prompts for the player's name
		playerName = JOptionPane.showInputDialog(null, INITIAL, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
		//String WELCOME must be ran after assigning playerName a value
		String WELCOME = "\nHello " + playerName + "! I heard you want to be a Java Programmer? Let's see if you can handle this quiz game.\nGet ready to put your knowledge to the test!"
				+ "\n\nClick OK to proceed to the main menu.";
		//displays the welcome message
		JOptionPane.showMessageDialog(null, WELCOME, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
	
		do
		{
			//creates a file and scanner object to read questions.txt
			//must be inside do for the delimiter to reset to the starting point of the file if user plays again
			File quizFile = new File("questions.txt");
			Scanner quizContent = new Scanner(quizFile);
			
			//calls for displayMainMenu method that prompts the user what menu to choose
			menuChoice = displayMainMenu();
			    
			if (menuChoice.equalsIgnoreCase("A"))
			{
				//calls for displayRules method to display the rules to the user, if user chooses A
				displayRules();
			}
			else if (menuChoice.equalsIgnoreCase("B"))
			{
				JOptionPane.showMessageDialog(null, PLAY_GAME, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
				//declare and initialize variables for the MC Quiz
				String PERFECT_SCORE_MESSAGE = "You got a perfect score!\nYou can program better than me.\n\nYour overall score is:";
				String DEFAULT_SCORE_MESSAGE = "Great job! But you can do better.\n\nYour overall score is:";
				String ZERO_SCORE_MESSAGE = "You didn't get a single question right.\nYou don't have a future in programming, for now. Study harder!\n\nYour overall score is:";
				String RETURN_MENU_MESSAGE = "Click OK to return to main menu.";
				int totalScore = 0;
				int maxPoints = 0;
				int pointValue = 0;
	
				//displays the questions in the text file with 7 iterations 
			    for (int i = 0; i < numofQ; i++)
			    {
					//the question read from the text file
				    Question[i] = quizContent.nextLine();
				    //MC question choices read from the text file
				    QuestionChoice_A[i] = quizContent.nextLine();
				    QuestionChoice_B[i] = quizContent.nextLine();
				    QuestionChoice_C[i] = quizContent.nextLine();
				    QuestionChoice_D[i] = quizContent.nextLine();
				    RightAnswer[i] = quizContent.nextLine();
				    QuestionPoint[i] = quizContent.nextInt();
				    //counts the maximum points
				    maxPoints += QuestionPoint[i];
				    
				    //calls the processQuestion methods that displays the question and choices, and prompts the user for the answer
				    //It also display if the answer is correct/incorrect, and returns the score the user receives for a specific question
				    pointValue = processQuestion(Question[i], QuestionChoice_A[i], QuestionChoice_B[i], QuestionChoice_C[i], QuestionChoice_D[i], RightAnswer[i], QuestionPoint[i]);
				    
				    //dummy read
				    quizContent.nextLine();
				    
				    //adds up the user's total score
				    totalScore += pointValue;
				    
				    //calls the displayScore method that displays the user's total score after every question
				    displayScore(totalScore);
				    
			    }
				
			    //displays Ending message and the total accumulated points
			    //a different message is displayed based on the points accumulated
			    if (totalScore == maxPoints)
			    	//shows the Perfect Score message
			    	JOptionPane.showMessageDialog(null, PERFECT_SCORE_MESSAGE + " " + totalScore + "\n\n" + RETURN_MENU_MESSAGE, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
			    else if (totalScore == 0)
			    	//shows the Zero Score message
			    	JOptionPane.showMessageDialog(null, ZERO_SCORE_MESSAGE + " " + totalScore + "\n\n" + RETURN_MENU_MESSAGE, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
			    else
			    	JOptionPane.showMessageDialog(null, DEFAULT_SCORE_MESSAGE + " " + totalScore + "\n\n" + RETURN_MENU_MESSAGE, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
			    
			    //----------------------------------THE HIGHSCORE.TXT CODE------------------------------------
			    //calls the readInHighScore method to open the highscore.txt file and read the top 3 high scores and player names, store the score in a local array, an close the file.
			    readInHighScore(highNames, highScores);
			    
			    //calls the compareScore method to compare the user's score and the stored high scores to see if a new record is made.
			    compareScore(totalScore, playerName, highNames, highScores);
			    
			    //calls the updateHighScore method that writes the changed high score list to highscore.txt file
			    updateHighScore(highNames, highScores);
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, EXIT_MESSAGE, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
			    //closes the text file if user stops playing
				quizContent.close();
			}
		}
		while (!(menuChoice.equalsIgnoreCase("C")));
		//will continue to iterate unless player chooses to exit "C"
		
	}
	/**
	 * displayMainMenu method displays, prompts, and returns main menu choice
	 * @return the main menu choice that the play chooses as a String value
	 */
	public static String displayMainMenu() {
		
		String MENU_INTRO = "Please select a menu:";
		String SEE_RULES = "a.) See Rules";
		String ENTER_GAME = "b.) Play Game";
		String EXIT = "c.) Exit Game";
		String INVALID_MESSAGE = "You typed an invalid input.\nClick OK to return to the main menu.";
		String menuChoice;
		
		//prompts the use for main menu choice
		menuChoice = JOptionPane.showInputDialog(null, MENU_INTRO + "\n" +SEE_RULES + "\n" + ENTER_GAME
				+ "\n" + EXIT + "\n\nEnter the desired menu (type a letter): ", "Main Menu", JOptionPane.INFORMATION_MESSAGE);
		
		//validates the main menu choice
				while (!(menuChoice.equalsIgnoreCase("A") || menuChoice.equalsIgnoreCase("B") || menuChoice.equalsIgnoreCase("C")))
				{
					JOptionPane.showMessageDialog(null, INVALID_MESSAGE);
					menuChoice = JOptionPane.showInputDialog(null, MENU_INTRO + "\n" +SEE_RULES + "\n" + ENTER_GAME
							+ "\n" + EXIT + "\n\nEnter the desired menu (type a letter): ");
				}
		
		return menuChoice;
	}
	/**
	 * displayRules method displays the rules of the quiz game
	 */
	public static void displayRules() {
		String QUIZ_RULES = "See Rules:\nThere will be 10 multiple choice questions with 4 possible answers."
	    		+ "\nThe questions will increase in difficulty, and each question has a corresponding point depending on its difficulty." 
	    		+ "\n" + "The player who scores the most points will get bragging rights!" + "\n";
		JOptionPane.showMessageDialog(null, QUIZ_RULES + "\n\nClick OK to return to the main menu.");
		
	}
	/**
	 * processQuestion method displays all the question and choices, prompts user for the answer, evaluates if the answer is correct/incorrect
	 * @param question holds the multiple choice question to be displayed and answered by the player
	 * @param ansA holds the first choice for the question
	 * @param ansB holds the second choice for the question
	 * @param ansC holds the third choice for the question
	 * @param ansD holds the fourth choice for the question
	 * @param correctAns stores the letter that has the right answer
	 * @param pointValue stores the point value of each question
	 * @return the point value of each question as int value
	 */
	public static int processQuestion(String question, String ansA, String ansB, String ansC, String ansD, String correctAns, int pointValue) {
		String RIGHT_ANSWER_MESSAGE = "Great job programmer! You got the answer right.";
		String WRONG_ANSWER_MESSAGE = "Can you even program? You got the answer wrong.";
		String INVALID_ANSWER_MESSAGE = "Your input is invalid. Try again. Possible inputs are A, B, C, or D.";
		String answerChoice;
		//displays the question and the choices, and prompts the user for the answer
	    answerChoice = JOptionPane.showInputDialog(null, question + "\n" + ansA + "\n" + ansB + "\n" + ansC + "\n" + ansD + "\n\nSelect your answer (type a letter): ", "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
	    //displays whether the player got the answer correct, wrong, or invalid, and displays the total score.

	    //validates input
		while (!(answerChoice.equalsIgnoreCase("A") || answerChoice.equalsIgnoreCase("B") || answerChoice.equalsIgnoreCase("C") || answerChoice.equalsIgnoreCase("D")))
		{
			JOptionPane.showMessageDialog(null, INVALID_ANSWER_MESSAGE, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
			//displays the question and the choices, and prompts the user for the answer
		    answerChoice = JOptionPane.showInputDialog(question + "\n\n" + ansA + "\n" + ansB + "\n" + ansC + "\n" + ansD + "\n\nSelect your answer (type a letter): ");
		}
		
    	if (answerChoice.equalsIgnoreCase(correctAns))
	    {
    		//displays Right Answer message and total score
    		JOptionPane.showMessageDialog(null, RIGHT_ANSWER_MESSAGE + "\n\nYou earned " + pointValue + " point/s.", "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
    		return pointValue;
	    }
	    else
	    {	
	    	//displays Wrong Answer message and total score
	    	JOptionPane.showMessageDialog(null, WRONG_ANSWER_MESSAGE + "\n\nYou didn't get any points.", "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
	    	return pointValue = 0;
	    }
		
		
	}
	/**
	 * displayScore method displays the cumulative total score after every question the player answers
	 * @param score is the cumulative total score
	 */
	public static void displayScore(int score) {
		JOptionPane.showMessageDialog(null, "Your current total score is: " + score, "Who Wants to Be a Java Programmer?", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * readInHighScore method reads the stored score and names of players in the highscore.txt
	 * @param highNames holds the reference to highNames array that has the names of the top 3 scoring players
	 * @param highScores holds the reference to highScores array that has the scores of the top 3 scoring players
	 * @throws IOException
	 */
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
	/**
	 * compareScore method compares the current score of the player to the top 3 scoring players and replaces the values in name and score array if there is a new high score
	 * @param highScore is the total score value of the current player
	 * @param userName is the name of the current player
	 * @param name array holds the reference to highNames array that has the names of the top 3 scoring players
	 * @param score array holds the reference to highScores array that has the scores of the top 3 scoring players
	 */
	public static void compareScore(int highScore, String userName, String[] name, int[] score) {
		
	   if (highScore > score[0])
	   {
		    //moves 2nd place to 3rd place
		    score[2] = score[1];
		   	name[2] = name[1];
		    //moves 1st place to 2nd place
			score[1] = score[0];
			name[1] = name[0];
			//replace highest score/name with the current player
			score[0] = highScore;
			name[0] = userName;
	   }
	   else if (highScore > score[1])
	   {
		   	//moves 2nd place to 3rd place
		    score[2] = score[1];
		   	name[2] = name[1];
			//replace 2nd highest score/name with the current player
			score[0] = highScore;
			name[0] = userName;
	   }
	   else if (highScore > score[2])
	   {
			//replace 3rd highest score/name with the current player
			score[0] = highScore;
			name[0] = userName;
	   }
		
		
	}
	/**
	 * updateHighScore method overwrites the names and scores of the top 3 scoring players stored in highscore.txt with the values stored in highName and highScore 
	 * @param highName array holds the reference to highNames array that has the names of the top 3 scoring players
	 * @param highScore array holds the reference to highScores array that has the scores of the top 3 scoring players
	 */
	public static void updateHighScore(String[] highName, int[] highScore) throws IOException {
		//creates a PrintWriter object
	    File highScoreFile = new File("highscore.txt");
        PrintWriter highScoreTracker = new PrintWriter(highScoreFile);
        for (int i = 0; i < 3; i++)
        {
        	highScoreTracker.print(highName[i] + " ");
        	highScoreTracker.println(highScore[i]);
        }
    	//closes file
    	highScoreTracker.close();
		
	}

}