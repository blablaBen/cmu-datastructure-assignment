package cmu.data.assignment;
import cmu.data.assignment.model.Lifeguard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App 
{
    public static void main( String[] args ) throws IOException {
        String inputFilePath = "./input/sample-input.txt";
        List<Lifeguard> allLifeguards = readLifeGuard(inputFilePath);
    }

    public static List<Lifeguard> readLifeGuard(String inputFilePath) throws IOException {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        fileReader = new FileReader(Paths.get(inputFilePath).toFile());
        bufferedReader = new BufferedReader(fileReader);

        String sCurrentLine;

        int totalLifeGuards = Integer.parseInt(bufferedReader.readLine());
        List<Lifeguard> result = new ArrayList<Lifeguard>();

        BufferedReader finalBufferedReader = bufferedReader;
        IntStream.range(1, totalLifeGuards).forEach(
                nbr -> {
                    try {
                        String[] lifeGuardInput = finalBufferedReader.readLine().split(" ");
                        result.add(new Lifeguard(lifeGuardInput[0], lifeGuardInput[1]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        try {
            if (bufferedReader != null)
                bufferedReader.close();
            if (fileReader != null)
                fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;

    }

    public static int getMaximumToTime(List<Lifeguard> allLifeguards) {
        int maximumToTime = 0;
        for(Lifeguard item : allLifeguards) {
            if(item.getToTime() > maximumToTime) {
                maximumToTime = item.getToTime();
            }
        }

        return maximumToTime;
    }
}
