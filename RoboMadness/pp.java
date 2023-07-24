import java.io.File;
import javax.imageio.ImageIO;
public class pp extends tower
{
    public pp()
    {
        super();
        this.serialNumber = 6;
        this.health = 10;
        this.orientation = 2;
        this.cost = 69;
        this.type = 1;
        this.damage = 2000;
        this.coolDown = 4000;
        this.supportAmplifier = 1;
        this.coolDownTimer = 0;
        this.attacking = false;
        this.attackGridLength = new int[] {2, 5};
        this.attackGridEPos = new int[] {1, 0};
        this.range = new int[][] 
        {
            {0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\pp\\idle\\idlePP" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\pp\\idle\\idlePP" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\pp\\idle\\idlePP" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\pp\\attack\\attackPP" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\pp\\attack\\attackPP" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\pp\\attack\\attackPP" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = ImageIO.read(new File("assets\\Towers\\pp\\idle\\idlePP0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("pp.pp()");
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new pp();
        playSoundEffect("BasicPlace");
        return towers;
    }

    public enemy[] attack(enemy[] enemyArr, int numEnemies)
    {
        if (coolDownTimer != 0)
        {
            coolDownTimer--;
            return enemyArr;
        }

        isStunned = false;
        for (int i = 0; i < numAttackGrid; i++)
        {
            for (int k = 0; k < numEnemies; k++)
            {
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1])
                {
                    // Armour Percise
                    enemyArr[k].health -= ((int)(Math.round(damage * supportAmplifier))); // ignores armour
                    System.out.printf("%npp || Health: %d", enemyArr[k].health);
                    coolDownTimer = coolDown;
                    attacking = true;
                    frame = 0;
                    activeAnimation = attackAnimation;
                    playSoundEffect("PPShot");
                    return enemyArr;
                }
            }
        }
        return enemyArr;
    }
}
