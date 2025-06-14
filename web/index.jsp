<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TB Perpustakaan - Beranda</title>
    <link rel="stylesheet" href="css/style.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container min-vh-100 d-flex flex-column justify-content-center align-items-center">

    <h1 class="mb-4 text-center">Selamat Datang di Sistem Perpustakaan</h1>

    <div class="row shadow p-4 bg-white rounded w-100" style="max-width: 800px;">
        <div class="col-md-6 border-end d-flex flex-column align-items-center">
            <h3>Login User</h3>
            <p class="text-muted">Masuk sebagai pengguna perpustakaan</p>
            <a href="login_user.jsp" class="btn btn-primary">Login User</a>
        </div>
        <div class="col-md-6 d-flex flex-column align-items-center">
            <h3>Login Admin</h3>
            <p class="text-muted">Masuk sebagai admin sistem</p>
            <a href="loginAdmin.jsp" class="btn btn-dark">Login Admin</a>
        </div>
    </div>

    <div class="mt-4 text-center">
        <h4>Tes Koneksi Database</h4>
        <p>
            <a href="testDbConnection" class="btn btn-outline-success">Klik di sini untuk Tes Koneksi ke Database</a>
        </p>
    </div>

</div>

<script src="js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>