import java.io.File;
import javax.imageio.ImageIO;
public class newInt extends enemy{
    public newInt()
    {
        super();
        this.maxHealth = 800;
        this.health = 800;
        this.armour = 10;
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
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Left\\newInt[]" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Left\\newInt[]" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Left\\newInt[]" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Right\\newInt[]" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Right\\newInt[]" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Right\\newInt[]" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\newInt[]\\Left\\newInt[]0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("newInt()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new newInt();
        return enemies;
    }
}
