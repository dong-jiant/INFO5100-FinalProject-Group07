package ui.logistics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import business.enterprise.LogisticsEnterprise;
import business.network.Network;
import ui.main.LoginJFrame;

public class LogisticsWorkAreaJPanel extends javax.swing.JPanel {

    private Network network;
    private LogisticsEnterprise enterprise;
    private JPanel workArea;

    public LogisticsWorkAreaJPanel(Network network, LogisticsEnterprise enterprise) {
        this.network = network;
        this.enterprise = enterprise;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        JButton btnManageShipments = new JButton("Manage Shipments");
        JButton btnTrack = new JButton("Track Shipments");
        JButton btnAssign = new JButton("Assign Delivery");
        JButton btnAlerts = new JButton("Delay Alerts");
        JButton btnLogout = new JButton("Logout");

        btnManageShipments.addActionListener(e -> openManageShipments());
        btnTrack.addActionListener(e -> openTrack());
        btnAssign.addActionListener(e -> openAssign());
        btnAlerts.addActionListener(e -> openAlerts());
        btnLogout.addActionListener(e -> logout());

        menuPanel.add(btnManageShipments);
        menuPanel.add(btnTrack);
        menuPanel.add(btnAssign);
        menuPanel.add(btnAlerts);
        menuPanel.add(btnLogout);

        workArea = new JPanel(new CardLayout());
        workArea.add(new JLabel("Logistics Coordinator Dashboard"), "HOME");

        add(menuPanel, BorderLayout.NORTH);
        add(workArea, BorderLayout.CENTER);
    }

    private void openManageShipments() {
        ManageShipmentsJPanel panel = new ManageShipmentsJPanel(workArea, network);
        workArea.add(panel, "ManageShipmentsJPanel");
        ((CardLayout) workArea.getLayout()).show(workArea, "ManageShipmentsJPanel");
    }

    private void openTrack() {
        TrackShipmentsJPanel panel = new TrackShipmentsJPanel(workArea, enterprise);
        workArea.add(panel, "TrackShipmentsJPanel");
        ((CardLayout) workArea.getLayout()).show(workArea, "TrackShipmentsJPanel");
    }

    private void openAssign() {
        AssignDeliveryJPanel panel = new AssignDeliveryJPanel(workArea, enterprise);
        workArea.add(panel, "AssignDeliveryJPanel");
        ((CardLayout) workArea.getLayout()).show(workArea, "AssignDeliveryJPanel");
    }

    private void openAlerts() {
        DelayAlertsJPanel panel = new DelayAlertsJPanel(workArea, enterprise);
        workArea.add(panel, "DelayAlertsJPanel");
        ((CardLayout) workArea.getLayout()).show(workArea, "DelayAlertsJPanel");
    }

    private void logout() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
        new LoginJFrame(network).setVisible(true);
    }
}
