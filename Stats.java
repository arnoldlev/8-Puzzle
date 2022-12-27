public class Stats {
    
    private int depth;
    private int h1Cost;
    private int h2Cost;
    private long h1Time;
    private long h2Time;

    public Stats(int depth, int h1Cost, int h2Cost, long h1Time, long h2Time) {
        this.depth = depth;
        this.h1Cost = h1Cost;
        this.h2Cost = h2Cost;
        this.h1Time = h1Time;
        this.h2Time = h2Time;
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int getH1Cost() {
        return h1Cost;
    }
    public void setH1Cost(int h1Cost) {
        this.h1Cost = h1Cost;
    }
    public int getH2Cost() {
        return h2Cost;
    }
    public void setH2Cost(int h2Cost) {
        this.h2Cost = h2Cost;
    }
    public long getH1Time() {
        return h1Time;
    }
    public void setH1Time(long h1Time) {
        this.h1Time = h1Time;
    }
    public long getH2Time() {
        return h2Time;
    }
    public void setH2Time(long h2Time) {
        this.h2Time = h2Time;
    }
}
