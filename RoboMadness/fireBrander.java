import java.io.File;
import javax.imageio.ImageIO;
public class fireBrander extends tower{
    public fireBrander()
    {
        super();
        this.serialNumber = 9;
        this.health = 10;
        this.orientation = 2;
        this.cost = 87;
        this.type = 1;
        this.damage = 10;
        this.coolDown = 20;
        this.supportAmplifier = 1;
        this.shotsFired = 0;
        this.coolDownTimer = 0;
        this.attacking = false;
        this.attackGridLength = new int[] {6, 6};
        this.attackGridEPos = new int[] {3, 3};
        this.range = new int[][] 
        {
            {0, 2, 2, 0, 2, 2, 0},
            {2, 0, 2, 0, 2, 0, 2},
            {2, 2, 0, 0, 0, 2, 2},
            {0, 0, 0, 1, 0, 0, 0},
            {2, 2, 0, 0, 0, 2, 2},
            {2, 0, 2, 0, 2, 0, 2},
            {0, 2, 2, 0, 2, 2, 0}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\FireBrander\\Idle\\FireBranderF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\FireBrander\\Idle\\FireBranderF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\FireBrander\\Idle\\FireBranderF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\FireBrander\\Attack\\FireBranderAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\FireBrander\\Attack\\FireBranderAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\FireBrander\\Attack\\FireBranderAF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = idleAnimation[0];
        } 
        catch (Exception e) 
        {
            System.out.println("Shellknock.Shellknock()");
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        playSoundEffect("FireBranderSpawn");
        towers[numOfTowers - 1] = new fireBrander();
        return towers;
    }

    public enemy[] attack(enemy[] enemyArr, int numEnemies)
    {
        if (coolDownTimer != 0)
        {
            coolDownTimer--;
            return enemyArr;
        }
        boolean hasShot = false;
        isStunned = false;
        for (int k = 0; k < numEnemies; k++)
        {
            for (int i = 0; i < numAttackGrid; i++)
            {
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1])
                {
                    // AOE
                    enemyArr[k].takeDamage((int)(Math.round(((damage+(shotsFired*3)) * supportAmplifier))));
                    System.out.printf("%nShell || Health: %d", enemyArr[k].health);
                    coolDownTimer = coolDown;
                    if (shotsFired < 130)
                    {
                        shotsFired++;
                    }
                    attacking = true;
                    frame = 0;
                    activeAnimation = attackAnimation;
                    hasShot = true;
                }
            }
        }
        //change this later (it's for testing)
        if (hasShot == true)
        {
            playSoundEffect("FireBranderShot");
        }
        return enemyArr;
    }
}
