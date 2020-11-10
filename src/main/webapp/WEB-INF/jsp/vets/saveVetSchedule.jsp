<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <html>
    <head>
        <title>Vet Schedule</title>
    </head>
    <body>

    <h2>Veterinarian</h2>
    <p><%= request.getParameter("veterinarian")%>
    </p>
    <p><%= request.getParameter("")%>
    </p>


    </body>
    </html>
</petclinic:layout>
