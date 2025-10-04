import java.util.*;

/**
 * Implements non-preemptive Priority Scheduling.
 * - Each process has a priority value; lower numbers mean higher priority.
 * - Once a process starts running, it finishes before the next one begins.
 */
public class PriorityScheduling {

    /**
     * Schedules processes by priority and prints:
     * - Waiting Time (WT) and Turnaround Time (TAT) per process.
     * - A Gantt chart showing execution order.
     * - Average WT and TAT.
     *
     * @param processes list of processes to schedule
     */
    public static void schedule(List<Process> processes) {
        if (processes == null || processes.isEmpty()) {
            System.out.println("\nPriority Scheduling: No processes to schedule.");
            return;
        }

        // Sort by priority (lower number = higher priority), then by arrival time if needed
        processes.sort(
            Comparator.comparingInt((Process p) -> p.priority)
                      .thenComparingInt(p -> p.arrivalTime)
        );

        int currentTime = 0;
        long totalWT = 0, totalTAT = 0;

        System.out.println("\nPriority Scheduling Gantt Chart:");

        // For Gantt chart
        List<String> executionOrder = new ArrayList<>();
        List<Integer> timestamps = new ArrayList<>();
        timestamps.add(currentTime); // start timeline at 0

        // Process tasks one by one
        for (Process p : processes) {
            // If CPU is idle until this process arrives, jump forward in time
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            int waitingTime = currentTime - p.arrivalTime;
            int turnaroundTime = waitingTime + p.burstTime;

            executionOrder.add("P" + p.pid);
            currentTime += p.burstTime;
            timestamps.add(currentTime);

            totalWT += waitingTime;
            totalTAT += turnaroundTime;

            System.out.println("P" + p.pid + " -> WT: " + waitingTime + ", TAT: " + turnaroundTime);
        }

        // Render Gantt chart
        GanttChart.display(executionOrder, timestamps);

        // Print averages
        int n = processes.size();
        System.out.println("Average WT: " + (double) totalWT / n);
        System.out.println("Average TAT: " + (double) totalTAT / n);
    }
}
