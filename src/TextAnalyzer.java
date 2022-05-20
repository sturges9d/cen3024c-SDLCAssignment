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

            Boolean printLines = false;
            Boolean readInput = true;
            String inputLine;
            ArrayList<String> textToAnalyze = new ArrayList<String>();
            while ((inputLine = input.readLine().replaceAll("\\<.*?>", "")) != null && readInput) {
                if (inputLine.equalsIgnoreCase("The Raven")) {
                    if (inputLine != "") {
                        printLines = true;
                    } else {
                        printLines = false;
                    }
                }
                if (inputLine.equalsIgnoreCase("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***")) {
                    printLines = false;
                    readInput = false;
                }
                if (printLines == true) {
                    System.out.println(inputLine);
                    if (inputLine.replaceAll("\\<.*?>", "") != "") {
                        textToAnalyze.add(inputLine);
                    }
                }
            }
            System.out.println("Array Contents: " + textToAnalyze);
            System.out.println("Selected Array Contents: " + textToAnalyze.get(0));
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}