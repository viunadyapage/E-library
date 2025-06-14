<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login User - Perpustakaan</title>
    <style>
        body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; min-height: 90vh; background-color: #f4f4f4; margin: 0; }
        .login-container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); width: 320px; }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        .input-group { margin-bottom: 15px; }
        .input-group label { display: block; margin-bottom: 5px; color: #555; font-size: 14px; }
        .input-group input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        .btn-login { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; transition: background-color 0.3s; }
        .btn-login:hover { background-color: #0056b3; }
        .error-message { color: red; background-color: #ffebee; border: 1px solid #ffcdd2; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; font-size: 14px; }
        .register-link { text-align: center; margin-top: 15px; font-size: 14px; }
        .register-link a { color: #007bff; text-decoration: none; }
        .register-link a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login Pengguna</h2>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <p class="error-message"><%= errorMessage %></p>
        <%
            }
        %>

        <form action="login" method="post">
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div>
                <button type="submit" class="btn-login">Login</button>
            </div>
        </form>
        <div class="register-link">
            <p>Belum punya akun? <a href="register">Daftar di sini</a></p>
        </div>
    </div>
</body>
</html>