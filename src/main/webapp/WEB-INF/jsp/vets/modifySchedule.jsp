<%@ page import="java.time.DayOfWeek" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>


<petclinic:layout pageName="vets">

    <div>
        <h1>${vet.firstName}'s Weekly Schedule</h1>
<%--        <form:form action="/modifySchedule" modelAttribute="vet" class="form-horizontal" id="edit-vet-form" th:object="${vet}" method="POST">--%>
<%--            <table style="border: 1px solid black; width: 75%">--%>
<%--                <tr style="border-bottom: 1px solid black;">--%>
<%--                    <th>Weekdays</th>--%>
<%--                    <th>Weekend</th>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <label>--%>
<%--                        <input type="checkbox" id="1" name="day" value="1">--%>
<%--                            Monday</label>--%>
<%--                    </td>--%>

<%--                    <td>--%>
<%--                        <label>--%>
<%--                        <input type="checkbox" id="6" name="day" value="6">--%>
<%--                            Saturday</label>--%>
<%--                    </td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>--%>
<%--                <label>--%>
<%--                <input type="checkbox" id="2" name="day" value="2">--%>
<%--                    Tuesday</label>--%>
<%--                    </td>--%>

<%--                    <td>--%>
<%--                        <label>--%>
<%--                        <input type="checkbox" id="7" name="day" value="7">--%>
<%--                            Sunday</label>--%>
<%--                    </td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>--%>
<%--                <label>--%>
<%--                <input type="checkbox" id="3" name="day" value="3">--%>
<%--                    Wednesday</label>--%>
<%--                    </td>--%>
<%--                    <td></td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>--%>

<%--                <label>--%>
<%--                <input type="checkbox" id="4" name="day" value="4">--%>
<%--                    Thursday</label>--%>
<%--                    </td>--%>
<%--                    <td></td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>--%>
<%--                <label>--%>
<%--                <input type="checkbox" id="5" name="day" value="5">--%>
<%--                    Friday</label>--%>
<%--                    </td>--%>
<%--                    <td></td>--%>
<%--                </tr>--%>
<%--            </table>--%>

<%--            <input type="submit" id="submitSchedule" value="Set Schedule">--%>

<%--        </form:form>--%>
<%--        th:href="@{/modifySchedule}"--%>



<form:form method="POST" th:object="${vet}" modelAttribute="vet">

    <form:checkboxes element="span class='checkbox'" path="schedules" items="${selectableDays}" itemValue="id" />

    <button type="submit">Save Changes</button>
</form:form>

    </div>

    <div class="col-md-2">
        <a href="<spring:url value="/scheduleList" htmlEscape="true" />">Return</a>
    </div>


</petclinic:layout>
