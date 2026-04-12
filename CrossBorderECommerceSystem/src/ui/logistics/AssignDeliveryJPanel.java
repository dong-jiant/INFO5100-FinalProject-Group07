package ui.logistics;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.enterprise.LogisticsEnterprise;
import business.shipment.Shipment;

public class  AssignDeliveryJPanel extends javax.swing.JPanel {
    private JPanel workArea;
    private LogisticsEnterprise logisticsEnterprise;
    private JTable tblAssign;
    private javax.swing.JButton btnBack;

    public AssignDeliveryJPanel(JPanel workArea, LogisticsEnterprise enterprise) {
        this.workArea = workArea;
        this.logisticsEnterprise = enterprise;
        initComponents();
        populateTable();
    }

    private void initComponents() {
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Assign Delivery");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        // 表格：ShipmentID OrderID Status ETA
        tblAssign = new JTable();
        tblAssign.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ShipmentID", "OrderID", "Status", "ETA" }
        ));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tblAssign);
        btnBack = new javax.swing.JButton("Back to Manage");
        btnBack.addActionListener(e -> goBack());

        // 布局
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

    // 填充表格
    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblAssign.getModel();
        model.setRowCount(0);

        for (Shipment s : logisticsEnterprise.getShipmentDirectory().getAllShipments()) {
            String eta = (s.getEta() != null) ? s.getEta() : "N/A";
            model.addRow(new Object[] {
                s.getTrackingNumber(),
                s.getOrderId(),
                s.getStatus(),
                eta
            });
        }
    }

    // 返回
    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}

