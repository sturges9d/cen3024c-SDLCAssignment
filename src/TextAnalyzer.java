import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TextAnalyzer{
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Something went wrong.");
        }
    }
}