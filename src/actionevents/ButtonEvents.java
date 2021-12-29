package actionevents;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.jcraft.jsch.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
//import java.io.File;
import com.jcraft.jsch.JSchException;
import net.sf.jcarrierpigeon.*;


public class ButtonEvents {
//  Components for file transfer Window!
    private JFrame Window;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JCheckBox UsePrivateKey;
    private JTextField TextFieldForHostname;
    private JTextField TextFieldForUsername;
    private JPasswordField FieldForPassword;
    private JTextField FieldForServerPath;
    private JTextField FieldForPortNumber;
    private JButton ButtonForFileChoosing;
    private JButton TransferFileBtn;
    private JButton ChoosePrivateKey;
    public static String FilePath;
    public String Password;
    public String PrivateKeyPath;

    private JButton getSavedSession;
    private DefaultListModel<String> listModel;
    private JList<String> sessionList;
    private JButton saveSessionBtn;
    private JButton loadSessionBtn;
    private JLabel savedSessionLabel;
//  Components for Remote software installation window
    public int type;


    public class FileTransferBtnEvent implements ActionListener {

        //        private JPasswordField PasswordFieldForPassword;
//        private JFileChooser FileToBeTransfered;
        public void actionPerformed(ActionEvent e) {
            Window = new JFrame("File Transfer");
            Label1 = new JLabel();
            Label2 = new JLabel();
            Label3 = new JLabel();
            Label4 = new JLabel();
            Label5 = new JLabel();
            savedSessionLabel = new JLabel();
            TextFieldForHostname = new JTextField();
            TextFieldForUsername = new JTextField();
            ChoosePrivateKey = new JButton();
            ButtonForFileChoosing = new JButton();
            TransferFileBtn = new JButton();
            FieldForPassword = new JPasswordField();
            UsePrivateKey = new JCheckBox();
            FieldForPortNumber = new JTextField();
            listModel = new DefaultListModel<>();
//            listModel.addElement("Item1");
//            listModel.addElement("Item2");
//          database related stuff comes here !
            try{
                Connection c = null;
                Statement stmt = null;
                c = DriverManager.getConnection("jdbc:sqlite:sessions.db");
                String query = "SELECT sessionname from SAVEDSESSIONS";
                stmt = c.createStatement();
                ResultSet result = stmt.executeQuery(query);
                while(result.next() != false){
                    String sessionName = result.getString("sessionname");
                    listModel.addElement(sessionName);
                }

            }
            catch(Exception exception){
                System.out.println("");
            }
            sessionList = new JList<>(listModel);
            sessionList.setBounds(450,150,100,120);
            saveSessionBtn = new JButton();
            loadSessionBtn = new JButton();
            Label1.setBounds(20, 20, 140, 60);
            Label1.setText("Enter IP address :");
            Label1.setFont(new Font("Verdana", Font.PLAIN, 15));
            Label2.setText("Enter Username : ");
            Label2.setBounds(20, 70, 140, 60);
            Label2.setFont(new Font("Verdana", Font.PLAIN, 15));
            Label3.setText("Enter Password : ");
            Label3.setBounds(20, 120, 140, 60);
            Label3.setFont(new Font("Verdana", Font.PLAIN, 15));
            Label4.setText("Port :");
            Label4.setBounds(20, 160, 140, 60);
            Label4.setFont(new Font("Verdana", Font.PLAIN, 15));
            Label5.setBounds(20, 250, 140, 60);
            Label5.setText("Server Path :");
            Label5.setFont(new Font("Verdana", Font.PLAIN, 15));
            savedSessionLabel.setText("Saved Sessions");
            savedSessionLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
            savedSessionLabel.setBounds(450,100,160,40);
            saveSessionBtn.setBounds(450,290,160,40);
            saveSessionBtn.setText("Save session");
            saveSessionBtn.setFont(new Font("Verdana", Font.PLAIN, 15));
            saveSessionBtn.addActionListener((ActionListener) new SaveSessionBtnEvent());
            loadSessionBtn.setBounds(450,350,160,40);
            loadSessionBtn.setText("Load session");
            loadSessionBtn.setFont(new Font("Verdana", Font.PLAIN, 15));
            loadSessionBtn.addActionListener((ActionListener) new LoadsessionBtnEvent());
            FieldForPortNumber.setBounds(170, 180, 180, 30);
            TextFieldForHostname.setBounds(170, 40, 180, 30);
            TextFieldForHostname.setFont(new Font("Verdana", Font.PLAIN, 13));
            TextFieldForUsername.setBounds(170, 95, 180, 30);
            TextFieldForUsername.setFont(new Font("Verdana", Font.PLAIN, 13));
            FieldForPassword.setBounds(170, 140, 180, 30);
            FieldForPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
            UsePrivateKey.setBounds(190, 320, 180, 40);
            UsePrivateKey.setFont(new Font("Verdana", Font.PLAIN, 15));
            UsePrivateKey.setText("Use Private Key");
            UsePrivateKey.addItemListener((ItemListener) new UsePrivateKeyCheckBoxEvent());
            FieldForServerPath = new JTextField();
            FieldForServerPath.setBounds(170, 270, 180, 30);
            FieldForServerPath.setFont(new Font("Verdana", Font.PLAIN, 13));
            ChoosePrivateKey.setBounds(170, 370, 180, 40);
            ChoosePrivateKey.setText("Choose Private Key");
            ChoosePrivateKey.setFont(new Font("Verdana", Font.PLAIN, 15));
            ChoosePrivateKey.addActionListener((ActionListener) new ChoosePrivateKeyBtnEvent());
            ButtonForFileChoosing.setBounds(170, 420, 180, 40);
            ButtonForFileChoosing.setText("Choose File ");
            ButtonForFileChoosing.setFont(new Font("Verdana", Font.PLAIN, 15));
            ButtonForFileChoosing.addActionListener((ActionListener) new FileChoosingButtonEvent());
            TransferFileBtn.setText("Transfer");
            TransferFileBtn.setBounds(170, 470, 180, 40);
            TransferFileBtn.setFont(new Font("Verdana", Font.PLAIN, 15));
            TransferFileBtn.addActionListener((ActionListener) new TransferFileBtnEvent());
            Window.setLayout(null);
            Window.setSize(700, 650);
            Window.add(Label1);
            Window.add(Label2);
            Window.add(Label3);
            Window.add(TextFieldForHostname);
            Window.add(TextFieldForUsername);
            Window.add(FieldForPassword);
            Window.add(UsePrivateKey);
            Window.add(ChoosePrivateKey);
            Window.add(ButtonForFileChoosing);
            Window.add(TransferFileBtn);
            Window.add(Label4);
            Window.add(FieldForPortNumber);
            Window.add(FieldForServerPath);
            Window.add(Label5);
            Window.add(sessionList);
            Window.add(saveSessionBtn);
            Window.add(loadSessionBtn);
            Window.add(savedSessionLabel);
            Window.setVisible(true);
        }
    }


    class FileChoosingButtonEvent implements ActionListener {
//        @Override

        public void actionPerformed(ActionEvent e) {
            JFileChooser FileToBeTransfered = new JFileChooser();
            int result = FileToBeTransfered.showOpenDialog(ButtonForFileChoosing);
//            if result is equal to zero then only
            if (result == JFileChooser.APPROVE_OPTION) {
//                JOptionPane.showMessageDialog(ButtonForFileChoosing,"You selected " + FileToBeTransfered.getSelectedFile().getPath());
                FilePath = FileToBeTransfered.getSelectedFile().getPath();
            }

//            Password = JOptionPane.showInputDialog("Enter password for user :");


        }
    }

    public class UsePrivateKeyCheckBoxEvent implements ItemListener {
//        @Override


        @Override
        public void itemStateChanged(ItemEvent e) {

            if(e.getStateChange() == e.SELECTED){
                FieldForPassword.setEnabled(false);
            }
            else if(e.getStateChange() == e.DESELECTED){
                FieldForPassword.setEnabled(true);
            }
        }

    }

        public class ChoosePrivateKeyBtnEvent implements ActionListener {
            //        @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser PrivateKeyFilePath = new JFileChooser();
                int result = PrivateKeyFilePath.showOpenDialog(ButtonForFileChoosing);
//            if result is equal to zero then only
                if (result == JFileChooser.APPROVE_OPTION) {
//                JOptionPane.showMessageDialog(ButtonForFileChoosing,"You selected " + FileToBeTransfered.getSelectedFile().getPath());
                    PrivateKeyPath = PrivateKeyFilePath.getSelectedFile().getPath();
                }


            }
        }
    

        public class TransferFileBtnEvent implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
//            JOptionPane.showMessageDialog(TransferFileBtn,FilePath + " is successfully transfered");
//            File transfer code using jsch comes here !
                JSch jsch = new JSch();
                if(PrivateKeyPath == null){
                    try {
                        Session session = jsch.getSession(TextFieldForUsername.getText(), TextFieldForHostname.getText(), Integer.parseInt(FieldForPortNumber.getText()));
                        Properties config = new Properties();
                        session.setConfig("StrictHostKeyChecking", "no");
                        jsch.setKnownHosts("C:\\Users\\BHUSHAN\\.ssh\\known_hosts");
                        session.setPassword(String.valueOf(FieldForPassword.getPassword()));
                        session.connect();

                        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
                        sftpChannel.connect();
                        String localFileLocation = FilePath;
                        String remoteFileLocation = FieldForServerPath.getText();
                        sftpChannel.put(localFileLocation, remoteFileLocation);
                        session.disconnect();
                        sftpChannel.exit();
                        ButtonEvents instance = new ButtonEvents();
                        DesktopNotificationGenerator.generateDesktopNotification("File Transferred Successfully !");




                    }
                    catch (JSchException | InterruptedException | SftpException | IOException | AWTException jSchException) {
                        jSchException.printStackTrace();
                    }

//                catch (SftpException | InterruptedException sftpException) {
//                    sftpException.printStackTrace();
//                }

                 }
                else if(PrivateKeyPath != null){
                    try{
                        jsch.addIdentity(PrivateKeyPath);
                        Thread.sleep(4000);

                        System.out.println("Private key added");

                        Session session =  jsch.getSession(TextFieldForUsername.getText(), TextFieldForHostname.getText(), Integer.parseInt(FieldForPortNumber.getText()));
                        session.setConfig("PreferredAuthentications", "P");
                        Properties config = new Properties();
                        config.put("PreferredAuthentications","publickey");
                        config.put("trust","true");
                        config.put("StrictHostKeyChecking","no");
                        session.setConfig(config);
                        session.connect();
//                        System.out.println("Session connected !");
                        Channel channel = session.openChannel("sftp");
                        channel.setInputStream(System.in);
                        channel.setOutputStream(System.out);
                        channel.connect();
                        ChannelSftp c = (ChannelSftp) channel;
                        String localFileLocation = FilePath;
                        String remoteFileLocation = FieldForServerPath.getText();
                        c.put(localFileLocation,remoteFileLocation);
                        c.exit();
                        session.disconnect();
                        channel.disconnect();


                        Thread.sleep(2000);
                        DesktopNotificationGenerator.generateDesktopNotification("File Transferred Successfully !");
                    }
                    catch(Exception e3){
                        System.out.println("Could not make connection !");
                        System.err.println(e3);
                    }

                }
            }
        }

        public class RemoteSoftwareInstallationBtnEvent implements ActionListener {
            @Override

            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                JCheckBox rsaCheckbox = new JCheckBox();
                JCheckBox dsaCheckbox = new JCheckBox();
                JButton generateKeyBtn = new JButton();
                generateKeyBtn.setText("Generate Keys");
                rsaCheckbox.setText("RSA Algorithm");
                rsaCheckbox.setBounds(40,40,160,40);
                rsaCheckbox.setFont(new Font("Verdana", Font.PLAIN, 15));
                dsaCheckbox.setText("DSA Algorithm");
                dsaCheckbox.setBounds(40,90,160,40);
                dsaCheckbox.setFont(new Font("Verdana", Font.PLAIN, 15));
                generateKeyBtn.setBounds(40,150,170,40);
                generateKeyBtn.addActionListener(new generateBtnEventListener());
                window.setSize(700,500);
                window.add(rsaCheckbox);
                window.add(dsaCheckbox);
                window.add(generateKeyBtn);
                window.setLayout(null);
                window.setVisible(true);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            class rsaCheckboxEventListener implements ItemListener{
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == 1){
                        type = 2;
                    }

                }
            }

            class dsaCheckboxEventListener implements ItemListener{
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == 1){
                        type = 3;
                    }
                }
            }

            class generateBtnEventListener implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
//                    System.out.println("This program is going to generate SSH Key pairs using jsch");
                    try {
                        JSch jsch = new JSch();

                        KeyPair kpair = KeyPair.genKeyPair(jsch, 2);
//                    kpair.setPassphrase("");
                        String filename = "C:\\Users\\BHUSHAN\\.ssh\\passkeyss_id_rsa";
                        kpair.writePrivateKey(filename);
                        kpair.writePublicKey(filename + ".pub", "");
//                        System.out.println("Finger print: " + kpair.getFingerPrint());
                        DesktopNotificationGenerator.generateDesktopNotification("Finger print: " + kpair.getFingerPrint());
                        kpair.dispose();
                        Thread.sleep(6);
                       DesktopNotificationGenerator.generateDesktopNotification("Created SSH key pairs inside the .ssh folder s");
                    }
                    catch(Exception e5){
                        System.out.println("Error occured !");
                    }
                }
            }
        }

        public class LoadsessionBtnEvent implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
//                load saved session data comes here
                if(sessionList.getSelectedIndex() != -1){
                    try{
                        String session = sessionList.getSelectedValue();
                        String sql = "select * from SAVEDSESSIONS where sessionname = "+ "'"+session+"'" + ";";
                        try{
                            Connection c = null;
                            Statement stmt = null;
                            c = DriverManager.getConnection("jdbc:sqlite:sessions.db");
//                            String query = "SELECT sessionname from SAVEDSESSIONS";
                            stmt = c.createStatement();
                            ResultSet result = stmt.executeQuery(sql);
                            String hostname,port,serverpath,username,password;
                            hostname = result.getString("host");
                            port = result.getString("port");
                            serverpath = result.getString("serverpath");
                            username = result.getString("username");
                            password = result.getString("password");

                            TextFieldForHostname.setText(hostname);
                            TextFieldForUsername.setText(username);
                            FieldForPassword.setText(password);
                            FieldForPortNumber.setText(port);
                            FieldForServerPath.setText(serverpath);
                            stmt.close();
                            c.commit();
                            c.close();

                        }
                        catch(Exception exception){
                            System.out.println("");
                        }
                    }
                    catch(Exception ee){
                        try {
                            DesktopNotificationGenerator.generateDesktopNotification("Error occured while loading saved session !");
                        }
                        catch(Exception eee){
                            System.out.println("");
                        }
                    }
                }
            }
        }
        public class SaveSessionBtnEvent implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection c = null;
                    Statement stmt = null;
                    c = DriverManager.getConnection("jdbc:sqlite:sessions.db");

                    stmt = c.createStatement();
                    String sql = null;
                    String jhost = TextFieldForHostname.getText();
                    String jusername = TextFieldForUsername.getText();
                    String jport = FieldForPortNumber.getText();
                    String jpassword = new String(FieldForPassword.getPassword());
                    String jserverPath = FieldForServerPath.getText();

                    sql = "insert into SAVEDSESSIONS (host, port, serverpath, username, password,sessionname) VALUES (" + "'" +jhost+"'" + "," + "'"+jport+"'"+","+"'"+jserverPath+"'"+","+"'"+jusername+"'"+","+"'"+jpassword +"'"+ "," +"null"+");";

                    stmt.execute(sql);
//                  update statement
                    /*
                        update SAVEDSESSIONS
                        set sessionname = 'input-value'
                        where host = jhost and username = jusername and password = jpassword and serverpath = jserverpath and port = jport;


                    */
//                    sql = "update SAVEDSESSIONS "+  "set";
                    String jsessionname = JOptionPane.showInputDialog(Window,"Set a name for the session :");
                    sql = "update SAVEDSESSIONS set sessionname = " +"'" +jsessionname +"'" + " where host = " + "'" + jhost + "'" + ";" ;

                    stmt.execute(sql);
                    DesktopNotificationGenerator.generateDesktopNotification("Session saved successfully !");
                    listModel.addElement(jsessionname);
                    stmt.close();
                    c.commit();
                    c.close();



                }
                catch(Exception exception){
                    System.out.println("Error " + exception.getMessage());
                }
            }
        }
    }


class DesktopNotificationGenerator{
    public static void generateDesktopNotification(String message) throws IOException, InterruptedException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("file-transfer.ico");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Successful", message, TrayIcon.MessageType.INFO);
        Thread.sleep(7000);
//        System.exit(0);
    }
}



