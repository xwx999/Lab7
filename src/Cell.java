
public class Cell {
    int isLiving;//是否存活,1为活，0为死

    public Cell(int isLiving) {
        this.isLiving = isLiving;
    }

    public int getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(int isLiving) {
        this.isLiving = isLiving;
    }
    @Override
    public String toString() {
        return "Cell{" +
                "isLiving=" + isLiving +
                '}';
    }
}