import java.util.Scanner;

public class PriorityScheduler {
    // Inner class to hold process details and scheduling metrics
    static class Proc {
        int pid;         // Process ID (1..n)
        int at;          // Arrival Time
        int bt;          // Burst Time (initial)
        int priority;    // Priority (lower value = higher priority)
        int ct = 0;      // Completion Time
        int tat = 0;     // Turnaround Time
        int wt = 0;      // Waiting Time
        boolean isCompleted = false;

        Proc(int pid, int at, int bt, int priority) {
            this.pid = pid;
            this.at = at;
            this.bt = bt;
            this.priority = priority;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Proc[] procs = new Proc[n];
        System.out.println("Enter arrival time, burst time and priority for each process:");
        for (int i = 0; i < n; i++) {
            int pid = i + 1;
            System.out.println("Process P[" + pid + "]:");
            System.out.print(" Arrival time: ");
            int at = sc.nextInt();
            System.out.print(" Burst time: ");
            int bt = sc.nextInt();
            System.out.print(" Priority (lower number = higher priority): ");
            int pr = sc.nextInt();
            procs[i] = new Proc(pid, at, bt, pr);
        }

        schedule(n, procs);
        sc.close();
    }

    public static void schedule(int n, Proc[] procs) {
        int currentTime = 0;
        int completedCount = 0;
        double totalWT = 0, totalTAT = 0;

        // --- Core Scheduling Logic: Non-Preemptive Dynamic Selection ---

        while (completedCount < n) {
            int highestPriority = Integer.MAX_VALUE;
            int selectedIndex = -1;

            // 1. Search for the highest priority process that has arrived
            for (int i = 0; i < n; i++) {
                if (!procs[i].isCompleted && procs[i].at <= currentTime) {
                    if (procs[i].priority < highestPriority) {
                        highestPriority = procs[i].priority;
                        selectedIndex = i;
                    } 
                    // FCFS tie-breaker for same priority
                    else if (procs[i].priority == highestPriority && selectedIndex != -1) {
                         // Tie-breaker: use earlier Arrival Time
                         if (procs[i].at < procs[selectedIndex].at) {
                            selectedIndex = i;
                        }
                    }
                }
            }

            if (selectedIndex != -1) {
                Proc p = procs[selectedIndex];
                
                // 2. Execute the selected process (Non-Preemptive: run to completion)
                currentTime += p.bt;
                
                // 3. Calculate metrics
                p.ct = currentTime;
                p.tat = p.ct - p.at;
                p.wt = p.tat - p.bt;
                
                p.isCompleted = true;
                completedCount++;

            } else {
                // 4. CPU is Idle: Advance time to the next unit until a process arrives
                currentTime++;
            }
        }
        
        // --- Output Metrics ---
        
        System.out.println("\n--- Non-Preemptive Priority Scheduling Results ---");
        System.out.println("P_ID\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (Proc p : procs) {
            totalWT += p.wt;
            totalTAT += p.tat;
            System.out.printf("P[%d]\t%d\t%d\t%d\t%d\t%d\t%d%n",
                    p.pid, p.at, p.bt, p.priority, p.ct, p.tat, p.wt);
        }

        System.out.printf("%nAverage Waiting Time: %.2f%n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f%n", totalTAT / n);
    }
}