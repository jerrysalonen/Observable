package observable;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Jerry Salonen
 */
public class DigitalClock extends ClockTimer {
    ClockTimer timer;
    public DigitalClock(ClockTimer ct) {
        timer = ct;
    }

    public void startClock(Observable o) {
        if (o == timer) {
            timer.addObserver(new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    draw(arg);
                }
            });
            new Thread(timer).start();
        }
    }

    private void draw(Object arg) {
        System.out.println(arg);
    }
}
