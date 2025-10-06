import java.util.*;

/**
 * Implements three classic contiguous memory allocation strategies:
 * 1) First-Fit   – take the first block that fits
 * 2) Best-Fit    – take the smallest block that fits
 * 3) Worst-Fit   – take the largest block that fits
 */
public class MemoryManagement {

    /**
     * First-Fit: scan blocks from left to right, assign the first large-enough block.
     */
    public static void firstFit(int[] memoryBlocks, int[] processSizes) {
        System.out.println("\nFirst-Fit Memory Allocation:");

        int[] allocatedBlocks = new int[processSizes.length];
        Arrays.fill(allocatedBlocks, -1);

        for (int i = 0; i < processSizes.length; i++) {
            // find first block j where memoryBlocks[j] >= processSizes[i]
            int chosen = -1;
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    chosen = j;
                    break; // first fit ⇒ stop at the first match
                }
            }
            if (chosen != -1) {
                allocatedBlocks[i] = chosen;
                memoryBlocks[chosen] -= processSizes[i]; // consume capacity
            }
        }

        printAllocation(processSizes, allocatedBlocks);
    }

    /**
     * Best-Fit: pick the smallest block that is still large enough.
     */
    public static void bestFit(int[] memoryBlocks, int[] processSizes) {
        System.out.println("\nBest-Fit Memory Allocation:");

        int[] allocatedBlocks = new int[processSizes.length];
        Arrays.fill(allocatedBlocks, -1);

        for (int i = 0; i < processSizes.length; i++) {
            int bestIdx = -1;

            // track the smallest feasible block
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    if (bestIdx == -1 || memoryBlocks[j] < memoryBlocks[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocatedBlocks[i] = bestIdx;
                memoryBlocks[bestIdx] -= processSizes[i];
            }
        }

        printAllocation(processSizes, allocatedBlocks);
    }

    /**
     * Worst-Fit: pick the largest block among those that are large enough.
     */
    public static void worstFit(int[] memoryBlocks, int[] processSizes) {
        System.out.println("\nWorst-Fit Memory Allocation:");

        int[] allocatedBlocks = new int[processSizes.length];
        Arrays.fill(allocatedBlocks, -1);

        for (int i = 0; i < processSizes.length; i++) {
            int worstIdx = -1;

            // track the largest feasible block
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    if (worstIdx == -1 || memoryBlocks[j] > memoryBlocks[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }

            if (worstIdx != -1) {
                allocatedBlocks[i] = worstIdx;
                memoryBlocks[worstIdx] -= processSizes[i];
            }
        }

        printAllocation(processSizes, allocatedBlocks);
    }

    /**
     * Prints exactly the same table shape and wording as your original:
     *   Process No. | Process Size | Block No. (or "Not Allocated")
     */
    private static void printAllocation(int[] processSizes, int[] allocatedBlocks) {
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processSizes.length; i++) {
            System.out.println(
                (i + 1) + "\t\t" + processSizes[i] + "\t\t" +
                (allocatedBlocks[i] != -1 ? (allocatedBlocks[i] + 1) : "Not Allocated")
            );
        }
    }
}
