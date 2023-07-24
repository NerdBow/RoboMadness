import java.io.File;
import javax.imageio.ImageIO;
public class iskarna extends tower
{
    public iskarna()
    {
        super();
        this.serialNumber = 4;
        this.health = 10;
        this.orientation = 2;
        this.cost = 28;
        this.type = 1;
        this.damage = 5;
        this.coolDown = 50;
        this.supportAmplifier = 1;
        this.coolDownTimer = 0;
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
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\Iskarna\\Idle\\IskarnaF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\Iskarna\\Idle\\IskarnaF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\Iskarna\\Idle\\IskarnaF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\Iskarna\\Attack\\IskarnaAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\Iskarna\\Attack\\IskarnaAF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\Iskarna\\Attack\\IskarnaAF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = idleAnimation[0];
        } 
        catch (Exception e) 
        {
            System.out.println("iskarna.iskarna()");
        }
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new iskarna();
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
                if (enemyArr[k].gridPos[0] == attackGrid[i][0] && enemyArr[k].gridPos[1] == attackGrid[i][1] && enemyArr[k].armour > 0)
                {
                    // Armour removing
                    enemyArr[k].armour -= ((int)(Math.round(damage * supportAmplifier)));
                    System.out.printf("%niskarna || Armour: %d", enemyArr[k].armour); 
                    coolDownTimer = coolDown;
                    attacking = true;
                    frame = 0;
                    activeAnimation = attackAnimation;
                    playSoundEffect("IskarnaShot");
                    return enemyArr;
                }
            }
        }
        return enemyArr;
    }
}