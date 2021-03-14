/*
    Step One: Check - Is user an Employee or a Manager?
*/
 //IMPLEMENTATION FOR STEP ONE - PENDING
 const URLBase = "http://localhost:8080/project-1";

 var i; //Simple iterator.
 
 //var sampleJSONList = [{"id":1,"firstName":"Employee","lastName":"Tester","email":"etest@test.com","username":"Etest","password":"Etest","role_id":1,"role":null},{"id":2,"firstName":"Manager","lastName":"Tester","email":"mtest@test.com","username":"Mtest","password":"Mtest","role_id":2,"role":null}]
 //var sampleObj = JSON.parse(JSON.stringify(sampleJSONList));

 function buildTableSkeletons() {

 var element1=document.getElementById("anchordiv1");
 var etable = document.createElement("TABLE");

 etable.id = "employee_table";
 element1.appendChild(etable);

 var etheadrow = document.createElement("tr");
 etable.appendChild(etheadrow);

 var eth1 = document.createElement("th");
 eth1.innerHTML = "First Name";
 etheadrow.appendChild(eth1);

 var eth2 = document.createElement("th");
 eth2.innerHTML = "Last Name";
 etheadrow.appendChild(eth2);

 var eth3 = document.createElement("th");
 eth3.innerHTML = "Email";
 etheadrow.appendChild(eth3);

 var eth4 = document.createElement("th");
 eth4.innerHTML = "Username";
 etheadrow.appendChild(eth4);

 var eth5 = document.createElement("th");
 eth5.innerHTML = "Password";
 etheadrow.appendChild(eth5);

 var eth6 = document.createElement("th");
 etheadrow.appendChild(eth6);

var element2=document.getElementById("anchordiv2");
 var rtable = document.createElement("TABLE");
 rtable.id = "reimbursement_table";
 element2.appendChild(rtable);

var rtheadrow = document.createElement("tr");
rtable.appendChild(rtheadrow);

var rth1 = document.createElement("th");
rth1.innerHTML = "Amount"
 rtheadrow.appendChild(rth1);

 var rth2 = document.createElement("th");
 rth2.innerHTML = "Submission Time"
  rtheadrow.appendChild(rth2);

  var rth3 = document.createElement("th");
rth3.innerHTML = "Resolution Time"
 rtheadrow.appendChild(rth3);

 var rth4 = document.createElement("th");
rth4.innerHTML = "Description"
 rtheadrow.appendChild(rth4);

 var rth5 = document.createElement("th");
rth5.innerHTML = "Submitter"
 rtheadrow.appendChild(rth5);

 var rth6 = document.createElement("th");
rth6.innerHTML = "Resolver"
 rtheadrow.appendChild(rth6);

 var rth7 = document.createElement("th");
rth7.innerHTML = "Status"
 rtheadrow.appendChild(rth7);

 var rth8 = document.createElement("th");
rth8.innerHTML = "Type"
 rtheadrow.appendChild(rth8);

 var rth9 = document.createElement("th");
 rth9.innerHTML = "Post"
 rtheadrow.appendChild(rth9);

 }

 /*
 for (i = 0; i < sampleObj.length; i++){

 }
 */

 function logout() {
    console.log("logout");
    var URL = URLBase + "/logout";
    fetch(URL)
    .then(response => response.json())
}

function employeeViewReimbursementRequests() {
    console.log("employeeViewReimbursementRequests");
    var URL = URLBase + "/viewallforemployee";
    fetch(URL)
    .then(response => response.json())
}

function employeeViewPendingReimbursementRequests() {
    console.log("employeeViewPendingReimbursementRequests");
    var URL = URLBase + "/viewpendingforemployee";
    fetch(URL)
    .then(response => response.json())
}

function employeeViewResolvedReimbursementRequests(){
    console.log("employeeViewResolvedReimbursementRequests");
    var URL = URLBase + "/viewresolvedforemployee";
    fetch(URL)
    .then(response => response.json())
}

function viewPersonalInfo(){
    console.log("viewPersonalInfo");
    var URL = URLBase + "/viewinfo";
    fetch(URL)
    .then(response => response.json())
}

function employeeUpdatePersonalInfo(){
    console.log("employeeUpdatePersonalInfo");
    var URL = URLBase + "/updateinfo";
    fetch(URL)
    .then(response => response.json())
}

function viewResolvePendingReimbursementRequests() {
    console.log("viewResolvePendingReimbursementRequests");
    var URL = URLBase + "/viewpending";
    fetch(URL)
    .then(response => response.json())
}

function viewResolvedReimbursementRequests() {
    console.log("viewResolvedReimbursementRequests");
    var URL = URLBase + "/viewresolved";
    fetch(URL)
    .then(response => response.json())
}

function viewAllEmployees(){
    console.log("viewAllEmployees");
    var URL = URLBase + "/employees";
    fetch(URL)
    .then(response => response.json())
}

function postReimbursement(){
    console.log("postReimbursement");
    var URL = URLBase + "/postreimbursement";
    fetch(URL)
    .then(response => response.json)
}

 function loadHandler() {
    var ck = document.cookie;
    console.log(ck);
    var role_id = parseInt(ck.charAt(ck.length-1));
    var element=document.getElementById("anchordiv");
    console.log(`${role_id}`)
     switch(role_id){
        //CASE 1: USER IS AN EMPLOYEE -

        case 1: 
        
        var btn1 = document.createElement("BUTTON");
        var btn2 = document.createElement("BUTTON");
        var btn3 = document.createElement("BUTTON");
        var btn4 = document.createElement("BUTTON");
        var btn5 = document.createElement("BUTTON");
        function tableGen(reimbursement_type) {//Reimbursement_type is an integer: 0 - Everything, 1 - Pending, 2 - Resolved
            /*TODO: Set up tableGen so that it submits a request to the servlet to get the list of reimbursements that satisfies the request.
            */
           console.log("Called tableGen w/ Reimbursement Type: " + reimbursement_type);
        }

        function employeeTableGen() {
            console.log("Called employeeTableGen");
        }

        function employeeUpdateTableGen(){
            console.log("Called employeeUpdateTableGen");
        }
        btn1.innerHTML = "View Reimbursement Requests";
        btn1.setAttribute("onclick", "employeeViewReimbursementRequests();");
        btn1.onclick = function() {employeeViewReimbursementRequests();};
        element.appendChild(btn1);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn2.innerHTML = "View <i>Pending</i> Reimbursement Requests"; //Add another button, this one shows pending reimbursements when clicked.
        btn2.setAttribute("onclick", "employeeViewPendingReimbursementRequests();");
        btn2.onclick = function() {employeeViewPendingReimbursementRequests();};
        element.appendChild(btn2);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn3.innerHTML = "View <i>Resolved</i> Reimbursement Requests"; //Add another button, this one shows resolved reimbursements when clicked.
        btn3.setAttribute("onclick", "employeeViewResolvedReimbursementRequests();");
        btn3.onclick = function() {employeeViewResolvedReimbursementRequests();};
        element.appendChild(btn3);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn4.innerHTML = "View Personal Info"; //Add another button, this one shows employee's personal info.
        btn4.setAttribute("onclick", "viewPersonalInfo();");
        btn4.onclick = function() {viewPersonalInfo();};
        element.appendChild(btn4);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn5.innerHTML = "Update Personal Info"; //Add another button, this one lets an employee update their personal info.
        btn5.setAttribute("onclick", "employeeUpdatePersonalInfo();");
        btn5.onclick = function() {employeeUpdatePersonalInfo();};
        element.appendChild(btn5);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));
        break;

        //CASE 2: USER IS A MANAGER -

        case 2:
        var btn1 = document.createElement("BUTTON");
        var btn2 = document.createElement("BUTTON");
        var btn3 = document.createElement("BUTTON");

        btn1.innerHTML = "View or Resolve Pending Reimbursement Requests"; //This button shows all pending reimbursements and lets the user mark them as pending, approved, or denied.
        btn1.setAttribute("onclick", "viewResolvePendingReimbursementRequests();");
        btn1.onclick = function() {viewResolvePendingReimbursementRequests();};
        element.appendChild(btn1);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn2.innerHTML = "View Resolved Reimbursement Requests"; //Add another button, this one shows pending reimbursements when clicked.
        btn2.setAttribute("onclick", "viewResolvedReimbursementRequests();");
        btn2.onclick = function() {viewResolvedReimbursementRequests();};
        element.appendChild(btn2);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn3.innerHTML = "View All Employees"; //Add another button, this one shows resolved reimbursements when clicked.
        btn3.setAttribute("onclick", "viewAllEmployees();");
        btn3.onclick = function() {viewAllEmployees();};
        element.appendChild(btn3);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        break;
        default:
            break;
        
    return;
 }
}

window.onload = loadHandler();
buildTableSkeletons();