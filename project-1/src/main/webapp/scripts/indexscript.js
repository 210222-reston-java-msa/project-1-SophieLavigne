/**
 * 
 */

function login() {
    console.log("Starting login attempt.");
    var uName = document.getElementById("username").value;
    var pwd = document.getElementById("password").value;

    var loginTemplate = {username: uName, password: pwd}

    var URL = "http://localhost:8080/project-1";

    fetch(URL+"/login", {
        method: 'post',
        body: JSON.stringify(loginTemplate)
    }).then(function(response){
        if (response.status == 200){
            sessionStorage.setItem("currentUsername", uName) //Use sessionStorage.getItem() to collect this later!
            sessionStorage.setItem("password", pwd); //Use sessionStorage.getItem() to collect this later!
            window.location = URL+"/reimbursements.html";
        }
    });


}