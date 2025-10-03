/**
 * Represents a single process in a CPU scheduling simulation.
 * 
 * Each process has:
 * - a unique process ID (pid)
 * - an arrival time (when it enters the ready queue)
 * - a burst time (total CPU time required)
 * - a priority (lower number = higher priority)
 * - a remaining time (for algorithms that preempt or time-slice)
 */
public class Process {

    /** Unique identifier for the process. */
    int pid;

    /** Time at which the process arrives in the ready queue. */
    int arrivalTime;

    /** Total execution time required by the process. */
    int burstTime;

    /** Process priority (lower value = higher priority). */
    int priority;

    /** Remaining execution time (used by Round Robin / preemptive scheduling). */
    int remainingTime;

    /**
     * Creates a new Process with the given attributes.
     *
     * @param pid         unique process ID
     * @param arrivalTime time the process becomes ready
     * @param burstTime   total CPU time needed
     * @param priority    priority level (lower value = higher priority)
     */
    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime; // start with full burst remaining
    }
}
