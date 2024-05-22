package Controller;

import Model.Apprentice;
import Model.ApprenticeDAO;
import View.Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controller implements ActionListener {

    ApprenticeDAO dao = new ApprenticeDAO();
    Apprentice ap = new Apprentice();
    Form form;

    public Controller(Form f) {
        this.form = f;
        this.form.btnToList.addActionListener(this);
        this.form.btnSave.addActionListener(this);
        this.form.btnSearch.addActionListener(this);
        this.form.btnDelete.addActionListener(this);
        this.form.btnUpdate.addActionListener(this);
        this.form.btnExit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnToList) {
            cleanTable();
            ToList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnSave) {
            save();
            ToList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnSearch) {
            int row = form.tblApprentice.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(form, "Please selected row");
            } else {
                int id = Integer.parseInt(form.tblApprentice.getValueAt(row, 0).toString());
                String typeDoc = form.tblApprentice.getValueAt(row, 1).toString();
                String numDoc = form.tblApprentice.getValueAt(row, 2).toString();
                String name = form.tblApprentice.getValueAt(row, 3).toString();
                String birthDate = form.tblApprentice.getValueAt(row, 4).toString();
                String gender = form.tblApprentice.getValueAt(row, 5).toString();
                String city = form.tblApprentice.getValueAt(row, 6).toString();
                form.txtId.setText(String.valueOf(id));
                form.cbxDocType.setSelectedItem(typeDoc);
                form.txtDocNumber.setText(numDoc);
                form.txtName.setText(name);
                form.jdcBirthDate.setDate(java.sql.Date.valueOf(birthDate));
                if (gender.equalsIgnoreCase("Male")) {
                    form.rbMale.setSelected(true);
                } else if (gender.equalsIgnoreCase("Female")) {
                    form.rbFemale.setSelected(true);
                }
                form.txtCity.setText(city);
            }
        }

        if (e.getSource() == form.btnUpdate) {
            update();
            ToList(form.tblApprentice);
            clean();
        }

        if (e.getSource() == form.btnDelete) {
            delete();
            ToList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnExit) {
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to exit the program", "Message", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

    public void ToList(JTable tblApprentice) {
        CenterCells(tblApprentice);
        DefaultTableModel model = (DefaultTableModel) form.tblApprentice.getModel();
        model.setRowCount(0);
        List<Apprentice> ToList = dao.ToList();
        for (Apprentice appre : ToList) {
            Object[] object = {appre.getId(), appre.getDoctype(), appre.getDocnumber(), appre.getName(), appre.getBirthdate(), appre.getGender(), appre.getCity()};
            model.addRow(object);
        }
        tblApprentice.setModel(model);
    }

    public void save() {
        String typeDoc = form.cbxDocType.getSelectedItem().toString();
        String numDoc = form.txtDocNumber.getText();
        String name = form.txtName.getText();
        java.util.Date birthDate = form.jdcBirthDate.getDate();
        String gender = form.rbMale.isSelected() ? "Male" : "Female";
        String city = form.txtCity.getText();

        Apprentice appre = new Apprentice();
        appre.setDoctype(typeDoc);
        appre.setDocnumber(numDoc);
        appre.setName(name);
        appre.setBirthdate(birthDate);
        appre.setGender(gender);
        appre.setCity(city);

        int r = dao.Add(appre);
        if (r == 1) {
            JOptionPane.showMessageDialog(form, "Registered user");
        } else {
            JOptionPane.showMessageDialog(form, "Unregistered user");
        }
    }

    public void update() {
        if (form.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(form, "Id not found, select an existing record");
        } else {
            int id = Integer.parseInt(form.txtId.getText());
            String typeDoc = form.cbxDocType.getSelectedItem().toString();
            String numDoc = form.txtDocNumber.getText();
            String name = form.txtName.getText();
            java.util.Date birthDate = form.jdcBirthDate.getDate();
            String gender = form.rbMale.isSelected() ? "Male" : "Female";
            String city = form.txtCity.getText();

            ap.setId(id);
            ap.setDoctype(typeDoc);
            ap.setDocnumber(numDoc);
            ap.setName(name);
            ap.setBirthdate(birthDate);
            ap.setGender(gender);
            ap.setCity(city);

            int r = dao.Add(ap);
            if (r == 1) {
                JOptionPane.showMessageDialog(form, "Update user");
            } else {
                JOptionPane.showMessageDialog(form, "user not updated");
            }
        }
    }

    public void delete() {
        int row = form.tblApprentice.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(form, "Please select a row");
        } else {
            int id = Integer.parseInt(form.tblApprentice.getValueAt(row, 0).toString());
            dao.Delete(id);
            JOptionPane.showMessageDialog(form, "Deleted record");
        }
        cleanTable();
    }

    public void CenterCells(JTable tblApprentice) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tblApprentice.getColumnCount(); i++) {
            tblApprentice.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) form.tblApprentice.getModel();
        model.setRowCount(0);
    }

    public void clean() {
        form.txtId.setText("");
        form.cbxDocType.setSelectedIndex(0);
        form.txtDocNumber.setText("");
        form.txtName.setText("");
        form.jdcBirthDate.setDate(null);
        form.rbMale.setSelected(false);
        form.rbFemale.setSelected(false);
        form.txtCity.setText("");

    }

}
