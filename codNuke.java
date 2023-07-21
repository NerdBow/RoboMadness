import java.io.File;
import javax.imageio.ImageIO;
public class codNuke extends enemy
{
    public codNuke()
    {
        super();
        this.health = 20000;
        this.maxHealth = 20000;
        this.armour = 50;
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
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\codNuke\\Left\\codNuke" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\codNuke\\Left\\codNuke" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\codNuke\\Left\\codNuke" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\codNuke\\Right\\codNuke" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\codNuke\\Right\\codNuke" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\codNuke\\Right\\codNuke" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\codNuke\\Left\\codNuke0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("codNuke()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new codNuke();
        playSoundEffect("CodNukeSpawn");
        return enemies;
    }
}