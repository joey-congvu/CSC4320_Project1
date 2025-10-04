import java.util.List;
import java.util.ArrayList;

/**
 * Entry point for the scheduling simulator.
 * Runs CPU schedulers (FCFS, SJF, RR, Priority), memory allocation (First/Best/Worst Fit),
 * and page replacement (FIFO, LRU) using the same input set.
 */
public class Main {

    /**
     * Orchestrates reading input, running algorithms, and printing results.
     *
     * @param args unused
     */
    public static void main(String[] args) {

        // 1) Load processes from file
        List<Process> processes = ProcessScheduler.readProcesses("processes.txt");

        // 2) FCFS
        System.out.println("\nFirst-Come, First-Served (FCFS)");
        FCFS.schedule(new ArrayList<>(processes)); // pass a copy to avoid mutating original list

        // 3) SJF
        System.out.println("\nShortest Job First (SJF)");
        SJF.schedule(new ArrayList<>(processes));

        // 4) Round Robin (quantum = 2)
        System.out.println("\nRound Robin (Quantum = 2)");
        RoundRobin.schedule(new ArrayList<>(processes), 2);

        // 5) Priority (non-preemptive)
        System.out.println("\nPriority Scheduling");
        PriorityScheduling.schedule(new ArrayList<>(processes));
    }
}
