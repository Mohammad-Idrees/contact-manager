<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
  <head>
    <title>sign in</title>
  </head>
  <body>
     <jsp:include page="navbar.jsp" />
     <div>
      <form method="post" action="/login">
        <h2>sign in</h2>

        <c:if test = "${param.error == 0}">
            <p>Invalid username or password</p>
        </c:if>

        <c:if test = "${param.logout == 0}">
            <p>Logout Successful</p>
        </c:if>

        <p>
          <label for="username">Username</label>
          <input type="text" id="username" name="username"  placeholder="Username" required>
        </p>
        <p>
          <label for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="Password" required>
        </p>
        <button type="submit">Sign in</button>
      </form>
</div>
</body></html>