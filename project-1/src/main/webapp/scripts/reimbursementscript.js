/*
    Step One: Check - Is user an Employee or a Manager?
*/
 //IMPLEMENTATION FOR STEP ONE - PENDING
 const URLBase = "http://localhost:8080/project-1";

 var i; //Simple iterator.
 var el;
 var dataOut;
 var ck = document.cookie;
 var firstName = "firstName" + "=";
 var role_id_kernel = "role_id" + "=";
 function cookiereader(){
 console.log(ck);
 var cookiesp1 = ck.split(";");
 for (var i = 0; i < cookiesp1.length; i++){
   var c = cookiesp1[i];
   while (c.charAt(0) == ' '){
     c = c.substring(1);
   }
   if (c.indexOf(firstName) == 0){
     return c.substring(firstName.length, c.length);
   }
   }
   return firstName;
  }
  function loginreader(){
  
 var cookiesp1 = ck.split(";");
 for (var i = 0; i < cookiesp1.length; i++){
   var c = cookiesp1[i];
   while (c.charAt(0) == ' '){
     c = c.substring(1);
   }
   if (c.indexOf(role_id_kernel) == 0){
     return c.substring(role_id_kernel.length, c.length);
  }
  }
 }

function genEmployeeForm() {
  var employeeForm = {
  username: document.getElementById("username").value,
  password: document.getElementById("password").value,
  firstName: document.getElementById("firstname").value,
  lastName: document.getElementById("lastname").value,
  email: document.getElementById("email").value
}
console.log(employeeForm);
return employeeForm;
}

function genReimbursementForm() {
  var reimbursementForm = {
    amount: document.getElementById("amount").value,
    description: document.getElementById("description").value,
    type: document.getElementById("type").value,
    }
  console.log(reimbursementForm);
  return reimbursementForm;
}

function genResolveReimbursementForm(){
  var resolveReimbursementForm = {
    status: document.getElementById("status_sel").value
  }
  console.log(resolveReimbursementForm);
  return resolveReimbursementForm;
}

 var element1=document.getElementById("anchordiv1");

 

 function logout() {
    console.log("logout");
    var URL = URLBase + "/logout";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        console.log(this.responseText);
        window.location.replace(URLBase+"/");
      }
    }
    xhr.open("POST", URL);
    xhr.send();
}

function employeeViewReimbursementRequests() {
    console.log("employeeViewReimbursementRequests");
    var URL = URLBase + "/viewallforemployee";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          console.log(myArr[0]);
          populateReimbursementTable(myArr, "reimbursement_table");
      }
  }
  xhr.open("GET", URL);
  xhr.send();
}

function employeeViewPendingReimbursementRequests() {
    console.log("employeeViewPendingReimbursementRequests");
    var URL = URLBase + "/viewpendingforemployee";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          populateReimbursementTable(myArr, "reimbursement_table");
      }
  }
  xhr.open("GET", URL);
  xhr.send();
}

function employeeViewResolvedReimbursementRequests(){
    console.log("employeeViewResolvedReimbursementRequests");
    var URL = URLBase + "/viewresolvedforemployee";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          console.log(myArr[0]);
          populateReimbursementTable(myArr, "reimbursement_table");
      }
  }
  xhr.open("GET", URL);
  xhr.send();
}
function prepEmployeeTable(table_id, anchor) {
  console.log("Hit the prepEmployeeTable function");
  var anchor = document.getElementById(anchor);
  var table = document.getElementById(table_id);
    if (table == null){
      table = document.createElement(table);
      table.id = table_id;
      anchor.appendChild(table);
      table = document.getElementById(table_id);
    }
  let tr = document.createElement("tr");
  table.appendChild(tr);
  
  let th = document.createElement("th");
  th.innerHTML = "First Name";
  tr.appendChild(th);
  
  th = document.createElement("th");
  th.innerHTML = "Last Name";
  tr.appendChild(th);

  th = document.createElement("th");
  th.innerHTML = "Email";
  tr.appendChild(th);

  th = document.createElement("th");
  th.innerHTML = "Username";
  tr.appendChild(th);

  th = document.createElement("th");
  th.innerHTML = "Password";
  tr.appendChild(th);
}

function populateEmployeeTable(table_id, someArray) {
  //NOTE: someArray is apparently *not* an Array going in here! So we'll have to transform it into one.
  console.log("Hit the populateEmployeeTable function");
  var table = document.getElementById(table_id);
  var i;
  var j;
  var k;
  let tr = document.createElement("tr");
  let td = document.createElement("td");
  someArray = Object.entries(someArray);
   console.log(someArray);
   for (i = 0; i < someArray.length; i++){
     someArray[i] = someArray[i][1];
   }
    
    console.log(someArray);
    console.log(someArray.length);
    for (i = 0; i < someArray.length; i++){
      tr = document.createElement("tr");
      table.appendChild(tr);
      td = document.createElement("td");
      td.innerHTML = someArray[i].firstName;
      tr.appendChild(td);
      td = document.createElement("td");
      td.innerHTML = someArray[i].lastName;
      tr.appendChild(td);
      td = document.createElement("td");
      td.innerHTML = someArray[i].email;
      tr.appendChild(td);
      td = document.createElement("td");
      td.innerHTML = someArray[i].username;
      //td.id = i+"username";
      //console.log(td.id);
      tr.appendChild(td);
      td = document.createElement("td");
      td.innerHTML = someArray[i].password;
      tr.appendChild(td); 
 
  if(role_id == 2){
          
    td = document.createElement("td");
    tr.appendChild(td);
    //td.innerHTML = "View Pending Reimbursements for Employee";
    pETbtn1 = document.createElement("button");
    pETbtn1.innerHTML = "View Pending Reimbursements";
    pETbtn1.setAttribute("onclick", "employeeViewPendingReimbursementRequests()");
    pETbtn1.onclick = function() {employeeViewPendingReimbursementRequests();};
    td.appendChild(pETbtn1);
    
    td = document.createElement("td");
    tr.appendChild(td);
    //td.innerHTML = "View Resolved Reimbursements for Employee";
    pETbtn2 = document.createElement("button");
    pETbtn2.innerHTML = "View Resolved Reimbursements";
    pETbtn2.setAttribute("onclick", "viewEmployeeResolvedReimbursementRequests()");
    pETbtn2.onclick = function() {viewEmployeeResolvedReimbursementRequests();};
    td.appendChild(pETbtn2);
    
    td = document.createElement("td");
    tr.appendChild(td);
    //td.innerHTML = "View All Reimbursements for Employee";
    pETbtn3 = document.createElement("button");
    pETbtn3.innerHTML = "View All Reimbursements for Employee";
    pETbtn3.setAttribute("onclick", "employeeViewReimbursementRequests()");
    pETbtn3.onclick = function() {employeeViewReimbursementRequests();};
    td.appendChild(pETbtn3);
      }
    }
    
    };//);

  function prepReimbursementTable(table_id, anchor) {
    console.log("Hit the prepReimbursementTable function");
    var anchor = document.getElementById(anchor);
    var table = document.getElementById(table_id);
    if (table == null){
      table = document.createElement("table");
      table.id = table_id;
      anchor.appendChild(table);
      table = document.getElementById(table_id);
    }
    let tr = document.createElement("tr");
    table.appendChild(tr);
    
    let th = document.createElement("th");
    th.innerHTML = "Amount";
    tr.appendChild(th);
    
    th = document.createElement("th");
    th.innerHTML = "Submission Time";
    tr.appendChild(th);

    th = document.createElement("th");
    th.innerHTML = "Resolution Time";
    tr.appendChild(th);

    th = document.createElement("th");
    th.innerHTML = "Description";
    tr.appendChild(th);

    th = document.createElement("th");
    th.innerHTML = "Submitter";
    tr.appendChild(th);

    if(role_id == 2){

    th = document.createElement("th");
    
    th.innerHTML = "Resolver";
    tr.appendChild(th);
    }

    else if(role_id == 1){
    th = document.createElement("th");
    th.innerHTML = "Resolver";
    tr.appendChild(th);
    }

    th = document.createElement("th");
    th.innerHTML = "Status";
    tr.appendChild(th);

    th = document.createElement("th");
    th.innerHTML = "Type";
    tr.appendChild(th);
  }

  function populateReimbursementTable(someArray, table_id){
    console.log("Attempting to populate reimbursement table.");
    var table = document.getElementById(table_id);
    console.log(table);
    var i;
    var j;
    var k;
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    someArray = Object.entries(someArray);
    for (i = 0; i < someArray.length; i++){
      someArray[i] = someArray[i][1];
    }
    
    console.log(someArray);
    console.log(someArray.length);
    for (j = 0; j < someArray.length; j++){ // 1. for each object create a new row (<tr>) and stick it onto (append) the table that exists
      tr = document.createElement("tr");
      table.appendChild(tr);
        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[1];
        tr.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[2];
        tr.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[3];
        tr.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[4];
        tr.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[6];
        tr.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[8];
        tr.appendChild(td);

        if(role_id == 1){
        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[(Object.keys(someArray[j].length - 2))];
        tr.appendChild(td);
        }
        else if(role_id == 2 && (Object.values(someArray[j])[(Object.keys(someArray[j].length - 2))] == "Pending")){
        td = document.createElement("td");
        tr.appendChild(td);
        var sel = document.createElement("select");
        sel.name = "status";
        sel.id = "status_sel";
        var opt1 = document.createElement("option");
        opt1.value = "Pending";
        opt1.innerHTML = "Pending"
        sel.appendChild(opt1);
        var opt2 = document.createElement("option");
        opt2.value = "Approved";
        opt2.innerHTML = "Approved";
        sel.appendChild(opt2);
        var opt3 = document.createElement("option");
        opt3.value = "Rejected";
        opt3.innerHTML = "Rejected";
        sel.appendChild(opt3);
        td.appendChild(sel);
        }

        td = document.createElement("td");
        td.innerHTML = Object.values(someArray[j])[(Object.keys(someArray[j]).length - 1)];
        tr.appendChild(td);
      if(role_id == 2){
          
        td = document.createElement("td");
        tr.appendChild(td);
        //td.innerHTML = "View Pending Reimbursements for Employee";
        pRTbtn1 = document.createElement("button");
        pRTbtn1.innerHTML = "Resolve Reimbursement";
        pRTbtn1.setAttribute("onclick", "resolveReimbursement()");
        pRTbtn1.onclick = function() {resolveReimbursement();};
        td.appendChild(pRTbtn1);
        }
      }
    }

function viewPersonalInfo(){
    console.log("viewPersonalInfo");
    var URL = URLBase + "/viewinfo";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        console.log(this.responseText);
        console.log(typeof(this.responseText));
        var myArr = JSON.parse(this.responseText);
        populateEmployeeTable("employee_table", myArr);
        }
    }
    xhr.open("GET", URL);
    xhr.send();
}

function viewEmployeeResolvedReimbursementRequests(){
  console.log("viewEmployeeResolvedReimbursementRequests");
    var URL = URLBase + "/viewresolvedforemployee";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        console.log(this.responseText);
        console.log(typeof(this.responseText));
        var myArr = JSON.parse(this.responseText);
        populateReimbursementTable(myArr, "reimbursement_table");
        }
    }
    xhr.open("GET", URL);
    xhr.send();
}

function employeeUpdateAction(){
    console.log("employeeUpdatePersonalInfo");
    var URL = URLBase + "/updateinfo";
    fetch(URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(genEmployeeForm()),
    })
    .then(response => response.json)
    .then(viewPersonalInfo());
  }

function viewPendingReimbursementRequests() {
    console.log("viewPendingReimbursementRequests");
    var URL = URLBase + "/viewpending";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          console.log(myArr[0]);
          populateReimbursementTable(myArr, "reimbursement_table");
        }
      }
      xhr.open("GET", URL);
      xhr.send();
}

function viewResolvedReimbursementRequests() {
    console.log("viewResolvedReimbursementRequests");
    var URL = URLBase + "/viewresolved";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          console.log(myArr[0]);
          populateReimbursementTable(myArr, "reimbursement_table");
        }
      }
      xhr.open("GET", URL);
      xhr.send();
}

function viewAllEmployees(){
    console.log("viewAllEmployees");
    var URL = URLBase + "/employees";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        console.log(this.responseText);
        console.log(typeof(this.responseText));
        var myArr = JSON.parse(this.responseText);
        populateEmployeeTable("employee_table", myArr);
      }
  }
  xhr.open("GET", URL);
  xhr.send();
}

function postReimbursement(){
    console.log("postReimbursement");
    var URL = URLBase + "/postreimbursement";
    fetch(URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(genReimbursementForm()),
    })
    .then(response => response.json)
    .then(employeeViewReimbursementRequests());
  }

function resolvePendingReimbursements(){
  console.log("postReimbursement");
    var URL = URLBase + "/resolvereimbursement";
    fetch(URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(genResolveReimbursementForm()),
    })
    .then(response => response.json)
    .then(viewAllReimbursementRequests());
  }

function viewAllReimbursementRequests(){
  console.log("View All Reimbursements");
    var URL = URLBase + "/viewall";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          populateReimbursementTable(myArr, "reimbursement_table");
      }
  }
  xhr.open("GET", URL);
  xhr.send();
}

function reimbursementAction(){
  switch(role_id){
    case 1:
      postReimbursement();
      break;
    case 2:
      resolvePendingReimbursements();
      break;

  }
}

 function loadHandler() {
    var element=document.getElementById("anchordiv");
    firstName = cookiereader();
    role_id = loginreader();
    role_id = Number(role_id);
    console.log(role_id);
    prepEmployeeTable("employee_table", "anchordiv1");
    prepReimbursementTable("reimbursement_table", "anchordiv2");
    switch(role_id){
        //CASE 1: USER IS AN EMPLOYEE -

        case 1: 
        
        var btn1 = document.createElement("BUTTON");
        var btn2 = document.createElement("BUTTON");
        var btn3 = document.createElement("BUTTON");
        var btn4 = document.createElement("BUTTON");

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

        break;

        //CASE 2: USER IS A MANAGER -

        case 2:
        var btn1 = document.createElement("BUTTON");
        var btn2 = document.createElement("BUTTON");
        var btn3 = document.createElement("BUTTON");
        var btn4 = document.createElement("BUTTON");

        btn1.innerHTML = "View or Resolve Pending Reimbursement Requests"; //This button shows all pending reimbursements and lets the user mark them as pending, approved, or denied.
        btn1.setAttribute("onclick", "viewPendingReimbursementRequests();");
        btn1.onclick = function() {viewPendingReimbursementRequests();};
        element.appendChild(btn1);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn2.innerHTML = "View Resolved Reimbursement Requests"; //Add another button, this one shows pending reimbursements when clicked.
        btn2.setAttribute("onclick", "viewResolvedReimbursementRequests();");
        btn2.onclick = function() {viewResolvedReimbursementRequests();};
        element.appendChild(btn2);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        btn3.innerHTML = "View All Reimbursements"; //Add another button, this one shows resolved reimbursements when clicked.
        btn3.setAttribute("onclick", "viewAllReimbursementRequests();");
        btn3.onclick = function() {viewAllReimbursementRequests();};
        element.appendChild(btn3);

        btn4.innerHTML = "View All Employees"; //Add another button, this one shows resolved reimbursements when clicked.
        btn4.setAttribute("onclick", "viewAllEmployees();");
        btn4.onclick = function() {viewAllEmployees();};
        element.appendChild(btn4);
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));

        break;
        default:
            break;
 }
 //var introP = document.createElement("p");
 //introP.innerHTML = "Welcome to the Reimbursement Portal, " + firstName;
 //element.appendChild(introP);
 //return;
}

window.onload = loadHandler();