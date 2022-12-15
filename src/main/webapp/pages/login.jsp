<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css" />
<%--    <link  rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>--%>
    <script src="https://kit.fontawesome.com/c291e34b67.js" crossorigin="anonymous"></script>
</head>

    <body onload="changeHashOnLoad();">

        <div class="reg-container">
            <div class="reg-form">
                <div class="reg-title">
                    <h1>Sign In</h1>
                    <div class="goto-login">
                        <p class="sing-in-label">Don`t have an account?</p>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="move_to_signup_page">
                            <button type="submit" class="btn-a-type">Register</button>
                        </form>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="sign_in">

                    <label for="Email"><input type="email" placeholder="Email" id="email" name="email"></label>

                    <label for="Password"><input type="password" placeholder="Password" id="password" name="password"></label>

                    <div>
                        <c:if test="${not empty error_message}">
                            ${error_message}
                        </c:if>
                    </div>

                    <button class=btn-create type="submit"><i class="fa-solid fa-right-to-bracket"></i>Log-in</button>
                </form>
            </div>
        </div>

        <%--    NOTIFICATION    --%>
<%--        <div class="toast">--%>
<%--            <div class="toast-content">--%>
<%--                <i class="fas fa-solid fa-check check"></i>--%>

<%--                <div class="message">--%>
<%--                    <span class="text text-1">Success</span>--%>
<%--                    <span class="text text-2">Signed in</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <i class="fa-solid fa-xmark close"></i>--%>
<%--            <div class="progress"></div>--%>
<%--        </div>--%>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<%--        <script src="js/script.js"></script>--%>
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
