import java.io.*;

class LRU {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter number of frames: ");
        int f = Integer.parseInt(br.readLine());
        int frame[] = new int[f];
        for (int i = 0; i < f; i++) frame[i] = -1;

        System.out.print("Enter number of pages: ");
        int n = Integer.parseInt(br.readLine());
        int pages[] = new int[n];

        System.out.println("Enter page numbers:");
        for (int i = 0; i < n; i++) pages[i] = Integer.parseInt(br.readLine());

        int pageFaults = 0;

        // To store when each frame was last used
        int recent[] = new int[f];

        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean hit = false;

            // Check if page already in frame
            for (int j = 0; j < f; j++) {
                if (frame[j] == page) {
                    hit = true;
                    recent[j] = i; // update recent use
                    break;
                }
            }

            if (!hit) {
                // Find empty frame
                int pos = -1;
                for (int j = 0; j < f; j++) {
                    if (frame[j] == -1) {
                        pos = j;
                        break;
                    }
                }

                // If no empty frame, replace least recently used
                if (pos == -1) {
                    int min = recent[0];
                    pos = 0;
                    for (int j = 1; j < f; j++) {
                        if (recent[j] < min) {
                            min = recent[j];
                            pos = j;
                        }
                    }
                }

                frame[pos] = page;
                recent[pos] = i;
                pageFaults++;
            }

            System.out.print("Frames: ");
            for (int j = 0; j < f; j++)
                System.out.print((frame[j] == -1 ? "-" : frame[j]) + " ");
            System.out.println();
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }
}