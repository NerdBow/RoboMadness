import java.io.File;
import javax.imageio.ImageIO;
public class serverSpider extends enemy{
    public serverSpider()
    {
        super();
        this.health = 10;
        this.maxHealth = 10;
        this.armour = 10;
        this.damage = 10;
        this.flying = false;
        this.willExplode = false;
        this.speedX = 8;
        this.speedY = 10;
        this.pixelMovement = new int[][] {{-16, 0}, {16, 0}, {0, -10}, {0, 10}};
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\spider\\Left\\spiderInServerRoom" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\spider\\Left\\spiderInServerRoom" + i + ".png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\spider\\Left\\spiderInServerRoom" + i + ".png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\spider\\Right\\spiderInServerRoom" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\spider\\Right\\spiderInServerRoom" + i + ".png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\spider\\Right\\spiderInServerRoom" + i + ".png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
            this.sprite = ImageIO.read(new File("assets\\Enemy\\spider\\Left\\spiderInServerRoom0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("serverSpider()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new serverSpider();
        playSoundEffect("SpiderSpawn");
        return enemies;
    }
}
