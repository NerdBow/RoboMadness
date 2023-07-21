import java.awt.image.BufferedImage;
public class enemy extends entity
{
    public String name;
    public int maxHealth;
    public int armour;
    public int steps;
    public int speedX;
    public int speedY;
    public int pixelMovement[][];
    public int damage;
    public boolean flying;
    public boolean willExplode;
    public int stunDuration; // Runs on frames
    public int distanceCovered; //measured in pixels
    public BufferedImage[] walkingAnimationLeft = new BufferedImage[30];
    public BufferedImage[] walkingAnimationRight = new BufferedImage[30];

    public enemy()
    {
    }
    
    public void takeDamage(int damageAmount)
    {
        this.health -= damageAmount * (100 - armour) * 0.01;
    }

    public void healHealth(int healAmount)
    {
        this.health += healAmount;
    }

    public void move(int x, int y)
    {
        this.position[0] += x;
        this.position[1] += y;
        distanceCovered += (Math.abs(x) + Math.abs(y));
        updateGridPlacement();
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        return enemies;
    }

    public tower[] explode(tower[] towerArr, int numOfTowers)
    {
        attackGridSetup();
        for (int i = 0; i < numAttackGrid; i++)
        {
            for (int k = 0; k < numOfTowers; k++)
            {
                if (towerArr[k].gridPos[0] == attackGrid[i][0] && towerArr[k].gridPos[1] == attackGrid[i][1])
                {
                    towerArr[k].getStunned(stunDuration);
                
                }
            }
        }
        playSoundEffect("KarolDeath");
        return towerArr;
    }
}
