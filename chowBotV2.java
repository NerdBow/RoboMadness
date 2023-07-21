import java.io.File;
import javax.imageio.ImageIO;
public class chowBotV2 extends enemy
{
    public chowBotV2()
    {
        super();
        this.health = 50000;
        this.maxHealth = 50000;
        this.armour = 20;
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
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\chowBot2\\chowBot0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("chowBotV2()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new chowBotV2();
        return enemies;
    }
}