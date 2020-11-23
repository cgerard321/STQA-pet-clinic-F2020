<%@ page import="java.time.LocalDate" %>
<%@ page import="org.springframework.samples.petclinic.util.CalendarHelper" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var = "calendar" value = "${CalendarHelper.getCalendar(LocalDate.now().getMonth().getValue() - 1, LocalDate.now().getYear())}"/>
<c:set var = "days" value = "${calendar.getDays()}"/>

<petclinic:layout pageName="home">
    <h1><fmt:message key="welcome"/></h1>

    <!--
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" alt="A cat and a dog" src="${petsImage}"/>
        </div>
    </div>
    -->

    <details id="hallOfFame" style="display:none">
    <summary><strong>Hall of Fame</strong></summary>
    <!-- Ryan L. -->
    <table>
        <thead class="thead">
            <tr id="ranking">
                <th class="text-center">1st</th>
                <th class="text-center">2nd</th>
                <th class="text-center">3rd</th>
            </tr>
            <tr>
                <th id="HOF1Name" class="text-center">Pet name [Pet average rating]</th>
                <th id="HOF2Name" class="text-center">Pet name [Pet average rating]</th>
                <th id="HOF3Name" class="text-center">Pet name [Pet average rating]</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <spring:url value="/resources/images/imagePlaceholder.png" htmlEscape="true" var="firstPlace"/>
                <td class="text-center" style="width:35%">
                    <div class="card">
                        <div class="card-inner">
                            <div class="card-front">
                                <img id="HOF1Img" src="${firstPlace}" alt="1st place">
                            </div>
                            <div class="card-back">
                                <h1 id="Name1">John Doe</h1>
                                <p id="owner1"></p>
                                <p id="timesRated1"></p>
                            </div>
                        </div>
                    </div>
                </td>
                <spring:url value="/resources/images/imagePlaceholder.png" htmlEscape="true" var="secondPlace"/>
                <td class="text-center" style="width:35%">
                    <div class="card">
                        <div class="card-inner">
                            <div class="card-front">
                                <img id="HOF2Img" src="${secondPlace}" alt="1st place">
                            </div>
                            <div class="card-back">
                                <h1 id="Name2">John Doe</h1>
                                <p id="owner2"></p>
                                <p id="timesRated2"></p>
                            </div>
                        </div>
                    </div>
                </td>
                <spring:url value="/resources/images/imagePlaceholder.png" htmlEscape="true" var="thirdPlace"/>
                <td class="text-center" style="width:35%">
                    <div class="card" id="card">
                        <div class="card-inner">
                            <div class="card-front">
                                <img id="HOF3Img" src="${thirdPlace}" alt="1st place">
                            </div>
                            <div class="card-back">
                                <h1 id="Name3">John Doe</h1>
                                <p id="owner3"></p>
                                <p id="timesRated3"></p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    </details>

    <br/>
<%--    Rating Related Buttons - Nichita--%>
    <a class="btn btn-default" href='<spring:url value="/ratings/new" htmlEscape="true"/>'>Rate Pet</a>

    <a class="btn btn-default" href='<spring:url value="/ratings" htmlEscape="true"/>'>View All Ratings</a>
    <br/>
<%--   Calendar -Louis C.--%>
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
                                        <span class="date">${days[i.index][j.index]}</span>
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
