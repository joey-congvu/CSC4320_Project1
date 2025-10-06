import java.util.*;

/**
 * Provides two classic page replacement algorithms:
 * 1) FIFO – First-In-First-Out: evicts the oldest page in memory
 * 2) LRU  – Least Recently Used: evicts the least recently accessed page
 *
 * Both simulate a fixed-size frame buffer and print the page table after each request.
 */
public class PageReplacement {

    /**
     * FIFO (First-In-First-Out) page replacement.
     * Keeps a simple queue of pages; oldest page is evicted when a fault occurs and memory is full.
     *
     * @param pages  sequence of page requests
     * @param frames number of available frames
     */
    public static void fifo(int[] pages, int frames) {
        Queue<Integer> queue = new LinkedList<>(); // keeps insertion order
        Set<Integer> memory = new HashSet<>();     // quick lookup for current pages
        int pageFaults = 0;

        System.out.println("\nFIFO Page Replacement:");

        for (int page : pages) {
            if (!memory.contains(page)) { // page fault
                if (queue.size() >= frames) {
                    int removed = queue.poll();    // remove oldest
                    memory.remove(removed);
                }
                queue.add(page);
                memory.add(page);
                pageFaults++;
            }
            System.out.println("Page Table: " + queue);
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }

    /**
     * LRU (Least Recently Used) page replacement.
     * Uses a LinkedHashMap in access-order mode to keep track of recency.
     *
     * @param pages  sequence of page requests
     * @param frames number of available frames
     */
    public static void lru(int[] pages, int frames) {
        // LinkedHashMap(true) keeps entries ordered by access
        LinkedHashMap<Integer, Integer> memory = new LinkedHashMap<>(frames, 0.75f, true);
        int pageFaults = 0;

        System.out.println("\nLRU Page Replacement:");

        for (int page : pages) {
            if (!memory.containsKey(page)) { // page fault
                if (memory.size() >= frames) {
                    // remove the least recently used (the first key in access order)
                    Integer lruPage = memory.keySet().iterator().next();
                    memory.remove(lruPage);
                }
                pageFaults++;
            }
            memory.put(page, 1); // insert or mark as most recently used
            System.out.println("Page Table: " + memory.keySet());
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }
}
