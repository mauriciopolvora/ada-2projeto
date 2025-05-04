public class Road {
    private int from;
    private int to;
    private int h;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getH() {return h;}

    public void setH(int h) {this.h = h;}

    public Road(int x, int y, int h) {
        this.from = x;
        this.to = y;
        this.h = h;
    }
}
