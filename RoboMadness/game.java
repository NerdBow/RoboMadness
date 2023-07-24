//Imports
import java.awt.*;
import javax.swing.*;

import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.BufferedImage;
import java.awt.event.*;

public class game extends JPanel implements MouseListener, MouseMotionListener, Runnable
{
	//Class Variables
    // Settings
    public static int gameState = 0;
    public static int fps = 30;
    public static int milli = (int)Math.round(1.0 / fps * 1000);
    public static int screenWidth = 1280;
    public static int screenHeight = 720;
    public static int frameCount = 0;

    //Currency
    public static int currencyHeld = 40;
    public static int addedCurrency = 1;
    public static int baseHealth = 2;

    // Inputs
    public static int mouseXPos;
    public static int mouseYPos;
    
    // Grid
    public static int[][][] grid = new int[10][6][4];
    public static int gridSquareLength = 128; // For image positioning later
    public static int gridSquareHeight = 100;

    //Map
    public static mapScript mapGenerator = new mapScript();
    public static int[][] gridTypeMap;
    public static int[][] MapTiming; // {Type of enemy: n, spawnTime(seconds): n}
    public static int map; // which map

    //Enemies
    public int numOfEnemies = 0;
    public int enemiesSpawned = 0;
    public int enemiesRemaining = 0;
    public enemy[] enemies = new enemy[numOfEnemies];
    public enemy[] allEnemies = {new codebat(), new karolTheBot(), new mitochondrianBattery(), new newInt(), new serverSpider(), new chowBotV2(), new codNuke(), new chowBot()};
    public int[][] enemyMovementKey = {{-8, 0}, {8, 0}, {0, -5}, {0, 5}};
    public static int[] enemySpawnPos;

    //Towers
    public int numOfTowers = 0;
    public tower[] towers = new tower[numOfTowers];
    public int loadoutCount = 0;
    public boolean[] chosenTowers = {false, false, false, false, false, false, false, false, false, false};
    public boolean[] chosenTowersMaster = {false, false, false, false, false, false, false, false, false, false};
    public tower[] allTowers = {new alex(), new Shellknock(), new sheriffCaliber(), new yato(), new iskarna(), new atm(), new pp(), new sasaki(), new aquaShock(), new fireBrander()};
    public tower[] deck = new tower[7];
    public tower selectedTower;
    public boolean deleteMode = false;
    public int[] maxNumberOfTower= {8, 4, 6, 1, 2, 1, 1, 3, 2, 2};
    public int[] maxNumberOfTowerMaster = {8, 4, 6, 1, 2, 1, 1, 3, 2, 2};

    //GUI
    public static int[][] menuButtons = {{340, 940}, {200, 300}, {400, 500}, {600, 700}, {430, 560}, {1080, 1220}, {590, 710}, {25,100}, {1180,1250}, {100, 250}, {251, 400}, {200, 1000}, {1150, 1250}, {550, 700}}; // First index is for the x values that they all share
    public static int[][] mapSelectionButtons = {{240, 475}, {175, 410}, {565, 810}, {930, 1165}, {30, 610}, {410, 700}};
    public static int[][] descriptionButtons = {{580, 705}, {1140, 1265}, {10, 140}};
    public static int descriptionPage = 0;

    //Images
    public BufferedImage menu; 
    public BufferedImage mapSelection;
    public BufferedImage towerImage;
    public BufferedImage enemyImage;
    public BufferedImage[] mapImages = new BufferedImage[3];
    public BufferedImage[] towerDescriptions = new BufferedImage[5];
    public BufferedImage currencyIcon;
    public BufferedImage enemyIcon;
    public BufferedImage gameOver;
    public BufferedImage attackSqaureImage;
    public BufferedImage selectedTrash;
    public BufferedImage clearScreen; 
    public BufferedImage towerSelection;
    public BufferedImage credits;
    public BufferedImage instructions;
    public static int imageOffSetX = (gridSquareLength - 96) / 2;
    public static int imageOffSetY = (gridSquareHeight - 96) / 2;

    // Music
    public static Clip battleST;
    public static Clip map1Music;
    public static Clip map2Music;
    public static Clip map3Music;

    //Class!!!
    public game()
    {
        //Settings for the game window
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(255, 255, 255));
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setFocusable(true);    
        
        //Creates a new thread for the run code
        Thread thread = new Thread(this);
        thread.start();

        // Image Loading
        try
        {
            mapSelection = ImageIO.read(new File("assets\\MapSelect.png"));
            mapImages[0] = ImageIO.read(new File("assets\\Map1Finished.png"));
            mapImages[1] = ImageIO.read(new File("assets\\Nuclearstoragefacilitymap.png"));
            mapImages[2] = ImageIO.read(new File("assets\\last_map.png"));
            towerSelection = ImageIO.read(new File("assets\\towerselect.png"));
            credits = ImageIO.read(new File("assets\\TempCredits.png"));
            instructions = ImageIO.read(new File("assets\\InstructionsMenu.png"));
            menu = ImageIO.read(new File("assets\\menu.png"));
            gameOver = ImageIO.read(new File("assets\\levelLose.png"));
            attackSqaureImage = ImageIO.read(new File("assets\\attackgrid.png"));
            clearScreen = ImageIO.read(new File("assets\\levelClear.png"));
            selectedTrash = ImageIO.read(new File("assets\\selectedTrash.png"));

            towerDescriptions[0] = ImageIO.read(new File("assets\\towerdesA.png"));
            towerDescriptions[1] = ImageIO.read(new File("assets\\towerdesB.png"));
            towerDescriptions[2] = ImageIO.read(new File("assets\\towerdesC.png"));
            towerDescriptions[3] = ImageIO.read(new File("assets\\towerdesD.png"));
            towerDescriptions[4] = ImageIO.read(new File("assets\\towerdesE.png"));
        }
        catch (Exception e)
        {
            System.out.println("Could not load in the images");
        }

        // Sound Loading
        try 
        {
            battleST = AudioSystem.getClip();
            battleST.open(AudioSystem.getAudioInputStream(new File("Sounds\\FortniteBGM.wav")));
            map1Music = AudioSystem.getClip();
            map1Music.open(AudioSystem.getAudioInputStream(new File("Sounds\\Map1Music.wav")));
            map2Music = AudioSystem.getClip();
            map2Music.open(AudioSystem.getAudioInputStream(new File("Sounds\\Map2Music.wav")));
            map3Music = AudioSystem.getClip();
            map3Music.open(AudioSystem.getAudioInputStream(new File("Sounds\\Map3Music.wav")));
        } 
        catch (Exception e) 
        {
            System.out.println("game.game()");
        }
        
    }

    //starts the game window
    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        JFrame myFrame = new JFrame("Game");
        game myPanel = new game();
        myFrame.add(myPanel);
        myFrame.setVisible(true);
        myFrame.pack();
        musicStart(gameState);
    }

    public void paintComponent(Graphics g) //game state's purpose can be altered
    {
        if (gameState == 0) // game state for menu
        {
            super.paintComponent(g);
            g.drawImage(menu, 0, 0, null);

        }
        else if (gameState == 1) //Tower Selection menu
        {
            super.paintComponent(g);
            g.drawImage(towerSelection, 0, 0, null);

            for (int i = 0, x = 232, y = 127; i < 10; i++, x += 160) // Draws out all the towers in the selection boxes
            {
                if (i == 5)
                {
                    y += 150;
                    x = 232;
                }
                g.drawImage(allTowers[i].sprite, x, y, null);
            }

            for (int i = 0; i < 7; i++) // Draws the images of the selected towers in the deck
            {
                if (deck[i] != null)
                {
                    g.drawImage(deck[i].sprite, 57 + (i * 160), 587, null);
                }
            }
            
        }
        else if (gameState == 2) //Map Selction menu
        {
            super.paintComponent(g);
            g.drawImage(mapSelection, 0, 0, null);
        }

        else if (gameState == 3) //Map 1
        {
            super.paintComponent(g);

            // GUI
            g.drawImage(mapImages[map-1], 0, 0, null); // TODO make this draw a map array which is dictated by a variable
            g.setFont(new Font("Game Of Squids", Font.BOLD, 20));
            g.setColor(new Color(15,15, 233));
            g.drawString(currencyHeld+"", 1100, 635);
            g.setFont(new Font("Segoe UI", Font.BOLD, 20));
            g.setColor(new Color(200,60, 15));
            g.drawString(enemies.length+"", 1200, 635);

            // Draws information on the tower at the bottom of the tower bar
            for (int i = 0; i < 7; i++)
            {
                g.drawImage(deck[i].sprite, 20 + (i*128), 610, null);
                g.setFont(new Font("Verdana", Font.BOLD, 20));
                g.setColor(new Color(60,210, 60));
                g.drawString(deck[i].cost+"", 20 + (i*128), 708);
                g.setFont(new Font("Verdana", Font.BOLD, 20));
                g.setColor(new Color(200,60, 60));
                g.drawString(maxNumberOfTower[deck[i].serialNumber]+"/"+maxNumberOfTowerMaster[deck[i].serialNumber], 70 + (i*128), 708);
            }



            // Game Logic
            frameCount++;
            spawnEnemies(MapTiming);
            moveEnemies(gridTypeMap);
            callAbilities();
            callAttackOnTowers();
            naturalCurrencyRegen();
            updateFrames();

            if (selectedTower != null) // The tower placement hovering system. This also covers the preview of the attack grid
            {
                int mouseToGridX = mouseXPos/gridSquareLength;
                int mouseToGridY = mouseYPos/gridSquareHeight;
                if (mouseYPos/gridSquareHeight < 6 && gridTypeMap[mouseToGridX][mouseToGridY] == 1)
                {
                    g.drawImage(selectedTower.sprite, grid[mouseToGridX][mouseToGridY][0] + imageOffSetX, grid[mouseToGridX][mouseToGridY][1] + imageOffSetY, null);
                    selectedTower.gridPos[0] = mouseToGridX;
                    selectedTower.gridPos[1] = mouseToGridY;
                    selectedTower.attackGrid = new int[selectedTower.numAttackGrid][2];
                    selectedTower.attackGridSetup();
                    int attackSquareX = 0;
                    int attackSquareY = 0;
                    for (int i = 0, l = selectedTower.attackGrid.length; i < l; i++)
                    {
                        attackSquareX = selectedTower.attackGrid[i][0];
                        attackSquareY = selectedTower.attackGrid[i][1];
                        g.drawImage(attackSqaureImage, grid[attackSquareX][attackSquareY][0], grid[attackSquareX][attackSquareY][1], null);
                    }
                }

            }

            if (deleteMode) // Draws the indicator if you have the trash can selected
            {
                g.drawImage(selectedTrash, 896, 600, null);
            }

            for (int i = 0; i < numOfTowers; i++) // for updating towers :)
            {
                System.out.println(towers[i].frame);
                if (towers[i].isStunned)
                {
                    g.drawImage(towers[i].sprite, towers[i].position[0] + imageOffSetX, towers[i].position[1] + imageOffSetY - 10, null);
                }
                else
                {
                    g.drawImage(towers[i].activeAnimation[towers[i].frame], towers[i].position[0] + imageOffSetX, towers[i].position[1] + imageOffSetY - 10, null);
                }
            }   
            
            for (int i = 0; i < numOfEnemies; i++) // For updating enemies
            {
                if (enemies[i].health > 0)
                {
                    g.drawImage(enemies[i].activeAnimation[enemies[i].frame], enemies[i].position[0] - 48, enemies[i].position[1] - 48, null);
                    // everything after this is for the health
                    g.setColor(new Color(0,0, 0));
                    g.fillRect(enemies[i].position[0] - 48, enemies[i].position[1] - 48 + 96, 96, 3);
                    g.setColor(new Color(255,128, 0));
                    g.fillRect(enemies[i].position[0] - 48, enemies[i].position[1] - 48 + 96, Math.round(((float)enemies[i].health/enemies[i].maxHealth)*96), 3);
                }
                else
                {
                    if (enemies[i].willExplode == true)
                    {
                        enemies[i].explode(towers, numOfTowers);
                    }
                    firstObjKill(i, 0);
                }
            }
            
            // Drawing the health bar
            g.setColor(new Color(30,210, 30));
            g.fillRect(1050, 670, (100*baseHealth), 30);
            g.drawRect(1245 - (100 *(baseHealth%2)), 670, (100 *(baseHealth%2)), 30);

            if (enemiesRemaining == 0) // Win Condition
            {
                endGame();
                gameState = 7;
            }
            if (baseHealth == 0) // Lose Condition
            {
                endGame();
                gameState = 6;
            }
        }
        else if (gameState == 4) //instructions
        {
            super.paintComponent(g);
            g.drawImage(instructions, 0, 0, null);
        }
        else if (gameState == 5) //credits
        {
            super.paintComponent(g);
            g.drawImage(credits, 0, 0, null);
        }
        else if (gameState == 6) //gameOver
        {
            super.paintComponent(g);
            g.drawImage(gameOver, 0, 0, null);
        }
        else if (gameState == 7) //winning screen
        {
            super.paintComponent(g);
            g.drawImage(clearScreen, 0, 0, null);
        }
        else if (gameState == 8) // the towers
        {
            super.paintComponent(g);
            g.drawImage(towerDescriptions[descriptionPage], 0, 0, null);
        }
    }

    // General Methods
    public static void gridSetup()
    {
        for (int y = 0; y < 6; y++)
        {
            for (int x = 0; x < 10; x++)
            {
                grid[x][y][0] = (x*128);       //second quaderent x
                grid[x][y][1] = (y*100);       //second quaderent y
                grid[x][y][2] = (x*128)+127;   //fourth quaderent x
                grid[x][y][3] = (y*100)+99;    //fourth quaderent y
            }
        }
    }

    // Music
    public static void musicStart(int gameState)
    {
        switch (gameState)
        {
            case 0:
                battleST.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            case 1:
                map1Music.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            case 2:
                map2Music.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            case 3:
                map3Music.loop(Clip.LOOP_CONTINUOUSLY);
                break;
        }
    }

    public static void musicStop(int gameState)
    {
        switch (gameState)
        {
            case 0:
                battleST.stop();
                break;
            case 1:
                map1Music.stop();
                break;
            case 2:
                map2Music.stop();
                break;
            case 3:
                map3Music.stop();
                break;
        }
    }


    //Method that plays when starting a game
    public void startGame(int map) throws IOException
    {
        game.map = map;
        gridTypeMap = mapGenerator.txtToMapArray("map" + map);
        MapTiming = mapGenerator.txtToMapTiming("time" + map);
        enemiesRemaining = mapGenerator.getNumOfEnemies("time" + map);
        enemySpawnPos = mapGenerator.getEnemySpawn("map" + map);
        gridSetup();   
    }

    //Method that plays when the game ends
    public void endGame()
    {
        enemies = new enemy[0];
        towers = new tower[0];
        frameCount = 0;
        baseHealth = 2;
        addedCurrency = 1;
        currencyHeld = 40;
        enemiesSpawned = 0;
        numOfEnemies = 0;
        numOfTowers = 0;
        selectedTower = null;
        maxNumberOfTower = maxNumberOfTowerMaster.clone();
    }

    public void sortEnemyArr(enemy[] enemyArr) // dumb bubble sort for the eneimes (Sorts by distance covered)
    {
        int sorted = 0;
        enemy temp = null; 
        for (int i = 0; i < numOfEnemies; i++)
        {
            for (int k = i; k < numOfEnemies - 1; k++)
            {
                if (enemyArr[k].distanceCovered < enemyArr[k + 1].distanceCovered)
                {
                    temp = enemyArr[k];
                    enemyArr[k] = enemyArr[k + 1];
                    enemyArr[k + 1] = temp;
                }
                else
                {
                    sorted++;
                }
            }
            if (sorted == numOfEnemies - i - 1)
            {
                break;
            }
        }
        enemies = enemyArr;
    }

    //method that extends the array of towers and enemies
    public void extendArray(entity[] inputArr)
    {
        if (inputArr == enemies)
        {
            enemy[] subArray = new enemy[enemies.length + 1];
            for (int i = 0; i < enemies.length; i++)
            {
                subArray[i] = enemies[i];
            }
            enemies = subArray;
            numOfEnemies++;
        }
        else
        {
            tower[] subArray = new tower[towers.length + 1];
            for (int i = 0; i < towers.length; i++)
            {
                subArray[i] = towers[i];
            }
            towers = subArray;
            numOfTowers++;
        }
    }

    public void firstObjKill(int pos, int type) // first parameter is just the position of the object and the second if you want to remove an enemy(0) or tower(1) object. 
    {
        if (type == 0)
        {
            int count = 0;
            enemy[] subArray = new enemy[enemies.length - 1];
            for (int i = 0; i < pos; i++)
            {
                subArray[count] = enemies[i];
                count++;
            }
            for (int i = pos + 1; i < numOfEnemies; i++)
            {
                subArray[count] = enemies[i];
                count++;
            }
            enemies = subArray;
            numOfEnemies--;
            enemiesRemaining--;
        }
        else
        {
            int count = 0;
            tower[] subArray = new tower[towers.length - 1];
            for (int i = 0; i < pos; i++)
            {
                subArray[count] = towers[i];
                count++;
            }
            for (int i = pos + 1; i < numOfTowers; i++)
            {
                subArray[count] = towers[i];
                count++;
            }
            numOfTowers--;
            towers = subArray;
        }
    }

    //Currency Methods
    public void naturalCurrencyRegen() 
    {
        if (frameCount % 20 == 0 && currencyHeld < 99) // every 20 frames you gain your added currency
        {
            currencyHeld += addedCurrency;
        }
    }
    
    //Animation Handlers
    public void updateFrames()
    {
        for (int i = 0; i < numOfTowers; i++)
        {
            if (towers[i].frame == 29)
            {
                if (towers[i].attacking)
                {
                    towers[i].activeAnimation = towers[i].idleAnimation;
                    towers[i].attacking = false;
                }
                towers[i].frame = 0;
            }
            else
            {
                towers[i].frame++;
            }
        }
        for (int i = 0; i < numOfEnemies; i++)
        {
            if (enemies[i].frame == 29)
            {
                enemies[i].frame = 0;
            }
            else
            {
                enemies[i].frame++;
            }
        }
    }
    
    //if they go out of the grid they move up (later make it so that they don't go out of the grid and they go to the next position)
    //   2
    // 0 E 1
    //   3

    // Enemy Methods
    public void moveEnemies(int[][] map)
    {
        for (int i = 0; i < numOfEnemies; i++)
        {
            if (enemies[i].steps == 0)
            {
                if(map[enemies[i].gridPos[0]][enemies[i].gridPos[1]] == 3) // If base
                {
                    baseHealth--;
                    firstObjKill(i, 0);
                    break;
                }
                else if (enemies[i].orientation == 0 || enemies[i].orientation == 1) // If going horizontally
                {
                    if (enemies[i].gridPos[1] != 0 && map[enemies[i].gridPos[0]][enemies[i].gridPos[1] - 1] == 0) // checks for a turn upwards
                    {
                        enemies[i].orientation = 2;
                        enemies[i].steps = enemies[i].speedY;
                    }
                    else if (enemies[i].gridPos[1] != 5 && map[enemies[i].gridPos[0]][enemies[i].gridPos[1] + 1] == 0) // checks for a turn downwards
                    {
                        enemies[i].orientation = 3;
                        enemies[i].steps = enemies[i].speedY;
                    }
                    else // no turn
                    {
                        enemies[i].steps = enemies[i].speedX;
                    }
                }
                else // If going vertically
                {
                    if (enemies[i].gridPos[0] != 0 && map[enemies[i].gridPos[0] - 1][enemies[i].gridPos[1]] == 0) // check for a turn left
                    {
                        enemies[i].orientation = 0;
                        enemies[i].activeAnimation = enemies[i].walkingAnimationLeft;
                        enemies[i].steps = enemies[i].speedX;
                    }
                    else if (enemies[i].gridPos[0] != 9 && map[enemies[i].gridPos[0] + 1][enemies[i].gridPos[1]] == 0) // checks for a turn right
                    {
                        enemies[i].orientation = 1;
                        enemies[i].activeAnimation = enemies[i].walkingAnimationRight;
                        enemies[i].steps = enemies[i].speedX;
                    }
                    else // no turn
                    {
                        enemies[i].steps = enemies[i].speedY;
                    }
                }
            }
            enemies[i].move(enemies[i].pixelMovement[enemies[i].orientation][0], enemies[i].pixelMovement[enemies[i].orientation][1]);
            enemies[i].steps--;
            sortEnemyArr(enemies);
        }
    }

    //Method for spawning enemies 
    public void spawnEnemies(int[][] timing)
    {
        if (enemiesSpawned < timing.length && frameCount/fps == timing[enemiesSpawned][1]) // checks for over spawning and if the timing matches
        {
            extendArray(enemies);
            enemies = allEnemies[timing[enemiesSpawned][0]].cloneEnemy(enemies, numOfEnemies); // cloneEnemy returns an enemy array!!
            enemies[numOfEnemies - 1].position[0] = grid[enemySpawnPos[0]][enemySpawnPos[1]][0] + 64;
            enemies[numOfEnemies - 1].position[1] = grid[enemySpawnPos[0]][enemySpawnPos[1]][1] + 50;
            enemies[numOfEnemies - 1].updateGridPlacement();
            enemiesSpawned++;
        }
    }

    // Tower Methods
    public int[][] towerPlacement(int[][] map)
    {
        int gridX = mouseXPos/128;
        int gridY = mouseYPos/100;

        if (gridX == 7 && gridY >= 6) // Checks for the 8th box for just remmoving your selected tower
        {
            selectedTower = null;
            return map;
        }

        if (gridY > 5) // not in the tower bar
        {
            return map;
        }

        if (map[gridX][gridY] == 1) // If Placeable tower position
        {
            maxNumberOfTower[selectedTower.serialNumber] -= 1;
            currencyHeld -= selectedTower.cost;
            map[gridX][gridY] = 2; 
            extendArray(towers);
            towers = selectedTower.cloneTower(towers, numOfTowers);
            towers[numOfTowers - 1].position[0] = grid[gridX][gridY][0]; //set towers array number to whichever tower is trying to be placed
            towers[numOfTowers - 1].position[1] = grid[gridX][gridY][1];
            towers[numOfTowers - 1].updateGridPlacement();
            towers[numOfTowers - 1].attackGridSetup();
            selectedTower = null;
        }
        return map;
    }

    public void buyTower() // Basically checks if you can even buy the tower 
    {
        int gridX = mouseXPos/128;
        int gridY = mouseYPos/100;
        System.out.println(gridX + "|" + gridY);
        if (gridY < 5 || gridX > 7)
        {
            return;
        }

        if (gridX == 7) // Checks for the 8th box for the deleting tower method
        {
            deleteMode = true;
            return;
        }

        selectedTower = deck[gridX];

        if (selectedTower.cost > currencyHeld)
        {
            selectedTower = null;
            return;
        }

        if (maxNumberOfTower[selectedTower.serialNumber] < 1)
        {
            selectedTower = null;
            return;
        }
    }
    
    //method for deleting towers
    public void deleteTower()
    {
        int gridX = mouseXPos/128;
        int gridY = mouseYPos/100;
        if (gridX == 7 && gridY >= 6) // Checks for the 8th box for the deleting tower method
        {
            deleteMode = false;
            return;
        }

        if (gridY > 5) // Makes sure the mouse is not in the the 7th row
        {
            return;
        }

        for (int i = 0; i < numOfTowers; i++) // Loops thorught all the towers to see if there is one on the position where you have clicked
        {
            if (towers[i].gridPos[0] == gridX && towers[i].gridPos[1] == gridY)
            {
                maxNumberOfTower[towers[i].serialNumber] = maxNumberOfTower[towers[i].serialNumber] + 1;
                firstObjKill(i, 1);
                gridTypeMap[gridX][gridY] = 1;
                deleteMode = false;
                return;
            }
        }
    }

    //method that calls for every tower to attack
    public void callAttackOnTowers()
    {
        for (int tower = 0; tower < numOfTowers; tower++)
        {
            towers[tower].attack(enemies, numOfEnemies);
        }
    }

    //method that calls for every towre to use their ability
    public void callAbilities()
    {
        for (int t = 0; t < numOfTowers; t++)
        {
            switch (towers[t].type) 
            {
                case 8:
                    addedCurrency = towers[t].makeMoney(addedCurrency);
                    break;
                case 9:
                    towers[t].supportAmplify(towers, numOfTowers);
                    break;
            }
        }
    }

    //this is where the dynamic things come in and constantly repaints
    public void run()
    {
        while (true)
        {
            repaint();    
            try
            {
                Thread.sleep(milli);
            }
            catch (Exception e)
            {
                e.printStackTrace(); //prints what exception has occured and what line number it happened in
            }
        }
    }
    //Method that plays a sound effect
    public void playSoundEffect(String soundEffectName)
    {
        String soundEffectFile = "Sounds\\" + soundEffectName + ".wav";
        try 
        {
            System.out.println("sound played");
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(soundEffectFile)));
            sound.start();
        } 
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
        {
            System.out.println("Cannot load in sound");
        }
    }
  
    // Inputs 
    @Override
    public void mousePressed(MouseEvent e) 
    {
        playSoundEffect("click");
        mouseXPos = e.getX();
        mouseYPos = e.getY();
        System.out.println("mouse pressed");
        switch (gameState)
        {
            case 0: //Main menu
                if (mouseXPos >= menuButtons[0][0] && mouseXPos <= menuButtons[0][1])
                {
                    if (mouseYPos >= menuButtons[1][0] && mouseYPos <= menuButtons[1][1]) // Start Button
                    {
                        gameState = 1;
                    }
                    else if (mouseYPos >= menuButtons[2][0] && mouseYPos <= menuButtons[2][1]) // Instructions Button
                    {
                        gameState = 4;
                    }
                    else if (mouseYPos >= menuButtons[3][0] && mouseYPos <= menuButtons[3][1]) // Credits Buttun
                    {
                        gameState = 5;
                    }
                }
                break;
            case 1: // Tower Selection
            int XPos = ((mouseXPos - 200) / 160);
            int YPos = 0;

                if (mouseYPos >= menuButtons[7][0] && mouseYPos <= menuButtons[7][1]) // Checks for the shared y of the 2 buttons at the top of the menu
                {
                    if (mouseXPos >= menuButtons[7][0] && mouseXPos <= menuButtons[7][1]) // The back button
                    {
                        gameState = 0;
                    }
                    else if (mouseXPos >= menuButtons[8][0] && mouseXPos <= menuButtons[8][1] && loadoutCount == 7) // Button to map select
                    {
                        gameState = 2;
                    }
                }
                else if (mouseYPos >= menuButtons[9][0] && mouseYPos <= menuButtons[10][1] && loadoutCount < 7) // Checks the y if you click into the box where all the towers are
                {
                    YPos = ((mouseYPos - 100) / 150);
                    if (XPos >= 0 && XPos <= 4 && chosenTowers[XPos + (YPos * 5)] == false) // Checks if the x is in the box and also if the tower you are clicking on is already selected
                    {
                        deck[loadoutCount] = allTowers[XPos + (YPos * 5)];
                        chosenTowers[XPos + (YPos * 5)] = true;
                        loadoutCount++;
                    }
                }
                else if (mouseYPos >= menuButtons[13][0] && mouseYPos <= menuButtons[13][1]) // trash button
                {
                    if (mouseXPos >= menuButtons[12][0] && mouseXPos <= menuButtons[12][1])
                    {
                        chosenTowers = chosenTowersMaster.clone();
                        deck = new tower[7];
                        loadoutCount = 0;
                    }
                }
                break;
            case 2: // Map selection 
                if (mouseYPos >= mapSelectionButtons[0][0] && mouseYPos <= mapSelectionButtons[0][1])
                {
                    if (mouseXPos >= mapSelectionButtons[1][0] && mouseXPos <= mapSelectionButtons[1][1]) // click on map 1
                    {
                        try 
                        {
                            startGame(1);
                            musicStop(0); 
                            musicStart(1);
                            gameState = 3;
                        } 
                        catch (Exception e1) 
                        {
                            e1.getStackTrace();
                        }
                    }
                    else if (mouseXPos >= mapSelectionButtons[2][0] && mouseXPos <= mapSelectionButtons[2][1]) // map 2
                    {
                        try 
                        {
                            startGame(2); 
                            musicStop(0);
                            musicStart(2);
                            gameState = 3;
                        } 
                        catch (Exception e1) 
                        {
                            e1.getStackTrace();
                        }
                    }
                    else if (mouseXPos >= mapSelectionButtons[3][0] && mouseXPos <= mapSelectionButtons[3][1]) // map 3
                    {
                        try 
                        {
                            startGame(3); 
                            musicStop(0);
                            musicStart(3);
                            gameState = 3;
                        } 
                        catch (Exception e1) 
                        {
                            e1.getStackTrace();
                        }
                    }
                }
                else if (mouseYPos >= mapSelectionButtons[4][1] && mouseYPos <= mapSelectionButtons[5][1]) // the back button
                {
                    if (mouseXPos >= mapSelectionButtons[4][0] && mouseXPos <= mapSelectionButtons[5][0])
                    {
                        gameState = 1;
                    }
                }
                break;
            case 3: // In game
                if (deleteMode) // Checking what each clicks be doing 
                {
                    deleteTower();
                }
                else if (selectedTower == null)
                {
                    buyTower();
                }
                else
                {
                    gridTypeMap = towerPlacement(gridTypeMap);
                }
                break;
            case 4: // Insturction menu 
                if (mouseXPos >= menuButtons[5][0] && mouseXPos <= menuButtons[5][1])
                {
                    if (mouseYPos >= menuButtons[4][0] && mouseYPos <= menuButtons[4][1])
                    {
                        gameState = 0;
                    }
                    if (mouseYPos >= menuButtons[6][0] && mouseYPos <= menuButtons[6][1])
                    {
                        gameState = 8;
                    }
                }
                break;
            case 5:
                gameState = 0;
            case 6:
                for (int i = 1; i <= 3; i++)
                {
                    musicStop(i);
                }
                musicStart(0);
                gameState = 0;
            case 7:
            for (int i = 1; i <= 3; i++)
                {
                    musicStop(i);
                }
                musicStart(0);
                gameState = 0;
            case 8:
                if (mouseYPos >= descriptionButtons[0][0] && mouseYPos <= descriptionButtons[0][1])
                {
                    if (mouseXPos >= descriptionButtons[1][0] && mouseXPos <= descriptionButtons[1][1])
                    {
                        if (descriptionPage == 4)
                        {
                            descriptionPage = 0;
                        }
                        else
                        {
                            descriptionPage++;
                        }
                    }
                    else if (mouseXPos >= descriptionButtons[2][0] && mouseXPos <= descriptionButtons[2][1])
                    {
                        gameState = 4;
                    }
                }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (gameState) // For the tower hovering system
        {
            case 3:
            if (selectedTower == null)
            {
                break;
            }
            mouseXPos = e.getX();
            mouseYPos = e.getY();
            break;
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {  
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
