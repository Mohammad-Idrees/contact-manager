<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="util.jsp" />
<head>
<title>Contact Form</title>
<style>
      .error {
         color: #ff0000;
      }
   </style>
</head>



<div id="wrapper">

        ${message}
        <!-- Page Content -->

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Contact Form</h1>
                            <form:form action="contacts" method="POST" modelAttribute="contactDto" enctype="multipart/form-data">
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
                                <button type="submit">submit</button>
                                </form:form>
                            </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->


    </div>
    <!-- /#wrapper -->
