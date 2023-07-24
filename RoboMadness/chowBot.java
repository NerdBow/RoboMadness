import java.io.File;
import javax.imageio.ImageIO;
public class chowBot extends enemy
{
    public chowBot()
    {
        super();
        this.name = "chowBot";
        this.health = 15000;
        this.maxHealth = 15000;
        this.armour = 15;
        this.damage = 10;
        this.flying = false;
        this.willExplode = false;
        this.speedX = 128;
        this.speedY = 100;
        this.pixelMovement = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\chowBot\\Left\\chowBot" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\chowBot\\Left\\chowBot" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\chowBot\\Left\\chowBot" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\chowBot\\Right\\chowBot" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\chowBot\\Right\\chowBot" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\chowBot\\Right\\chowBot" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\chowBot\\Left\\chowBot0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("chowBot()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new chowBot();
        return enemies;
    }
}
