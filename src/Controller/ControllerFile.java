package Controller;

import Model.Apprentice;
import Model.File;
import Model.FileDAO;
import View.FormFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ControllerFile implements ActionListener {

    FileDAO daof = new FileDAO();
    File fil = new File();
    FormFile formfile = new FormFile();

    public ControllerFile(FormFile fl) {
        this.formfile = fl;
        this.formfile.btnToList.addActionListener(this);
        this.formfile.btnSave.addActionListener(this);
        this.formfile.btnSearch.addActionListener(this);
        this.formfile.btnDelete.addActionListener(this);
        this.formfile.btnUpdate.addActionListener(this);
        this.formfile.btnExit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formfile.btnToList) {
            cleanTableF();
            ToListF(formfile.tblFile);
            cleanF();
        }
        if (e.getSource() == formfile.btnSave) {
            saveF();
            ToListF(formfile.tblFile);
            cleanF();
        }
        if (e.getSource() == formfile.btnSearch) {
            int row = formfile.tblFile.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(formfile, "Please selected row");
            } else {
                int id = Integer.parseInt(formfile.tblFile.getValueAt(row, 0).toString());
                String officeNumber = formfile.tblFile.getValueAt(row, 1).toString();
                String tokenName = formfile.tblFile.getValueAt(row, 2).toString();
                String campus = formfile.tblFile.getValueAt(row, 3).toString();
                String city = formfile.tblFile.getValueAt(row, 4).toString();

                formfile.txtId.setText(String.valueOf(id));
                formfile.txtIdApprentice.setText(String.valueOf(id));
                formfile.txtOfficeNumber.setText(officeNumber);
                formfile.txtTokenName.setText(tokenName);
                formfile.txtCampus.setText(campus);
                formfile.txtCity1.setText(city);
            }

        }
        if (e.getSource() == formfile.btnUpdate) {
            updateF();
            ToListF(formfile.tblFile);
            cleanF();
        }

        if (e.getSource() == formfile.btnDelete) {
            deleteF();
            ToListF(formfile.tblFile);
            cleanF();
        }
        if (e.getSource() == formfile.btnExit) {
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to exit the program", "Message", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }

    }

    public void ToListF(JTable tblFile) {
        CenterCellsF(tblFile);
        DefaultTableModel model = (DefaultTableModel) formfile.tblFile.getModel();
        model.setRowCount(0);
        List<File> ToList = daof.ToListF();
        for (File fil : ToList) {
            Object[] object = {fil.getId(), fil.getOfficeNumber(), fil.getTokenName(), fil.getCampus(), fil.getCity(), fil.getIdApprentice()};
            model.addRow(object);
        }
        tblFile.setModel(model);
    }

    public void saveF() {
        String officeNumber = formfile.txtOfficeNumber.getText();
        String tokenName = formfile.txtTokenName.getText();
        String campus = formfile.txtCampus.getText();
        String city = formfile.txtIdApprentice.getText();
        int idA = Integer.parseInt(formfile.txtIdApprentice.getText());

        File fil = new File();
        fil.setOfficeNumber(officeNumber);
        fil.setTokenName(tokenName);
        fil.setCampus(campus);
        fil.setCity(city);
        fil.setIdApprentice(idA);

        int r = daof.AddF(fil);
        if (r == 1) {
            JOptionPane.showMessageDialog(formfile, "Registered user");
        } else {
            JOptionPane.showMessageDialog(formfile, "Unregistered user");
        }
    }

    public void updateF() {
        if (formfile.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(formfile, "Id not found, select an existing record");
        } else {
            int id = Integer.parseInt(formfile.txtId.getText());
            String officeNumber = formfile.txtOfficeNumber.getText();
            String tokenName = formfile.txtTokenName.getText();
            String campus = formfile.txtCampus.getText();
            String city = formfile.txtIdApprentice.getText();
            int idForanea = Integer.parseInt(formfile.txtIdApprentice.getText());

            fil.setId(id);
            fil.setOfficeNumber(officeNumber);
            fil.setTokenName(tokenName);
            fil.setCampus(campus);
            fil.setCity(city);
            fil.setIdApprentice(idForanea);

            int r = daof.UpdateF(fil);
            if (r == 1) {
                JOptionPane.showMessageDialog(formfile, "Update user");
            } else {
                JOptionPane.showMessageDialog(formfile, "user not updated");
            }
        }
    }

    public void deleteF() {
        int row = formfile.tblFile.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(formfile, "Please select a row");
        } else {
            int id = Integer.parseInt(formfile.tblFile.getValueAt(row, 0).toString());
            daof.DeleteF(id);
            JOptionPane.showMessageDialog(formfile, "Deleted record");
        }
        cleanTableF();
    }

    public void CenterCellsF(JTable tblFile) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < formfile.tblFile.getColumnCount(); i++) {
            tblFile.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void cleanTableF() {
        DefaultTableModel model = (DefaultTableModel) formfile.tblFile.getModel();
        model.setRowCount(0);
    }

    public void cleanF() {
        formfile.txtId.setText("");
        formfile.txtIdApprentice.setText("");
        formfile.txtOfficeNumber.setText("");
        formfile.txtTokenName.setText("");
        formfile.txtCampus.setText("");
        formfile.txtCity1.setText("");
    }
}