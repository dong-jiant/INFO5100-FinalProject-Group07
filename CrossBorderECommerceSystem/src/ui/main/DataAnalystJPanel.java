package ui.main;

import business.enterprise.PlatformEnterprise;
import business.network.Network;
import business.order.Order;
import java.awt.BorderLayout;
import java.awt.Window;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataAnalystJPanel extends JPanel {
    private final Network network;
    private final PlatformEnterprise platformEnterprise;

    public DataAnalystJPanel(Network network, PlatformEnterprise platformEnterprise) {
        this.network = network;
        this.platformEnterprise = platformEnterprise;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));

        JTabbedPane tabs = new JTabbedPane();

        // Order Summary tab
        DefaultTableModel orderModel = new DefaultTableModel(
            new String[]{"Order ID", "Customer", "Country", "Status", "Total", "Risk"}, 0);
        for (Order o : platformEnterprise.getOrderDirectory().getOrderList()) {
            orderModel.addRow(new Object[]{o.getOrderId(), o.getCustomerName(), o.getCountry(),
                o.getStatus(), String.format("$%.2f", o.getTotalPrice()), o.getRiskFlag()});
        }
        tabs.addTab("Order Summary", new JScrollPane(new JTable(orderModel)));

        // Country Summary tab
        java.util.Map<String, int[]> countryStats = new java.util.HashMap<>();
        java.util.Map<String, Double> countryRev = new java.util.HashMap<>();
        for (Order o : platformEnterprise.getOrderDirectory().getOrderList()) {
            String c = o.getCountry() == null ? "Unknown" : o.getCountry();
            countryStats.putIfAbsent(c, new int[]{0, 0});
            countryStats.get(c)[0]++;
            if (!"Normal".equals(o.getRiskFlag())) countryStats.get(c)[1]++;
            countryRev.merge(c, o.getTotalPrice(), Double::sum);
        }
        DefaultTableModel countryModel = new DefaultTableModel(
            new String[]{"Country", "Orders", "Revenue", "High Risk"}, 0);
        for (String c : countryStats.keySet()) {
            countryModel.addRow(new Object[]{c, countryStats.get(c)[0],
                String.format("$%.2f", countryRev.get(c)), countryStats.get(c)[1]});
        }
        tabs.addTab("Country Summary", new JScrollPane(new JTable(countryModel)));

        // KPI tab
        int totalOrders = platformEnterprise.getOrderDirectory().getOrderList().size();
        int riskCount = 0;
        double totalRev = 0;
        for (Order o : platformEnterprise.getOrderDirectory().getOrderList()) {
            totalRev += o.getTotalPrice();
            if (!"Normal".equals(o.getRiskFlag())) riskCount++;
        }
        DefaultTableModel kpiModel = new DefaultTableModel(new String[]{"Metric", "Value"}, 0);
        kpiModel.addRow(new Object[]{"Total Orders", totalOrders});
        kpiModel.addRow(new Object[]{"Total Revenue", String.format("$%.2f", totalRev)});
        kpiModel.addRow(new Object[]{"High Risk Orders", riskCount});
        tabs.addTab("KPI Dashboard", new JScrollPane(new JTable(kpiModel)));

        add(new JLabel("  Data Analyst Dashboard", SwingConstants.LEFT), BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
            new LoginJFrame(network).setVisible(true);
        });
        JPanel bottom = new JPanel();
        bottom.add(btnLogout);
        add(bottom, BorderLayout.SOUTH);
    }
}
