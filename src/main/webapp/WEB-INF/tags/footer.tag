<%-- Testing Change Christos --%>
<%-- Second push Test --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="footer">
    <div class="links">
        <ul id="horizontal-list">
        <li class="active">
            <spring:url value="/" var="homeUrl" />
            <a href="${fn:escapeXml(homeUrl)}" title="home page">
                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                <span>Home</span>
            </a>
        </li>
        <li class="">
            <spring:url value="/owners/find.html" var="findUrl" />
            <a href="${fn:escapeXml(findUrl)}" title="find owners">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                <span>Find owners</span>
            </a>
        </li>
        <li class="">
            <a href="#" title="find pets">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                <span>Find Pets</span>
            </a>
        </li>
        <li class="">
            <spring:url value="/vets.html" var="vetUrl" />
            <a href="${fn:escapeXml(vetUrl)}" title="veterinarians">
                <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                <span>Veterinarians</span>
            </a>
        </li>
        <li class="">
            <spring:url value="/appointments/viewForm" var="apptUrl" />
            <a href="${fn:escapeXml(apptUrl)}" title="appointments">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                <span>Appointments</span>
            </a>
        </li>
            <spring:url value="/oups.html" var="oupsUrl" />
            <a href="${fn:escapeXml(oupsUrl)}" title="trigger a RuntimeException to see how it is handled">
                <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                <span>Error</span>
            </a>
        </li>
        </ul>
    </div>

</div>


<%-- Placed at the end of the document so the pages load faster --%>
<spring:url value="/webjars/jquery/2.2.4/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<%-- jquery-ui.js file is really big so we only load what we need instead of loading everything --%>
<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js" var="jQueryUiCore"/>
<script src="${jQueryUiCore}"></script>

<%-- Bootstrap --%>
<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>

<%--Custom Js--%>
<spring:url value="/resources/javascript/HallOfFame.js" var="hofJS"/>
<script src="${hofJS}"></script>
<spring:url value="/resources/javascript/GoToTopJavascript.js" var="backToTopJs"/>
<script src="${backToTopJs}"></script>

