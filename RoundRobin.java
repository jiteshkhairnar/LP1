import java.util.*;

class RoundRobin {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int bt[] = new int[n], wt[] = new int[n], tat[] = new int[n];
        int rem_bt[] = new int[n];
        int tq, total = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rem_bt[i] = bt[i];
        }

        System.out.print("Enter time quantum: ");
        tq = sc.nextInt();

        int t = 0;
        while (true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > tq) {
                        t += tq;
                        rem_bt[i] -= tq;
                    } else {
                        t += rem_bt[i];
                        wt[i] = t - bt[i];
                        rem_bt[i] = 0;
                    }
                }
            }
            if (done) break;
        }

        for (int i = 0; i < n; i++) tat[i] = bt[i] + wt[i];
        float avgwt = 0, avgtat = 0;
        System.out.println("\nProcess\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += tat[i];
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
        System.out.println("\nAverage WT: " + avgwt / n);
        System.out.println("Average TAT: " + avgtat / n);
    }
}