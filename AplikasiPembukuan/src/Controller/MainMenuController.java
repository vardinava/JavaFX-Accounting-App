package Controller;

import DAO.kasDAO;
import Model.kas;
import Model.nota;
import Model.stat;
import Utility.JDBCConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Vardina Nava M K
 */
public class MainMenuController implements Initializable {
    @FXML
    private TableView<kas> tbl_kas;
    @FXML
    private TableColumn<String,kas> col_tgl;
    @FXML
    private TableColumn<String,kas> col_ket;
    @FXML
    private TableColumn<Integer,kas> col_debit;
    @FXML
    private TableColumn<Integer,kas> col_kredit;
    @FXML
    private TableColumn<Integer,kas> col_saldo;
    @FXML
    private Button btn_print;
    @FXML
    private Button btn_submit;
    @FXML
    TextField tfieldd;
    @FXML
    TextField tfieldket;
    @FXML
    TextField tfieldid;
    @FXML
    ToggleGroup tg_dk;
    @FXML
    RadioButton rb_debit;
    @FXML
    RadioButton rb_kredit;
    @FXML
    Label lb_saldo;
    @FXML
    Label lb_jum;
    @FXML
    Label lb_kredit;
    @FXML
    Label lb_debit;
    int nilai,d,k;

    public MainMenuController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
        col_tgl.setCellValueFactory(new PropertyValueFactory<String,kas>("tgl"));
        col_ket.setCellValueFactory(new PropertyValueFactory<String,kas>("keterangan"));
        col_debit.setCellValueFactory(new PropertyValueFactory<Integer,kas>("debit"));
        col_kredit.setCellValueFactory(new PropertyValueFactory<Integer,kas>("kredit"));
        col_saldo.setCellValueFactory(new PropertyValueFactory<Integer,kas>("saldo"));
    }

    public void refreshTable(){
        kasDAO kDao = new kasDAO();
        tbl_kas.setItems(kDao.showData());
        lb_saldo.setText(String.valueOf(stat.saldo));
        lb_jum.setText(String.valueOf(stat.jumlah));
        lb_debit.setText(String.valueOf(stat.debit));
        lb_kredit.setText(String.valueOf(stat.kredit));
    }

    public void reset(){
        tfieldid.clear();
        tfieldket.clear();
        tfieldd.clear();
    }

    @FXML
    private void addKas(ActionEvent event) {
        String id = tfieldid.getText();
        String ket = tfieldket.getText();
        nilai = Integer.parseInt(tfieldd.getText());
        Date t = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = df.format(t);
        d=0;
        k=0;
        RadioButton rb = (RadioButton) tg_dk.getSelectedToggle();
        if (rb.getText().equalsIgnoreCase("Debit")){
            d=nilai;
        }else{
            k=nilai;
        }
        nota n = new nota(id,tgl,ket);
        kas kas = new kas(n,d,k,0);
        kasDAO kDao = new kasDAO();
        kDao.addData(kas);
        refreshTable();
        reset();
    }

    @FXML
    private void printReport(ActionEvent event) {
        JasperPrint jp;
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("Report/Flower_Landscape.jasper", param, JDBCConnection.getConnection());
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Laporan Pembukuan");
            viewer.setVisible(true);
        }
        catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

}
