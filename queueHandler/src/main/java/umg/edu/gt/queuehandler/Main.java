package umg.edu.gt.queuehandler;

import umg.edu.gt.queue.Queue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Queue<Song> highPriority = new Queue<>();
        Queue<Song> normalPriority = new Queue<>();
        
        Song s1 = new Song("Shoot to Thrill", "AC/DC", 8, 1);
        Song s2 = new Song("Enter Sandman", "Metallica", 12, 2);
        Song s3 = new Song("Highway to Hell", "AC/DC", 6, 1);
        Song s4 = new Song("Come As You Are", "Nirvana", 10, 2);

        addSong(highPriority, normalPriority, s1);
        addSong(highPriority, normalPriority, s2);
        addSong(highPriority, normalPriority, s3);	
        addSong(highPriority, normalPriority, s4);

        System.out.println("[LOG] Starting playlist...");

        while (!highPriority.isEmpty() || !normalPriority.isEmpty()) {

            Song current;

            if (!highPriority.isEmpty()) {
                current = highPriority.dequeue();
            } else {
                current = normalPriority.dequeue();
            }

            playSong(current);
        }

        System.out.println("[LOG] Playlist finished.");
    }

    private static void addSong(Queue<Song> high, Queue<Song> normal, Song song) {
        if (song.getPriority() == 1) {
            high.enqueue(song);
        } else {
            normal.enqueue(song);
        }
    }

    private static void playSong(Song song) throws InterruptedException {

        System.out.println("[LOG] Now playing: " + song.getTitle()
                + " - " + song.getArtist()
                + " (" + song.getDuration() + "s)");

        boolean skipped = false;

        for (int i = 1; i <= song.getDuration(); i++) {

            int totalBars = 10;
            int progress = (i * totalBars) / song.getDuration();

            StringBuilder bar = new StringBuilder("[");
            for (int j = 0; j < totalBars; j++) {
                if (j < progress) {
                    bar.append("#");
                } else {
                    bar.append("-");
                }
            }
            bar.append("]");

            System.out.println("[LOG] Playing: "
                    + song.getTitle()
                    + " " + bar
                    + " " + i + "s / "
                    + song.getDuration() + "s");

            Thread.sleep(1000);

            try {
                if (System.in.available() > 0) {
                    while (System.in.available() > 0) {
                        System.in.read(); // limpiar completamente buffer
                    }
                    skipped = true;
                    break;
                }
            } catch (Exception ignored) {}
        }

        if (skipped) {
            System.out.println("[LOG] Song skipped: " + song.getTitle());
        } else {
            System.out.println("[LOG] Finished: " + song.getTitle());
        }
    }
}