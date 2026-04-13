package ui.logistics;

import business.enterprise.LogisticsEnterprise;
import business.network.Network;
import business.shipment.Shipment;
import java.awt.Window;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import ui.main.LoginJFrame;

public class DeliveryJPanel extends javax.swing.JPanel {

    private LogisticsEnterprise logisticsEnterprise;
    private Network network;
    private String deliveryUsername;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JButton btnMarkDelivered;
    private javax.swing.JButton btnFailed;
    private javax.swing.JButton btnLogout;

    public DeliveryJPanel(Network network, LogisticsEnterprise logisticsEnterprise, String deliveryUsername) {
        this.network = network;
        this.logisticsEnterprise = logisticsEnterprise;
        this.deliveryUsername = deliveryUsername;
        initComponents();
        populateMyShipmentsTable();
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel("Delivery Staff Panel — " + deliveryUsername);
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14));

        jTable1 = new javax.swing.JTable();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] { "Shipment ID", "Order ID", "Status", "ETA", "Destination" }
        ));
        jScrollPane1 = new javax.swing.JScrollPane(jTable1);

        btnViewDetails = new javax.swing.JButton("View Details");
        btnMarkDelivered = new javax.swing.JButton("Mark Delivered");
        btnFailed = new javax.swing.JButton("Mark Failed");
        btnLogout = new javax.swing.JButton("Logout");

        btnViewDetails.addActionListener(this::btnViewDetailsActionPerformed);
        btnMarkDelivered.addActionListener(this::btnMarkDeliveredActionPerformed);
        btnFailed.addActionListener(this::btnFailedActionPerformed);
        btnLogout.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
            new LoginJFrame(network).setVisible(true);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnViewDetails)
                        .addGap(40)
                        .addComponent(btnMarkDelivered)
                        .addGap(40)
                        .addComponent(btnFailed)
                        .addGap(40)
                        .addComponent(btnLogout)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(49)
                .addComponent(jLabel1)
                .addGap(18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetails)
                    .addComponent(btnMarkDelivered)
                    .addComponent(btnFailed)
                    .addComponent(btnLogout))
                .addContainerGap(56, Short.MAX_VALUE)
        );
    }

    private void populateMyShipmentsTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        if (logisticsEnterprise == null || deliveryUsername == null) return;

        for (Shipment shipment : logisticsEnterprise.getShipmentDirectory().getAllShipments()) {
            if (!deliveryUsername.equals(shipment.getAssignedDeliveryStaff())) continue;
            model.addRow(new Object[] {
                shipment.getShipmentId(),
                shipment.getOrderId(),
                shipment.getStatus(),
                shipment.getEta(),
                shipment.getDestination()
            });
        }
    }

    private Shipment getSelectedShipment() {
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a shipment first.");
            return null;
        }
        return logisticsEnterprise.getShipmentDirectory().findById(String.valueOf(jTable1.getValueAt(row, 0)));
    }

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {
        Shipment s = getSelectedShipment();
        if (s == null) return;
        JOptionPane.showMessageDialog(this,
            "Shipment ID: " + s.getShipmentId()
            + "\nOrder ID: " + s.getOrderId()
            + "\nStatus: " + s.getStatus()
            + "\nETA: " + s.getEta()
            + "\nDestination: " + s.getDestination()
            + "\nTracking: " + s.getTrackingNumber()
            + "\nNotes: " + (s.getNotes() == null ? "N/A" : s.getNotes()),
            "Shipment Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnMarkDeliveredActionPerformed(java.awt.event.ActionEvent evt) {
        Shipment s = getSelectedShipment();
        if (s == null) return;
        s.setStatus(Shipment.STATUS_DELIVERED);
        s.setDeliveredDate(new Date());
        JOptionPane.showMessageDialog(this, "Shipment marked as Delivered.");
        populateMyShipmentsTable();
    }

    private void btnFailedActionPerformed(java.awt.event.ActionEvent evt) {
        Shipment s = getSelectedShipment();
        if (s == null) return;
        s.setStatus(Shipment.STATUS_RETURNED);
        s.setNotes("Delivery failed");
        JOptionPane.showMessageDialog(this, "Shipment marked as Failed.");
        populateMyShipmentsTable();
    }
}
