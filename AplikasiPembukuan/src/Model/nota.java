package Model;

/**
 *
 * @author Vardina Nava M K
 */
public class nota {
    private String id;
    private String tanggal;
    private String keterangan;

    public nota() {
    }


    public nota(String id, String tanggal, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }



    @Override
    public String toString() {
        return "nota{" +
                "keterangan='" + keterangan + '\'' +
                '}';
    }
}
