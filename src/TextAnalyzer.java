/*
 * Author.....: Stephen Sturges
 * Date.......: 05/18/2022
 * Description: For the CEN 3024C SDLC Assignment.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class TextAnalyzer{
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            // Define loop control variables and create ArrayList for analysis.
            Boolean readInput = true;
            Boolean printLines = false;
            Boolean emptyLine = false;
            ArrayList<String> textToAnalyze = new ArrayList<String>();
            String inputLine;
            while ((inputLine = input.readLine().replaceAll("<[^>]*>", "").replaceAll("’", "'")) != null && readInput) {
                // Controls the start of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase("The Raven")) {
                    printLines = true;
                }
                // Removes empty lines from being included in the relevant text ArrayList.
                if (inputLine.isEmpty()) {
                    emptyLine = true;
                } else {
                    emptyLine = false;
                }
                // Controls the end of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***")) {
                    printLines = false;
                    readInput = false;
                }
                // Splits the String lines into string words and places them into the textToAnalyze array.
                if (printLines == true && emptyLine == false) {
                    System.out.println(inputLine);
                    String outputTextArray[] = inputLine.split("&mdash|[^’'a-z[A-Z]]");
                    for (int i = 0; i < outputTextArray.length; i++) {
                        if (!outputTextArray[i].isEmpty()) {
                            textToAnalyze.add(outputTextArray[i].trim().toLowerCase());
                        }
                    }
                }
            }
            // Display Index of element and it's contents for debugging purposes.
            for (int i = 0; i < textToAnalyze.size(); i++) {
                System.out.println("ArrayList Element: " + i + ", " + textToAnalyze.get(i));
            }
            // Display a selected index's content for debugging purposes.
            int indexToDisplay = 69;
            System.out.println("Selected Array Element: " + indexToDisplay + ", Contents: " + textToAnalyze.get(indexToDisplay));

            // Count the number of occurances of a word in the ArrayList of text from the URL.
            ArrayList<String> results = new ArrayList<String>();
            for (int i = 0; i < textToAnalyze.size(); i++) {
                int wordCount = 0;
                String word = textToAnalyze.get(i);
                for (int j = 0; j < textToAnalyze.size(); j++) {
                    if (textToAnalyze.get(j).equalsIgnoreCase(word)) {
                        wordCount++;
                        textToAnalyze.remove(j);
                    }
                }
                results.add(word + ", " + wordCount);
                wordCount = 0;
            }

            // Display the results ArrayList.
            for (String string : results) {
                System.out.println(string);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}