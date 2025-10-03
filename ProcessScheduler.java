import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads process data from a file and creates a list of {@code Process} objects
 * for CPU scheduling simulations.
 */
public class ProcessScheduler {

    /**
     * Loads process information from a text file.
     *
     * Expected file format (space-separated, with a header on the first line):
     * <pre>
     * PID  ArrivalTime  BurstTime  Priority
     * 1    0            5          2
     * 2    2            3          1
     * ...
     * </pre>
     *
     * @param filename the path to the file containing process data
     * @return a list of {@code Process} objects created from the file content
     */
    public static List<Process> readProcesses(String filename) {
        List<Process> processes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by one or more whitespace characters
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length < 4) continue; // skip malformed lines

                int pid         = Integer.parseInt(tokens[0]); // Process ID
                int arrivalTime = Integer.parseInt(tokens[1]); // Arrival time
                int burstTime   = Integer.parseInt(tokens[2]); // CPU burst time
                int priority    = Integer.parseInt(tokens[3]); // Priority

                processes.add(new Process(pid, arrivalTime, burstTime, priority));
            }

        } catch (IOException e) {
            System.err.println("Failed to read file '" + filename + "': " + e.getMessage());
        }

        return processes;
    }
}
