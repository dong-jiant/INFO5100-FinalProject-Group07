package ui.main;

import business.enterprise.PlatformEnterprise;
import business.network.Network;
import business.order.Order;
import java.awt.BorderLayout;
import java.awt.Window;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerJPanel extends JPanel {
    private final Network network;
    private final PlatformEnterprise platformEnterprise;
    private JTable table;
    private DefaultTableModel model;

    public CustomerJPanel(Network network, PlatformEnterprise platformEnterprise) {
        this.network = network;
        this.platformEnterprise = platformEnterprise;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));
        add(new JLabel("  Customer Portal"), BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Order ID", "Status", "Shipment", "Total", "Risk"}, 0);
        table = new JTable(model);
        populateTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton btnPlace = new JButton("Place Order");
        JButton btnReturn = new JButton("Request Return");
        JButton btnLogout = new JButton("Logout");
        btnPlace.addActionListener(e -> placeOrder());
        btnReturn.addActionListener(e -> requestReturn());
        btnLogout.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
            new LoginJFrame(network).setVisible(true);
        });
        actions.add(btnPlace);
        actions.add(btnReturn);
        actions.add(btnLogout);
        add(actions, BorderLayout.SOUTH);
    }

    private void populateTable() {
        model.setRowCount(0);
        for (Order o : platformEnterprise.getOrderDirectory().getOrderList()) {
            model.addRow(new Object[]{o.getOrderId(), o.getStatus(), o.getShipmentStatus(),
                String.format("$%.2f", o.getTotalPrice()), o.getRiskFlag()});
        }
    }

    private void placeOrder() {
        String name = JOptionPane.showInputDialog(this, "Your name:");
        String err = business.ValidationHelper.validateName(name);
        if (err != null) { JOptionPane.showMessageDialog(this, err); return; }
        String email = JOptionPane.showInputDialog(this, "Your email:");
        err = business.ValidationHelper.validateEmail(email);
        if (err != null) { JOptionPane.showMessageDialog(this, err); return; }
        String country = JOptionPane.showInputDialog(this, "Destination country:");
        if (country == null || country.trim().isEmpty()) return;
        platformEnterprise.getOrderDirectory().addOrder(name.trim(), email.trim(), country.trim());
        populateTable();
        JOptionPane.showMessageDialog(this, "Order placed successfully!");
    }

    private void requestReturn() {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select an order."); return; }
        String orderId = String.valueOf(model.getValueAt(row, 0));
        Order order = platformEnterprise.getOrderDirectory().findById(orderId);
        if (order == null) return;
        if (!"Delivered".equalsIgnoreCase(order.getShipmentStatus()) && !"Completed".equalsIgnoreCase(order.getStatus())) {
            JOptionPane.showMessageDialog(this, "Only delivered orders can be returned.");
            return;
        }
        order.setStatus("Return Requested");
        populateTable();
        JOptionPane.showMessageDialog(this, "Return requested for " + orderId);
    }
}
