import java.util.*;

/**
 * Implements the Round Robin CPU scheduling algorithm.
 * Each process gets a fixed time slice (quantum) in cyclic order.
 */
public class RoundRobin {
    /**
     * Runs Round Robin scheduling and prints:
     * - The Gantt chart (execution timeline).
     * - Waiting Time (WT) and Turnaround Time (TAT) per process.
     * - Average WT and TAT.
     *
     * @param processes list of processes to schedule
     * @param quantum   fixed CPU time slice for each process
     */
    public static void schedule(List<Process> processes, int quantum) {
        if (processes == null || processes.isEmpty()) {
            System.out.println("\nRound Robin (Quantum = " + quantum + "): No processes to schedule.");
            return;
        }

        // Ready queue (initially contains all processes in the given order)
        Queue<Process> readyQueue = new LinkedList<>(processes);

        int currentTime = 0;
        long totalWT = 0, totalTAT = 0;

        // Tracks when each process finishes
        Map<Integer, Integer> completionTime = new HashMap<>();

        // For Gantt chart visualization
        List<String> executionOrder = new ArrayList<>();
        List<Integer> timestamps = new ArrayList<>();
        timestamps.add(currentTime);

        System.out.println("\nRound Robin (Quantum = " + quantum + ") Gantt Chart:");

        // Main Round Robin loop
        while (!readyQueue.isEmpty()) {
            Process p = readyQueue.poll();

            // Time to run in this slice
            int runTime = Math.min(p.remainingTime, quantum);

            executionOrder.add("P" + p.pid);
            currentTime += runTime;
            timestamps.add(currentTime);
            p.remainingTime -= runTime;

            if (p.remainingTime > 0) {
                // Still has work — put it back at the end of the queue
                readyQueue.add(p);
            } else {
                // Done — remember its completion time
                completionTime.put(p.pid, currentTime);
            }
        }

        // Compute and print per-process metrics
        for (Process p : processes) {
            int tat = completionTime.get(p.pid) - p.arrivalTime; // Turnaround time
            int wt = tat - p.burstTime;                          // Waiting time

            totalWT += wt;
            totalTAT += tat;

            System.out.println("P" + p.pid + " -> WT: " + wt + ", TAT: " + tat);
        }

        // Render Gantt chart
        GanttChart.display(executionOrder, timestamps);

        // Print averages
        int n = processes.size();
        System.out.println("Average WT: " + (double) totalWT / n);
        System.out.println("Average TAT: " + (double) totalTAT / n);
    }
}
