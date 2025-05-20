package com.example;

//class that hold all the info about the given word from the API gotten from API 
public class WordData {
    private final String word;  //variables all here 
    private final String definition; 
    private final String synonym;   
    private final String antonym;   

    //constructs the worddata object with the required info from the API 
    public WordData(String word, String definition, String synonym, String antonym) {
        this.word = word;//sets them all from the API to these variables to show them off 
        this.definition = definition;
        this.synonym = synonym;
        this.antonym = antonym;
    }

    //getters for all fields
    public String getWord() {
        return word;
    }
    //gets the definition 
    public String getDefinition() {
        return definition;
    }
    //gets the synonym 
    public String getSynonym() {
        return synonym;
    }
    //gets the antonym 
    public String getAntonym() {
        return antonym;
    }
}