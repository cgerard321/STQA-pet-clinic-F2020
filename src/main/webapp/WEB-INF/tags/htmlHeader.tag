<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
PetClinic :: a Spring Framework demonstration
--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags --%>

    <spring:url value="/resources/images/favicon.png" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <title>PetClinic :: a Spring Framework demonstration</title>

    <%-- CSS generated from LESS --%>

    <spring:url value="/resources/css/petclinic.css" var="petclinicCss"/>
    <link href="${petclinicCss}" rel="stylesheet"/>
    <spring:url value="/resources/css/foundation-icons/foundation-icons.css" var="foundationIcon"/>
    <link href="${foundationIcon}" rel="stylesheet">

    <%-- Css added for ownerListTable --%>
    <spring:url value="/resources/css/ownerListTable.css" var="hallOfFame" />
    <link href="${hallOfFame}" rel="stylesheet">

    <%-- Css added for back to top button --%>
    <spring:url value="/resources/css/goBackToTop.css" var="goBackToTopCss" />
    <link href="${goBackToTopCss}" rel="stylesheet">


    <%-- Css added for OwnerListTable --%>
    <spring:url value="/resources/css/ownerListTable.css" var="ownerTable" />
    <link href="${ownerTable}" rel="stylesheet">

    <%-- Css added for calendar --%>
    <spring:url value="/resources/css/calendar.css" var="calendarCss" />
    <link href="${calendarCss}" rel="stylesheet">

    <%-- Css added for hall of fame --%>
    <spring:url value="/resources/css/hallOfFame.css" var="hallOfFame" />
    <link href="${hallOfFame}" rel="stylesheet">

    <%-- Css added for showing pet information in hall of fame #Simon St-Andre --%>
    <spring:url value="/resources/css/petInfoCard.css" var="cardInfo"/>
    <link href="${cardInfo}" rel="stylesheet">

    <spring:url value="/resources/css/headerPage.css" var="headerCss"/>
    <link href="${headerCss}" rel="stylesheet">

    <spring:url value="/resources/css/footer.css" var="footerCss"/>
    <link href="${footerCss}" rel="stylesheet">

    <%-- Css for Pet Page #Simon St-Andre --%>
    <spring:url value="/resources/css/petPage.css" var="petPageCss"/>
    <link href="${petPageCss}" rel="stylesheet">
    <%-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --%>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Only datepicker is used -->
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"/>
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.theme.min.css" var="jQueryUiThemeCss"/>
    <link href="${jQueryUiThemeCss}" rel="stylesheet"/>

</head>
