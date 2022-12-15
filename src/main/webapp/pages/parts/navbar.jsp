<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="nav">
    <li class="logo">Travel.com</li>
<%--    <li tabindex="0"><i class="fa-solid fa-magnifying-glass"></i></li>--%>
<%--    <li tabindex="0"><i class="fa-solid fa-user"></i></li>--%>

    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="move_to_main_page">
        <button type="submit">
            <i class="fa-solid fa-house"></i>
        </button>
    </form>

    <c:if test="${role == 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="move_to_add_train_page">
            <button type="submit">
                <i class="fa-solid fa-train"></i>
            </button>
        </form>

        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="move_to_add_route_page">
            <button type="submit">
                <i class="fa-solid fa-route"></i>
            </button>
        </form>

        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="get_users">
            <button type="submit">
                <i class="fa-solid fa-users"></i>
            </button>
<%--            <a class="nav-link active" href=# onclick="document.getElementById('users_list').submit()" tabindex="-1" aria-disabled="true"><i class="fa-solid fa-users"></i></a>--%>
        </form>
    </c:if>

    <c:if test="${role != 'ADMIN' && role != 'USER'}">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="move_to_signup_page">
            <button type="submit">
                <i class="fa-solid fa-user-plus"></i>
            </button>
        </form>
    </c:if>

    <c:if test="${role == 'ADMIN' || role == 'USER'}">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="move_to_profile_page">
            <button type="submit">
                <i class="fa-solid fa-user"></i>
            </button>
        </form>

        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="sign_out">
            <button type="submit">
                <i class="fa-solid fa-right-from-bracket"></i>
            </button>
        </form>
    </c:if>

</ul>
