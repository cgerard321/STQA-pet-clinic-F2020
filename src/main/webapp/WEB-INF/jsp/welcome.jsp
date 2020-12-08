<%@ page import="java.time.LocalDate" %>
<%@ page import="org.springframework.samples.petclinic.util.CalendarHelper" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
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
        <!-- Christos C.-->
        <div class="input-group">

            <select class="form-control" name='petType' id="petType">
                <option selected="selected" value="all">All</option>


            </select>
            <span class="input-group-btn">
    <button class="btn btn-default" id="sortHall" type="button">Set Filter</button>
  </span>
        </div>
        <summary id="Hall"><strong>Hall of Fame</strong></summary>
    <!-- Ryan L. -->
    <table>
        <thead class="thead">
            <tr id="ranking">
                <th id="HOFRank1" class="text-center">1st</th>
                <th id="HOFRank2" class="text-center">2nd</th>
                <th id="HOFRank3" class="text-center">3rd</th>
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
                <td class="text-center"  id="HOF1TD" style="width:35%">
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
                <td class="text-center" id="HOF2TD" style="width:35%">
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
                <td class="text-center" id="HOF3TD" style="width:35%">
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

    <a class="btn btn-default" href='<spring:url value="/ratings/findPetRatings" htmlEscape="true"/>'>View Pet Ratings</a>

    <br/>

    <%-- Calendar -Louis C. --%>

    <%-- For anyone that wants to work on this here is the origin of the css for the calendar that has been modified to show a dinamic calendar --%>
    <%-- https://bootsnipp.com/snippets/v200E --%>
    <div class="row">
        <div class="col-md-12">
            <div id="calendar">
                <table>
                    <summary>
                        <strong id="currentMonth"><c:out value="${LocalDate.now().getMonth()}"/></strong>
                        <span id="currentYear"><c:out value="${LocalDate.now().getYear()}"/></span>
                    </summary>
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

                    <%-- Loop for weeks --%>
                    <c:forEach begin="0" end="${calendar.getNumberOfWeeks()}" varStatus="i">
                        <tr>

                        <%-- Loop for day in the week --%>
                        <c:forEach begin="0" end="6" varStatus="j">

                            <%-- Check if there is a day if not puts empty cell --%>
                            <c:choose>
                                <c:when test="${days[i.index][j.index]=='0'}">
                                    <td></td>
                                </c:when>
                                <c:otherwise>

                                    <%-- Check if the day is the current day to add css --%>
                                    <td class="<c:if test = "${LocalDate.now().getDayOfMonth() == days[i.index][j.index] && LocalDate.now().getMonth().getValue() - 1 == calendar.getMonth() && LocalDate.now().getYear() == calendar.getYear()}">
                                            current-day</c:if>">
                                        <%-- Content of the cell for the day --%>
                                        <span class="date">${days[i.index][j.index]}
                                            <%-- Create the list for the events / visits --%>
                                            <ul>
                                            </ul>
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
