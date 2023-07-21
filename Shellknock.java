import java.io.File;
import javax.imageio.ImageIO;
public class Shellknock extends tower
{
    public Shellknock()
    {
        super();
        this.serialNumber = 1;
        this.health = 10;
        this.orientation = 2;
        this.cost = 29;
        this.type = 1;
        this.damage = 300;
        this.coolDown = 150;
        this.supportAmplifier = 1;
        this.attacking = false;
        this.coolDownTimer = 0;
        this.attackGridLength = new int[] {4, 5};
        this.attackGridEPos = new int[] {2, 0};
        this.range = new int[][] 
        {
            {2, 2, 2, 0, 2, 2},
            {2, 2, 0, 0, 0, 2},
            {1, 0, 0, 0, 0, 0},
            {2, 2, 0, 0, 0, 2},
            {2, 2, 2, 0, 2, 2}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Idle\\ShellKnockF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Idle\\ShellKnockF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Idle\\ShellKnockF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Attack\\ShellKnockAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Attack\\ShellKnockAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Attack\\ShellKnockAF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = ImageIO.read(new File("assets\\Towers\\ShellKnock\\Idle\\ShellKnockF0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("Shellknock.Shellknock()");
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new Shellknock();
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
                    // AOE
                    enemyArr[k].takeDamage((int)(Math.round(damage * supportAmplifier)));
                    System.out.printf("%nShell || Health: %d", enemyArr[k].health);
                    coolDownTimer = coolDown;
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
            playSoundEffect("ShellKnockShot");
        }
        return enemyArr;
    }
}
