<%@ page import="java.time.LocalDate" %>
<%@ page import="org.springframework.samples.petclinic.util.CalendarHelper" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<c:set var = "calendar" value = "${CalendarHelper.getCalendar(LocalDate.now().getMonth().getValue() - 1, LocalDate.now().getYear())}"/>
<c:set var = "days" value = "${calendar.getDays()}"/>
<c:set var = "events" value = "${calendar.getEvents()}"/>

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" alt="A cat and a dog" src="${petsImage}"/>
        </div>
    </div>

    <%-- Calendar -Louis C. --%>

    <%-- For anyone that wants to work on this here is the origin of the calendar --%>
    <%-- https://bootsnipp.com/snippets/v200E --%>
    <div class="row">
        <div class="col-md-12">
            <div id="calendar">
                <table>
                    <summary><strong><c:out value="${LocalDate.now().getMonth()}"/></strong> <c:out
                            value="${LocalDate.now().getYear()}"/></summary>
                    <thead>
                    <tr>
                        <th>Sun</th>
                        <th>Mon</th>
                        <th>Tue</th>
                        <th>Wed</th>
                        <th>Thu</th>
                        <th>Fri</th>
                        <th>Sat</th>
                    </tr>
                    </thead>
                    <c:forEach begin="0" end="${calendar.getNumberOfWeeks()}" varStatus="i">
                        <c:choose>
                            <c:when test=""></c:when>
                        </c:choose>
                        <tr>
                        <c:forEach begin="0" end="6" varStatus="j">
                            <c:choose>
                                <c:when test="${days[i.index][j.index]=='0'}">
                                    <td></td>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${LocalDate.now().getDayOfMonth() == days[i.index][j.index] && LocalDate.now().getMonth().getValue() - 1 == calendar.getMonth() && LocalDate.now().getYear() == calendar.getYear()}">
                                            <td class="current-day">
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                        </c:otherwise>
                                    </c:choose>
                                        <span class="date">${days[i.index][j.index]}
                                            <c:if test = "${events.containsKey(days[i.index][j.index])}">
                                                 <ul>
                                                    <li>
                                                        <c:set var = "event" value = "${events.get(days[i.index][j.index])}"/>
                                                        <span class="event">${event.getDescription()}</span>
                                                        <span class="time">${event.getTime()}</span>
                                                    </li>
                                                </ul>
                                            </c:if>
                                        </span>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
                <script>
                    // wait for the page to load to make sure JQuery is fully loaded
                    document.addEventListener("DOMContentLoaded", function(event) {
                        $('tr:has(td.current-day)').addClass('current-week');
                    });
                </script>
            </div>
        </div>
    </div>
</petclinic:layout>
