<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.timetraveling.models.users.UserOpenJPADAO" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Skills</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/skillsStyle.css">
    <link rel="stylesheet" href="css/modal.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/pageSettings.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
<header>
    <div>
        <a class="headerLogo" href="home">
            <img src="resources/images/skividark.png" alt="SkiVI Logo">
        </a>
    </div>
    <div class="authButtons">
        <button type="button" class="homeButton"><img class="bowOverlay" src="resources/images/bow.png"
                                                      alt="bow overlay">Home</button>
        <div class="userNav">
            <div class="profilePictureIcon" onclick="menuToggle();">
                <img src="<%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getProfilePicture() %>" alt="user" width="100">
            </div>
            <div class="menuUser">
                <a class="userName" href="profile.jsp"><strong><%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getUsername()%></strong></a>
                <ul>
                    <li>
                        <a class="tabButton" href="profile.jsp"><i class="fa fa-user">My profile</i></a>
                    </li>
                    <li>
                        <a class="tabButton" href="settings.jsp"><i class="fa fa-cog">Settings</i></a>
                    </li>
                    <li>
                        <a class="tabButton" href="logout"><i class="fa fa-sign-out">Logout</i></a>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</header>
<main>

    <section class="container">

        <div class="leftbox">
            <nav id="settingsBar">
                <a class="tabButton active"><i class="fa fa-user"><span class="hideButtonContent">Profile</span></i></a>
                <a class="tabButton"><i class="fa fa-cog"><span class="hideButtonContent">Settings</span></i></a>
            </nav>
        </div> <!--  end leftbox-->

        <div class="rectangleToPlayWith">
            <span class="rectangleToPlayDecoration">SkiVI</span>
        </div>

        <div id="settingsContainer" class="rightbox">
            <div id="profileTab" class="profile tab">
                <h1>Personal Info</h1>
                <div>
                    <h2>Account</h2>
                    <form novalidate method="POST" action="update-info">
                        <label class="label">
                            <span class="labelTitle">Full name</span>
                            <input type="text" id="username" name="username" value="<%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getUsername()%>"/>
                            <span class="error" aria-live="polite"></span>
                        </label>
                        <label class="label">
                            <span class="labelTitle">Email</span>
                            <input type="email" id="email" name="email" value="<%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getEmail()%>"/>
                            <span class="error" aria-live="polite"></span>
                        </label>
                        <label class="description">
                            <span class="labelTitle">Description</span>
                            <input type="text" id="description" name="description" value="<%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getDescription()%>"/>
                            <span class="error" aria-live="polite"></span>
                        </label>


                        <button class="updateSettingsButton" type="submit">Update</button>
                    </form>
                </div>
                <div>
                    <h2>
                        Change Password
                    </h2>
                    <form id="passwordChangeForm" novalidate method="POST" action="update-password">
                        <div class="passwordArea">
                            <label class="label">
                                <span class="labelTitle">Old Password</span>
                                <input type="password" id="oldPassword" name="oldPassword">
                            </label>
                            <button type="button" class="eye" onclick="if (oldPassword.type == 'text') oldPassword.type = 'password';
      else oldPassword.type = 'text';"><img
                                    src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.iconsdb.com%2Ficons%2Fpreview%2Fpink%2Feye-xxl.png&f=1&nofb=1"
                                    alt="Eye button for showing password"></button>
                            <span class="error" aria-live="polite"></span>
                        </div>

                        <div class="passwordArea">
                            <label class="label">
                                <span class="labelTitle">New Password</span>
                                <input type="password" id="newPassword" name="newPassword">

                            </label>
                            <button type="button" class="eye" onclick="if (newPassword.type == 'text') newPassword.type = 'password';
      else newPassword.type = 'text';"><img
                                    src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.iconsdb.com%2Ficons%2Fpreview%2Fpink%2Feye-xxl.png&f=1&nofb=1"
                                    alt="Eye button for showing password"></button>
                            <span class="error" aria-live="polite"></span>
                        </div>

                        <button class="updateSettingsButton" type="submit">Update</button>
                    </form>
                </div>

            </div> <!--  end rightbox-->

            <div id="settingsTab" class="profile tab hideTab">
                <!--  al doilea tab-->
                <h1>Notification Settings</h1>
                <h2>Email Notifications</h2>
                <form>
                    <div>
                        <input type="radio" id="allow" name="notification" value="yes">
                        <label for="allow">Yes</label>

                        <input type="radio" id="deny" name="notification" value="no">
                        <label for="deny">No</label>
                    </div>

                    <button class="updateSettingsButton">Update</button>
                </form>
            </div>
        </div>
    </section>
</main>
<footer>
    <section class="footerLeft">
        <img src="resources/images/skivi.png" alt="White logo of SkiVI">
        <h3>About<span> SkiVi</span></h3>

        <p class="footerLinks">
            <a href="home">Home</a>|
            <a href="#">About</a>|
            <a href="#">Contact</a>
        </p>

        <p class="footerProjectName">© 2021 SkiVi</p>
    </section>

    <section class="footerCenter">
        <h3>
            Contact data
        </h3>
        <div>
            <i class="fa fa-phone"></i>
            <p>0752128223</p>
        </div>
        <div>
            <i class="fa fa-inbox"></i>
            <p><a href="#">elena.straton@info.uaic.ro</a></p>
        </div>
    </section>
    <section class="footerRight">
        <h3>About the project</h3>
        <p class="footerProjectAbout">
            To design a modular Web application with the role of virtual instructor to offer sets of activities and training
            resources aimed at learning / deepening skills such as wire dancing, dead languages (eg, Latin, Aramaic),
            self-defense styles, singing to a musical instrument (harpsichord, Teremin, tuba), survival (in the jungle, in
            the home, cosmic vehicle, polluted neighborhood, ...), resuscitation procedures, making origami and others.

            Each skill will be implemented by an independent Web microservice that will be updated automatically or upon
            request. At least 3 such microservices will be developed as case studies.

            The basic functionalities of the system (instruction management, users interested in learning skills, etc.) will
            be offered via an API adopting the REST or GraphQL paradigm.
        </p>

    </section>
</footer>


<script src="js/modalwindowpop.js"></script>
<script src="js/modalwindowclose.js"></script>
<script src="js/usermenutoggle.js"></script>
<script src="js/pageSettings.js"></script>
</body>
</html>