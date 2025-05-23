CREATE TABLE status_buku (
    idStatus VARCHAR(50) PRIMARY KEY,
    namaStatus VARCHAR(50) NOT NULL
);

INSERT INTO status_buku (idStatus, namaStatus) VALUES
('Tersedia', 'Tersedia'),
('Dipinjam', 'Dipinjam'),
('Tidak_Tersedia', 'Tidak Tersedia');

CREATE TABLE kategori (
    idKategori VARCHAR(36) PRIMARY KEY,
    namaKategori VARCHAR(100) NOT NULL
);

CREATE TABLE buku (
    idBuku VARCHAR(36) PRIMARY KEY,
    judul VARCHAR(255),
    penulis VARCHAR(255),
    penerbit VARCHAR(255),
    tahunTerbit YEAR,
    idStatus VARCHAR(50),
    FOREIGN KEY (idStatus) REFERENCES status_buku(idStatus)
);

CREATE TABLE buku_kategori (
    idBuku VARCHAR(36),
    idKategori VARCHAR(36),
    PRIMARY KEY (idBuku, idKategori),
    FOREIGN KEY (idBuku) REFERENCES buku(idBuku) ON DELETE CASCADE,
    FOREIGN KEY (idKategori) REFERENCES kategori(idKategori) ON DELETE CASCADE
);