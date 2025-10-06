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

         // 6) Memory allocation demos
        int[] memoryBlocks = {100, 500, 200, 300, 600}; // KB
        int[] processSizes = {212, 417, 112, 426};      // KB

        MemoryManagement.firstFit(memoryBlocks.clone(), processSizes); // clone to preserve original blocks
        MemoryManagement.bestFit(memoryBlocks.clone(), processSizes);
        MemoryManagement.worstFit(memoryBlocks.clone(), processSizes);

        // 7) Page replacement demos
        int[] pages  = {1, 3, 0, 3, 5, 6, 3};
        int   frames = 3;

        PageReplacement.fifo(pages, frames);
        PageReplacement.lru(pages, frames);
    }
}
