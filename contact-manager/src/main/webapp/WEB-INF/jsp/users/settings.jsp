

<jsp:include page="util.jsp" />
<title>Settings</title>

<div id="wrapper">

        <!-- Page Content -->

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">

                        ${message}
                        <br><br>
                        Change Password
                        <br><br>
                        <form action="/users/change-password" method="POST">
                        Old Password:
                        <input type="password" name="old-password" />
                        <br><br>
                        New Password
                        <input type="password" name="new-password" />
                        <br><br>
                        <button type="submit">submit</submit>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->


    </div>
    <!-- /#wrapper -->