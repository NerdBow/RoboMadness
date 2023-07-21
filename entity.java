import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
public class entity 
{
    public int health;
    public int[] position = new int[2];
    public int orientation = 0;
    public int gridPos[] = new int[2]; // 0 = x and 1 = y
    public int[][] range;
    public int[][] attackGrid;
    public int[] attackGridLength; // Formated in y, x (Y IS FIRST NOT X) using 0 index Lets the Y and X dimensions of the range
    public int[] attackGridEPos; // Same formating as attackGridLength Just the pos of the tower
    public int numAttackGrid;
    public int frame = 0;
    public boolean attacking = false;
    public BufferedImage sprite;
    public BufferedImage[] activeAnimation = new BufferedImage[30];


    public entity()
    {
    }

    public void playSoundEffect(String soundEffectName)
    {
        String soundEffectFile = "Sounds\\" + soundEffectName + ".wav";
        try 
        {
            System.out.println("sound played");
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(soundEffectFile)));
            sound.start();

        } 
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
        {
            System.out.println(e);
        }
    }

    public void attackGridExtender(int[][] atkgrid, int x, int y)
    {
        int atkLen = atkgrid.length;
        int[][] copyArr = new int[atkLen + 1][2];
        for (int i = 0; i < atkLen; i++)
        {
            copyArr[i][0] = atkgrid[i][0];
            copyArr[i][1] = atkgrid[i][1];
        }
        copyArr[atkLen][0] = x;
        copyArr[atkLen][1] = y;
        this.attackGrid = copyArr;
    }

    public void attackGridSetup()
    {
        this.attackGrid = new int[0][2];
        int[] startsEndsGrid = {-attackGridEPos[0], attackGridLength[0] - attackGridEPos[0], -attackGridEPos[1], attackGridLength[1] - attackGridEPos[1]};
        for (int y = startsEndsGrid[0]; y <= startsEndsGrid[1]; y++)
        {
            for (int x = startsEndsGrid[2]; x <= startsEndsGrid[3]; x++)
            {
                if (range[y + attackGridEPos[0]][x + attackGridEPos[1]] == 0)
                {
                    if ((gridPos[0] + x) < 10 && (gridPos[0] + x) > -1 && (gridPos[1] + y < 6) && (gridPos[1] + y) > -1) // Makes sure that the attackgridSqaure is actually on the map
                    {
                        attackGridExtender(attackGrid, gridPos[0] + x, gridPos[1] + y);
                    }
                }
            }
        }
        numAttackGrid = this.attackGrid.length;
    }

    public void updateGridPlacement()
    {
        gridPos[0] = position[0] / 128; // the pixel length
        gridPos[1] = position[1] / 100; // the pixel height
    }
}