import java.io.File;
import javax.imageio.ImageIO;
public class yato extends tower
{
    public yato()
    {
        super();
        this.serialNumber = 3;
        this.health = 10;
        this.orientation = 2;
        this.cost = 60;
        this.type = 9;
        this.damage = 0;
        this.coolDown = 0;
        this.shotsFired = 0;
        this.coolDownTimer = 0;
        this.attacking = false;
        this.attackGridLength = new int[] {4, 4};
        this.attackGridEPos = new int[] {2, 2};
        this.range = new int[][] 
        {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        try 
        {
            for (int i = 0; i < 10; i++)
            {
                this.idleAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF" + i + ".png"));
                this.idleAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF" + i + ".png"));
                this.idleAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF" + i + ".png"));
                this.attackAnimation[(i * 3)] = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF" + i + ".png"));
                this.attackAnimation[(i * 3) + 1] = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF" + i + ".png"));
                this.attackAnimation[(i * 3) + 2] = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF" + i + ".png"));
            }
            this.activeAnimation = this.idleAnimation;
            this.sprite = ImageIO.read(new File("assets\\Towers\\Yato\\YatoF0.png"));
        } 
        catch (Exception e) 
        {
            System.out.println("yato.yato()");
        }
    }
    public tower[] cloneTower(tower[] towers, int numOfTowers)
    {
        towers[numOfTowers - 1] = new yato();
        playSoundEffect("YatoSpawn");
        return towers;
    }
}
