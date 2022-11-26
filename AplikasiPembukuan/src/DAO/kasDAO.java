package DAO;

import Model.kas;
import Model.nota;
import Model.stat;
import Utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vardina Nava M K
 */
public class kasDAO implements daoInterface<kas> {

    @Override
    public int addData(kas data) {
        int result = 0;
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date tgl = formatter.parse(data.getTgl());
            java.sql.Date sqlDate = new java.sql.Date(tgl.getTime());
            String query = "INSERT INTO detail_nota(nota,keterangan,tanggal) VALUES (?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getId());
            ps.setString(2,data.getKeterangan());
            ps.setDate(3,sqlDate);
            result = ps.executeUpdate();}
        catch(SQLException | ParseException ex){
            System.out.println(ex.getMessage());
        }
        try{
            String query = "INSERT INTO kas(nota,debit,kredit) VALUES (?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getId());
            ps.setInt(2,data.getDebit());
            ps.setInt(3,data.getKredit());
            result = ps.executeUpdate();}
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(kas data) {
        return 0;
    }

    @Override
    public int updateData(kas data) {
        return 0;
    }

    @Override
    public ObservableList<kas> showData() {
        ObservableList<kas> klist =FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM kas k JOIN detail_nota n ON k.nota = n.nota";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            int s = 0, j=0,d=0,k=0;
            while (res.next()){
                String id = res.getString("nota");
                Date t = res.getDate("tanggal");
                String ket = res.getString("keterangan");
                int debit = res.getInt("debit");
                int kredit = res.getInt("kredit");
                s = s+debit-kredit;
                d+=debit;
                k+=kredit;
                j+=1;
                DateFormat df = new SimpleDateFormat("dd MMM yyyy");
                String tgl = df.format(t);
                nota n = new nota(id,tgl,ket);
                kas c = new kas(n,debit,kredit,s);
                c.setTgl(tgl);
                klist.add(c);
            }
            stat.jumlah=j;
            stat.saldo=s;
            stat.debit=d;
            stat.kredit=k;
        } catch (SQLException ex) {
            Logger.getLogger(kasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klist;
    }
}
