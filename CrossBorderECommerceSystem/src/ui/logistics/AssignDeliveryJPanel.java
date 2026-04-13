package ui.logistics;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.enterprise.LogisticsEnterprise;
import business.shipment.Shipment;
import business.user.UserAccount;

public class AssignDeliveryJPanel extends javax.swing.JPanel {
    private JPanel workArea;
    private LogisticsEnterprise logisticsEnterprise;
    private JTable tblAssign;
    private javax.swing.JButton btnAssign;
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

        tblAssign = new JTable();
        tblAssign.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Shipment ID", "Order ID", "Status", "Assigned Staff", "ETA" }
        ));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tblAssign);

        btnAssign = new javax.swing.JButton("Assign Staff");
        btnAssign.addActionListener(e -> assignStaff());

        btnBack = new javax.swing.JButton("Back to Manage");
        btnBack.addActionListener(e -> goBack());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(titleLabel)
            .addComponent(scrollPane, 700, 700, 700)
            .addGroup(layout.createSequentialGroup()
                .addGap(200)
                .addComponent(btnAssign)
                .addGap(80)
                .addComponent(btnBack)
                .addGap(200))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGap(30)
            .addComponent(titleLabel)
            .addGap(20)
            .addComponent(scrollPane, 300, 300, 300)
            .addGap(30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAssign)
                .addComponent(btnBack))
            .addGap(30)
        );
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblAssign.getModel();
        model.setRowCount(0);

        for (Shipment s : logisticsEnterprise.getShipmentDirectory().getAllShipments()) {
            model.addRow(new Object[] {
                s.getShipmentId(),
                s.getOrderId(),
                s.getStatus(),
                s.getAssignedDeliveryStaff() != null ? s.getAssignedDeliveryStaff() : "Unassigned",
                s.getEta() != null ? s.getEta() : "N/A"
            });
        }
    }

    private void assignStaff() {
        int row = tblAssign.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a shipment first.");
            return;
        }

        String shipmentId = String.valueOf(tblAssign.getValueAt(row, 0));
        Shipment shipment = logisticsEnterprise.getShipmentDirectory().findById(shipmentId);
        if (shipment == null) {
            JOptionPane.showMessageDialog(this, "Shipment not found.");
            return;
        }

        // Build list of delivery staff usernames
        java.util.List<String> staffNames = new java.util.ArrayList<>();
        for (UserAccount ua : logisticsEnterprise.getUserAccountDirectory().getUserAccountList()) {
            if ("DELIVERY_STAFF".equals(ua.getRole())) {
                staffNames.add(ua.getUsername());
            }
        }

        if (staffNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No delivery staff available.");
            return;
        }

        String selected = (String) JOptionPane.showInputDialog(
            this, "Assign delivery staff:", "Assign",
            JOptionPane.QUESTION_MESSAGE, null,
            staffNames.toArray(new String[0]), staffNames.get(0)
        );

        if (selected != null) {
            shipment.setAssignedDeliveryStaff(selected);
            populateTable();
            JOptionPane.showMessageDialog(this, "Assigned " + selected + " to " + shipmentId);
        }
    }

    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}
