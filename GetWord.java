package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GetWord
{
    private static ArrayList<String> wordList = new ArrayList<>();
    static Random rand = new Random();
    
    public static String guessWord()
    {
        // Read in word list and select word
        try
        {
            File file = new File("hangman/files/dict.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine())
            {
                wordList.add(scan.nextLine());
            }
            scan.close();
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return wordList.get(rand.nextInt(wordList.size()));
    }
}