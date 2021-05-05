package model;

public class Count {
    private int count;
    private double count1;
    public Count(int count) {
        this.count = count;
    }

    public Count() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int increment(){
        count1 = count1 +0.5;
        count = (int)count1;
        return count++;
    }
}
