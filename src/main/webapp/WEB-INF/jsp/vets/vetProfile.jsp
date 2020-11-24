<%--
  Created by Maria Carolina Avila.
  Team: APPT
  Date: 2020-11-23
  Time: 3:57 p.m.
--%>
<%@ page import="java.time.DayOfWeek" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<petclinic:layout pageName="vetProfile">
<h1><c:out value="${vet.lastName},${vet.firstName}" /></h1>

    <main>
        <p><c:forEach var="specialty" items="${vet.specialties}">
            <c:out value="${specialty.name} "/>
        </c:forEach>
            <c:if test="${vet.nrOfSpecialties == 0}">general veterinarian</c:if>
        </p>

        <h3>About me</h3>
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce mattis dapibus turpis, eget cursus magna
            finibus sed. Vivamus nec nulla eget odio feugiat sollicitudin ut sit amet ante. Phasellus in volutpat magna.
            Donec eu massa risus. Vivamus eget ex eu mauris tincidunt ornare. Fusce porta semper sagittis. Pellentesque
            laoreet finibus sem sit amet tristique. Curabitur consectetur vel libero ac convallis.
            Morbi orci nunc, mattis eget luctus id, lacinia eu nisi.
        </p>

        <h3>Schedule</h3>
        <table id="vetSchedule" class="table table-striped table-bordered" aria-describedby="veterinarianSchedule">
            <thead>
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Pet name</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${schedule.toArray()}" var="appointment">
                    <tr>
                        <td>${appointment.getDate()}</td>
                        <td>${appointment.getPet()}</td>
                        <td>
                            <form action="<spring:url value="/vets/${appointment.getId()}/cancel"/>">
                                <button class="btn btn-default" type="submit" name="cancel">Cancel</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${schedule.size() == 0}">
                    <tr>
                        <td colspan="3" class="text-center">No Appointments Scheduled</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </main>


</petclinic:layout>
