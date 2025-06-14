

-- Tabel Berita
CREATE TABLE berita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    judul VARCHAR(255) NOT NULL,
    isi TEXT NOT NULL,
    tanggal TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    penulis VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO berita (judul, isi, penulis) VALUES
('Teknologi AI Terbaru', 'Perkembangan teknologi AI semakin pesat di tahun ini. Berbagai inovasi bermunculan...', 'Admin User'),
('Ekonomi Indonesia 2024', 'Perekonomian Indonesia menunjukkan tren positif pada kuartal ini...', 'Admin User');

-- Tabel Komentar


CREATE TABLE komentar (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isi TEXT NOT NULL,
    tanggal TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    username VARCHAR(100) NOT NULL,
    id_berita INT NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (id_berita) REFERENCES berita(id) ON DELETE CASCADE
);

INSERT INTO komentar (isi, tanggal, username, id_berita)  VALUES ("Ini sebaiknya ....", now(), 'nita12', 1);

SELECT * FROM komentar WHERE id_berita = 1 ORDER BY tanggal DESC;

-- Tabel Notifikasi
CREATE TABLE notifikasi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isi TEXT NOT NULL,
    tanggal TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipe VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'unread',
    id_pengguna INT NOT NULL,
    FOREIGN KEY (id_pengguna) REFERENCES pengguna(id) ON DELETE CASCADE
);

-- Insert sample data
INSERT INTO pengguna (nama, email, password) VALUES
('John Doe', 'john@example.com', 'password123'),
('Jane Smith', 'jane@example.com', 'password123');

INSERT INTO admin (nama, email, password) VALUES
('Admin User', 'admin@example.com', 'admin123');

