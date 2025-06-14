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

    if (loggedInUser == null) {
        response.sendRedirect("login_user.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Berita</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .berita-judul {
        color: green;
        font-size: 2.5em; /* Adjust size as needed, e.g., 2.5em for large font */
        font-weight: bold; /* Optional: makes the font bolder */        
    }
    
    .commentator {
        color: green;
        font-size: 0.5em; /* Adjust size as needed, e.g., 2.5em for large font */        
    }

    </style>
    
</head>
    <%@ include file="../header/userHeader.jsp" %>
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
        
        <% if (errorMessage != null) { %>
            <div class="error">
                <%= errorMessage %>
            </div>
        <% } else if (beritaList != null && !beritaList.isEmpty()) { %>
            <% for (Berita berita : beritaList) { %>
                <div class="berita-item">
                    <div class="berita-judul">
                        <%= berita.getJudul() %>
                    </div>
                    <div class="berita-meta">
                        Penulis: <%= berita.getPenulis() %> | 
                        Tanggal: <%= dateFormat.format(berita.getTanggal()) %>
                    </div>
                    <div class="berita-isi">
                        <%= berita.getIsi() /*berita.getIsi().length() > 300 ? 
                            berita.getIsi().substring(0, 300) + "..." : 
                            berita.getIsi()*/ %>
                    </div>
                    


                <!-- Komentar Section -->
                <div class="komentar-section">
                    <h4>Komentar</h4> <ul>
                    <%
                        List<Komentar> komentarList = null;
                        try {
                            komentarList = komentarDAO.getKomentarByBerita(berita.getId());
                        } catch (Exception e) {
                            out.println("<div class='error'>Gagal memuat komentar: " + e.getMessage() + "</div>");
                        }

                        if (komentarList != null && !komentarList.isEmpty()) {
                            for (Komentar komentar : komentarList) {
                    %>
                        <div class="komentar">

                            <div>
                                <li>   <%= komentar.getIsi() %>  <div class="commentator"> <%= komentar.getIdPengguna() %>   <%= dateFormat.format(komentar.getTanggal()) %> </div></li>
                            </div>
                        </div>
                    <%
                            } // end for
                    %>                  
                    </ul>
                    <%
                        } else {
                    %>
                        <div>Tidak ada komentar untuk berita ini.</div>
                    <%
                        }
                    %>
                </div>
                    
                    
                    <!-- Form Komentar -->
   <div class="mt-3">
    <textarea class="form-control mb-2" id="komentar_<%= berita.getId() %>" placeholder="Tulis komentar..."></textarea>
    <button class="btn btn-primary" onclick="kirimKomentar(<%= berita.getId() %>)">Kirim Komentar</button>
    <div id="status_<%= berita.getId() %>" class="mt-2 text-success"></div>
   </div>
                </div>
            <% } %>
            
            
        <% } else { %>
            <div class="no-berita">
                Tidak ada berita yang tersedia.
            </div>
        <% } %>
        
 
         
    </div>
    
    <script>
function kirimKomentar(idBerita) {
    const komentar = document.getElementById('komentar_' + idBerita).value;
    if (!komentar.trim()) {
        alert("Komentar tidak boleh kosong.");
        return;
    }

    fetch('../AddKomentar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            id_berita: idBerita,
            isi: komentar,
            username : "<%=loggedInUser.getUsername() %>"
        })
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('status_' + idBerita).textContent = data;
        document.getElementById('komentar_' + idBerita).value = '';
        location.reload(); // Refresh the page after successful comment submission
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('status_' + idBerita).textContent = 'Gagal mengirim komentar.';
    });
}
</script>

    
    
</body>
</html>
