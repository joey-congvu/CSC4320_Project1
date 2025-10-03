import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * First-Come, First-Served (FCFS) scheduler.
 * Assumes each Process has: pid, arrivalTime, burstTime (and optionally priority).
 */
public class FCFS {

    /**
     * Runs FCFS on the given processes, prints per-process WT/TAT, a Gantt chart, and averages.
     *
     * Side effects:
     * - Sorts the provided list by arrival time (stable for equal arrivals).
     * - Prints results to stdout and calls GanttChart.display(executionOrder, timestamps).
     */
    public static void schedule(List<Process> processes) {
        if (processes == null || processes.isEmpty()) {
            System.out.println("No processes to schedule.");
            return;
        }

        // 1) Sort by arrival time (then PID for determinism if arrivals tie).
        processes.sort(
            Comparator.comparingInt((Process p) -> p.arrivalTime)
                      .thenComparingInt(p -> p.pid)
        );

        // 2) Accumulators & timeline
        int time = 0;          // simulated current time
        long totalWT = 0L;     // sum of waiting times
        long totalTAT = 0L;    // sum of turnaround times

        List<String> executionOrder = new ArrayList<>(); // labels for Gantt bars ("P1", "P2", ...)
        List<Integer> timestamps    = new ArrayList<>(); // boundaries for each bar; size = order.size() + 1
        timestamps.add(time);                             // timeline starts at t=0

        System.out.println("\nFCFS Gantt Chart:");
        for (Process p : processes) {

            // If CPU is idle until the next process arrives, advance time to its arrival.
            if (time < p.arrivalTime) {
                time = p.arrivalTime;
                // (We don't render an explicit "IDLE" segment here to keep the original output shape.)
            }

            // Waiting time: how long the process waited in the ready queue.
            int waitingTime = time - p.arrivalTime;

            // Turnaround time: waiting + service time.
            int turnaroundTime = waitingTime + p.burstTime;

            // Record execution for the Gantt chart.
            executionOrder.add("P" + p.pid);

            // Advance time by the burst.
            time += p.burstTime;

            // Mark the end of this slice in the timeline.
            timestamps.add(time);

            // Accumulate metrics.
            totalWT  += waitingTime;
            totalTAT += turnaroundTime;

            // Per-process report.
            System.out.println("P" + p.pid + " -> WT: " + waitingTime + ", TAT: " + turnaroundTime);
        }

        // 3) Render Gantt chart and averages
        GanttChart.display(executionOrder, timestamps);

        int n = processes.size();
        System.out.println("Average WT: "  + (totalWT  / (double) n));
        System.out.println("Average TAT: " + (totalTAT / (double) n));
    }
}
