package cmu.data.assignment;
import cmu.data.assignment.model.CompanyPool;
import cmu.data.assignment.model.Lifeguard;
import cmu.data.assignment.model.TimeSlot;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
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

        List<TimeSlot> allTimeSlot = new ArrayList<>();
        BufferedReader finalBufferedReader = bufferedReader;
        IntStream.rangeClosed(1, totalLifeGuards).forEach(
                index -> {
                    try {
                        String[] lifeGuardInput = finalBufferedReader.readLine().split(" ");
                        Lifeguard guard = new Lifeguard(index, lifeGuardInput[0], lifeGuardInput[1]);
                        TimeSlot timeSlotStart = new TimeSlot(guard.startTime, guard.name, true);
                        TimeSlot timeSlotEnd = new TimeSlot(guard.toTime, guard.name, false);
                        allTimeSlot.add(timeSlotStart);
                        allTimeSlot.add(timeSlotEnd);
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

        return new CompanyPool(allTimeSlot, totalLifeGuards);

    }

    public static int getMaximumAmountOfTimeWhenGuardGetFired(CompanyPool poolWithLifeguard) {
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
