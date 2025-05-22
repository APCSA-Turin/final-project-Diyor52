package com.example;

//used for reading API responses
import java.io.BufferedReader;
import java.io.InputStreamReader;
//used for making HTTP requests
import java.net.HttpURLConnection;
import java.net.URL;
//used for examining JSON data
import org.json.JSONArray;
import org.json.JSONObject;

public class DictionaryAPI {
    //100 common words for the game
    private static final String[] possibleWords = {
        "apple", "banana", "cherry", "date", "elderberry","fig", "grape", "honeydew", "kiwi", "lemon","mango", "nectarine", "orange", "peach", "quince","raspberry", "strawberry", "tangerine", "project", "vanilla",
        "watermelon", "apricot", "blueberry", "cantaloupe", "jam","elderflower", "feijoa", "guava", "huckleberry", "chicken","jackfruit", "kumquat", "lychee", "mulberry", "olive",
        "persimmon", "pomegranate", "avocado", "blackberry", "coconut","durian", "eggplant", "grapefruit", "cocao", "jujube","hello", "lime", "melon", "papaya", "pear",
        "tiger", "lion", "penguin", "wolf", "computer","phone", "keyboard", "mouse", "monitor", "laptop","tablet", "charger", "cable", "router", "printer","scanner", "speaker", "headphones", "microphone", "camera",
        "television", "remote", "battery", "flashlight", "clock","watch", "bracelet", "necklace", "earrings", "ring","jacket", "shirt", "pants", "shoes", "socks","hat", "gloves", "scarf", "umbrella", "backpack",
        "wallet", "purse", "mirror", "comb", "toothbrush","soap", "shampoo", "towel", "blanket", "pillow","chair", "table", "lamp", "bed", "sofa"
    };

     //picks a random word from list
    public static String getRandomWord() {
        //we multiply by word count to get a possible index from the array
        int randomIndex = (int)(Math.random() * possibleWords.length);
        return possibleWords[randomIndex];
    }


    public static WordData getData() throws Exception {
        String randomWord = getRandomWord();
        String endpoint = "https://api.dictionaryapi.dev/api/v2/entries/en/" + randomWord;
        String jsonResponse = getData(endpoint);
        
         try {
            //parse(examine it) the JSON response
            JSONArray jsonArray = new JSONArray(jsonResponse);
            JSONObject firstEntry = jsonArray.getJSONObject(0);
            
            //extract the word and get it 
            String word = firstEntry.getString("word").toLowerCase();
            
            //get the first meaning and definition
            JSONArray meanings = firstEntry.getJSONArray("meanings");
            JSONObject firstMeaning = meanings.getJSONObject(0);
            JSONArray definitions = firstMeaning.getJSONArray("definitions");
            String definition = definitions.getJSONObject(0).getString("definition");
            
            //try to get synonyms if not available it says so 
            String synonym = "No synonyms available";
            JSONArray synonyms = firstMeaning.optJSONArray("synonyms");
            if (synonyms != null && synonyms.length() > 0) {
                synonym = synonyms.getString(0);
            }
            
            //same for antonyms
            String antonym = "No antonyms available";
            JSONArray antonyms = firstMeaning.optJSONArray("antonyms");
            if (antonyms != null && antonyms.length() > 0) {
                antonym = antonyms.getString(0);
            }
            
            //return everything up to the wordData which is then sent to the game then UI 
            return new WordData(word, definition, synonym, antonym);
            
        } catch (Exception e) {
            //if API fails use fallback data
            return new WordData(randomWord, "A common object","No synonyms available", "No antonyms available");
        }
    }

    //helper method to make the actual API call(basically mrs Turin's method she gave us but I split it into 2 parts to make it easier for me to visualize)
    public static String getData(String endpoint) throws Exception {
         /*endpoint is a url (string) that you get from an API website*/
        URL url = new URL(endpoint);
        /*connect to the URL*/
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /*creates a GET request to the API.. Asking the server to retrieve information for our program*/
        connection.setRequestMethod("GET");
        /* When you read data from the server, it wil be in bytes, the InputStreamReader will convert it to text. 
        The BufferedReader wraps the text in a buffer so we can read it line by line*/
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;//variable to store text, line by line
        /*A string builder is similar to a string object but faster for larger strings, 
        you can concatenate to it and build a larger string. Loop through the buffer 
        (read line by line). Add it to the stringbuilder */
        StringBuilder content = new StringBuilder();
        while ((inputLine = buff.readLine()) != null) {
            content.append(inputLine);
        }
        buff.close(); //close the bufferreader
        connection.disconnect(); //disconnect from server 
        return content.toString(); //return the content as a string
    }
}

