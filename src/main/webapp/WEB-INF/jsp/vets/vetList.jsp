<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2 id="veterinarians">Veterinarians</h2>

    <div class="row">
        <table id="vetsTable" class="table table-striped" aria-describedby="veterinarians">
            <thead>
            <tr>
                <th scope="col">Name</th>
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

<%--    Appointment Scheduler - feat/APPT-SQTA-34_Vet_Set_Schedule--%>
    <div>
        <h2>Set Weekly Availabilities</h2>

        <label>Select a Veterinarian</label>
        <select>
            <c:forEach items="${vets.vetList}" var="vet">
                    <option>
                        <c:out value="${vet.firstName} ${vet.lastName}"/>
                    </option>
            </c:forEach>
        </select>


<%--        Gabriel Schedule Form--%>

        <form id="scheduler" action="saveVetSchedule.jsp">
            <label>Monday</label>
            <input type="checkbox" id="1" name="monday">
            <br>

            <label>Tuesday</label>
            <input type="checkbox" id="2" name="tuesday">
            <br>


            <label>Wednesday</label>
            <input type="checkbox" id="3" name="wednesday">
            <br>


            <label>Thursday</label>
            <input type="checkbox" id="4" name="thursday">
            <br>


            <label>Friday</label>
            <input type="checkbox" id="5" name="friday">
            <br>


            <input type="submit" id="submitSchedule" value="Set Schedule">

        </form>
    </div>



    <div class="row">
        <div class="col-md-2">
            <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
        </div>
        <div class="col-md-2">
            <a href="<spring:url value="/vets.json" htmlEscape="true" />">View as JSON</a>
        </div>
    </div>
</petclinic:layout>
