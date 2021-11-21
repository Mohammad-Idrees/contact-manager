<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>SignUp</title>
<style>
      .error {
         color: #ff0000;
      }
   </style>
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <h3>${message}</h3>
    <h1>SignUp</h1>
    <form:form action="registration" method="POST" modelAttribute="userDto">
    <div>
    Name:
    <form:input type="text" path="name"/>
    <br>
    <form:errors path = "name" cssClass = "error" />
    </div>
    <div>
    Email:
    <form:input type="email" path="email"/>
    <br>
    <form:errors path = "email" cssClass = "error" />
    </div>
    <div>
    Password
    <form:input type="password" path="password"/>
    <br>
    <form:errors path = "password" cssClass = "error" />
    </div>
    <button type="submit">signup</button>
    </form:form>
</body>

</html>

