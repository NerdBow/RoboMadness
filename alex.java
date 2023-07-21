import java.io.File;
import javax.imageio.ImageIO;
public class alex extends tower
{
    public alex()
    {
        super(); 
        this.serialNumber = 0;
        this.health = 10;
        this.orientation = 2;
        this.cost = 9;
        this.type = 0;
        this.damage = 40;
        this.coolDown = 25;
        this.coolDownTimer = 0;
        this.supportAmplifier = 1;
        this.attacking = false;
        this.attackGridLength = new int[] {2, 2};
        this.attackGridEPos = new int[] {1, 1};
        this.range = new int[][] 
        {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\alex\\idle\\" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\alex\\idle\\" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\alex\\idle\\" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\alex\\attack\\attackAlex" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\alex\\attack\\attackAlex" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\alex\\attack\\attackAlex" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = ImageIO.read(new File("assets\\Towers\\Alex\\idle\\0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new alex();
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

        for (int k = 0; k < numEnemies; k++)
        {
            for (int i = 0; i < numAttackGrid; i++)
            {
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1])
                {
                    // First Target
                    enemyArr[k].takeDamage((int)(Math.round(damage * supportAmplifier)));
                    System.out.printf("%nAlex || Health: %d", enemyArr[k].health);
                    attacking = true;
                    activeAnimation = attackAnimation;
                    frame = 0;
                    coolDownTimer = coolDown;
                    playSoundEffect("LaserDart");
                    return enemyArr;
                }
            }
        }
        return enemyArr;
    }
}