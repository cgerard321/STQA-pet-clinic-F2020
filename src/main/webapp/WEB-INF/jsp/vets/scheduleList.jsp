<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<petclinic:layout pageName="schedules">
    <html>
    <head>
        <title>scheduleList</title>
    </head>
    <body>
    <h1 style="text-align:center">All Vet Schedules</h1>

    <div class="row">
        <table id="vetsTable" class="table table-striped" aria-describedby="veterinarians">
            <thead>
            <tr>
                <th scope="col">Veterinarian Id</th>
                <th scope="col">Available Day</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${schedules.scheduleList}" var="schedule">
                <tr>
                    <td>
                        <c:out value="${schedule.vetId}"/>
                    </td>
                    <td>
                            <%--        hardcode--%>
                        <c:if test="${schedule.dayAvailable == 1}">Monday</c:if>
                        <c:if test="${schedule.dayAvailable == 2}">Tuesday</c:if>
                        <c:if test="${schedule.dayAvailable == 3}">Wednesday</c:if>
                        <c:if test="${schedule.dayAvailable == 4}">Thursday</c:if>
                        <c:if test="${schedule.dayAvailable == 5}">Friday</c:if>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    </body>
    </html>
</petclinic:layout>
