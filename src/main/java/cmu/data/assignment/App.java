package cmu.data.assignment;
import cmu.data.assignment.model.CompanyPool;
import cmu.data.assignment.model.Lifeguard;
import cmu.data.assignment.model.TimeSlot;
import cmu.data.assignment.model.TimeSlotWithOccupier;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
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
            List<TimeSlotWithOccupier> timeSlotWithOccupiers = generateTimeSlotWithOcc(poolWithLifeguard);
            int result = calculateTheMaximumTime(timeSlotWithOccupiers, poolWithLifeguard);
            String outputFilename = fileItem.getName().replace('.', '-') + ".out";
            writeResultToOutput("./output/"+outputFilename, 0);
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

        allTimeSlot.sort((a1, a2) -> {
            return a1.pointTime - a2.pointTime;
        });
        return new CompanyPool(allTimeSlot, totalLifeGuards);

    }

    public static List<TimeSlotWithOccupier> generateTimeSlotWithOcc(CompanyPool poolWithLifeguard) {
        List<TimeSlotWithOccupier> timeSlotWithOccupiers = new ArrayList<>();
        List<String> currentOccupyers = new ArrayList<>();
        TimeSlot prevTime = null;
        for(TimeSlot timeSlot: poolWithLifeguard.allTimeSlot) {
            if(prevTime != null) {
                int timeLength = timeSlot.pointTime - prevTime.pointTime;
                if(timeLength != 0) {
                    String[] allOcc = currentOccupyers.toArray(new String[0]);
                    timeSlotWithOccupiers.add(new TimeSlotWithOccupier(allOcc, timeLength, prevTime.pointTime, timeSlot.pointTime));
                }
            }

            if(timeSlot.isStartTime) {
                currentOccupyers.add(timeSlot.lifeguardName + "");
            } else {
                currentOccupyers.remove(timeSlot.lifeguardName+"");
            }
            prevTime = timeSlot;
        }
        return timeSlotWithOccupiers;
    }

    public static int calculateTheMaximumTime(List<TimeSlotWithOccupier> timeSlotWithOccupiers, CompanyPool companyPool) {
        int allOccupiedTime = 0;
        for(TimeSlotWithOccupier timeSlotWithOccupier : timeSlotWithOccupiers) {
            allOccupiedTime += timeSlotWithOccupier.timeLength;
            if(timeSlotWithOccupier.occupyers.length == 1) {
                int occId = Integer.parseInt(timeSlotWithOccupier.occupyers[0]);
                companyPool.lifeguardTimeOccupying[occId - 1] += timeSlotWithOccupier.timeLength;
            }
        }

        int theLowestLifeguardTimeOccupying = Arrays.stream(companyPool.lifeguardTimeOccupying).min().getAsInt();
        return allOccupiedTime - theLowestLifeguardTimeOccupying;

    }




    public static void writeResultToOutput(String filePathAndName, int result) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filePathAndName, "UTF-8");
        writer.println(result);
        writer.close();
    }

}
