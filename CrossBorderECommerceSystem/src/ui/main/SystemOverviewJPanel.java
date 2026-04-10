/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.main;
import business.enterprise.Enterprise;
import business.enterprise.PlatformEnterprise;
import business.network.Network;
import business.order.Order;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stelladong
 */
public class SystemOverviewJPanel extends JPanel {     private final JPanel workArea;
    private final Network network;
    private final PlatformEnterprise platformEnterprise;

    public SystemOverviewJPanel(JPanel workArea, Network network, PlatformEnterprise platformEnterprise) {
        this.workArea = workArea;
        this.network = network;
        this.platformEnterprise = platformEnterprise;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("System Overview", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        int enterpriseCount = network.getEnterprises().size();

        int userCount = 0;
        for (Enterprise e : network.getEnterprises()) {
            userCount += e.getUserAccountDirectory().getUserAccountList().size();
        }

        int orderCount = platformEnterprise == null ? 0
                : platformEnterprise.getOrderDirectory().getOrderList().size();

        int riskCount = 0;
        if (platformEnterprise != null) {
            for (Order o : platformEnterprise.getOrderDirectory().getOrderList()) {
                if (o.getRiskFlag().equals("High Value") || o.getRiskFlag().equals("Delay Risk")) {
                    riskCount++;
                }
            }
        }

        String[] columns = {"Metric", "Value"};
        Object[][] data = {
            {"Total Enterprises", enterpriseCount},
            {"Total Users",       userCount},
            {"Total Orders",      orderCount},
            {"High Risk Orders",  riskCount}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Helvetica Neue", Font.BOLD, 13));
        table.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnBack = new JButton("<< Back");
        btnBack.addActionListener(e -> {
            workArea.remove(this);
            CardLayout layout = (CardLayout) workArea.getLayout();
            layout.previous(workArea);
        });
        add(btnBack, BorderLayout.SOUTH);
    }
}
