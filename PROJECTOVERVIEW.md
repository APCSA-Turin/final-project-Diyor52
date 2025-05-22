
# Diyor Abduvaliev: Written Walkthrough 

## 1.Project Overview - HangMan Game
This Java project generates a random word from the 100 list of random words. It then gets the word and the person has to guess it either before the full hangman is drawn or before 30 seconds is up with the timer system. It also has a synonym, definition, and antonym hint system from the dictionary API to help the player and make it easier for them. I made it just to entertain other students and to start off my intro into game development. 

## 2. Code Breakdown
### Dictionary API.java 
fetches the data needed for the game to function using the API.
	      Methods: 
- WordData.getData() = gets the data from api and sends it to the word data class by accessing the synonyms, antonyms and definition of the word.
- getData()= string version which actually gets the stuff from the API. 

### HangManGame.java
core logic of the game. Basically makes the hidden spaces the game won game over those things 
	       Methods:
- Getters for everything: synonym, definition, antonym, and gameover and game won 
- guessletter(char letter) : takes the letter and checks if that letter is inside the given hidden word.

### HangManUI.java 
makes the interface in the terminal of the actual game use the hangman game class to help. 
	    Methods: 
- InitializeHangManStages : makes the drawings in the terminal  
- startTimer() : uses the timer system imported to start the timer. The timer system allows the user to set a certain time amount for the player to win or lose if it goes past 30 seconds and the word is still not guessed. 
- dispayGame(): displays the whole game 
- PlayGame(): starts the whole game and the logic while the game is running. includes the timer; the average time it takes you to complete; and to check the letter guessed and if it is possible to accept it


### WordData.java
class that holds all the info gotten from the API and without this class to get everything most of the code would not have worked. Sends the info gotten to the game class then that game sends to the UI.

### App.java
the class which runs the whole game and makes it playable

.
## 3. Features Implemented (Rubric Aligned)

✔ Base Project (88%)
 - Uses an external API (Free Dictionary API)
 - Uses multiple Java methods and logic
 - Parses JSON response using basic string matching
 - Provides interactive user experience through the terminal

✔ Written Walkthrough ()
 - This document fulfills the walkthrough requirement

✔Basic statistics(+6%)
- Gets the average amount of time it took the player to solve the hangman based on how many rounds they played. 

## 4. Output Example

*Picture of output on a google doc*

Welcome to Hangman!
You have 30 seconds to guess the word!
Your word has 5 letters: _____

-------------------------------
  +---+
  |   |
      |
      |
      |
      |
=========

Time left: 30 seconds
Attempts left: 6
Current word: _____
Guessed letters: []

Hints:
Definition: Panthera tigris, a large predatory mammal of the cat family, indigenous to Asia.
Synonym: No synonyms available
Antonym: No antonyms available

Guess a letter: 



## 5. What I Learned
- I learned how to use an API 
- Learned how to make JSON objects and the usage of Maven 
- I also learned about the timer system and the hashset function by watching videos and stackoverflow and the geeks website 
- I learned how to connect to an API 
- I learned how the try and catch function works as well incase certain things with the API goes wrong. 



## 6. Bonus

- I really had a problem with the timer system as I had to do 2 diverse ones one for the regular start timer and then the other for precise timing and how to incorporate the timer system into my game and utilize it. 
- In the future especially over summer I am planning on making  a GUI for this project and turn it into my first actual game created with another interface used. 


