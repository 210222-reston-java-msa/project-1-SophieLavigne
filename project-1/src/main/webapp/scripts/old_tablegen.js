/*

let employeeParse = [{First_Name: "first_name", Last_Name: "last_name", Email: "email", Username: "username", Password: "pass"}]; //Ones in quotes should be parsed from JSON.
 let managerParse = [{First_Name: "first_name", Last_Name: "last_name", Email: "email", Username: "username", Password: "REDACTED"}]; //Ones in quotes should be parsed from JSON.
 let reimbursementParse = [{Amount: "amount", Submission_Time: "submitted", Resolution_Time: "resolved", Description: "description", Submitter: "submitter", Resolver: "resolver", Status: "status", Type: "type"}];

 
 function tableGen(table, anchor, data, parse) {
  anchor = document.getElementById(anchor); //Find the specified anchor.  
  table.innerHTML = this.table;
  table.id = this.table;
  console.log(table instanceof(Node));
  if (table instanceof(Node)){
  anchor.removeChild(document.getElementById(table.id)); //Remove old version of the table.
  }
  table = document.createElement("TABLE"); //Make the table anew.
    
    let thead = table.createTHead(); //Give the table a head.
    let row = thead.insertRow(); //Give the table a top row.
    let tdata = Object.keys(parse[0]); //Give the table a header from these keys.
    for (let key of tdata){
        let th = document.createElement("th");
        let text = document.createTextNode(key);
        th.appendChild(text);
        row.appendChild(th);
    }

    anchor.appendChild(table);

    /* Key, Data, Element structure.
    Key: Each JSON object in the "data" JSON array.
    Data: The whole JSON array.
    Element: The content of the [row]th cell of the table.

    
    if (data != null){
    let row2 = table.insertRow();
    console.log("Outputting data to error check datakeys.");
    console.log(data);
    console.log("TYPEOF DATA");
    console.log(typeof(data));
    datakeys = Object.keys(data[0]);
    console.log(datakeys);
    if (datakeys.includes("amount")){
      keyorder = [datakeys[1], datakeys[2], datakeys[3], datakeys[4], datakeys[6], datakeys[8], datakeys[11], datakeys[12]];
      for (let key of keyorder){
        for (element in key) {
          let cell = row2.insertCell();
            let text = document.createTextNode(element[key]);
            cell.appendChild(text);
        }
      }
    }
    else if (datakeys.includes("email")){
      keyorder = [datakeys.find("first_name"), datakeys.find("last_name"), datakeys.find("email"), datakeys.find("username"), datakeys.find("password")];
      for (let key of keyorder){
        for (element in key) {
          let cell = row2.insertCell();
            let text = document.createTextNode(element[key]);
            cell.appendChild(text);
        }
      }
    }
    for (let key of data) {
      for (element in key) {
        console.log("ELEMENT KEY");
        console.log(element[key]);
        console.log("ELEMENT");
        console.log(element);
        console.log("DATA for KEY");
        console.log(data[key]);
          if (element[key] != undefined){
            let cell = row2.insertCell();
            let text = document.createTextNode(element[key]);
            cell.appendChild(text)
          }
          else{

          } 
            }
            document.createElement("br");
            document.createElement("br");
          }
        }
      }
      */