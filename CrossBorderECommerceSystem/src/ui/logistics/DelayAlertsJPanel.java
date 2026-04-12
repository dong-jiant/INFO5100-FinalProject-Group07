package ui.logistics;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.enterprise.LogisticsEnterprise;
import business.shipment.Shipment;

public class DelayAlertsJPanel extends javax.swing.JPanel {
    private JPanel workArea;
    private LogisticsEnterprise logisticsEnterprise;
    private JTable tblAlert;
    private javax.swing.JButton btnBack;

    public DelayAlertsJPanel(JPanel workArea, LogisticsEnterprise enterprise) {
        this.workArea = workArea;
        this.logisticsEnterprise = enterprise;
        initComponents();
        populateAlertTable();
    }

    private void initComponents() {
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Delay Alerts");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        // 表格：ShipmentID | OrderID | CurrentStatus | RiskType
        tblAlert = new JTable();
        tblAlert.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ShipmentID", "OrderID", "CurrentStatus", "RiskType" }
        ));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tblAlert);
        btnBack = new javax.swing.JButton("Back to Manage");
        btnBack.addActionListener(e -> goBack());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGap(30)
            .addComponent(titleLabel)
            .addGap(20)
            .addComponent(scrollPane, 700, 700, 700)
            .addGap(30)
            .addComponent(btnBack)
            .addGap(30)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGap(30)
            .addComponent(titleLabel)
            .addGap(20)
            .addComponent(scrollPane, 300, 300, 300)
            .addGap(30)
            .addComponent(btnBack)
            .addGap(30)
        );
    }

    private void populateAlertTable() {
        DefaultTableModel model = (DefaultTableModel) tblAlert.getModel();
        model.setRowCount(0);

        for (Shipment s : logisticsEnterprise.getShipmentDirectory().getAllShipments()) {
            String risk;
            String status = s.getStatus();
            
            // 自动判断风险类型
            if (status.equalsIgnoreCase("Delivered")) {
                risk = "Normal";
            } else if (status.equalsIgnoreCase("In Transit")) {
                risk = "Medium Risk";
            } else {
                risk = "High Risk";
            }

            model.addRow(new Object[] {
                s.getTrackingNumber(),
                s.getOrderId(),
                status,
                risk
            });
        }
    }

    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}