<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="util.jsp" />
<head>
<title>Edit User</title>
<style>
      .error {
         color: #ff0000;
      }

      img {
          width: 100px;
          height: 100px;
          object-fit:cover;
          border-radius: 50%;
          border: 1px solid black;
      }
   </style>
</head>

<div id="wrapper">

<!-- Page Content -->

        <div id="page-content-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">

                          <c:if test = "${userDto.getImage().equals('default.png')}">
                               <img src="<c:url value="/users-img/${userDto.getImage()}"/>"/>
                          </c:if>
                          <c:if test = "${!userDto.getImage().equals('default.png')}">
                               <img src="<c:url value="/users-img/${userDto.getPhone()}${userDto.getImage()}"/>"/>
                          </c:if>
                        <br>
                        <br>
                        <form:form action="/users/edit-profile" method="POST" modelAttribute="userDto" enctype="multipart/form-data">
                            <form:input type="hidden" path="userId" value="${userDto.getUserId()}" />
                            <form:input type="hidden" path="image" value="${userDto.getImage()}" />
                            <div>
                                Name :
                                <form:input type="text" path="name"/>
                                <br>
                                <form:errors path = "name" cssClass = "error" />
                            </div>
                            <br>

                            <div>
                                Email:
                                <form:input type="email" path="email"/>
                            </div>

                            <br>
                            <div>
                                <input type="file" name="file"/>
                            </div>
                            <br>
                            <div>
                                <button type="submit">Update</button>
                                </form:form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->




</div>
