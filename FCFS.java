import java.util.*;

class FCFS {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int bt[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int p[] = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
            System.out.print("Enter burst time for P" + p[i] + ": ");
            bt[i] = sc.nextInt();
        }

        wt[0] = 0;
        for (int i = 1; i < n; i++) wt[i] = wt[i - 1] + bt[i - 1];
        for (int i = 0; i < n; i++) tat[i] = wt[i] + bt[i];

        float avgwt = 0, avgtat = 0;
        System.out.println("\nProcess\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += tat[i];
            System.out.println("P" + p[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
        System.out.println("\nAverage WT: " + avgwt / n);
        System.out.println("Average TAT: " + avgtat / n);
    }
}
