package ui.logistics;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.enterprise.LogisticsEnterprise;
import business.shipment.Shipment;

public class TrackShipmentsJPanel extends javax.swing.JPanel {
    private JPanel workArea;
    private LogisticsEnterprise logisticsEnterprise;
    private JTable tblTrack;
    private javax.swing.JButton btnBack;

    public TrackShipmentsJPanel(JPanel workArea, LogisticsEnterprise enterprise) {
        this.workArea = workArea;
        this.logisticsEnterprise = enterprise;
        initComponents();
        populateTrackTable();
    }

    private void initComponents() {
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Shipment Tracking Details");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        tblTrack = new JTable();
        tblTrack.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ShipmentID", "OrderID", "Status", "ETA", "Carrier", "DeliveryStaff" }
        ));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tblTrack);
        btnBack = new javax.swing.JButton("Back to Manage Shipments");
        btnBack.addActionListener(e -> goBack());

        // 布局
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGap(30, 30, 30)
                .addComponent(titleLabel)
                .addGap(20, 20, 20)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBack)
                .addGap(30, 30, 30)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleLabel)
                .addGap(20, 20, 20)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBack)
                .addGap(30, 30, 30)
        );
    }

    // 填充追踪表格
    private void populateTrackTable() {
        DefaultTableModel model = (DefaultTableModel) tblTrack.getModel();
        model.setRowCount(0);

        for (Shipment s : logisticsEnterprise.getShipmentDirectory().getAllShipments()) {
            model.addRow(new Object[] {
                s.getTrackingNumber(),
                s.getOrderId(),
                s.getStatus(),
                s.getEta() != null ? s.getEta() : "N/A",
                s.getCarrier() != null ? s.getCarrier() : "Not Assigned",
                s.getAssignedDeliveryStaff() != null ? s.getAssignedDeliveryStaff() : "Not Assigned"
            });
        }
    }

    // 返回上一页
    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}