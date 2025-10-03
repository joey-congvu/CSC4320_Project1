import java.util.*; // Import Java utility classes

// Class responsible for showing a Gantt Chart for process scheduling
public class GanttChart {
    
    /**
     * Prints a simple Gantt Chart showing the order of process execution.
     *
     * @param executionOrder list of process IDs in the order they were executed (e.g., P1, P2, P3).
     * @param timestamps     list of time markers for each process start/end (size = order + 1).
     */
    public static void display(List<String> executionOrder, List<Integer> timestamps) {
        System.out.println("\nGantt Chart:"); // Chart header

        // Print the top row: process execution sequence as bars
        for (String p : executionOrder) {
            System.out.print("| " + p + " "); 
        }
        System.out.println("|"); // Close the last bar

        // Print the timeline row: timestamps under the chart
        for (int time : timestamps) {
            System.out.print(time + "    "); 
        }
        System.out.println("\n"); // Add a new line after the chart for spacing
    }
}
