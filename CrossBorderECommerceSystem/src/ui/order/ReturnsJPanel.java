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

public class ReturnsJPanel extends JPanel {
    private final JPanel workArea;
    private final PlatformEnterprise platformEnterprise;
    private JTable table;
    private DefaultTableModel model;

    public ReturnsJPanel(JPanel workArea, PlatformEnterprise platformEnterprise) {
        this.workArea = workArea;
        this.platformEnterprise = platformEnterprise;
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        model = new DefaultTableModel(
                new Object[]{"Order ID", "Customer", "Status", "Shipment"},
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
        JButton btnApprove = new JButton("Approve Return");
        JButton btnReject = new JButton("Reject Return");
        JButton btnBack = new JButton("<< Back");
        btnApprove.addActionListener(e -> approveReturn());
        btnReject.addActionListener(e -> rejectReturn());
        btnBack.addActionListener(e -> goBack());
        actions.add(btnApprove);
        actions.add(btnReject);
        actions.add(btnBack);
        add(actions, BorderLayout.SOUTH);
    }

    private void populateTable() {
        model.setRowCount(0);
        for (Order order : platformEnterprise.getOrderDirectory().getOrderList()) {
            if ("Delivered".equalsIgnoreCase(order.getShipmentStatus()) || "Return Requested".equalsIgnoreCase(order.getStatus())) {
                model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getStatus(),
                    order.getShipmentStatus()
                });
            }
        }
    }

    private void approveReturn() {
        Order order = getSelectedOrder();
        if (order == null) {
            return;
        }
        order.setStatus("Returned");
        order.setShipmentStatus("Returned");
        populateTable();
    }

    private void rejectReturn() {
        Order order = getSelectedOrder();
        if (order == null) {
            return;
        }
        order.setStatus("Return Rejected");
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
