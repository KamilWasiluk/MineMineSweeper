package GUI;

public class Timer {
    private static long startTime;
    private static int seconds;
    private static int minutes;

    private Timer() {}

    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public static int getCurrentSeconds() {
        return seconds = (int) ((System.currentTimeMillis() - startTime) / 1000) % 60;
    }

    public static int getCurrentMinutes() {
        return minutes = (int) ((System.currentTimeMillis() - startTime) / 1000) / 60;
    }

    
}
