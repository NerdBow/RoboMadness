import java.io.File;
import javax.imageio.ImageIO;
public class sasaki extends tower 
{
    public sasaki()
    {
        super();
        this.serialNumber = 7;
        this.shotsFired = 300;
        this.health = 10;
        this.orientation = 2;
        this.cost = 60;
        this.type = 1;
        this.damage = 225;
        this.coolDown = 120;
        this.supportAmplifier = 1;
        this.attacking = false;
        this.coolDownTimer = 0;
        this.attackGridLength = new int[] {2, 10};
        this.attackGridEPos = new int[] {1, 5};
        this.range = new int[][] 
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\Sasaki\\Idle\\SasakiF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\Sasaki\\Idle\\SasakiF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\Sasaki\\Idle\\SasakiF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\Sasaki\\Attack\\SasakiAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\Sasaki\\Attack\\SasakiAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\Sasaki\\Attack\\SasakiAF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = idleAnimation[0];
        } 
        catch (Exception e) 
        {
            System.out.println("sasaki.sasaki()");
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new sasaki();
        playSoundEffect("SasakiSpawn");
        return towers;
    }

    public enemy[] attack(enemy[] enemyArr, int numEnemies)
    {
        shotsFired--;
        if (coolDownTimer != 0)
        {
            coolDownTimer--;
            return enemyArr;
        }

        isStunned = false;
        for (int k = 0; k < numEnemies; k++)
        {
            for (int i = 0; i < numAttackGrid; i++)
            {
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1])
                {
                    if (shotsFired >= 0)
                    {
                        enemyArr[k].takeDamage((int)(Math.round(damage * supportAmplifier)));
                        playSoundEffect("sasakiShot");
                    }
                    else
                    {
                        enemyArr[k].health -= ((int)(Math.round((damage*7) * supportAmplifier)));
                        playSoundEffect("sasakiPower");
                        shotsFired = 300;
                    }
                    System.out.printf("%nsasaki || Health: %d", enemyArr[k].health);
                    coolDownTimer = coolDown;
                    attacking = true;
                    frame = 0;
                    activeAnimation = attackAnimation;
                    return enemyArr;
                }
            }
        }
        return enemyArr;
    }
}
