package cmu.data.assignment;
import cmu.data.assignment.model.CompanyPool;
import cmu.data.assignment.model.Lifeguard;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App 
{
    public static void main( String[] args ) throws IOException {

        File folder = new File("./input");
        File[] listOfFiles = folder.listFiles();

        for(File fileItem: listOfFiles) {
            System.out.println("Start to process:" + fileItem.getName());
            CompanyPool poolWithLifeguard = readLifeGuard(fileItem.toPath());
            int maximumAmountOfTimeWhenGuardGetFired = getMaximumAmountOfTimeWhenGuardGetFired(poolWithLifeguard);
            String outputFilename = fileItem.getName().replace('.', '-') + ".out";
            writeResultToOutput("./output/"+outputFilename, maximumAmountOfTimeWhenGuardGetFired);
        }
    }

    public static CompanyPool readLifeGuard(Path inputFilePath) throws IOException {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        fileReader = new FileReader(inputFilePath.toFile());
        bufferedReader = new BufferedReader(fileReader);

        String sCurrentLine;

        int totalLifeGuards = Integer.parseInt(bufferedReader.readLine());
        List<Lifeguard> result = new ArrayList<Lifeguard>();

        BufferedReader finalBufferedReader = bufferedReader;
        IntStream.rangeClosed(1, totalLifeGuards).forEach(
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

        return new CompanyPool(result);

    }

    public static int getMaximumAmountOfTimeWhenGuardGetFired(CompanyPool poolWithLifeguard) {
        int maximumTime = getMaximumAvailToTime(poolWithLifeguard.getCurrentLifeguard());

        int maximumAmountOfTimeWhenGuardGetFired = 0;
        for(int i=0 ; i< poolWithLifeguard.getCurrentLifeguard().size(); i++) {
            boolean[] timeSlots = new boolean[maximumTime];
            List<Lifeguard> allLifeguardsWhenOneGetFired = poolWithLifeguard.getFiredLifeguard(i);
            for(Lifeguard lifeguard : allLifeguardsWhenOneGetFired) {
                IntStream.rangeClosed(lifeguard.getStartTime(), lifeguard.getRealEndTime()).forEach(
                        index -> {
                           timeSlots[index] = true;
                        }
                );
            }

            int totoalAmountOfTimeslots = 0;
            for(boolean hasGuard: timeSlots) {
                if(hasGuard) {
                    totoalAmountOfTimeslots ++;
                }
            }

            maximumAmountOfTimeWhenGuardGetFired = totoalAmountOfTimeslots > maximumAmountOfTimeWhenGuardGetFired ? totoalAmountOfTimeslots : maximumAmountOfTimeWhenGuardGetFired;
        }

        return maximumAmountOfTimeWhenGuardGetFired;
    }




    private static int getMaximumAvailToTime(List<Lifeguard> allLifeguards) {
        int maximumToTime = 0;
        for(Lifeguard item : allLifeguards) {
            if(item.getToTime() > maximumToTime) {
                maximumToTime = item.getToTime();
            }
        }

        return maximumToTime;
    }

    public static void writeResultToOutput(String filePathAndName, int result) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filePathAndName, "UTF-8");
        writer.println(result);
        writer.close();
    }

}
