package ui.order;

import business.enterprise.PlatformEnterprise;
import business.order.Order;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerServiceJPanel extends JPanel {
    private final JPanel workArea;
    private final PlatformEnterprise platformEnterprise;
    private JTable table;
    private DefaultTableModel model;

    public CustomerServiceJPanel(JPanel workArea, PlatformEnterprise platformEnterprise) {
        this.workArea = workArea;
        this.platformEnterprise = platformEnterprise;
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        model = new DefaultTableModel(
                new Object[]{"Order ID", "Customer", "Email", "Status", "Shipment", "Risk"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton btnResolve = new JButton("Resolve Ticket");
        JButton btnBack = new JButton("<< Back");
        btnResolve.addActionListener(e -> resolveTicket());
        btnBack.addActionListener(e -> goBack());
        actions.add(btnResolve);
        actions.add(btnBack);
        add(actions, BorderLayout.SOUTH);
    }

    private void populateTable() {
        model.setRowCount(0);
        for (Order order : platformEnterprise.getOrderDirectory().getOrderList()) {
            model.addRow(new Object[]{
                order.getOrderId(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getStatus(),
                order.getShipmentStatus(),
                order.getRiskFlag()
            });
        }
    }

    private void resolveTicket() {
        Order order = getSelectedOrder();
        if (order == null) {
            return;
        }
        if ("Cancelled".equalsIgnoreCase(order.getStatus()) || "Completed".equalsIgnoreCase(order.getStatus())) {
            JOptionPane.showMessageDialog(this, "No open issue for this order.");
            return;
        }
        order.setStatus("Customer Service Resolved");
        populateTable();
    }

    private Order getSelectedOrder() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select an order.");
            return null;
        }
        String orderId = String.valueOf(model.getValueAt(row, 0));
        return platformEnterprise.getOrderDirectory().findById(orderId);
    }

    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}
