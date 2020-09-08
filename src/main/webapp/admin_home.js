/**
 * 
 */

console.log("in admin_home js file");

const url2 = "http://localhost:8080/project1/manager/";

var receivingData = sessionStorage.getItem("Lucifer");
receivingData = JSON.parse(receivingData);

console.log("receiving data: " + receivingData);

var fName = receivingData.firstName;
document.getElementById('profile_name').innerText = fName;

var logoutBtn = document.getElementById('logout_btn');
logoutBtn.addEventListener("click", logout);

let userId = receivingData.usersId;
console.log("user id " + userId);

allReimbs();

var REIMB_ID;
var REIMB_STATUS;

//updateReimbDetails();

let submitBtn = document.getElementById("submit_reimb_btn");
submitBtn.addEventListener("click", updateReimbursement);

let cancelBtn = document.getElementById("cancel_btn");
cancelBtn.addEventListener("click", cancelInputs);

async function allReimbs() {
    console.log("in all reimbs function ");

    let resp = await fetch(url2, {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            console.log(data);

            document.getElementById("reimb_tbody").innerText = "";

            for (var i = 0; i < data.length; i++) {
                let submitted = data[i].reimbSubmitted;
                let resolved = data[i].reimbResolved;
                var tsResolved;
                var fResolved;
                if (resolved) {
                    //tsResolved = new Date(resolved);
                    //fResolved = ("0" + (tsResolved.getMonth() + 1)).slice(-2) + '/' + ("0" + tsResolved.getDate()).slice(-2) + '/' + tsResolved.getFullYear();
                    fResolved = convertTimestampToDate(resolved);
                }
                else {
                    fResolved = "";
                }
                //var tsSubmitted = new Date(submitted);
                //var fSubmitted = ("0" + (tsSubmitted.getMonth() + 1)).slice(-2) + '/' + ("0" + tsSubmitted.getDate()).slice(-2) + '/' + tsSubmitted.getFullYear();
                var fSubmitted = convertTimestampToDate(submitted);

                let row = document.createElement("tr");
                let cell = document.createElement("td");
                cell.innerHTML = data[i].reimbId;
                row.appendChild(cell);
                let cell2 = document.createElement("td");
                cell2.innerHTML = data[i].reimbStatusFK.reimbStatus;
                row.appendChild(cell2);
                let cell3 = document.createElement("td");
                cell3.innerHTML = data[i].reimbTypeFK.reimbType;
                row.appendChild(cell3);
                let cell4 = document.createElement("td");
                cell4.innerHTML = "$" + data[i].reimbAmount;
                row.appendChild(cell4);
                let cell5 = document.createElement("td");
                cell5.innerHTML = fSubmitted;
                row.appendChild(cell5);
                let cell6 = document.createElement("td");
                cell6.innerHTML = fResolved; //data[i].reimbResolved;
                row.appendChild(cell6);

                document.getElementById("reimb_tbody").appendChild(row);

            }

            myFunction();
            console.log("reimb id = " + REIMB_ID);
            console.log("reimb status " + REIMB_STATUS);
            

        }).catch((error) => {

            console.log("Couldn't add the data to the tbody");
        });

}

function myFunction() {
    var index;
    var tbl = document.getElementById("home_reimb_table");
    // console.log("Found " + x + " cells in a row.");

    var rowlength = document.getElementById("home_reimb_table").rows.length;
    //console.log("found " + r + " row in this table");

    for (var i = 0; i < rowlength; i++) {

        //var rowId = this.parentNode.rowIndex;
        //console.log("row id " + rowId);

        tbl.rows[i].addEventListener("click", function () {
            //var collength = document.getElementById("home_reimb_table").rows[i].cells.length;
            //console.log("row number: " + i);


            if (typeof index !== "undefined") {
                tbl.rows[index].classList.toggle("selected");
            }
            console.log(" type of row index " + typeof index);

            // get the selected row index
            index = this.rowIndex;
            // add class selected to the row
            this.classList.toggle("selected");


            console.log(typeof index);


            var msg = "i am clicking : "
            var rowsNotSelected = tbl.getElementsByTagName('tr');
            console.log("not selected " + rowsNotSelected.innerHTML);

            for (var j = 0; j < this.cells.length; j++) {

                //console.log("I am clicking  " + tbl.rows[i].cells[j].innerHTML);
                msg += this.cells[j].innerHTML;

                //rowsNotSelected[j].style.backgroundColor = "";
                //rowsNotSelected[j].classList.remove('selected');
                
                
            }
            console.log(msg);
            console.log("the row i clicked reimb id = " + this.cells[0].innerHTML);
            REIMB_ID = this.cells[0].innerHTML;
            console.log("the row i clicked reimb status = " + this.cells[1].innerHTML);
            REIMB_STATUS = this.cells[1].innerHTML;


            console.log("REIMB_ID = " + REIMB_ID);
            console.log("REIMB_STATUS = " + REIMB_STATUS);
            //var rowSelected = tbl.getElementsByTagName('tr')[i];

            //rowSelected.style.backgroundColor = "grey";
            //rowSelected.className += " selected";

            updateReimbView(REIMB_ID, REIMB_STATUS);
        });
    }

}

async function updateReimbView(id, status) {
    var reimbid = id;
    var reimbstatus = status;
    console.log("reimb id = " + reimbid + " status =  " + reimbstatus);

    var id = document.getElementById("reimb_id");
    var amount = document.getElementById("reimb_amount");
    var date = document.getElementById("reimb_date");
    var type = document.getElementById("reimb_type");
    var desc = document.getElementById("reimb_desc");
   

    let resp = await fetch(url2 + reimbid, {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            console.log("reimb data " + data);

            if (data.reimbStatusFK.reimbStatus == "Approved") {
                document.getElementById("admin_home_warning").style.display = 'block';
                document.getElementById("admin_home_warning").innerText = "*** This reimbursement has been approved. Click the next one."
            }
            else if (reimbstatus == "Pending" || reimbstatus == "Denied") {
                
                document.getElementById("admin_home_warning").innerText = "";
                document.getElementById("admin_home_warning").style.display = 'none';

                id.value = data.reimbId;
                amount.value = data.reimbAmount;
                date.value = convertTimestampToDate(data.reimbSubmitted);
                type.value = data.reimbTypeFK.reimbType;
                desc.value = data.reimbDescription;

            }

           
            

            //console.log("option status " + statusOption);


            
            



        }).catch((error) => {
            console.log("Couldn't add the data to the tbody");
    }); 


}

async function updateReimbursement() {
    
    console.log("updating reimb ");
    var id = document.getElementById("reimb_id").value;
    var reimbid = id;

    var status = document.getElementById("reimb_status");
    let statusOption = status.options[status.selectedIndex].value;
    console.log("statusOption " + statusOption);
    var reimbstatus = statusOption;

    console.log("user id = " + userId + " reimb id = " + reimbid + " going to update status to =  " + reimbstatus);

    if (reimbstatus == 0) {
        document.getElementById("admin_home_warning").style.display = 'block';
        document.getElementById("admin_home_warning").innerText = "*** Choose appropriate status."
    }
    else {
        document.getElementById("admin_home_warning").innerText = "";
        document.getElementById("admin_home_warning").style.display = 'none';

        let updateReimbJason = {
            reimbId: reimbid,
            reimbResolver: userId,
            reimbStatusFK: reimbstatus
        }

        console.log("updateReimbJason " + updateReimbJason);

        let resp = await fetch(url + "manager/", {
            method: 'POST',
            body: JSON.stringify(updateReimbJason),
            credentials: 'include'
        });

        if (resp.status === 201) {
            document.getElementById("admin_home_warning").style.display = 'block';
            document.getElementById("admin_home_warning").style.color = 'green';
            document.getElementById("admin_home_warning").innerText = "*** Reimbursement successfully updated."
            document.getElementById("reimb_tbody").innerText = "";
            allReimbs();

            cancelInputs();
        }

    }


    
}
function cancelInputs() {
    console.log("cancel inputsss");
    document.getElementById("reimb_id").value="";
    document.getElementById("reimb_amount").value="";
    document.getElementById("reimb_date").value="";
    document.getElementById("reimb_type").value="";
    document.getElementById("reimb_desc").value = "";
    document.getElementById("reimb_status").value = "0";
}

function convertTimestampToDate(ts) {
    //console.log(ts);
    var tsDate = new Date(ts);
    var formattedDate = ("0" + (tsDate.getMonth() + 1)).slice(-2) + '/' + ("0" + tsDate.getDate()).slice(-2) + '/' + tsDate.getFullYear();
    //console.log(formattedDate);
    return formattedDate;


}

async function logout() {

    let resp = await fetch(url + "logout", {
        credentials: 'include'
    });

    if (resp.status === 200) {
        console.log("log out ...");
        window.location.replace("login.html");
        //window.alert("You are trying to log out. Are you sure?");
    }
}

function convertTimestampToDate(ts) {
    console.log(ts);
    var tsDate = new Date(ts);
    var formattedDate = ("0" + (tsDate.getMonth() + 1)).slice(-2) + '/' + ("0" + tsDate.getDate()).slice(-2) + '/' + tsDate.getFullYear();
    console.log(formattedDate);
    return formattedDate;


}