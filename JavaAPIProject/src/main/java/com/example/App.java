package com.example;

//the starting point of the game where to actually run in 
public class App {
    public static void main(String[] args) {
        //https://www.w3schools.com/java/java_try_catch.asp I learned try and catch here
        try {//tries it out and then the exception it catches 
            //get word data from DictionaryAPI
            WordData wordData = DictionaryAPI.getData();
            
            //starts the game 
            HangManGame game = new HangManGame(wordData);
            HangManUI ui = new HangManUI(game);
            
            //displays the UI with the hangman 
            ui.playGame();
            
        } catch (Exception e) {//exception if it does not start it prints that it failed incase of missing word data/ API is down or much more 
            System.out.println("Failed to initialize the game: " + e.getMessage());
        }
    }
}