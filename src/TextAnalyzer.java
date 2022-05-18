/*
 * Author.....: Stephen Sturges
 * Date.......: 05/18/2022
 * Description: For the CEN 3024C SDLC Assignment.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TextAnalyzer{
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));

            Boolean printLines = false;
            String inputLine;
            while ((inputLine = input.readLine().replaceAll("\\<.*?>", "")) != null) {
                if (inputLine.equalsIgnoreCase("The Raven")) {
                    printLines = true;
                }
                if (inputLine == "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***") {
                    printLines = false;
                }
                if (printLines == true) {
                    System.out.println(inputLine);
                }
                System.out.println("No line printed.");
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }
}