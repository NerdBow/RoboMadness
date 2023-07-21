import java.io.File;
import javax.imageio.ImageIO;
public class mitochondrianBattery extends enemy
{
    public mitochondrianBattery()
    {
        super();
        this.health = 100;
        this.maxHealth = 100;
        this.armour = 95;
        this.damage = 10;
        this.flying = false;
        this.willExplode = false;
        this.speedX = 32;
        this.speedY = 50;
        this.pixelMovement = new int[][] {{-4, 0}, {4, 0}, {0, -2}, {0, 2}};
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\mBatt\\Left\\mBatt" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\mBatt\\Left\\mBatt" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\mBatt\\Left\\mBatt" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\mBatt\\Right\\mBatt" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\mBatt\\Right\\mBatt" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\mBatt\\Right\\mBatt" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\mBatt\\Left\\mBatt0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("mBatt()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new mitochondrianBattery();
        return enemies;
    }
}