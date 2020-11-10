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
                <th scope="col">Veterinarian Id</th>
                <th scope="col">Specialties</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${vets.vetList}" var="vet">
                <tr>
                    <td>
                        <c:out value="${vet.firstName} ${vet.lastName}"/>
                    </td>
                    <td>
                        <c:out value="${vet.id}"/>
                    </td>


                    <td>
                        <c:forEach var="specialty" items="${vet.specialties}">
                            <c:out value="${specialty.name} "/>
                        </c:forEach>
                        <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <spring:url value="/displaySchedule" var="formUrl"/>
    <form:form modelAttribute="vets" action="${fn:escapeXml(formUrl)}" method="GET" class="form-horizontal"
               id="findSchedule">

        <label>Veterinarian ID</label>
        <select name="vetId">
            <c:forEach items="${vets.vetList}" var="vet">

                <option><c:out value="${vet.id}"/></option>

            </c:forEach>
        </select>
        <br>

        <input type="submit" value="View Schedule">
    </form:form>

    <div class="row">
        <div class="col-md-2">
            <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
        </div>

        <div class="col-md-2">
            <a class="button button-primary" href="${pageContext.servletContext.contextPath}/scheduleList">View All
                Schedules</a>
        </div>

        <div class="col-md-2">
            <a href="<spring:url value="/vets.json" htmlEscape="true" />">View as JSON</a>
        </div>
    </div>

</petclinic:layout>
