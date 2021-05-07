<%@ page import="com.timetraveling.models.users.UserOpenJPADAO" %><%--
  Created by IntelliJ IDEA.
  User: Teddy
  Date: 4/23/2021
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/pageProfile.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Page-Profile</title>
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
                        <a class="tabButton" href="#"><i class="fa fa-sign-out">Logout</i></a>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</header>

<main>
    <div class="wrapper">
        <div class="profileNavigation">
            <img src="<%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getProfilePicture() %>" alt="user" width="100">
            <form id="profilePictureForm" method="post" enctype="multipart/form-data" action="update-profile-picture">
                <input id="profilePictureInput" type="file" name="file" accept="image/*">
            </form>
            <span><%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getUsername()%></span>
            <nav id="settingsBar">
                <a id="profileInfoButton" class="tabButton active" href="">Info</a>
                <a id="achievementReference" class="tabButton" href="">🏆 Achievements</a>
            </nav>
        </div><!-- endLeft -->

        <div class="profileInfoTab">
            <div id="infoTab" class="tab">
                <div class="info">
                    <h3>Information</h3>
                    <div class="userProfileData">
                        <div class="data">
                            <h4>Email</h4>
                            <p><%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getEmail()%></p>
                        </div>
                        <div class="data">
                            <h4>Description</h4>
                            <p><%=new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getDescription()%></p>
                        </div>
                    </div>
                </div>

                <div class="projects">
                    <h3>Skills</h3>
                    <div class="userSkillsProfileData">

                        <div class="data">
                            <form id="skillForm">
                                <label>
                                    <span>Add new Skill:</span>
                                    <input type="text" placeholder="Add skills" class="add-text" id="skillInput" name="skill" list="skill-list" autocomplete="off">
                                    <datalist id="skill-list">
                                        <option value="Cooking">
                                        <option value="Time travelling">
                                        <option value="First aid">
                                    </datalist>
                                </label>

                                <button id="addSkill" class="addButton" type="submit">Add skill</button>
                            </form>
                        </div>


                        <div class="data">
                            <ul id="skillList" class="list">
                                <% for (String skill : new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getSkills()) { %>
                                  <li><%= skill %></li>
                                <% } %>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div id="scoresTab" class="tab hideTab">
                <h4>My scores:</h4>
                <h4>Favorite skills:</h4>
                <ul>
                    <% for (String skill : new UserOpenJPADAO().findByID((int) request.getSession().getAttribute("id")).getFavouriteSkills()) { %>
                        <li><%= skill %></li>
                    <% } %>
                </ul>
            </div>

        </div> <!-- endRight -->

    </div>
</main>


<footer>
    <section class="footerLeft">
        <img src="resources/images/skivi.png" alt="White logo of SkiVI">
        <h3>About<span> SkiVi</span></h3>

        <p class="footerLinks">
            <a href="#">Home</a>|
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
<script src="js/pageProfile.js"></script>

</body>
</html>