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

</head>

<body>
<%@include file="jspf/navbar.jspf" %>
<br>
<c:set var="lang" value="${sessionScope.session_lang}"></c:set>


<main id="main">
    <c:set var="role" value="${sessionScope.ses_role}"></c:set>
    <h1>${role}</h1>
    <c:set var="booked" value="${booked_seats}"/>
    <c:set var="ses_id" value="${session_id}" scope="session"></c:set>
    <c:set var="text" value="Session{id=${ses_id}"></c:set>
    <c:forEach var="ticks" items="${sessionScope.tickets}">
        <c:choose>
            <c:when test="${ses_id==ticks.session.id}">
                <c:set var="smth" value="${smth}${ticks}"></c:set>
            </c:when>
        </c:choose>
    </c:forEach>
    <br>
    <div class="center-block">
        <c:forEach var="column" items="${seats}">
            <div class="row">
                <c:forEach var="seat" items="${column.seats}">
                    <c:set value="${column.price+seat.price}" var="price"></c:set>
                    <div class="col-sm-1">
                        <c:choose>
                            <c:when test="${fn:containsIgnoreCase(booked, seat)}">
                                <a href="" class="icon ion-android-cancel"></a>
                            </c:when>
                            <c:when test="${fn:contains(smth, seat)&&fn:contains(smth, text)}">
                                <a href="" class="icon ion-android-checkbox-outline"></a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addToCart?id_seat=${seat.id}&id_col=${column.id}&ses_id=${session_id}"
                                   class="icon ion-android-checkbox-outline-blank"
                                   title="<lng:tr text="Row" locale="${lang}"/>: ${column.id}
<lng:tr text="Column" locale="${lang}"/>: ${seat.id}
<lng:tr text="Price" locale="${lang}"/>: ${price}"></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
                <br>
            </div>
        </c:forEach>
    </div>
    <a href="/sessions_for_day?day_of_week=${sessionScope.day}"><lng:tr text="Back" locale="${lang}"/></a>
</main>
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