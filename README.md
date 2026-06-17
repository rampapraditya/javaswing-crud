# CRUD Java Swing MVC dengan MySQL

Proyek ini adalah aplikasi desktop manajemen data produk sederhana menggunakan Java Swing, database MySQL, dan arsitektur model **MVC (Model-View-Controller)**. Proyek ini dibuat menggunakan **NetBeans IDE** dan menerapkan teknik **Abstraksi Interface** pada lapisan akses datanya.

## 🛠️ Spesifikasi Teknologi
*   **Java Development Kit (JDK):** Versi 8 atau di atasnya
*   **IDE:** NetBeans IDE
*   **Database Server:** MySQL / MariaDB (XAMPP)
*   **Database Driver:** `mysql-connector-j-8.0.33.jar` (Driver MySQL versi 8.x terbaru untuk mendukung enkripsi keamanan dan performa kueri yang lebih optimal)

---

## 📂 Struktur Package & File Proyek
```text
📦 src
 ┣ 📂 config
 ┃ ┗ 📜 DatabaseConfig.java       # Pengaturan & koneksi ke MySQL
 ┣ 📂 model
 ┃ ┗ 📜 Produk.java               # Kelas model data (POJO)
 ┣ 📂 repository
 ┃ ┣ 📜 ProdukRepository.java     # Interface untuk abstraksi CRUD
 ┃ ┗ 📜 ProdukRepositoryImpl.java # Implementasi query SQL ke MySQL
 ┣ 📂 controller
 ┃ ┗ 📜 ProdukController.java     # Logika bisnis (Jembatan View & Model)
 ┣ 📂 view
 ┃ ┗ 📜 MainView.java             # GUI Form Java Swing (NetBeans GUI Builder)
```

---

## 🗄️ Langkah 1: Persiapan Database MySQL
1. Aktifkan modul **Apache** dan **MySQL** pada panel kontrol XAMPP Anda.
2. Buka browser dan masuk ke **phpMyAdmin** (`http://localhost/phpmyadmin`).
3. Buat database baru dengan nama `db_toko`.
4. Jalankan kueri SQL berikut untuk membuat tabel `produk`:

```sql
CREATE DATABASE db_toko;
USE db_toko;

CREATE TABLE produk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_produk VARCHAR(100) NOT NULL,
    harga DOUBLE NOT NULL,
    stok INT NOT NULL
);
```

---

## 🔌 Langkah 2: Memasang MySQL Connector (`mysql-connector-j-8.0.33.jar`)

Agar aplikasi Java dapat berkomunikasi dengan server database MySQL, Anda wajib menambahkan pustaka driver ini ke dalam classpath NetBeans:

### A. Jika Menggunakan Proyek Ant biasa (Non-Maven):
1. Download file driver `mysql-connector-j-8.0.33.jar`.
2. Di panel **Projects** NetBeans sebelah kiri, cari folder proyek Anda.
3. Klik kanan pada folder **Libraries** ➡️ Pilih **Add JAR/Folder**.
4. Cari dan pilih file `mysql-connector-j-8.0.33.jar` yang sudah di-download, lalu klik **Open**.

### B. Jika Menggunakan Proyek Maven:
Buka file `pom.xml` Anda, lalu tambahkan dependensi berikut di dalam tag `<dependencies>`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
```

---

## 🚀 Langkah 3: Menjalankan Aplikasi

1. Buka proyek Anda di NetBeans IDE.
2. Pastikan konfigurasi *username* dan *password* database di file `src/config/DatabaseConfig.java` sudah sesuai dengan pengaturan komputer Anda.
3. Cari file **`MainView.java`** di dalam package `view`.
4. Klik kanan pada file `MainView.java` ➡️ Pilih **Run File** (atau tekan kombinasi tombol **Shift + F6**).

---

## ⚙️ Fitur Aplikasi
*   **Create:** Menambah data produk baru ke database melalui input form.
*   **Read:** Menampilkan data real-time dari database ke dalam `JTable`.
*   **Update:** Mengubah data produk yang dipilih dari tabel berdasarkan ID.
*   **Delete:** Menghapus data produk dari tabel dan database.
