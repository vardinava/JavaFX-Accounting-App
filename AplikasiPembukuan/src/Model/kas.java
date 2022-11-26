package Model;

/**
 *
 * @author Vardina Nava M K
 */
public class kas {
    private int debit, kredit, saldo;
    private String id, keterangan;
    private String tgl;

    public kas(Model.nota nota, int debit, int kredit, int saldo) {
        this.keterangan = nota.getKeterangan();
        this.id = nota.getId();
        this.debit = debit;
        this.kredit = kredit;
        this.saldo= saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
