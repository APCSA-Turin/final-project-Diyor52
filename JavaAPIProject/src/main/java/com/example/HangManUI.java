package com.example;

import java.util.HashSet;//array of characters that are used 
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;//timer 
import java.util.TimerTask;//makes a timer for the game 

//makes the inferface in the terminal with hangman uses scanner as well to input the guesses 
public class HangManUI {
    private HangManGame game;
    private final String[] hangmanStages;//hangman stages 
    private Set<Character> guessedLetters;//makes a array of characters that are already used 
    
    //visual countdown
    //https://stackoverflow.com/questions/43796179/make-a-simple-javascript-timer learned how to make a timer here 
    private Timer timer;
    private int timeLeft = 30;
    private boolean timeUp = false;
    
    //precise timing 
    private boolean gameRunning = false;
    //long allows to use a much more variety of numbers
    //https://stackoverflow.com/questions/5857812/long-vs-integer-long-vs-int-what-to-use-and-when learned about the long variable 
    private long roundStartTime = 0;
    
    //game stats
    private int roundsPlayed = 0;
    private static int totalRoundTimes = 0; 
    
    //scanner used 
    private final Scanner scanner;

    public HangManUI(HangManGame game) {
        this.game = game;//constructor that makes the game 
        hangmanStages = initializeHangmanStages();
        guessedLetters = new HashSet<>();
        scanner = new Scanner(System.in);
    }

    private String[] initializeHangmanStages() {
        return new String[] {
            //nothing 
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            //head
            "  +---+\n  |   |\n  0    |\n      |\n      |\n      |\n=========",
            //head torso 
            "  +---+\n  |   |\n  0   |\n  |   |\n      |\n      |\n=========",
            //head torso arm
            "  +---+\n  |   |\n  0   |\n /|  |\n      |\n      |\n=========",
            //head torso arm arm 
            "  +---+\n  |   |\n  0   |\n /|\\  |\n      |\n      |\n=========",
            //head torso arm arm leg 
            "  +---+\n  |   |\n  0   |\n /|\\  |\n /    |\n      |\n=========",
            //head torso arm arm leg leg 
            "  +---+\n  |   |\n  0   |\n /|\\  |\n / \\  |\n      |\n========="
        };//draws it out on the terminal 
    }

    //starts the timer 
    private void startTimer() {//https://www.youtube.com/watch?app=desktop&v=QEF62Fm81h4 all about the timer 
        timer = new Timer();//30 second timer 
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {//starts the timer
                if (timeLeft > 0 && gameRunning) {
                    timeLeft--;
                } else {
                    timeUp = true;//if time up stops it 
                    timer.cancel();
                    gameRunning = false;
                }
            }
        }, 1000, 1000); //update every second
    }

    //all the display feature shown 
    public void displayGame() {
        System.out.println("\n-------------------------------");
        System.out.println(hangmanStages[6 - game.getAttemptsLeft()]);
        System.out.println("\nTime left: " + timeLeft + " seconds");
        System.out.println("Attempts left: " + game.getAttemptsLeft());
        System.out.println("Current word: " + game.getGuessedWord());
        System.out.println("Guessed letters: " + guessedLetters);
        System.out.println("\nHints:");
        System.out.println("Definition: " + game.getDefinition());
        System.out.println("Synonym: " + game.getSynonym());
        System.out.println("Antonym: " + game.getAntonym());

    }

    //plays the game and starts it
    public void playGame() {
        try {//https://www.w3schools.com/java/java_try_catch.asp learned catch and try here which bascially tries this out and for exceptions does the catch 
            boolean playAgain = true;
            
            while (playAgain) {
                timeLeft = 30;//sets timer to 30
                timeUp = false;//time up is false 
                guessedLetters.clear();//clears and makes it underscores again 
                gameRunning = true;
                roundStartTime = System.currentTimeMillis();//caluculates in milliseconds when it started to be as precise as possible //https://www.tutorialspoint.com/java/lang/system_currenttimemillis.htm 
                startTimer();
                
                //all the start features 
                System.out.println("Welcome to Hangman!");
                System.out.println("You have 30 seconds to guess the word!");
                System.out.println("Your word has " + game.getWord().length() + " letters: " + game.getGuessedWord());
                
                //made a while loop to go through each time until one of the things here is false 
                while (gameRunning && !game.isGameOver() && !timeUp) {
                    displayGame();
                    System.out.println("");
                    System.out.println("***Be aware if you guess the whole word and get it wrong it automatically makes you lose and adds 30 seconds to your average!!!!!");
                    System.out.print("\nGuess a letter or type the whole word: ");//can guess the whole word or single letters 
                    
                    String input = scanner.nextLine().toLowerCase();//takes the input from the user 
                    
                    //check if input is a single letter instead of a full word 
                    //the letter is an actual single letter
                    if (input.length() == 1 && Character.isLetter(input.charAt(0))){
                        char guess = input.charAt(0);
                        if (guessedLetters.contains(guess)) {
                            System.out.println("You already guessed that!");
                            continue;
                        }
                        
                        guessedLetters.add(guess);//adding the guess to the array of guessed letters 
                        if (game.guessLetter(guess)) {
                            System.out.println("Correct!");//guessed the correct letter 
                        } else {
                            System.out.println("Wrong guess!");
                        }
                    } 
                    //check if input is a word guess instead of a single character 
                    else if (input.length() > 1) {
                        if (game.guessWord(input)) {
                            System.out.println("Correct! You guessed the word!");
                        } else {
                            System.out.println("Wrong word guess! Game over!");
                            timeUp = true; //end the game and timer of 30 seconds is up 
                        }
                    } 
                    else {
                        System.out.println("Please enter a single letter or a word guess: ");
                        continue;
                    }
                    
                    if (game.isGameWon()) {
                        gameRunning = false;
                    }
                }
                
                timer.cancel();
                displayGame();//end game and shows off stats 

                //calculate stats how long it took 
                long roundTime = (System.currentTimeMillis() - roundStartTime) / 1000;//from milliseconds to seconds to make it much more accurate 
                if (timeUp || !game.isGameWon()) {
                    roundTime = 30;  //max time if failed
                }
                
                //adds to total round to find average 
                totalRoundTimes += roundTime;
                roundsPlayed++;
                
                //calculate average time
                double averageTime;
                if (roundsPlayed > 0) {
                    averageTime = (double)totalRoundTimes / roundsPlayed;
                    averageTime = Math.round(averageTime*100)/100.0;
                } else {
                    averageTime = 0;
                }
                
                //round time in seconds
                System.out.println("\nTime taken: " + roundTime + " seconds");
                if (timeUp) {
                    System.out.println("Time's up!!! The word was: " + game.getWord());
                } else if (game.isGameWon()) {
                    System.out.println("You won!!! The word was: " + game.getWord());
                } else {
                    System.out.println("Game over!!! The word was: " + game.getWord());
                }
                
                //show average time
                System.out.println("\nYour current average: " + averageTime + " seconds per round");
                
                //play again feature 
                System.out.print("\nPlay again? (yes/no): ");
                String playAgainInput = scanner.nextLine().toLowerCase();
                if (playAgainInput.equals("yes")) {
                    WordData newWordData = DictionaryAPI.getData();
                    game = new HangManGame(newWordData);//starts the game again 
                } else {
                    //if not gives the final stats as shown 
                    playAgain = false;
                    System.out.println("\nFinal stats:");
                    System.out.println("Total rounds played: " + roundsPlayed);
                    
                    //calculate final average
                    double finalAverage;
                    if (roundsPlayed > 0) {
                        finalAverage = Math.round(((double)totalRoundTimes / roundsPlayed)*100)/100.0;
                    } else {
                        finalAverage = 0;
                    }
                    System.out.println("Average time per round: " + finalAverage + " seconds");
                    System.out.println("Thanks for playing!!!");
                }
            }
        } catch (Exception e) {//the exception if it fails to get the word; leads to cancel out everything and give an error 
            if (timer != null){//avoid the exception error
            timer.cancel();//cancels and done sends an error message 
            System.out.println("Error: " + e.getMessage());//gives the detail of what caused the error
            e.printStackTrace();
            }
        } 
        
        scanner.close(); //always closes scanner
    }
}