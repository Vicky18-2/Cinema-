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
<c:set var="lang" value="${sessionScope.session_lang}"></c:set>
<br>


<main id="main">

    <c:set var="film" value="${sessions[0].film}"></c:set>
    <c:set var="role" value="${sessionScope.ses_role}"></c:set>
    <div class="container">
        <h5>|<a href="/sessions_for_day?day_of_week=${day}&sort_content="><lng:tr text="Sort_by_time"
                                                                                  locale="${lang}"/></a>|
            |<a href="/sessions_for_day?day_of_week=${day}&sort_content=seat"><lng:tr text="Sort_by_seats"
                                                                                      locale="${lang}"/></a>|
            |<a href="/sessions_for_day?day_of_week=${day}&sort_content=film"><lng:tr text="Sort_by_film_name"
                                                                                      locale="${lang}"/></a>|<br></h5>
        <br>
        <c:if test="${role eq 'ADMIN'}"><a href="/createSession?day=${day}"><lng:tr text="Create_session"
                                                                                    locale="${lang}"/></a></c:if>
        <h3>${day}</h3>
        <div class="row">
            <c:choose>
                <c:when test="${sessionScope.sort_content eq 'film'}">
                    <c:forEach var="s" items="${sessions}">
                        <div class="col-sm-1">
                            <a href="seats?session_id=${s.id}" class="dates">
                                    ${s.time}
                            </a>
                        </div>
                        <div class="col-sm-4">
                            <h4><b><lng:tr text="Movie" locale="${lang}"/>: </b>${s.film.name}</h4>
                        </div>
                    </c:forEach>
                </c:when>
                <c:when test="${sessionScope.sort_content eq 'seat'}">
                    <c:forEach var="sessionSeat" items="${sessionsSeat}">
                        <div class="border-primary">
                            <div class="col-sm-1">
                                <a href="seats?session_id=${sessionSeat.key.id}" class="dates">
                                        ${sessionSeat.key.time}
                                </a>
                            </div>
                            <div class="col-sm-4">
                                <h4><b><lng:tr text="Movie" locale="${lang}"/>: </b>${sessionSeat.key.film.name}</h4>
                            </div>
                            <div class="col-sm-4">
                                <h4><b><lng:tr text="Available_seats" locale="${lang}"/>: </b>${sessionSeat.value}</h4>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="s" items="${sessions}">
                        <div class="border-primary">
                            <div class="col-sm-1">
                                <a href="seats?session_id=${s.id}" class="dates">
                                        ${s.time}
                                </a>
                            </div>
                            <div class="col-sm-2">
                                <h4><b><lng:tr text="Movie" locale="${lang}"/>: </b>${s.film.name}</h4>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <br>
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

