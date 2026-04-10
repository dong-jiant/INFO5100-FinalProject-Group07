package ui.main;

import business.enterprise.SupplierEnterprise;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.product.ManageProductJPanel;

/**
 *
 * @author Yiru Chen
 */

public class SupplierWorkAreaJPanel extends JPanel {
    private final SupplierEnterprise supplierEnterprise;
    private JPanel workArea;

    public SupplierWorkAreaJPanel(SupplierEnterprise supplierEnterprise) {
        this.supplierEnterprise = supplierEnterprise;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        JButton btnManageProducts = new JButton("Manage Supplier Products");
        btnManageProducts.addActionListener(e -> openProductPanel());
        menuPanel.add(btnManageProducts);

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
}
