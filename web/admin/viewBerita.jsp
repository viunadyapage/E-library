<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.Berita" %>
<%@ page import="com.tubespbo.tbperpustakaan.dao.BeritaDAO" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.Komentar" %>
<%@ page import="com.tubespbo.tbperpustakaan.dao.KomentarDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.User" %> 
<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");


%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Berita</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  
    <style>
        .berita-item { 
            border: 1px solid #ddd; 
            padding: 15px; 
            margin: 10px 0; 
            border-radius: 5px; 
        }
        .berita-judul { 
            font-size: 1.5em; 
            font-weight: bold; 
        }
        .berita-meta { 
            color: #666; 
            font-size: 0.9em; 
            margin: 5px 0; 
        }
        .error { 
            color: red; 
            padding: 10px; 
            border: 1px solid red; 
            margin: 10px 0; 
        }
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
        }
        .modal-content {
            background-color: white;
            margin: 15% auto;
            padding: 20px;
            width: 70%;
            max-width: 500px;
            border-radius: 5px;
        }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 8px 15px;
            margin: 5px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-secondary { background-color: #6c757d; color: white; }
        .btn-edit { background-color: #28a745; color: white; }
    </style>
   
</head>
    <%@ include file="../header/adminHeader.jsp" %>

 
 <body>
    <div class="container">
        <h1>Daftar Berita Terbaru</h1>
        
        <%
            BeritaDAO beritaDAO = new BeritaDAO();
              KomentarDAO komentarDAO = new KomentarDAO();
            List<Berita> beritaList = null;
            String errorMessage = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
            
            try {
                beritaList = beritaDAO.getAllBerita();
            } catch (Exception e) {
                errorMessage = "Terjadi kesalahan dalam mengambil data berita: " + e.getMessage();
            }
        %>
        

 
 
   <button class="btn-primary" onclick="openAddModal()">Tambah Berita</button>

 
    <% if (errorMessage != null) { %>
        <div class="error">
            <%= errorMessage %>
        </div>
    <% } else if (beritaList != null && !beritaList.isEmpty()) { %>
        <% for (Berita berita : beritaList) { %>
            <div class="berita-item" id="berita-<%= berita.getId() %>">
                <div class="berita-judul">
                    <%= berita.getJudul() %>
                </div>
                <div class="berita-meta">
                    Penulis: <%= berita.getPenulis() %> | 
                    Tanggal: <%= dateFormat.format(berita.getTanggal()) %>
                </div>
                <div class="berita-isi">
                    <%= berita.getIsi() %>
                </div>
                <button class="btn-edit" onclick="openEditModal('<%= berita.getId() %>', '<%= berita.getJudul().replaceAll("\"", "&quot;") %>', '<%= berita.getPenulis().replaceAll("\"", "&quot;") %>', '<%= berita.getIsi().replaceAll("\n", "\\\\n").replaceAll("\"", "&quot;") %>')">Edit</button>
                <button class="btn-delete" onclick="deleteBerita('<%= berita.getId() %>')">Hapus</button>
                <div class="komentar-section">


                </div>
            </div>
        <% } %>
    <% } else { %>
        <p>Tidak ada berita tersedia.</p>
    <% } %>

    <!-- Modal for Add/Edit -->
    <div id="beritaModal" class="modal">
        <div class="modal-content">
            <h2 id="modalTitle">Tambah Berita</h2>
            <form id="beritaForm" method="post" action="berita">
                <input type="hidden" id="beritaId" name="id">
                <input type="hidden" id="action" name="action" value="add">
                <div class="form-group">
                    <label for="judul">Judul:</label>
                    <input type="text" id="judul" name="judul" required>
                </div>
                <div class="form-group">
                    <label for="penulis">Penulis:</label>
                    <input type="text" id="penulis" name="penulis" required>
                </div>
                <div class="form-group">
                    <label for="isi">Isi:</label>
                    <textarea id="isi" name="isi" rows="5" required></textarea>
                </div>
                <button type="submit" class="btn-primary">Simpan</button>
                <button type="button" class="btn-secondary" onclick="closeModal()">Batal</button>
            </form>
        </div>
    </div>

    <script>
        function openAddModal() {
            document.getElementById('modalTitle').textContent = 'Tambah Berita';
            document.getElementById('beritaId').value = '';
            document.getElementById('action').value = 'add';
            document.getElementById('judul').value = '';
            document.getElementById('penulis').value = '';
            document.getElementById('isi').value = '';
            document.getElementById('beritaModal').style.display = 'block';
        }

        function openEditModal(id, judul, penulis, isi) {
            document.getElementById('modalTitle').textContent = 'Edit Berita';
            document.getElementById('beritaId').value = id;
            document.getElementById('action').value = 'update';
            document.getElementById('judul').value = judul;
            document.getElementById('penulis').value = penulis;
            document.getElementById('isi').value = isi.replace(/\\n/g, '\n');
            document.getElementById('beritaModal').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('beritaModal').style.display = 'none';
        }

        function deleteBerita(id) {
            if (confirm('Apakah Anda yakin ingin menghapus berita ini?')) {
                const formData = new FormData();
                formData.append('action', 'delete');
                formData.append('id', id);

                fetch('berita', {
                    method: 'POST',
                    body: formData
                })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        return response.text().then(text => { throw new Error(text); });
                    }
                })
                .catch(error => {
                    alert('Gagal menghapus berita: ' + error.message);
                });
            }
        }

        function addKomentar(event, beritaId) {
            event.preventDefault();
            const form = event.target;
            const formData = new FormData(form);
            formData.append('action', 'comment');
            formData.append('beritaId', beritaId);

            fetch('berita', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    return response.text().then(text => { throw new Error(text); });
                }
            })
            .catch(error => {
                alert('Gagal menambahkan komentar: ' + error.message);
            });
        }

        // Close modal when clicking outside
        window.onclick = function(event) {
            const modal = document.getElementById('beritaModal');
            if (event.target == modal) {
                closeModal();
            }
        }
    </script>  
   
   
   
   
   
   
   
 
 </div>       
      
</body>

</html>           
           
           
           