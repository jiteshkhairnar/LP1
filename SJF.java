import java.util.*;

class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int at[] = new int[n], bt[] = new int[n], rt[] = new int[n], wt[] = new int[n], tat[] = new int[n];
        int complete = 0, time = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival and burst time for P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        while (complete != n) {
            int min = Integer.MAX_VALUE, shortest = -1;
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && rt[i] < min) {
                    min = rt[i];
                    shortest = i;
                    found = true;
                }
            }
            if (!found) { time++; continue; }
            rt[shortest]--;
            if (rt[shortest] == 0) {
                complete++;
                int finish = time + 1;
                wt[shortest] = finish - bt[shortest] - at[shortest];
                if (wt[shortest] < 0) wt[shortest] = 0;
            }
            time++;
        }

        float avgwt = 0, avgtat = 0;
        System.out.println("\nProcess\tAT\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
            avgwt += wt[i];
            avgtat += tat[i];
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
        System.out.println("\nAverage WT: " + avgwt / n);
        System.out.println("Average TAT: " + avgtat / n);
    }
}