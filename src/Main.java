import javax.swing.*;
import actionevents.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Main {
            private JFrame Window;
            private JButton FileTransferBtn;
            private JButton RemoteSoftwareInstallationBtn;

            public Main()
            {
                this.Window = new JFrame("FTP CLIENT");
                this.FileTransferBtn = new JButton();
                this.RemoteSoftwareInstallationBtn = new JButton();
                FileTransferBtn.setText("File Transfer");
                FileTransferBtn.setBounds(90,120,220,60);
                ButtonEvents BtnEventsOuter = new ButtonEvents();
                ButtonEvents.FileTransferBtnEvent FileTransferButton = BtnEventsOuter.new FileTransferBtnEvent();
                FileTransferBtn.addActionListener((ActionListener)FileTransferButton);
//                FileTransferBtn.setFont(new Font("Times new roman", Font.PLAIN, 13));
                RemoteSoftwareInstallationBtn.setText("SSH Keypair generator ");
//                RemoteSoftwareInstallationBtn.setFont(new Font("Times new Roman", Font.PLAIN, 13));
                RemoteSoftwareInstallationBtn.setBounds(330,120,220,60);
                ButtonEvents.RemoteSoftwareInstallationBtnEvent RemoteSoftwareInstallationButton = BtnEventsOuter.new RemoteSoftwareInstallationBtnEvent();
                // RemoteSoftwareInstallationBtn.addActionListener((ActionListener) RemoteSoftwareInstallationButton);
                RemoteSoftwareInstallationBtn.addActionListener((ActionListener) BtnEventsOuter.new RemoteSoftwareInstallationBtnEvent());
                Window.setLayout(null);
                Window.setSize(700,500);
                Window.add(FileTransferBtn);
                Window.add(RemoteSoftwareInstallationBtn);
                Window.setVisible(true);
                Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            public static void main(String[] args) {
                Main App = new Main();
            }
}
