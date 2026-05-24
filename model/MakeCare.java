package model;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MakeCare {
    private int id;
    private String namaBrand;
    private String jenisSkincare;
    private BigDecimal harga;
    private LocalDate tanggalKadaluarsa;

    public MakeCare ()  {
    }

    public MakeCare(int id, String namaBrand, String jenisSkincare,
                    BigDecimal harga, LocalDate tanggalKadaluarsa) {
        this.id = id;
        this.namaBrand = namaBrand;
        this.jenisSkincare = jenisSkincare;
        this.harga = harga;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getNamaBrand() {
        return namaBrand;
    }

    public String getJenisSkincare() {
        return jenisSkincare;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public LocalDate getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setNamaBrand(String namaBrand) {
        this.namaBrand = namaBrand;
    }

    public void setJenisSkincare(String jenisSkincare) {
        this.jenisSkincare = jenisSkincare;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public void setTanggalKadaluarsa(LocalDate tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }
}