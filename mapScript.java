import java.util.Scanner;
import java.io.*;

public class mapScript {

    public int[][] txtToMapArray(String fileName) throws IOException
    {
        File mapFile = new File("map\\" + fileName + ".txt");
        Scanner sc = new Scanner(mapFile);
        int[][] map = new int[10][6];
        String rawMapRow = "";
        for (int y = 0; y < 6; y++)
        {
            rawMapRow = sc.nextLine();
            for (int x = 0; x < 10; x++)
            {
                map[x][y] = Integer.parseInt(rawMapRow.substring(x, x + 1));
            }
        }
        sc.close();
        return map;
    }

    public int[][] txtToMapTiming(String fileName) throws IOException
    {
        File timingFile = new File("map\\" + fileName + ".txt");
        Scanner sc = new Scanner(timingFile);
        int numEnemies = Integer.parseInt(sc.nextLine());
        int[][] timing = new int[numEnemies][2];
        String line = "";
        for (int i = 0; i < numEnemies; i++)
        {
            line = sc.nextLine();
            timing[i][0] = Integer.parseInt(line.substring(0, 1));
            timing[i][1] = Integer.parseInt(line.substring(1));
        }
        sc.close();
        return timing;
    }
    
    public int getNumOfEnemies(String fileName) throws IOException
    {
        File timingFile = new File("map\\" + fileName + ".txt");
        Scanner sc = new Scanner(timingFile);
        int numEnemies = Integer.parseInt(sc.nextLine());
        sc.close();
        return(numEnemies);
    }
    public int[] getEnemySpawn(String fileName) throws IOException
    {
        int map[][] = txtToMapArray(fileName);
        int spawn[] = new int[2];
        for (int y = 0; y < 6; y++)
        {
            for (int x = 0; x < 10; x++)
            {
                if (map[x][y] == 4)
                {
                    spawn[0] = x;
                    spawn[1] = y;
                    return spawn;    
                }
            }
        }
        return spawn;
    }
}
