  
  
# Diyor Abduvaliev: Written Walkthrough

## 1.Project Overview- HangMan Game 
This Java project generates a random word from the 100 list of random words. It then gets the word and the person has to guess it either before the full hangman is drawn or before 30 seconds is up with the timer system. It also has a synonym, definition, and antonym hint system from the dictionary API to help the player and make it easier for them. I made it just to entertain other students and to start off my intro into game development. You have to type each letter or type the whole word out. Play again feature incorparated too as there is a statistical average used which gets the average amount of time it took the player to guess the word correctly in the number of rounds they played.If the hangman is fully drawn or timer runs out it instantly makes that round time equal to 30. 

## 2. Code Breakdown  
### Dictionary API.java 
- fetches the data needed for the game to function using the API.  
        Methods:  
```java
WordData getData()
```
- gets the data from api based on the string version of the getData() method and sends it to the word data class by accessing the synonyms, antonyms and definition of the word.  
- gets the endpoint from the getData() method 
- looks through the array of words
- makes the JSON object of the first index of the array 
- gets the word from the keyword "word" in the JSON object
- looks through the array of meanings of the first word and creates a JSON object of the first word
- looks through the array of definitions of the JSON object that was created and picks the first definition 
- looks through the array of synonyms as well and picks the first one and if null or its is not there then it returns "No synonym available" 
- looks through the array of antonym as well and picks the first one and if null or its is not there then it returns "No antonym available" 
- returns the info gathered as a WordData object

```java
getData(String endpoint)
```
- string version which actually gets the original word from the API.
- Makes an HTTP post request to the API
- connects my program to the API using this endpoint
- returns the JSON in which the user should use 

### HangManGame.java 
- core logic of the game. Basically makes the hidden spaces the game won game over those things  
         Methods:  
- Getters for everything: synonym, definition, antonym, and gameover and game won  

```java
guessWord(String guessedWord)
```
- takes the full word that is guessed
- if that is correct then ends the game with the time it took 
- else if wrong then ends game and the player loses and adds 30 seconds 

```java
guessletter(char letter) 
```
- takes the letter and checks if that letter is inside the given hidden word.
- if not it takes off a life
- letter is added to the hashset of letters guessed. 

```java
HangManGame(WordData wordData)
```

- constructor of the game class and gets the data from the word data class and uses it to complete its own process. 


### HangManUI.java  
- makes the interface in the terminal of the actual game use the hangman game class to help. This uses the hangManGame class while displaying the game and while running checks if letter guessed; word guessed; timer up; time it took; average; final average 
      Methods:  

```java
InitializeHangManStages() 
```
- makes the drawings in the terminal  

```java
startTimer() 
```
- uses the timer system imported to start the timer. 
- The timer system allows the user to set a certain time amount for the player to win or lose 
- if it goes past 30 seconds and the word is still not guessed then you lose. 
- uses currentmilli to get the time in milliseconds then convert to seconds and how long it took

```java
dispayGame()
```
- displays the whole game 

```java
playGame():
```
- starts the whole game and the logic while the game is running. 
- includes the timer
- the average time it takes you to complete
- check the letter guessed and if it is possible to accept it
- checks if the word is fully guessed 


```java
HangManUI(HangManGame game)
```

- constructor of the UI class
- initializes the game using the game class
- uses hashset in order to add guessed letters 
- scanner to input user guesses
- initializes the drawings as well


### WordData.java  
- class that holds all the info gotten from the API and without this class to get everything most of the code would not have worked. Sends the info gotten to the game class then that game sends to the UI.

```java
WordData(String word, String definition, String synonym, String antonym)
```
- constructor of the wordData class initializes and declares each of the parameters based on the data gotten from the dictionary API class

### App.java 
- the class which runs the whole game and makes it playable

```java
Main(String[] args)
```
- generates everything based on the other classes and runs the game starts with API class then word data class then the hangmangame class then lastly the hangmanUi class 
- creates the wordData based on the dictionary API class
- then uses the word Data inside the HangManGame class to initialize everything
- finally uses the hangManGame class to make the HangmanUI and then plays game

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
- Using local data inside my program to get the statistical average 

✔Filter/Sort data(+2%) 
- Filters the synonyms, definitions, and antonyms from the JSON objects and access those only without the others being needed
- checking API for the definitions, synonyms, and antonyms


## 4. Output Example

![Screenshot 2025-05-22 190445](https://github.com/user-attachments/assets/370e8e81-7639-4b46-86b0-ec1264d51004)

## 5. What I Learned
- I learned how to use an API  
- Learned how to make JSON objects and the usage of Maven  
- I also learned about the timer system and the hashset function by watching videos and stackoverflow and the geeks website  
- I learned how to connect to an API  
- I learned how the try and catch function works as well incase certain things with the API goes wrong.

## 6. Bonus

- I really had a problem with the timer system as I had to do 2 diverse ones one for the regular start timer and then the other for precise timing and how to incorporate the timer system into my game and utilize it.  
- In the future especially over summer I am planning on making  a GUI for this project and turn it into my first actual game created with another interface used.


