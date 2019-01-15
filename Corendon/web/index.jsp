<%--
  Created by IntelliJ IDEA.
  User: ayrtonfurtadomartins
  Date: 12/10/2018
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Corendon | Captive portal</title>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <header>
            <img class="logo2" src="img/corendon_logo.jpg">
        </header>

        <section id="container">
            <img class= logo src="img/corendon_logo.jpg">
            <div class="text">
                <br>
                <h1><br>Corendon Wi-Fi Portaal</h1>
                <p>Gelieve uw achternaam en ticketnummer invoeren</p>
            </div>
            <div class="loginform">
                <form name="loginForm" method="POST" action="Servlet">
                    <label><b>Voornaam</b></label>
                    <input name="firstname" class="login" type="text" placeholder="Ex. John" required/>
                    <label><b>Achternaam</b></label>
                    <input name="lastname" class="login" type="text" placeholder="Ex. Doe" required/>
                    <label><b>Ticketnummer</b></label>
                    <input name="ticketnumber" class="login" type="text" placeholder="Ex. CD770" required/>
                    <p>Door het gebruik maken van het netwerk dient u akkoord te gaan met onze
                        <a href="url">Algemene Voorwaarden.</a>
                        <input id="checkbox" type="checkbox" name="box" value="Car" required/>
                        <p>Akkoord. *</p>
                    <input type="submit" value="Verbinding opzetten" id="button"/>
                </form>
            </div>
            <form action="${pageContext.request.contextPath}Servlet" method="post">
                <input type="submit" name="Logout" value="Logout" /> </form>


            </div>
        </section>
    </body>
</html>
