
public class Cell {
    int isLiving;//�Ƿ���,1Ϊ�0Ϊ��

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