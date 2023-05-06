import java.util.Scanner;

public class BankCLI {
    static BankAccount[] bankAccounts;
    private static int jumlahAkun;

    public static void main(String[] args) {
        bankAccounts = new BankAccount[100];
        jumlahAkun = 0;

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            tampilkanMenu();
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    daftarAkun();
                    break;
                case 2:
                    kirimUang();
                    break;
                case 3:
                    setorUang();
                    break;
                case 4:
                    tampilkanDaftarAkun();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);
    }

    public static void tampilkanMenu() {
        System.out.println("========== BANK IKLC ==========");
        System.out.println("1. Daftar Akun");
        System.out.println("2. Kirim Uang");
        System.out.println("3. Setor Uang");
        System.out.println("4. Tampilkan Daftar Akun");
        System.out.println("5. Keluar");
        System.out.print("Masukkan pilihan: ");
    }

    public static void daftarAkun() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan saldo awal: ");
        double saldo = scanner.nextDouble();

        scanner.nextLine(); // Mengonsumsi karakter newline

        BankAccount akunBaru = new BankAccount(nama, saldo);
        bankAccounts[jumlahAkun] = akunBaru;
        jumlahAkun++;

        System.out.println("Akun berhasil didaftarkan dengan nomor: " + akunBaru.getNomorAkun());
        System.out.println("Tanggal registrasi:" + akunBaru.getTanggalRegistrasi());
    }

    public static void kirimUang() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nomor akun pengirim: ");
        String nomorAkunPengirim = scanner.nextLine();

        System.out.print("Masukkan nomor akun penerima: ");
        String nomorAkunPenerima = scanner.nextLine();

        System.out.print("Masukkan jumlah uang yang akan dikirim: ");
        double jumlah = scanner.nextDouble();

        scanner.nextLine(); // Mengonsumsi karakter newline

        BankAccount akunPengirim = cariAkunBankDenganNomor(nomorAkunPengirim);
        BankAccount akunPenerima = cariAkunBankDenganNomor(nomorAkunPenerima);

        if (akunPengirim == null || akunPenerima == null) {
            System.out.println("Nomor akun pengirim atau penerima tidak valid. Silakan coba lagi.");
            return;
        }

        if (akunPengirim.getSaldo() < jumlah) {
            System.out.println("Saldo akun pengirim tidak mencukupi.");
            return;
        }

        akunPengirim.setSaldo(akunPengirim.getSaldo() - jumlah);
        akunPenerima.setSaldo(akunPenerima.getSaldo() + jumlah);

        System.out.println("Pengiriman uang sejumlah " + jumlah + " berhasil.");
        System.out.println("Saldo akun pengirim: " + akunPengirim.getSaldo());
        System.out.println("Saldo akun penerima: " + akunPenerima.getSaldo());
    }

    public static void setorUang() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nomor akun: ");
        String nomorAkun = scanner.nextLine();

        System.out.print("Masukkan jumlah uang yang akan disetor: ");
        double jumlah = scanner.nextDouble();

        scanner.nextLine(); // Mengonsumsi karakter newline

        BankAccount akun = cariAkunBankDenganNomor(nomorAkun);

        if (akun == null) {
            System.out.println("Nomor akun tidak valid. Silakan coba lagi.");
            return;
        }

        akun.setSaldo(akun.getSaldo() + jumlah);

        System.out.println("Penyetoran uang sejumlah " + jumlah + " berhasil.");
        System.out.println("Saldo akun: " + akun.getSaldo());
    }

    public static void tampilkanDaftarAkun() {
        System.out.println("========== DAFTAR AKUN ==========");
        for (int i = 0; i < jumlahAkun; i++) {
            System.out.println("Akun " + (i + 1) + ":");
            System.out.println(bankAccounts[i]);
            System.out.println();
        }
    }

    public static BankAccount cariAkunBankDenganNomor(String nomorAkun) {
        for (int i = 0; i < jumlahAkun; i++) {
            if (bankAccounts[i] != null && bankAccounts[i].getNomorAkun().equals(nomorAkun)) {
                return bankAccounts[i];
            }
        }
        return null;
    }
}
