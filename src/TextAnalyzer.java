/*
 * Author.....: Stephen Sturges
 * Date.......: 05/18/2022
 * Description: For the CEN 3024C SDLC Assignment.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class TextAnalyzer{
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            Boolean readInput = true;
            Boolean printLines = false;
            Boolean emptyLine = false;
            ArrayList<String> textToAnalyze = new ArrayList<String>();
            String inputLine;
            while ((inputLine = input.readLine().replaceAll("<[^>]*>", "").replaceAll("’", "'")) != null && readInput) {
                if (inputLine.equalsIgnoreCase("The Raven")) {
                    printLines = true;
                }
                if (inputLine.isEmpty()) {
                    emptyLine = true;
                } else {
                    emptyLine = false;
                }
                if (inputLine.equalsIgnoreCase("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***")) {
                    printLines = false;
                    readInput = false;
                }
                if (printLines == true && emptyLine == false) {
                    System.out.println(inputLine);
                    // String outputTextArray[] = inputLine.split("&mdash|[^a-z[A-Z]]");
                    String outputTextArray[] = inputLine.split("&mdash|[^’'a-z[A-Z]]");
                    for (int i = 0; i < outputTextArray.length; i++) {
                        if (!outputTextArray[i].isEmpty()) {
                            textToAnalyze.add(outputTextArray[i].trim().toLowerCase());
                        }
                    }
                }
            }
            for (int i = 0; i < textToAnalyze.size(); i++) {
                System.out.println("ArrayList Element: " + i + ", " + textToAnalyze.get(i));
            }
            int indexToDisplay = 69;
            System.out.println("Selected Array Element: " + indexToDisplay + ", Contents: " + textToAnalyze.get(indexToDisplay));
            // String[] lines = textToAnalyze.get(indexToDisplay).split("&mdash|[^abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]");
            // for (int i = 0; i < lines.length; i++) {
            //     if (!lines[i].isEmpty()) {
            //         System.out.println("Lines Array Element: " + i + ", " + lines[i]);
            //     }
            // }
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
            for (String string : results) {
                System.out.println(string);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}