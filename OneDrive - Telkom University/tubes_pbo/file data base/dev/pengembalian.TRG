TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER after_pengembalian_insert\nAFTER INSERT ON pengembalian\nFOR EACH ROW\nBEGIN\n    -- Update status buku berdasarkan status pengembalian\n    IF NEW.status = \'Dikembalikan\' THEN\n        UPDATE buku \n        SET status = \'Tersedia\' \n        WHERE ID_buku = (SELECT ID_buku FROM peminjaman WHERE ID_peminjaman = NEW.ID_peminjaman);\n    ELSEIF NEW.status = \'Hilang\' THEN\n        UPDATE buku \n        SET status = \'Hilang\' \n        WHERE ID_buku = (SELECT ID_buku FROM peminjaman WHERE ID_peminjaman = NEW.ID_peminjaman);\n    END IF;\n    \n    -- Update status peminjaman\n    UPDATE peminjaman \n    SET status = NEW.status \n    WHERE ID_peminjaman = NEW.ID_peminjaman;\nEND'
sql_modes=1098907648
definers='root@localhost'
client_cs_names='utf8mb4'
connection_cl_names='utf8mb4_unicode_ci'
db_cl_names='utf8mb4_general_ci'
created=1749820202187306
