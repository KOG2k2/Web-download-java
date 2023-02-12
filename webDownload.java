import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;

public class webDownload {
    public static void DownloadWebpage(String webpage, String outputFileName){
        try {
            URL url = new URL(webpage);
            BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            String line;
            while ((line = readr.readLine()) != null){
                writer.write(line);
            }

            readr.close();
            writer.close();
            System.out.println("Succesfully Downloaded.");
        }

        catch(MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
        }
        catch (IOException ie){
            System.out.println("IOException raised");
        }
    }

    public static String[] getURLFromFile(String fileName){
        File inputFile = new File(fileName);
        String URLString[] = new String[50];
        try{
            int index = 0;
            Scanner urlScanner = new Scanner(inputFile);
            while(urlScanner.hasNextLine()){
                URLString[index] = urlScanner.nextLine();
                index++;
            }
            urlScanner.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found");
        }
        return URLString;
    }

    public static void main(String args[])
        throws IOException
    {
        String url[] = getURLFromFile("input.txt");
        String initialName = "download_";
        String finalName = null;

        for(int i = 0; i < url.length; i++){
            if(url[i] != null){
                finalName = initialName + Integer.toString(i) + ".html";
                DownloadWebpage(url[i], finalName);
            }
        }
    }
}