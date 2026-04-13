package ui.logistics;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import business.enterprise.Enterprise;
import business.enterprise.LogisticsEnterprise;
import business.network.Network;
import business.shipment.Shipment;

/**
 *
 * @author admin
 */
public class ManageShipmentsJPanel extends javax.swing.JPanel {
    private JPanel workArea;
    private Network network;
    private LogisticsEnterprise logisticsEnterprise;

    private javax.swing.JButton buttonA; // Track Shipments
    private javax.swing.JButton buttonB; // Assign Delivery
    private javax.swing.JButton buttonC; // Delay Alerts

    public ManageShipmentsJPanel(JPanel workArea, Network network) {
        initComponents();
        this.workArea = workArea;
        this.network = network;
        
        // Initialize logistics enterprise
        for (Enterprise e : network.getEnterprises()) {
            if (e instanceof LogisticsEnterprise) {
                this.logisticsEnterprise = (LogisticsEnterprise) e;
                break;
            }
        }
        
        populateTable();
        
        // Bind all button events
        buttonA.addActionListener(e -> openTrackShipmentsPanel());
        buttonB.addActionListener(e -> openAssignDeliveryPanel());
        buttonC.addActionListener(e -> openDelayAlertsPanel());
    }

    // Open tracking panel
    private void openTrackShipmentsPanel() {
        if (logisticsEnterprise == null) {
            JOptionPane.showMessageDialog(this, "Logistics Enterprise not found!");
            return;
        }
        TrackShipmentsJPanel panel = new TrackShipmentsJPanel(workArea, logisticsEnterprise);
        workArea.add(panel, "TrackShipmentsJPanel");
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.show(workArea, "TrackShipmentsJPanel");
    }
    
    // Open assign delivery panel
    private void openAssignDeliveryPanel() {
        if (logisticsEnterprise == null) {
            JOptionPane.showMessageDialog(this, "Logistics Enterprise not found!");
            return;
        }
        AssignDeliveryJPanel panel = new AssignDeliveryJPanel(workArea, logisticsEnterprise);
        workArea.add(panel, "AssignDeliveryJPanel");
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.show(workArea, "AssignDeliveryJPanel");
    }
    
    // Open delay alerts panel
    private void openDelayAlertsPanel() {
        if (logisticsEnterprise == null) {
            JOptionPane.showMessageDialog(this, "Logistics Enterprise not found!");
            return;
        }
        DelayAlertsJPanel panel = new DelayAlertsJPanel(workArea, logisticsEnterprise);
        workArea.add(panel, "DelayAlertsJPanel");
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.show(workArea, "DelayAlertsJPanel");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShipmentss = new javax.swing.JTable();
        btnAddShipments = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();

        buttonA = new javax.swing.JButton("Track Shipments");
        buttonB = new javax.swing.JButton("Assign Delivery");
        buttonC = new javax.swing.JButton("Delay Alerts");

        jLabel1.setText("Manage Shipments");

        tblShipmentss.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Shipment ID", "Order ID", "Origin", "Status", "Date"
            }
        ));
        jScrollPane1.setViewportView(tblShipmentss);

        btnAddShipments.setText("Add Shipments");
        btnAddShipments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddShipmentsActionPerformed(evt);
            }
        });

        jButton1.setText("Delete Shipments");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnBack)
                .addGap(78, 78, 78)
                .addComponent(jButton1)
                .addGap(80, 80, 80)
                .addComponent(btnAddShipments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnNext)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(buttonA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(buttonB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(buttonC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddShipments)
                    .addComponent(jButton1)
                    .addComponent(btnBack)
                    .addComponent(btnNext))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonA)
                    .addComponent(buttonB)
                    .addComponent(buttonC))
                .addGap(40, 40, 40)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }

    private void btnAddShipmentsActionPerformed(java.awt.event.ActionEvent evt) {
        // Create dialog input fields
        javax.swing.JTextField trackingField = new javax.swing.JTextField();
        javax.swing.JTextField orderIdField = new javax.swing.JTextField();
        javax.swing.JTextField supplierField = new javax.swing.JTextField();
        javax.swing.JComboBox<String> statusBox = new javax.swing.JComboBox<>(
            new String[]{"Pending", "In Transit", "Delivered", "Failed"}
        );

        // Additional fields
        javax.swing.JTextField etaField = new javax.swing.JTextField();
        javax.swing.JTextField carrierField = new javax.swing.JTextField();
        javax.swing.JTextField originField = new javax.swing.JTextField();
        javax.swing.JTextField destinationField = new javax.swing.JTextField();
        javax.swing.JTextField notesField = new javax.swing.JTextField();
        javax.swing.JTextField deliveryStaffField = new javax.swing.JTextField();

        // Assemble dialog content
        Object[] fields = {
        	"Order ID:", orderIdField,
            "Tracking Number:", trackingField,
            "Supplier:", supplierField,
            "ETA:", etaField,
            "Carrier:", carrierField,
            "Origin:", originField,
            "Destination:", destinationField,
            "Notes:", notesField,
            "Delivery Staff:", deliveryStaffField,
            "Status:", statusBox,
        };

        // 弹出输入框
        int result = JOptionPane.showConfirmDialog(this, fields, "Add New Shipment",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Get input values
            String trackingNumber = trackingField.getText().trim();
            String orderId = orderIdField.getText().trim();
            String supplier = supplierField.getText().trim();
            String status = (String) statusBox.getSelectedItem();

            // Get additional field values
            String etaStr = etaField.getText().trim();
            String carrier = carrierField.getText().trim();
            String origin = originField.getText().trim();
            String destination = destinationField.getText().trim();
            String notes = notesField.getText().trim();
            String deliveryStaff = deliveryStaffField.getText().trim();

            // Required field validation
            if (trackingNumber.isEmpty() || orderId.isEmpty() || supplier.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tracking Number, Order ID, Supplier are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (logisticsEnterprise == null) {
                JOptionPane.showMessageDialog(this, "Logistics Enterprise not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Shipment newShipment = logisticsEnterprise.getShipmentDirectory().addShipment();
                newShipment.setTrackingNumber(trackingNumber);
                newShipment.setOrderId(orderId);
                newShipment.setStatus(status);
                newShipment.setCreatedDate(new Date());

                // Set additional fields
                if (etaStr != null && !etaStr.isEmpty()) {
                    newShipment.setEta(etaStr); // Supports simple date input
                }
                newShipment.setCarrier(carrier);
                newShipment.setOrigin(origin);
                newShipment.setDestination(destination);
                newShipment.setNotes(notes);
                newShipment.setAssignedDeliveryStaff(deliveryStaff);

                populateTable();
                JOptionPane.showMessageDialog(this, "Shipment added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Add failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {}

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblShipmentss.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a Shipments to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            DefaultTableModel model = (DefaultTableModel) tblShipmentss.getModel();
            String shipmentId = model.getValueAt(selectedRow, 0).toString();

            for (Shipment ship : logisticsEnterprise.getShipmentDirectory().getAllShipments()) {
                if (ship.getShipmentId().equals(shipmentId)) {
                    logisticsEnterprise.getShipmentDirectory().removeShipment(ship);
                    break;
                }
            }
            populateTable();
            JOptionPane.showMessageDialog(this, "Shipment deleted successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }

    void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblShipmentss.getModel();
        model.setRowCount(0);
        if (logisticsEnterprise == null) return;

        ArrayList<Shipment> shipmentList = logisticsEnterprise.getShipmentDirectory().getAllShipments();
        for (Shipment ship : shipmentList) {
            Object[] row = new Object[5];
            row[0] = ship.getShipmentId();
            row[1] = ship.getOrderId();
            row[2] = ship.getOrigin();
            row[3] = ship.getStatus();
            row[4] = ship.getCreatedDate().toGMTString();
            model.addRow(row);
        }
    }

    private void setTableColumnWidths() {
        TableColumn column;
        column = tblShipmentss.getColumnModel().getColumn(0); column.setPreferredWidth(160);
        column = tblShipmentss.getColumnModel().getColumn(1); column.setPreferredWidth(160);
        column = tblShipmentss.getColumnModel().getColumn(2); column.setPreferredWidth(160);
        column = tblShipmentss.getColumnModel().getColumn(3); column.setPreferredWidth(160);
        column = tblShipmentss.getColumnModel().getColumn(4); column.setPreferredWidth(160);
        tblShipmentss.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    // Variables declaration
    private javax.swing.JButton btnAddShipments;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblShipmentss;
    // End of variables declaration
}


