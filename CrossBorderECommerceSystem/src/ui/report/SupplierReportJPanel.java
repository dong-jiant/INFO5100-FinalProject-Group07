package ui.report;

import business.enterprise.SupplierEnterprise;
import business.product.Product;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SupplierReportJPanel extends JPanel {
    private final JPanel workArea;
    private final SupplierEnterprise supplierEnterprise;
    private JLabel lblSku;
    private JLabel lblLowStock;
    private JLabel lblInventoryValue;

    public SupplierReportJPanel(JPanel workArea, SupplierEnterprise supplierEnterprise) {
        this.workArea = workArea;
        this.supplierEnterprise = supplierEnterprise;
        initComponents();
        loadMetrics();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel metricsPanel = new JPanel(new GridLayout(3, 1, 6, 6));
        lblSku = new JLabel("Total SKU: 0");
        lblLowStock = new JLabel("Low Stock Products: 0");
        lblInventoryValue = new JLabel("Inventory Value: $0.00");

        metricsPanel.add(lblSku);
        metricsPanel.add(lblLowStock);
        metricsPanel.add(lblInventoryValue);

        JButton btnBack = new JButton("<< Back");
        btnBack.addActionListener(e -> goBack());

        add(new JLabel("Supplier KPI Summary"), BorderLayout.NORTH);
        add(metricsPanel, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }

    private void loadMetrics() {
        int totalSku = supplierEnterprise.getProductDirectory().getProductList().size();
        int lowStockCount = 0;
        double inventoryValue = 0.0;

        for (Product product : supplierEnterprise.getProductDirectory().getProductList()) {
            if (product.isLowStock()) {
                lowStockCount++;
            }
            inventoryValue += product.getPrice() * product.getStockQty();
        }

        lblSku.setText("Total SKU: " + totalSku);
        lblLowStock.setText("Low Stock Products: " + lowStockCount);
        lblInventoryValue.setText(String.format("Inventory Value: $%.2f", inventoryValue));
    }

    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}
