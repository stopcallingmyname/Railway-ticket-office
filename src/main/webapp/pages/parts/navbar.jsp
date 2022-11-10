<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="nav">
    <li class="logo">Travel.com</li>
<%--    <li tabindex="0"><i class="fa-solid fa-magnifying-glass"></i></li>--%>
<%--    <li tabindex="0"><i class="fa-solid fa-user"></i></li>--%>

    <c:if test="${role != 'ADMIN' && role != 'USER'}">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="move_to_signup_page">
            <button type="submit">
                <i class="fa-solid fa-lock"></i>
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
