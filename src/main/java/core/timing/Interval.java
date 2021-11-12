package core.timing;

import java.util.function.Consumer;

public class Interval {
    private double period;
    private double accTime;

    public Interval(double period) {
        this.period = period;
        this.resetTime();
    }

    public void resetTime() {
        this.accTime = 0d;
    }

    public boolean addTime(double dt) {
        this.accTime += dt;
        return this.accTime >= this.period;
    }

    public void tick(double dt, Consumer<Double> runner) {
        if(this.addTime(dt)) {
            runner.accept(this.accTime);
            this.resetTime();
        }
    }

    public double getAccumulatedTime() {
        return accTime;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period, boolean resetTime) {
        if(resetTime) {this.resetTime();}
        this.period = period;
    }

    public void setPeriod(double period) {
        this.setPeriod(period, true);
    }
}
