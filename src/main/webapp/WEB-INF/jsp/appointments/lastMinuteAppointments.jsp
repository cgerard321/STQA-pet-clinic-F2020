<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<petclinic:layout pageName="confirmCustomAppointment">
    <head>
        <meta name="author" content="Alexandra Mormontoy"/>
        <title>Available Visits</title>
    </head>

    <body>
    <h1>Book a quick Appointment</h1>
    <h2>Available Dates for the next 3 days</h2>

    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Options</th>
            <th>Day</th>
            <th>Veterinarian</th>
            <th>Specialty</th>
        </tr>
        </thead>

        <!-- Code to create each row-->
        <c:set var="flag" value="0" scope="page"/>
        <c:forEach items="${vets.vetList}" var="vet">
            <c:forEach items="${vet.getSchedulesLastMinute()}" var="sched">

            <c:set var="flag" value="${flag + 1}" scope="page"/>
            <tr>
                <td>
                    <c:out value="${flag}"/>
                </td>
                <td>
                    <c:out value="${sched}"/>
                </td>
                <td><c:out value="${vet.firstName} ${vet.lastName}"/></td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">None</c:if>
                </td>
            </tr>
        </c:forEach>
        </c:forEach>
    </table>

    <form:form class="form-horizontal">
        <label class="control-label">Choose your Appointment </label>
        <select class="form-control" name="result">
            <c:forEach var="count" begin="1" end="${flag}">
                <option value="<c:out value="${count}"/>"><c:out value="${count}"/></option>
            </c:forEach>
        </select>
        <br><br>
        <input class="btn btn-default" type="submit" value="Set Appointment"/>
    </form:form>

    </body>
</petclinic:layout>
