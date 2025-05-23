package com.example;

//this class helps with the core logic for the guessing game: it includes the target the hints the players progress and the attempts left all in this class
public class HangManGame {
    private final String word;          
    private final String definition;  //all the variables used here 
    private final String synonym;       
    private final String antonym;
    private final StringBuilder guessedWord;  //tracks correctly guessed letters
    private int attemptsLeft;           //remaining guesses 

   //gets the word data from the dictionary API and then usees the hints and word for the actual game 
    public HangManGame(WordData wordData) {
        word = wordData.getWord();//constructor here 
        definition = wordData.getDefinition();
        synonym = wordData.getSynonym();
        antonym = wordData.getAntonym();
        guessedWord = new StringBuilder();
        
        //the word that has to be guessed with the underscores 
        for (int i = 0; i < word.length(); i++) {
            guessedWord.append("_");
        }
        this.attemptsLeft = 6;//has 6 attempts to draw out hangman
    }

    //gets the letter from the player and what they inputted and if its correct sets the character at the index in which it is supposed to be 
    public boolean guessLetter(char letter) {
        boolean correctGuess = false;//make it false at first 
        letter = Character.toLowerCase(letter);
        
        //for loop to check through each time if the guess was correct or not 
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) == letter) {
                guessedWord.setCharAt(i, word.charAt(i));
                correctGuess = true; //if the word does equal that letter in that place then set it there 
            }
        }
        
        //if its wrong though it takes off a point from the attempts left if correct does not 
        if (!correctGuess) {
            attemptsLeft--;
        }
        
        return correctGuess;
    }

    //allows player to guess the entire word at once instead of just the letters to make it faster but have to make sure no space in front or anything like that 
    public boolean guessWord(String guessedWord) {
        if (word.equals(guessedWord)) {
            //if correct reveals all letters by using a for loop 
            for (int i = 0; i < word.length(); i++) {
                this.guessedWord.setCharAt(i, word.charAt(i));//sets each character to the correct letter if guessed correctly 
            }
            return true;
        } else {
            //if wrong set attempts to 0 making the player automaticlly lose and add 30 seconds to your timer 
            attemptsLeft = 0;
            return false;
        }
    }

    //checks if the player has won which is that all the letters were guessed correctly
    public boolean isGameWon() {
        return guessedWord.toString().equals(word);//once it is equal then game is won 
    }

    //now for game over a bit more complicated. It includes by guessing the word or running out of attempts or if timer runs out but that is in the UI class
    public boolean isGameOver() {
        return attemptsLeft <= 0 || isGameWon();
    }

    //just the getters gets the word
    public String getWord() {
        return word;
    }
    //gets the definition of word 
    public String getDefinition() {
        return definition;
    }
    //gets the synonym of word 
    public String getSynonym() {
        return synonym;
    }
    //gets the anyonym of word 
    public String getAntonym() {
        return antonym;
    }

    //gets the current places of the letters this bascially returns the guessed letters and the underscores is kept if unguessed 
    public String getGuessedWord() {
        return guessedWord.toString();
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }
}