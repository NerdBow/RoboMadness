import java.io.File;
import javax.imageio.ImageIO;
public class codebat extends enemy
{
    public codebat()
    {
        super();
        this.name = "codebat";
        this.maxHealth = 240;
        this.health = 240;
        this.armour = 0;
        this.damage = 10;
        this.flying = true;
        this.willExplode = false;
        this.speedX = 16;
        this.speedY = 20;
        this.pixelMovement = new int[][] {{-8, 0}, {8, 0}, {0, -5}, {0, 5}};
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\codeBat\\Left\\codebat" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\codeBat\\Left\\codebat" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\codeBat\\Left\\codebat" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\codeBat\\Right\\codebat" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\codeBat\\Right\\codebat" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\codeBat\\Right\\codebat" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\codeBat\\Left\\codebat0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("codebat.codebat()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new codebat();
        return enemies;
    }
}
