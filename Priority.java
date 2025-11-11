import java.util.*;

class Priority {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int bt[] = new int[n], at[] = new int[n], pr[] = new int[n];
        int wt[] = new int[n], tat[] = new int[n], ct[] = new int[n];
        int pid[] = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter AT, BT, and Priority for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
        }

        // Sort by arrival then priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1] || (at[j] == at[j + 1] && pr[j] > pr[j + 1])) {
                    int temp = at[j]; at[j] = at[j + 1]; at[j + 1] = temp;
                    temp = bt[j]; bt[j] = bt[j + 1]; bt[j + 1] = temp;
                    temp = pr[j]; pr[j] = pr[j + 1]; pr[j + 1] = temp;
                    temp = pid[j]; pid[j] = pid[j + 1]; pid[j + 1] = temp;
                }
            }
        }

        ct[0] = at[0] + bt[0];
        tat[0] = ct[0] - at[0];
        wt[0] = tat[0] - bt[0];
        for (int i = 1; i < n; i++) {
            ct[i] = ct[i - 1] + bt[i];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        float avgwt = 0, avgtat = 0;
        System.out.println("\nProcess\tAT\tBT\tPR\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += tat[i];
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
        System.out.println("\nAverage WT: " + avgwt / n);
        System.out.println("Average TAT: " + avgtat / n);
    }
}