<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%--<%--%>
<%--    String[] availableDays = request.getParameterValues("day");--%>
<%--    request.setAttribute("availableDays", availableDays);--%>

<%--    String vet = request.getParameter("veterinarian");--%>
<%--    request.setAttribute("vet", vet);--%>
<%--%>--%>


<petclinic:layout pageName="displaySchedule">

    <html>
    <head>
        <title>Vet Schedule</title>
    </head>
    <body>
    <div>
        <h2>Veterinarian ID</h2>
        <strong><c:out value="${schedule.vetId}"/></strong>


        <h3>Available Day</h3>
            <%--        hardcode--%>
        <p><c:if test="${schedule.dayAvailable == 1}">Monday</c:if>
            <c:if test="${schedule.dayAvailable == 2}">Tuesday</c:if>
            <c:if test="${schedule.dayAvailable == 3}">Wednesday</c:if>
            <c:if test="${schedule.dayAvailable == 4}">Thursday</c:if>
            <c:if test="${schedule.dayAvailable == 5}">Friday</c:if></p>
    </div>
    </body>
    </html>
</petclinic:layout>
