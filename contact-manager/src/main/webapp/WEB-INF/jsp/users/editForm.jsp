<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="util.jsp" />
<head>
<title>Edit Contact</title>
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

                          <c:if test = "${contactDto.getImage().equals('default.png')}">
                               <img src="<c:url value="/contacts/${contactDto.getImage()}"/>"/>
                          </c:if>
                          <c:if test = "${!contactDto.getImage().equals('default.png')}">
                               <img src="<c:url value="/contacts/${contactDto.getPhone()}${contactDto.getImage()}"/>"/>
                          </c:if>
                        <br>
                        <br>
                        <form:form action="/users/edit-contact/${contactDto.getContactId()}" method="POST" modelAttribute="contactDto" enctype="multipart/form-data">
                            <form:input type="hidden" path="contactId" value="${contactDto.getContactId()}" />
                            <form:input type="hidden" path="image" value="${contact.getImage()}" />
                            <div>
                                Name :
                                <form:input type="text" path="name"/>
                                <br>
                                <form:errors path = "name" cssClass = "error" />
                            </div>
                            <br>
                            <div>
                                Phone:
                                <form:input type="text" path="phone"/>
                                <br>
                                <form:errors path = "phone" cssClass = "error" />
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
