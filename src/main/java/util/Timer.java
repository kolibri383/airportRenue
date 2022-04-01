package util;

public class Timer {
    private long startTime;
    private long stopTime;
    private boolean isRun = false;

    public Timer() {
    }

    public void start() {
        startTime = System.currentTimeMillis();
        isRun = true;
    }

    public void stop() {
        if (isRun)
            stopTime = System.currentTimeMillis();
    }

    public long getRangeTime() {
        if (isRun)
            return stopTime - startTime;
        return -1;
    }
}
