package observable;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jerry Salonen
 */
public class ClockTimer extends Observable implements Runnable {
    private int hour = 0, minute = 0, second = 0;

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void run() {
        TimerTask tick = new TimerTask() {
            public void run() {
                second ++;
                if (second == 60) {
                    second = 0;
                    minute++;
                }
                if (minute == 60 && second == 0) {
                    minute = 0;
                    hour ++;
                }
                if (hour == 24) {
                    hour = 0;
                }
                String response = hour + ":" + minute + ":" + second;
                setChanged();
                notifyObservers(response);
            }
        };
        Timer timer = new Timer("Timer");

        long delay  = 1000L;
        long period = 1000L;
        timer.scheduleAtFixedRate(tick, delay, period);
    }


}
