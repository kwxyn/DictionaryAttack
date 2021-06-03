import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.text.DecimalFormat;

public class MainFrame extends JFrame implements ActionListener
{
    String uploadPWPath = "";
    String uploadDictPath ="";
    String outputPath="";
    String selectedSHA="";
    JComboBox ComboArea3;
    JTextArea ConsoleArea;
    

 MainFrame()
    {   
        this.setTitle("SHA256 Dictionary Attack"); // Set the title of frame
        this.setSize(800,500); // Set the x-dimension and y-dimension of the frame
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit out of application
        this.setResizable(false);

        ImageIcon image = new ImageIcon("logo.jpg"); // Create an image icon
        this.setIconImage(image.getImage()); // Set icon for the application
        this.getContentPane().setBackground(new Color(0x123456)); // Change colour of background 

        // Panels
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setBounds(10,10,150,100);
        topLeftPanel.setBackground(new Color(0x123456));
        topLeftPanel.setLayout(new GridLayout(3,1,10,10));

        JPanel topRightPanel = new JPanel();
        topRightPanel.setBounds(170,10,600,100);
        topRightPanel.setBackground(new Color(0x123456));
        topRightPanel.setLayout(new GridLayout(3,1,10,10));

        JPanel middlePanel = new JPanel();
        middlePanel.setBounds(10,120,760,280);
        middlePanel.setBackground(Color.BLACK);
        middlePanel.setLayout(new BorderLayout(0,0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(10,410,760,40);
        bottomPanel.setBackground(new Color(0x123456));
        bottomPanel.setLayout(new GridLayout(1,2,30,30));

        // Buttons
        JButton uploadPW = new JButton("Upload password");
        JButton uploadDICT = new JButton("Upload dictionary");
        JButton outputPW = new JButton("SHA algorithm");
        JButton exeButton = new JButton("EXECUTE");

        // Text areas
        JTextArea textArea1 = new JTextArea("Hashed passwords JSON file");
        JTextArea textArea2 = new JTextArea("Dictionary TXT file");
        ConsoleArea = new JTextArea("Ouput console",17,108);
        textArea1.setBackground(Color.BLACK);
        textArea1.setForeground(Color.GREEN);
        textArea2.setBackground(Color.BLACK);
        textArea2.setForeground(Color.GREEN);
    
        ConsoleArea.setBackground(Color.BLACK);
        ConsoleArea.setForeground(Color.GREEN); 
        
        String [] typeOfSHA = {"SHA-1" , "SHA-256" , "SHA-512"};
        ComboArea3 = new JComboBox(typeOfSHA);
        ComboArea3.addActionListener(this);
        ComboArea3.setBackground(Color.BLACK);
        ComboArea3.setForeground(Color.GREEN);

        // Label
        JLabel credits = new JLabel("DEVELOPED BY KWAN");
        credits.setForeground(Color.white);
        credits.setFont(new Font("Futura", Font.PLAIN,14));

        topLeftPanel.add(uploadPW);
        topLeftPanel.add(uploadDICT);
        topLeftPanel.add(outputPW);

        topRightPanel.add(textArea1);
        topRightPanel.add(textArea2);
        topRightPanel.add(ComboArea3);

        middlePanel.add(ConsoleArea);

            // ScrollBar
            JScrollPane scroll = new JScrollPane (ConsoleArea, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        middlePanel.add(scroll);
        bottomPanel.add(credits);
        bottomPanel.add(exeButton);
        
        this.add(topLeftPanel);
        this.add(topRightPanel);
        this.add(middlePanel);
        this.add(bottomPanel);

        scroll.setEnabled(true);
        DefaultCaret caret = (DefaultCaret)ConsoleArea.getCaret(); 
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        PrintStream con = new PrintStream(new TextAreaOutputStream(ConsoleArea));
        System.setOut(con);
        System.setErr(con);

        this.setVisible(true); 

        // Button Behavior
        uploadPW.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ConsoleArea.setText("");
                    JButton open = new JButton("Click me");
                    JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new java.io.File("C://Desktop"));
                    fc.setDialogTitle("Directory");
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fc.setFileFilter(new FileNameExtensionFilter("JSON FILES", "json"));
                    if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
                    {}
                    uploadPWPath = fc.getSelectedFile().getAbsolutePath(); 
                    textArea1.setText(uploadPWPath);
                    ConsoleArea.setText("Hash Passwords uploaded from\n "+uploadPWPath);
                }
                catch (Exception event)
                {
                    System.out.println("Null");
                }

            }
        });

        uploadDICT.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ConsoleArea.setText("");
                    JButton open = new JButton("Click me");
                    JFileChooser dfc = new JFileChooser();
                    dfc.setCurrentDirectory(new java.io.File("C://Desktop"));
                    dfc.setDialogTitle("Directory");
                    dfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    dfc.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
                    if(dfc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
                    {}
                    uploadDictPath = dfc.getSelectedFile().getAbsolutePath();   
                    textArea2.setText(uploadDictPath);
                    ConsoleArea.setText("Dictionary File uploaded from\n "+uploadDictPath);
                }
                catch (Exception event1)
                {
                    System.out.println("Null");
                }
            }
        });


        exeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            //EXECUTE DICTIONARY ATTACK==================
            JSONParser jsonParser = new JSONParser();
            ArrayList <User> users = new  ArrayList <User>();
            try(FileReader reader = new FileReader(uploadPWPath))
            {
                
                long startTime = System.currentTimeMillis(); //start time
                Object obj = jsonParser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray hashedPasswords = (JSONArray) jsonObject.get("HashedPasswords");
                for (int i=0; i<hashedPasswords.size();i++)
                {
                    String hashedPassword = (String)hashedPasswords.get(i);
                    String[] name_hashedPassword = hashedPassword.split(":");
                    String name = name_hashedPassword[0]; // NAME
                    String hash = name_hashedPassword[1]; // HASH
                    users.add(new User(name,hash)); 
                }
                Scanner s = new Scanner(new File(uploadDictPath));
                    while(s.hasNextLine())
                        {
                            String hashedWord="";
                            String word = s.nextLine(); // Get the word from dictionary
                            // System.out.println("Checking "+word);
                            for(int i = 0; i < users.size(); i++)
                            {   
                               if(selectedSHA == "SHA-1")
                                 {
                                    hashedWord = HashUtils.sha1Hash(word);
                                 }
                               else if(selectedSHA == "SHA-256")
                                {
                                    hashedWord = HashUtils.sha256Hash(word); 
                                }
                                else if(selectedSHA == "SHA-512")
                                {
                                    hashedWord = HashUtils.sha512Hash(word); 
                                }
                                
                                // Check for equal hashed values     
                                if(users.get(i).getHash().compareTo(hashedWord)==0) 
                                {
                                    users.get(i).setPassword(word);
                                }
                
                            }	
                        }
                        ConsoleArea.append("RESULT:\n");
                        for(int i = 0; i < users.size(); i++) {
                            System.out.println(users.get(i).getName()+":"+
                            users.get(i).getHash()+ " = "+
                            users.get(i).getPassword());
                        }

                long endTime   = System.currentTimeMillis();
                NumberFormat formatter = new DecimalFormat("#0.00000");
                System.out.println("Execution time: " + formatter.format((endTime - startTime) / 1000d) + " seconds");
                
            }
            catch(Exception exception) {
                System.out.println("ERROR: File Not Found or Wrong file format");
            }  
                }
            });
    }

        // SHA selection 
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == ComboArea3)
            {
                ConsoleArea.setText("");
                selectedSHA = (String) ComboArea3.getSelectedItem();
                System.out.println("Using " +selectedSHA + " algorithm");
                } 
        }
}