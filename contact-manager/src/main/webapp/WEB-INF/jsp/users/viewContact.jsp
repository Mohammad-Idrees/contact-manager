<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<style>
img {
    width: 200px;
    height: 200px;
    object-fit:cover;
    border-radius: 50%;
    border: 1px solid black;
}
</style>

<jsp:include page="util.jsp" />
<title>Contact</title>

<div id="wrapper">

<!-- Page Content -->

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">

                      <c:if test = "${contactDto.getImage().equals('default.png')}">
                           <img src="<c:url value="/contacts/${contactDto.getImage()}"/>"/>
                      </c:if>
                      <c:if test = "${!contactDto.getImage().equals('default.png')}">
                           <img src="<c:url value="/contacts/${contactDto.getPhone()}${contactDto.getImage()}"/>"/>
                      </c:if>


                        <br><br>
                        Name : ${contactDto.getName()}
                        <br><br>
                        Phone: ${contactDto.getPhone()}
                        <br><br>
                        Email: ${contactDto.getEmail()}
                        <br><br>
                        <button> <a href="/users/edit-contact/${contactDto.getContactId()}">Edit</a></button>
                        <button><a href="/users/delete-contact/${contactDto.getContactId()}">Delete</a></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->




</div>