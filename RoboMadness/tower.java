import java.awt.image.BufferedImage;
public class tower extends entity
{
    public int serialNumber;
    public int damage;
    public int type; // type 0 = targets enemy at the front of the line within range  type 1 = AOE type 2 = support
    public int coolDown;
    public int coolDownTimer = 0; // by frame not seconds
    public int cost;
    public int maximum;
    public int strongestEnemy;
    public int shotsFired;
    public double supportAmplifier;
    public boolean isStunned = false;
    public int[] topLeftGrid = new int[2];
    public BufferedImage[] attackAnimation = new BufferedImage[30];
    public BufferedImage[] idleAnimation = new BufferedImage[30];

    public tower()
    {
    }

    public void getStunned(int duration)
    {
        coolDownTimer += duration;
        isStunned = true;
    }

    public enemy[] attack(enemy[] enemyArr, int numEnemies)
    {
        return enemyArr;
    }

    public void getTopLeft()
    {
        topLeftGrid[0] = gridPos[0] - attackGridEPos[0];
        topLeftGrid[1] = gridPos[1] - attackGridEPos[2];
    }

    public tower[] supportAmplify(tower[] towerArr, int numOfTowers)
    {
        for (int i = 0; i < numAttackGrid; i++)
        {
            for (int k = 0; k < numOfTowers; k++)
            {
                if (towerArr[k].gridPos[0] == attackGrid[i][0] && towerArr[k].gridPos[1] == attackGrid[i][1])
                {
                    towerArr[k].supportAmplifier = 1.3;
                }
            }
        }
        return towerArr;
    }

    public int makeMoney(int addedMoney)
    {
        return addedMoney;
    }

    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        return towers;
    }
}
