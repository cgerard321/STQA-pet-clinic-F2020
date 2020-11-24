<%@ page import="java.time.DayOfWeek" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<petclinic:layout pageName="vets">
    <h2 id="veterinarians">Veterinarians</h2>

    <div class="row">
        <table id="vetsTable" class="table table-striped" aria-describedby="veterinarians">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Availability</th>
<%--                <th scope="col"></th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${vets.vetList}" var="vet">
                <tr>
                    <td>
                        <c:out value="${vet.firstName} ${vet.lastName}"/>
                    </td>

                    <td>
                        <ol>
                            <c:forEach var="day" items="${vet.schedules}">

                                <li style="list-style-type: none"><c:out value="${day.name}"/></li>

                            </c:forEach>
                            <c:if test="${vet.nrOfDaysAvailable == 0}">N/A</c:if>
                        </ol>
                    </td>

<%--                    <td>--%>
<%--                        <a href="<spring:url value="/vets.html" htmlEscape="true"/>">Edit</a>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


<%--<div>--%>
<%--    <form>--%>
<%--<label>Select Veterinarian</label>--%>
<%--        --%>
<%--        <select>--%>
<%--            <c:forEach items="${vets.vetList}" var="vet">--%>
<%--                <option value="${vet.lastName}">${vet.lastName}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </form>--%>
<%--</div>--%>


    <div class="col-md-2">
        <a href="<spring:url value="/vets.html" htmlEscape="true" />">Return</a>
    </div>


</petclinic:layout>
