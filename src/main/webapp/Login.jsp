<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/locale.tld" prefix="lng" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>BizPage Bootstrap Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700"
          rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link href="css/style.css" rel="stylesheet">

    <!-- =======================================================
      Theme Name: BizPage
      Theme URL: https://bootstrapmade.com/bizpage-bootstrap-business-template/
      Author: BootstrapMade.com
      License: https://bootstrapmade.com/license/
    ======================================================= -->
</head>

<body>
<%@include file="jspf/navbar.jspf" %>
<c:set var="ses_role" value="${sessionScope.ses_role}"></c:set>
<c:set var="lang" value="${sessionScope.session_lang}"></c:set>
<br>
<div class="card">
    <article class="card-body mx-auto" style="max-width: 400px;">
        <h2><lng:tr text="Register" locale="${lang}"/></h2>
        <form method="post" action="login">
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="name" class="form-control" placeholder="<lng:tr text="Name" locale="${lang}"></lng:tr>"
                       type="text" required>
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                </div>
                <input name="email" class="form-control"
                       placeholder="<lng:tr text="Email_address" locale="${lang}"></lng:tr>" type="email" required
                       pattern="\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b">
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                </div>
                <select name="gender" class="form-control" required>
                    <option value="MALE" selected><lng:tr text="Male" locale="${lang}"></lng:tr></option>
                    <option value="FEMALE"><lng:tr text="Female" locale="${lang}"></lng:tr></option>
                </select>
            </div> <!-- form-group end.// -->
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
                </div>
                <input name="birthday" class="form-control"
                       placeholder="<lng:tr text="Your_birthday" locale="${lang}"></lng:tr>" type="date" required>
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                </div>
                <input name="pass" class="form-control" placeholder="<lng:tr text="Password" locale="${lang}"></lng:tr>"
                       type="password" required
                       pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
                       title="<lng:tr text="PassPattern" locale="${lang}"/>">
            </div>
            <c:if test="${ses_role eq 'ADMIN'}">
                <select name="role" class="form-control">
                    <option value="USER" selected><lng:tr text="User" locale="${lang}"></lng:tr></option>
                    <option value="ADMIN"><lng:tr text="Admin" locale="${lang}"></lng:tr></option>
                </select>
            </c:if>
            <!-- form-group// -->
            <!-- form-group// -->
            <div class="form-group">
                <button type="submit" class="btn btn-info"><h6><lng:tr text="Create_account"
                                                                       locale="${lang}"></lng:tr></h6></button>
            </div> <!-- form-group// -->
            <p class="text-center"><h5><lng:tr text="Have_account" locale="${lang}"/></h5> <a href="/sign_in"><lng:tr
                text="servlets.Login" locale="${lang}"></lng:tr></a> </p>
        </form>
    </article>
</div> <!-- card.// -->

</div>
<!--container end.//-->

<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/jquery/jquery-migrate.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/superfish/hoverIntent.js"></script>
<script src="lib/superfish/superfish.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/counterup/counterup.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/isotope/isotope.pkgd.min.js"></script>
<script src="lib/lightbox/js/lightbox.min.js"></script>
<script src="lib/touchSwipe/jquery.touchSwipe.min.js"></script>
<!-- Contact Form JavaScript File -->
<script src="contactform/contactform.js"></script>

<!-- Template Main Javascript File -->
<script src="js/main.js"></script>

</body>
</html>

