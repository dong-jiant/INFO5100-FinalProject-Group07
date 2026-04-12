package ui.main;

import business.enterprise.SupplierEnterprise;
import business.network.Network;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import ui.product.ManageProductJPanel;
import ui.report.SupplierReportJPanel;
import ui.request.SupplierWorkRequestJPanel;

/**
 *
 * @author Yiru Chen
 */

public class SupplierWorkAreaJPanel extends JPanel {
    private final SupplierEnterprise supplierEnterprise;
    private final Network network;
    private JPanel workArea;

    public SupplierWorkAreaJPanel(Network network, SupplierEnterprise supplierEnterprise) {
        this.network = network;
        this.supplierEnterprise = supplierEnterprise;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        JButton btnManageProducts = new JButton("Manage Supplier Products");
        JButton btnWorkRequests = new JButton("Process Work Requests");
        JButton btnSupplierReport = new JButton("Supplier KPI Report");
        JButton btnLogout = new JButton("Logout");
        btnManageProducts.addActionListener(e -> openProductPanel());
        btnWorkRequests.addActionListener(e -> openWorkRequestPanel());
        btnSupplierReport.addActionListener(e -> openSupplierReportPanel());
        btnLogout.addActionListener(e -> logout());
        menuPanel.add(btnManageProducts);
        menuPanel.add(btnWorkRequests);
        menuPanel.add(btnSupplierReport);
        menuPanel.add(btnLogout);

        workArea = new JPanel(new CardLayout());
        workArea.add(new JLabel("Supplier Dashboard"), "HOME");

        add(menuPanel, BorderLayout.NORTH);
        add(workArea, BorderLayout.CENTER);
    }

    private void openProductPanel() {
        ManageProductJPanel panel = new ManageProductJPanel(workArea, supplierEnterprise);
        workArea.add(panel, "ManageProductJPanel");
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.show(workArea, "ManageProductJPanel");
    }

    private void openWorkRequestPanel() {
        SupplierWorkRequestJPanel panel = new SupplierWorkRequestJPanel(workArea, supplierEnterprise);
        workArea.add(panel, "SupplierWorkRequestJPanel");
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.show(workArea, "SupplierWorkRequestJPanel");
    }

    private void openSupplierReportPanel() {
        SupplierReportJPanel panel = new SupplierReportJPanel(workArea, supplierEnterprise);
        workArea.add(panel, "SupplierReportJPanel");
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.show(workArea, "SupplierReportJPanel");
    }

    private void logout() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
        new LoginJFrame(network).setVisible(true);
    }
}
