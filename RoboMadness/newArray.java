import java.io.File;
import javax.imageio.ImageIO;
public class newArray extends enemy{
    public newArray()
    {
        super();
        this.health = 800;
        this.armour = 0;
        this.damage = 10;
        this.flying = false;
        this.willExplode = false;
        this.speedX = 16;
        this.speedY = 20;
        this.pixelMovement = new int[][] {{-8, 0}, {8, 0}, {0, -5}, {0, 5}};
        try 
        {
            this.sprite = ImageIO.read(new File("assets\\Enemy\\codebat.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("codebat.codebat()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new newArray();
        return enemies;
    }
}
