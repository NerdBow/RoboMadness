import java.io.File;
import javax.imageio.ImageIO;
public class atm extends tower
{
    boolean hasBuffed = false;

    public atm()
    {
        super();
        this.serialNumber = 5;
        this.health = 10;
        this.orientation = 2;
        this.cost = 80;
        this.type = 8;
        this.damage = 10;
        this.coolDown = 20;
        this.coolDownTimer = 0;
        this.supportAmplifier = 1;
        this.attacking = false;
        this.attackGridLength = new int[] {2, 2};
        this.attackGridEPos = new int[] {1, 1};
        this.range = new int[][] 
        {
            {2, 0, 2},
            {0, 1, 0},
            {2, 0, 2}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\atm\\ATMIdle\\idleATM" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\atm\\ATMIdle\\idleATM" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\atm\\ATMIdle\\idleATM" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\atm\\ATMattack\\attackATM" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\atm\\ATMattack\\attackATM" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\atm\\ATMattack\\attackATM" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = ImageIO.read(new File("assets\\Towers\\atm\\ATMIdle\\idleATM0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("atm.atm()");
        }
    }

    public int makeMoney(int addedMoney)
    {
        if (!hasBuffed)
        {
            addedMoney *= 2;
            hasBuffed = true;
            return addedMoney;
        }
        return addedMoney;
    }

    public enemy[] attack(enemy[] enemyArr, int numEnemies)
    {
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
                    // First Target
                    enemyArr[k].takeDamage((int)(Math.round(damage * supportAmplifier)));
                    System.out.printf("%nATM || Health: %d", enemyArr[k].health);
                    coolDownTimer = coolDown;
                    attacking = true;
                    frame = 0;
                    activeAnimation = attackAnimation;
                    playSoundEffect("AtmAttack");
                    return enemyArr;
                }
            }
        }
        return enemyArr;
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        playSoundEffect("AtmSpawn");
        towers[numOfTowers - 1] = new atm();
        return towers;
    }

}
