<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
    <head>
        <title>Main</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <%--    <link  rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>--%>
        <script src="https://kit.fontawesome.com/c291e34b67.js" crossorigin="anonymous"></script>
    </head>

    <body onload="changeHashOnLoad();">
    <jsp:include page="parts/navbar.jsp"/>

        <div class="reg-container">
            <div class="reg-form">
                <div class="reg-title">
                    <h1>[ Main Page ]</h1>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th scope="col">Route</th>
                        <th scope="col">Departure</th>
                        <th scope="col">Destination</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="route" items="${routes_list}">
                        <tr>
                            <td>
                                <form id="${route.id}" action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="route_id" value="${route.id}">
                                    <input type="hidden" name="command" value="get_route">
                                    <a class="nav-link active" href=# onclick="document.getElementById('${route.id}').submit()" tabindex="-1" aria-disabled="true">${route.id}</a>
                                </form>
                            </td>
                            <td>${route.departureStation}</td>
                            <td>${route.destinationStation}</td>
<%--                            <td>--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${user.isActivated == true}">--%>
<%--                                        <i class="fa-solid fa-user-check"></i>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${user.isActivated == false}">--%>
<%--                                        <i class="fa-solid fa-user-minus"></i>--%>
<%--                                    </c:when>--%>
<%--                                </c:choose>--%>
<%--                            </td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            let storedHash = window.location.hash;
            function changeHashOnLoad() {
                window.location.hash = "1";
            }
            window.onhashchange = function () {
                window.location.hash = storedHash;
            }
        </script>

    </body>
</html>

