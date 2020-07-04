
public class Map {
    int Length;
    int Width;
    Cell[][] map;

    public Map(int Length, int Width) {
        this.Length = Length;
        this.Width = Width;
        map = new Cell[Length][Width];
        for (int i = 0; i < Length; i++) {
            for (int j = 0; j < Width; j++){
                Cell cell = new Cell(0);
                map[i][j] = cell;
            }

        }
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public Cell[][] getMap() {
        return map;
    }
    //��յ�ͼ
    public void clearMap(){
        for (int i = 0; i < Length; i++)
            for (int j = 0; j < Width; j++)
                map[i][j].isLiving=0;
    }

    //��ȡϸ�����ھ�����
    public int getNeighbors(int m, int n) {
        int sum = 0;
        //���Ͻ�
        if(m==0&&n==0) {
            sum+= map[1][0].isLiving;
            sum+= map[0][1].isLiving;
            sum+= map[1][1].isLiving;
        }
        //���Ͻ�
        else if(m==0&&n==Length-1) {
            sum+= map[0][Length-2].isLiving;
            sum+= map[1][Length-2].isLiving;
            sum+= map[1][Length-1].isLiving;
        }
        //���½�
        else if(m==Width-1&&n==0) {
            sum+= map[Width-1][1].isLiving;
            sum+= map[Width-2][0].isLiving;
            sum+= map[Width-2][1].isLiving;
        }
        //���½�
        else if(m==Width-1 && n==Length-1) {
            sum+= map[Width-2][Length-2].isLiving;
            sum+= map[Width-1][Length-2].isLiving;
            sum+= map[Width-2][Length-1].isLiving;
        }
        //��һ��
        else if(m==0) {
            sum+= map[0][n-1].isLiving;
            sum+= map[0][n+1].isLiving;
            sum+= map[1][n-1].isLiving;
            sum+= map[1][n].isLiving;
            sum+= map[1][n+1].isLiving;
        }
        //���һ��
        else if(m==Width-1) {
            sum+= map[Width-1][n-1].isLiving;
            sum+= map[Width-1][n+1].isLiving;
            sum+= map[Width-2][n-1].isLiving;
            sum+= map[Width-2][n].isLiving;
            sum+= map[Width-2][n+1].isLiving;
        }
        //��һ��
        else if(n==0) {
            sum+= map[m+1][0].isLiving;
            sum+= map[m-1][0].isLiving;
            sum+= map[m-1][1].isLiving;
            sum+= map[m][1].isLiving;
            sum+= map[m+1][1].isLiving;
        }
        //���һ��
        else if(n==Length-1) {
            sum+= map[m+1][Length-1].isLiving;
            sum+= map[m-1][Length-1].isLiving;
            sum+= map[m-1][Length-2].isLiving;
            sum+= map[m][Length-2].isLiving;
            sum+= map[m+1][Length-2].isLiving;

        }
        else {
            for (int i = m - 1; i <= m + 1; i++)
                for (int j = n - 1; j <= n + 1; j++) {
                    //�����Լ�
                    if (i == m&&j == n){
                        continue;
                    }
                    sum += map[i][j].isLiving; //�����ھ���
                }
        }
        return sum;
    }
    //����
    public Cell[][] update() {
        Cell[][] newMap = new Cell[Length][Width];
        for (int i = 0; i < Length; i++)
            for (int j = 0; j < Width; j++) {
                Cell cell = new Cell(0);
                switch (getNeighbors(i, j)) {
                    case 2:
                        cell.isLiving = map[i][j].isLiving; //ϸ��״̬���ֲ���
                        break;
                    case 3:
                        cell.isLiving = 1; // ϸ������Ϊ1
                        break;
                    default:
                        cell.isLiving = 0; // ϸ������Ϊ0
                        break;
                }
                newMap[i][j]=cell;
            }
      map=newMap;
      // map=newMap;
      
      /*
        for (int i = 0; i < Length; i++)
            for (int j = 0; j < Width; j++)
                map[i][j] = newMap[i][j];
		*/
      
        return newMap;
       
    }

}

