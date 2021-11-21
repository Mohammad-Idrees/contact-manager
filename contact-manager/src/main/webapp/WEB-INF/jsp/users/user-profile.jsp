<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<style>
img {
    width: 100px;
    height: 100px;
    object-fit:cover;
    border-radius: 50%;
    border: 1px solid black;
}
</style>

<jsp:include page="util.jsp" />
<title>Profile</title>

<div id="wrapper">

<!-- Page Content -->

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">

                        <c:if test = "${user.getImage().equals('default.png')}">
                             <img src="<c:url value="/users-img/${user.getImage()}"/>"/>
                        </c:if>
                        <c:if test = "${!user.getImage().equals('default.png')}">
                             <img src="<c:url value="/users-img/${user.getUserId()}${user.getImage()}"/>"/>
                        </c:if>

                        <br><br>
                        Name : ${user.getName()}
                        <br><br>
                        Email: ${user.getEmail()}
                        <br><br>
                        Role : ${user.getRole()}
                        <br><br>

                        <button><a href="/users/edit-profile">Update Profile</a></button>

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->




</div>