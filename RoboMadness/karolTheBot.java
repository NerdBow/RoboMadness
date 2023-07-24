import java.io.File;
import javax.imageio.ImageIO;
public class karolTheBot extends enemy
{
    public karolTheBot()
    {
        super();
        this.name = "karol";
        this.maxHealth = 500;
        this.health = 500;
        this.armour = 10;
        this.damage = 10;
        this.flying = false;
        this.willExplode = true;
        this.speedX = 32;
        this.speedY = 20;
        this.pixelMovement = new int[][] {{-4, 0}, {4, 0}, {0, -5}, {0, 5}};
        this.attackGridLength = new int[] {2, 2};
        this.attackGridEPos = new int[] {1, 1};
        this.stunDuration = 45;
        this.range = new int[][] 
        {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.walkingAnimationLeft[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\KaroltheBot\\Left\\KaroltheBotF" + i + "L.png"));
                this.walkingAnimationLeft[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\KaroltheBot\\Left\\KaroltheBotF" + i + "L.png"));
                this.walkingAnimationLeft[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\KaroltheBot\\Left\\KaroltheBotF" + i + "L.png"));
                this.walkingAnimationRight[(i * 3)] = ImageIO.read(new File("assets\\Enemy\\KaroltheBot\\Right\\KaroltheBotF" + i + "R.png"));
                this.walkingAnimationRight[(i * 3) + 1] = ImageIO.read(new File("assets\\Enemy\\KaroltheBot\\Right\\KaroltheBotF" + i + "R.png"));
                this.walkingAnimationRight[(i * 3) + 2] = ImageIO.read(new File("assets\\Enemy\\KaroltheBot\\Right\\KaroltheBotF" + i + "R.png"));
            }
            this.activeAnimation = this.walkingAnimationLeft;
        } 
        catch (Exception e) 
        {
            System.out.println("karolTheBot.karolTheBot()");
        }
    }

    public enemy[] cloneEnemy(enemy[] enemies, int numOfEnemies)
    {
        enemies[numOfEnemies - 1] = new karolTheBot();
        playSoundEffect("KarolSpawn");
        return enemies;
    }
}