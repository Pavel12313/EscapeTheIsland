import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EscapeTheIsland extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JButton startButton;
    private JButton restartButton;
    private JButton quitButton;
    private JButton exploreButton;
    private JButton gatherButton;
    private JButton craftButton;
    private JButton solveButton;
    private JButton restButton;
    
    private JTextArea textArea;
    private JScrollPane scrollPane;
    
    private boolean escapeSuccess;
    private int playerHealth;
    private int playerHunger;
    private int playerThirst;
    private int daysOnIsland;
    private String[] inventory;
    private int inventorySize;
    private JLabel healthLabel;
    private JLabel hungerLabel;
    private JLabel thirstLabel;
    private JLabel daysLabel;

  public EscapeTheIsland() {
    // Set up the frame
    setTitle("Escape the Island");
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    ImageIcon image = new ImageIcon("./island2.png");

    // Create the JLabel object and pass the ImageIcon object as an argument
    JLabel imageLabel = new JLabel(image);

    // Add the JLabel object to the GUI
    add(imageLabel);

    imageLabel.setPreferredSize(new Dimension(200, 200));

    // Create the buttons
    startButton = new JButton("Start");
    restartButton = new JButton("Restart");
    quitButton = new JButton("Quit");
    exploreButton = new JButton("Explore");
    gatherButton = new JButton("Gather");
    craftButton = new JButton("Craft");
    solveButton = new JButton("Solve");
    restButton = new JButton("Rest");

    // Add action listeners to the buttons
    startButton.addActionListener(this);
    restartButton.addActionListener(this);
    quitButton.addActionListener(this);
    exploreButton.addActionListener(this);
    gatherButton.addActionListener(this);
    craftButton.addActionListener(this);
    solveButton.addActionListener(this);



    // Create the text area and scroll pane
    textArea = new JTextArea(20, 40);
    scrollPane = new JScrollPane(textArea);
    textArea.setEditable(false);
    restButton.addActionListener(this);
    healthLabel = new JLabel("Health: 100");
    hungerLabel = new JLabel("Hunger: 0");
    thirstLabel = new JLabel("Thirst: 0");
    daysLabel = new JLabel("Days: 0");

    // Add the buttons and scroll pane to the frame
    add(startButton);
    add(restartButton);
    add(quitButton);
    add(exploreButton);
    add(gatherButton);
    add(craftButton);
    add(solveButton);
    add(restButton);
    add(scrollPane);
    add(healthLabel); 
    add(thirstLabel);
    add(hungerLabel);
    add(daysLabel);

    // Initialize the game variables
    escapeSuccess = false;
    playerHealth = 100;
    playerHunger = 0;
    playerThirst = 0;
    daysOnIsland = 0;
    inventory = new String[10];
    inventorySize = 0;

    // Disable the restart, explore, gather, craft, solve, and rest buttons until the start button is clicked
    restartButton.setEnabled(false);
    exploreButton.setEnabled(false);
    gatherButton.setEnabled(false);
    craftButton.setEnabled(false);
    solveButton.setEnabled(false);
    restButton.setEnabled(false);
  }
  public void displayPlayerStatus() {
    hungerLabel.setText("Hunger: " + playerHunger);
    healthLabel.setText("Health: " + playerHealth);
    thirstLabel.setText("Thirst: " + playerThirst);
    daysLabel.setText("Days: " + daysOnIsland); 
    
  }
  
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand(); // Process player input
    if(action.equals("Start")) {
      // Print the introduction
      textArea.append("Escape the Island へようこそ！\n");
      textArea.append("あなたは孤島に放り出されており、脱出する方法を見つける必要があります。\n");      

      // Enable the restart, explore, gather, craft, solve, and rest buttons
      restartButton.setEnabled(true);
      exploreButton.setEnabled(true);
      gatherButton.setEnabled(true);
      craftButton.setEnabled(true);
      solveButton.setEnabled(true);
      restButton.setEnabled(true);
    } else if (action.equals("Restart")) {
      // Reset the game variables and clear the text area
      escapeSuccess = false;
      playerHealth = 100;
      playerHunger = 0;
      playerThirst = 0;
      daysOnIsland = 0;
      inventory = new String[10];
      inventorySize = 0;
      textArea.setText("");
      textArea.append("ゲームがリスタートされました。\n");
    } else if (action.equals("Quit")) {
      // End the game
      System.exit(0);
    } else if (action.equals("Explore")) 
    {
      // Explore the island and potentially find items or clues
      int random = (int)(Math.random() * 10);
      if (random < 3) {
        textArea.append("あなたは新鮮な水源を見つけました！\n");
        playerThirst = 0;
      } else if (random < 6) {
        textArea.append("あなたは脱出ラフトの一部を見つけました！\n");
        if (inventorySize < 10) {
          inventory[inventorySize] = "raft piece";
          inventorySize++;
        } else {
          textArea.append("あなたのインベントリがいっぱいのため、ラフトのピースを拾うことができません。\n");
        }
      } else if (random < 8) {
        textArea.append("あなたは食べることのできる果物を見つけました！\n");
        playerHunger = 0;
      } else {
        textArea.append("探索中に何も見つかりませんでした。\n");
      }
      daysOnIsland++;
      playerHunger += 10;
      playerThirst += 7;
      playerHealth -= 10;
      displayPlayerStatus();
      if (playerHealth <= 0) {
        textArea.append("あなたは飢餓や脱水で死亡しました。ゲームオーバーです。\n");
        restartButton.setEnabled(true);
        exploreButton.setEnabled(false);
        gatherButton.setEnabled(false);
        craftButton.setEnabled(false);
        solveButton.setEnabled(false);
        restButton.setEnabled(false);
      }
    } else if (action.equals("Gather")) {
      // Gather resources and potentially find items
      int random = (int)(Math.random() * 10);
      if (random < 3) {
        textArea.append("木板を見つけました！\n");
        if (inventorySize < 10) {
          String item = "wood";
          inventory[inventorySize] = item;
          inventorySize++;
        } else {
          textArea.append("あなたのインベントリがいっぱいのため、木板を拾うことができません。\n");
        }
      } else if (random < 5) {
        textArea.append("巻物として使用できる葉を見つけました！\n");
        if (inventorySize < 10) {
          inventory[inventorySize] = "bandage";
          inventorySize++;
        } else {
          textArea.append("あなたのインベントリがいっぱいのため、巻物を拾うことができません。\n");
        }
      } else {
        textArea.append("リソースを収集している間に何も見つかりませんでした。\n");
      }
      daysOnIsland++;
      playerHunger += 6;
      playerThirst += 14;
      playerHealth -= 10;
      displayPlayerStatus();
      if (playerHealth <= 0) {
        textArea.append("あなたは飢餓や脱水で死亡しました。ゲームオーバーです。\n");
        restartButton.setEnabled(true);
        exploreButton.setEnabled(false);
        gatherButton.setEnabled(false);
        craftButton.setEnabled(false);
        solveButton.setEnabled(false);
        restButton.setEnabled(false);
      }
    } else if (action.equals("Craft")) {
      // Attempt to craft an item using resources in the inventory
      boolean raftPieceFound = false;
      boolean woodFound = false;
      for (int i = 0; i < inventorySize; i++) {
        if (inventory[i].equals("raft piece")) {
          raftPieceFound = true;
        } else if (inventory[i].equals("wood")) {
          woodFound = true;
        }
      }
      if (raftPieceFound && woodFound) {
        textArea.append("ラフトをクラフトしました！\n");
        escapeSuccess = true;
      } else {
        textArea.append("ラフトをクラフトするための必要なリソースを持っていません。\n");
      }
      daysOnIsland++;
      playerHunger++;
      playerThirst++;
      playerHealth -= 5;
      displayPlayerStatus();
      if (playerHealth <= 0) {
        textArea.append("あなたは飢餓や脱水で死亡しました。ゲームオーバーです。.\n");
        restartButton.setEnabled(true);
        exploreButton.setEnabled(false);
        gatherButton.setEnabled(false);
        craftButton.setEnabled(false);
        solveButton.setEnabled(false);
        restButton.setEnabled(false);
        }
        } else if (action.equals("Solve")) {
        // Attempt to solve a puzzle and potentially find a clue
        int random = (int)(Math.random() * 10);
        if (random < 5) {
        textArea.append("パズルを解決し、手がかりを見つけました！\n");
        if (inventorySize < 10) {
        inventory[inventorySize] = "clue";
        inventorySize++;
        } else {
        textArea.append("あなたのインベントリがいっぱいのため、手がかりを拾うことができません。\n");
        }
        } else {
        textArea.append("パズルを解決できませんでした。\n");
        }
        daysOnIsland++;
        playerHunger++;
        playerThirst++;
        playerHealth -= 5;
        displayPlayerStatus();
        if (playerHealth <= 0) {
        textArea.append("あなたは飢餓や脱水で死亡しました。ゲームオーバーです。\n");
        restartButton.setEnabled(true);
        exploreButton.setEnabled(false);
        gatherButton.setEnabled(false);
        craftButton.setEnabled(false);
        solveButton.setEnabled(false);
        restButton.setEnabled(false);
      }
      } else if (action.equals("Rest")) {
      // Rest to restore health
      textArea.append("あなたは休んで健康を取り戻しました。\n");
      playerHealth += 15;
      if (playerHealth > 100) {
      playerHealth = 100;
      }
      daysOnIsland++;
      playerHunger++;
      playerThirst++;
      displayPlayerStatus();
      } else {
      textArea.append("\n");
      }
      
      if (escapeSuccess) {
      textArea.append("おめでとうございます、あなたは島を脱出しました！\n");
      restartButton.setEnabled(true);
      exploreButton.setEnabled(false);
      gatherButton.setEnabled(false);
      craftButton.setEnabled(false);
      solveButton.setEnabled(false);
      restButton.setEnabled(false);
      }
if (escapeSuccess) {
  // Print a message and end the game if the player has successfully escaped the island
  textArea.append("おめでとうございます、あなたは島を脱出しました！\n");
  textArea.append("脱出するのに " + daysOnIsland + " 日かかりました。\n");
  restartButton.setEnabled(true);
  exploreButton.setEnabled(false);
  gatherButton.setEnabled(false);
  craftButton.setEnabled(false);
  solveButton.setEnabled(false);
  restButton.setEnabled(false);
}
}

public static void main(String[] args) {
EscapeTheIsland frame = new EscapeTheIsland();
frame.setVisible(true);
}
}
