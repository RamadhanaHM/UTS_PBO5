import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BankAccount {
    private String nama;
    private String nomorAkun;
    private double saldo;
    private LocalDateTime tanggalRegistrasi;

    public BankAccount(String nama, double saldo) {
        this.nama = nama;
        this.saldo = saldo;
        this.nomorAkun = generateNomorAkun();
        this.tanggalRegistrasi = LocalDateTime.now();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorAkun() {
        return nomorAkun;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getTanggalRegistrasi() {
        return tanggalRegistrasi;
    }

    private String generateNomorAkun() {
        Random random = new Random();
        String nomorAkun;
        boolean unik;

        do {
            int randomNumber = random.nextInt(900000) + 100000; // Menghasilkan angka acak 100000 - 999999
            nomorAkun = String.valueOf(randomNumber);
            unik = isNomorAkunUnik(nomorAkun);
        } while (!unik);

        return nomorAkun;
    }

    private boolean isNomorAkunUnik(String nomorAkun) {
        for (BankAccount account : BankCLI.bankAccounts) {
            if (account != null && account.getNomorAkun().equals(nomorAkun)) {
                return false; // Jika nomor akun sudah ada dalam array bankAccounts
            }
        }
        return true; // Jika nomor akun unik
    }

   
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Y-M-d H:m:s");
        return "Nama: " + nama +
                "\nNomor Akun: " + nomorAkun +
                "\nSaldo: " + saldo +
                "\nTanggal Registrasi: " + tanggalRegistrasi.format(formatter);
    }
}
