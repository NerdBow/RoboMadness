import java.io.File;
import javax.imageio.ImageIO;
public class sheriffCaliber extends tower
{
    public sheriffCaliber()
    {
        super();
        this.serialNumber = 2;
        this.health = 10;
        this.orientation = 2;
        this.cost = 18;
        this.type = 3;
        this.damage = 65;
        this.coolDown = 40;
        this.shotsFired = 0;
        this.coolDownTimer = 0;
        this.attacking = false;
        this.supportAmplifier = 1;
        this.attackGridLength = new int[] {4, 4};
        this.attackGridEPos = new int[] {2, 2};
        this.range = new int[][] 
        {
            {2, 2, 0, 2, 2},
            {2, 0, 0, 0, 2},
            {0, 0, 1, 0, 0},
            {2, 0, 0, 0, 2},
            {2, 2, 0, 2, 2}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Idle\\SheriffCaliberF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Idle\\SheriffCaliberF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Idle\\SheriffCaliberF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Attack\\SheriffCaliberAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Attack\\SheriffCaliberAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Attack\\SheriffCaliberAF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = ImageIO.read(new File("assets\\Towers\\SheriffCaliber\\Idle\\SheriffCaliberF0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("sheriffCaliber.sheriffCaliber()");
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new sheriffCaliber();
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
        boolean hasShot = false;
        for (int k = 0; k < numEnemies; k++)
        {
            for (int i = 0; i < numAttackGrid; i++)
            {
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1])
                {
                    if (shotsFired == 2)
                    {
                        enemyArr[k].takeDamage((int)(Math.round((damage * 6) * supportAmplifier)));
                        System.out.printf("%nSheriff || Health: %d", enemyArr[k].health);
                        coolDownTimer = coolDown;
                        attacking = true;
                        activeAnimation = attackAnimation;
                        shotsFired = 0;
                        hasShot = true;
                    }
                    else
                    {
                        enemyArr[k].takeDamage((int)(Math.round(damage * supportAmplifier)));
                        shotsFired++;
                        System.out.printf("%nSheriff || Health: %d", enemyArr[k].health);
                        coolDownTimer = coolDown;
                        attacking = true;
                        frame = 0;
                        activeAnimation = attackAnimation;
                        playSoundEffect("CaliberLight");
                        return enemyArr;
                    }
                }
            }
        }
        if (hasShot== true)
        {
            playSoundEffect("CaliberHeavy");
        }
        return enemyArr;
    }
}
