let mql = window.matchMedia('(max-width: 600px)');

function openNav() {
    if (mql.matches) {
        document.getElementById("sideNavigation").style.width = "100vw";
        document.getElementById("loginHome").style.marginLeft = "100vw";
    } else {
        document.getElementById("sideNavigation").style.width = "50vw";
        document.getElementById("loginHome").style.marginLeft = "50vw";
        document.getElementById("design").style.transform += "translate(-50%, 0)";
        var ret = document.getElementById("design").style.transform.replace('translate(0px)','');
        document.getElementById("design").style.transform = ret;
        document.getElementById("loginHeader").style.textAlign = "right";
        document.getElementById("loginHeader").style.width = "70%";
        document.getElementById("loginHeader").style.left = "0";
    }
}

function closeNav() {
    if (mql.matches) {
        document.getElementById("sideNavigation").style.width = "0";
        document.getElementById("loginHome").style.margin = "auto";
    } else {
        document.getElementById("sideNavigation").style.width = "0";
        document.getElementById("loginHome").style.margin = "auto";
        document.getElementById("design").style.transform += "translate(0, 0)";
        var ret = document.getElementById("design").style.transform.replace('translate(-50%)','');
    
        console.log(ret);
        document.getElementById("design").style.transform = ret;
        document.getElementById("loginHeader").style.textAlign = "left";
        document.getElementById("loginHeader").style.width = "100%";
        document.getElementById("loginHeader").style.left = "15%";
    }
    
}
/*
    POATE FI IMBUNATATIT!!!
*/
function toggleRegisterWindow() {
    var old = document.getElementById("localSignIn");
    var newdiv = document.getElementById("registerForm");

    old.style.display = "none";
    newdiv.style.display = "flex";
    newdiv.style.flexDirection = "column";
    if (!mql.matches) {
        design.style.transform = "scale(100%, 120%)";
    } else {
        design.style.transform = "scale(60%, 80%)";
    }
    newdiv.style.justifyContent = "center";

    document.getElementById("loginTrigger").style.display = "block";
    document.getElementById("registerTrigger").style.display = "none";
}

function toggleLoginWindow() {
    const old = document.getElementById("registerForm");
    const newdiv = document.getElementById("localSignIn");

    old.style.display = "none";
    newdiv.style.display = "flex";
    newdiv.style.flexDirection = "column";
    if (!mql.matches) {
        design.style.transform = "scale(100%, 100%)";
    } else {
        design.style.transform = "scale(60%, 60%)";
    }
    newdiv.style.justifyContent = "center";

    document.getElementById("registerTrigger").style.display = "block";
    document.getElementById("loginTrigger").style.display = "none";
}

/*const mq = window.matchMedia( "(min-width: 500px)" );
if (mq.matches) {
    document.getElementById("design").style.backgroundColor = "green";
    } else {
        document.getElementById("design").style.backgroundColor = "red";
    }*/

