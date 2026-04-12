package ui.request;

import business.enterprise.SupplierEnterprise;
import business.workrequest.WorkRequest;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SupplierWorkRequestJPanel extends JPanel {
    private final JPanel workArea;
    private final SupplierEnterprise supplierEnterprise;
    private JTable table;
    private DefaultTableModel model;

    public SupplierWorkRequestJPanel(JPanel workArea, SupplierEnterprise supplierEnterprise) {
        this.workArea = workArea;
        this.supplierEnterprise = supplierEnterprise;
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        model = new DefaultTableModel(
                new Object[]{"Request ID", "Type", "Source Ent", "Target Ent", "Source Org", "Target Org", "Status", "Payload"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        JButton btnProcessing = new JButton("Mark Processing");
        JButton btnCompleted = new JButton("Complete");
        JButton btnRejected = new JButton("Reject");
        JButton btnBack = new JButton("<< Back");

        btnProcessing.addActionListener(e -> updateStatus("processing"));
        btnCompleted.addActionListener(e -> updateStatus("completed"));
        btnRejected.addActionListener(e -> updateStatus("rejected"));
        btnBack.addActionListener(e -> goBack());

        actionPanel.add(btnProcessing);
        actionPanel.add(btnCompleted);
        actionPanel.add(btnRejected);
        actionPanel.add(btnBack);
        add(actionPanel, BorderLayout.SOUTH);
    }

    private void populateTable() {
        model.setRowCount(0);
        for (WorkRequest request : supplierEnterprise.getWorkRequestDirectory().getRequestList()) {
            model.addRow(new Object[]{
                request.getRequestId(),
                request.getRequestType(),
                request.getSourceEnterprise(),
                request.getTargetEnterprise(),
                request.getSourceOrganization(),
                request.getTargetOrganization(),
                request.getStatus(),
                request.getBusinessPayload()
            });
        }
    }

    private void updateStatus(String action) {
        WorkRequest request = getSelectedRequest();
        if (request == null) {
            return;
        }

        if ("processing".equals(action)) {
            request.markProcessing("supplier");
        } else if ("completed".equals(action)) {
            request.complete("supplier");
        } else if ("rejected".equals(action)) {
            request.reject("supplier");
        }

        populateTable();
    }

    private WorkRequest getSelectedRequest() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select one request.");
            return null;
        }
        String requestId = String.valueOf(model.getValueAt(row, 0));
        for (WorkRequest request : supplierEnterprise.getWorkRequestDirectory().getRequestList()) {
            if (request.getRequestId().equals(requestId)) {
                return request;
            }
        }
        JOptionPane.showMessageDialog(this, "Request not found.");
        return null;
    }

    private void goBack() {
        workArea.remove(this);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);
    }
}
