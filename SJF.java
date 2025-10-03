import java.util.*;

/**
 * The SJF class implements the Shortest Job First (SJF) CPU scheduling algorithm.
 * It schedules processes by executing the process with the shortest burst time first.
 * It prints the execution order along with calculating the waiting time (WT) and turnaround time (TAT) for each process.
 */
public class SJF {

    /**
     * This method implements the Shortest Job First (SJF) scheduling algorithm.
     * It calculates the waiting time (WT) and turnaround time (TAT) for each process.
     * The processes are sorted based on their burst time (shortest first).
     * 
     * @param processes List of Process objects to be scheduled.
     */
    public static void schedule(List<Process> processes) {
        // Sort the processes based on burst time in ascending order (Shortest Job First)
        processes.sort(Comparator.comparingInt(p -> p.burstTime));

        // Time tracker, total Waiting Time and Turnaround Time
        int time = 0, totalWT = 0, totalTAT = 0;
        
        // To store the execution order and timestamps for the Gantt chart
        System.out.println("\nSJF Gantt Chart:");
        
        // List to store the execution order (e.g., P1, P2, P3) and timestamps (time at each process completion)
        List<String> executionOrder = new ArrayList<>();
        List<Integer> timestamps = new ArrayList<>();
        timestamps.add(time); // Add the starting time (0) to the timestamps list

        // Iterate through the sorted processes (sorted by burst time)
        for (Process p : processes) {
            // If the current time is less than the process arrival time, set time to the arrival time
            if (time < p.arrivalTime) time = p.arrivalTime;

            // Calculate waiting time (time - arrival time)
            int waitingTime = time - p.arrivalTime;
            
            // Calculate turnaround time (waiting time + burst time)
            int turnaroundTime = waitingTime + p.burstTime;

            // Add process ID to the execution order list (Gantt chart)
            executionOrder.add("P" + p.pid);
            
            // Update the current time by adding the burst time of the process
            time += p.burstTime;
            
            // Add the current time to the timestamps list
            timestamps.add(time);

            // Accumulate the total waiting time and turnaround time
            totalWT += waitingTime;
            totalTAT += turnaroundTime;

            // Print the waiting time and turnaround time for the current process
            System.out.println("P" + p.pid + " -> WT: " + waitingTime + ", TAT: " + turnaroundTime);
        }

        // Display the Gantt chart with the execution order and timestamps
        GanttChart.display(executionOrder, timestamps);

        // Print the average waiting time and average turnaround time
        System.out.println("Average WT: " + (double) totalWT / processes.size());
        System.out.println("Average TAT: " + (double) totalTAT / processes.size());
    }
}
