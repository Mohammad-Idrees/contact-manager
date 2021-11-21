<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<style>
img {
    width: 35px;
    height: 35px;
    object-fit:cover;
    border-radius: 50%;
    border: 1px solid black;
}
</style>

<jsp:include page="util.jsp" />
<title>Contacts</title>

<div id="wrapper">

${message}
        <h1>Contacts</h1>

        List of Contacts

        <table border="1" cellpadding="5" cellspacing="5">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                          <th>${contact.getContactId()}</th>
                          <td>

                          <c:if test = "${contact.getImage().equals('default.png')}">
                                 <img src="<c:url value="/contacts/${contact.getImage()}"/>"/>
                            </c:if>
                            <c:if test = "${!contact.getImage().equals('default.png')}">
                                 <img src="<c:url value="/contacts/${contact.getPhone()}${contact.getImage()}"/>"/>
                            </c:if>
                          <a href="contacts/${contact.getContactId()}">${contact.getName()}</a>
                          </td>
                          <td>${contact.getPhone()}</td>
                          <td>
                          <button> <a href="edit-contact/${contact.getContactId()}">Edit</a></button>
                          <button><a href="delete-contact/${contact.getContactId()}">Delete</a></button>
                          </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br><br>
            <%--For displaying Previous link except for the 1st page --%>
            <c:if test="${currentPage != 1}">
                <td><a href="/users/contacts?page=${currentPage - 1}">Previous</a></td>
            </c:if>

            <%--For displaying Page numbers.
            The when condition does not display a link for the current page--%>
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="/users/contacts?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="/users/contacts?page=${currentPage + 1}">Next</a></td>
            </c:if>


    </div>
    <!-- /#wrapper -->