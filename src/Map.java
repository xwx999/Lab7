
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
    //清空地图
    public void clearMap(){
        for (int i = 0; i < Length; i++)
            for (int j = 0; j < Width; j++)
                map[i][j].isLiving=0;
    }

    //获取细胞的邻居数量
    public int getNeighbors(int m, int n) {
        int sum = 0;
        //左上角
        if(m==0&&n==0) {
            sum+= map[1][0].isLiving;
            sum+= map[0][1].isLiving;
            sum+= map[1][1].isLiving;
        }
        //右上角
        else if(m==0&&n==Length-1) {
            sum+= map[0][Length-2].isLiving;
            sum+= map[1][Length-2].isLiving;
            sum+= map[1][Length-1].isLiving;
        }
        //左下角
        else if(m==Width-1&&n==0) {
            sum+= map[Width-1][1].isLiving;
            sum+= map[Width-2][0].isLiving;
            sum+= map[Width-2][1].isLiving;
        }
        //右下角
        else if(m==Width-1 && n==Length-1) {
            sum+= map[Width-2][Length-2].isLiving;
            sum+= map[Width-1][Length-2].isLiving;
            sum+= map[Width-2][Length-1].isLiving;
        }
        //第一行
        else if(m==0) {
            sum+= map[0][n-1].isLiving;
            sum+= map[0][n+1].isLiving;
            sum+= map[1][n-1].isLiving;
            sum+= map[1][n].isLiving;
            sum+= map[1][n+1].isLiving;
        }
        //最后一行
        else if(m==Width-1) {
            sum+= map[Width-1][n-1].isLiving;
            sum+= map[Width-1][n+1].isLiving;
            sum+= map[Width-2][n-1].isLiving;
            sum+= map[Width-2][n].isLiving;
            sum+= map[Width-2][n+1].isLiving;
        }
        //第一列
        else if(n==0) {
            sum+= map[m+1][0].isLiving;
            sum+= map[m-1][0].isLiving;
            sum+= map[m-1][1].isLiving;
            sum+= map[m][1].isLiving;
            sum+= map[m+1][1].isLiving;
        }
        //最后一列
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
                    //跳过自己
                    if (i == m&&j == n){
                        continue;
                    }
                    sum += map[i][j].isLiving; //计算邻居数
                }
        }
        return sum;
    }
    //更新
    public Cell[][] update() {
        Cell[][] newMap = new Cell[Length][Width];
        for (int i = 0; i < Length; i++)
            for (int j = 0; j < Width; j++) {
                Cell cell = new Cell(0);
                switch (getNeighbors(i, j)) {
                    case 2:
                        cell.isLiving = map[i][j].isLiving; //细胞状态保持不变
                        break;
                    case 3:
                        cell.isLiving = 1; // 细胞活着为1
                        break;
                    default:
                        cell.isLiving = 0; // 细胞死了为0
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

