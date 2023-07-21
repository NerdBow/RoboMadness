import java.io.*;
import javax.imageio.ImageIO;
public class aquaShock extends tower{
    //constructor for AquaShock
    public aquaShock()
    {
        super();
        //AquaShock Stats
        this.serialNumber = 8;
        this.health = 10;
        this.orientation = 2;
        this.cost = 40;
        this.type = 1;
        this.damage = 400;
        this.coolDown = 55;
        this.supportAmplifier = 1;
        this.attacking = false;
        this.coolDownTimer = 0;
        this.attackGridLength = new int[] {2, 2};
        this.attackGridEPos = new int[] {1, 1};
        this.range = new int[][] 
        {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        //Setting the animation array to AquaShock's sprites
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\AquaShock\\Idle\\AquaShockF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\AquaShock\\Idle\\AquaShockF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\AquaShock\\Idle\\AquaShockF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\AquaShock\\Attack\\AquaShockAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\AquaShock\\Attack\\AquaShockAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\AquaShock\\Attack\\AquaShockAF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = idleAnimation[0];
        } 
        catch (Exception e) 
        {
            System.out.println("aquashock.aquashock()");
        }
    }
    //method that clones the tower
    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new aquaShock();
        playSoundEffect("AquaShockSpawn");
        return towers;
    }
    //attack method
    public enemy[] attack(enemy[] enemyArr, int numEnemies)
    {
        if (coolDownTimer != 0)
        {
            coolDownTimer--;
            return enemyArr;
        }
        isStunned = false;
        boolean hasShot = false;
        for (int k = 0; k < numEnemies; k++)
        {
            for (int i = 0; i < numAttackGrid; i++)
            {
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1] && enemyArr[k].armour <= 0)
                {
                    enemyArr[k].takeDamage((int)(Math.round(damage * supportAmplifier)));
                    System.out.printf("%naquashock || Armour: %d", enemyArr[k].health); 
                    coolDownTimer = coolDown;
                    attacking = true;
                    frame = 0;
                    activeAnimation = attackAnimation;
                    playSoundEffect("AquashockShot");
                    hasShot = true;
                }
            }
        }
        if (hasShot== true)
        {
            playSoundEffect("AquaShockShot");
        }
        return enemyArr;
    }
}
