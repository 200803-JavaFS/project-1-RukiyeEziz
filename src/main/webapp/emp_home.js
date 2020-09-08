/**
 * Rukiye Eziz
 * Project - 1
 */

console.log("in emp_home js file");
  
const url1 = "http://localhost:8080/project1/employee/";

var receivingData = sessionStorage.getItem("Lucifer");
receivingData = JSON.parse(receivingData);

//console.log("receiving data: " + receivingData);

var fName = receivingData.firstName;
document.getElementById('profile_name').innerText = fName;

let userId = receivingData.usersId;
//console.log("user id " + userId);

var logoutBtn = document.getElementById('logout_btn');
logoutBtn.addEventListener("click", logout);

empReimbs();

var createNewBtn = document.getElementById("create_new_btn");
createNewBtn.addEventListener("click", () => {

    let detailBlock = document.getElementById("detail_block");
    detailBlock.style.display = 'block';
    //console.log("clik meee");

    var today = new Date();
    var todayDate = (today.getMonth() + 1) + '/' + today.getDate() + '/' + today.getFullYear();
    let fts = (today.getTime() / 1000) * 1000;
    //console.log(fts);
    document.getElementById("reimb_date").value = todayDate;

    getNewReimbDetails();

});


async function empReimbs() {
    
    //console.log("in emp reimbs function ");
    //console.log("user id " + userId);

    let resp = await fetch(url1 + userId, {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            //console.log(data);

            document.getElementById("reimb_tbody").innerText = "";

            for (var i = 0; i < data.length; i++) {
                let submitted = data[i].reimbSubmitted;
                let resolved = data[i].reimbResolved;
                var tsResolved;
                var fResolved;

                if (resolved) {
                    fResolved = convertTimestampToDate(resolved);
                }
                else {
                    fResolved = "";
                }
                
                var fSubmitted = convertTimestampToDate(submitted);

                let row = document.createElement("tr");
                let cell = document.createElement("td");
                cell.innerHTML = "EXP-" + data[i].reimbId;
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

        }).catch((error) => {
            console.log("Couldn't add the data to the tbody");
        });

}

function getNewReimbDetails() {  

    let submitBtn = document.getElementById("submit_btn");
    submitBtn.addEventListener("click", addNewReimb);
    
    let cancelBtn = document.getElementById("cancel_btn");
    cancelBtn.addEventListener("click", cancelInputs);
}

async function addNewReimb() {
    //console.log("new reimb add new ");
    //console.log("user id " + userId);

    let amountInput = document.getElementById("reimb_amount").value;
    let descrptionInput = document.getElementById("reimb_desc").value;
    let typeInput = document.getElementById("reimb_option");
    let type = typeInput.options[typeInput.selectedIndex].value;

    //console.log("option type " + type);

    var today = new Date();
    var todayDate = (today.getMonth() + 1) + '/' + today.getDate() + '/' + today.getFullYear();
    let fts = (today.getTime() / 1000) * 1000;

    //console.log(fts);
    //console.log("dateInput " + todayDate);
    //console.log("typeInput " + type);
    //console.log("descrptionInput " + descrptionInput);
    //console.log("amountInput " + amountInput);

    let newReimb = {
        reimbAmount: amountInput,
        reimbSubmitted: fts,
        reimbResolved: null,
        reimbDescription: descrptionInput,
        reimbReceipt: null,
        reimbAuthor: userId,
        reimbResolver: null,
        reimbStatusFK: 3,          //pending
        reimbTypeFK: type

    }

    console.log("if boolean check" + newReimb);

    let resp = await fetch(url + "employee/", {
        method: 'POST',
        body: JSON.stringify(newReimb),
        credentials: 'include'
    });

    if (resp.status === 201) {
        empReimbs();
        cancelInputs();
    }
    else {
        var warning = document.getElementById("emp_home_warning");
        warning.style.display = 'block';
        warning.innerText = "*** Invalid input values. Re-enter again...";
        //console.log("something went wrong");
    }


}

function cancelInputs() {
    //console.log("cancel inputsss");
    var today = new Date();
    var todayDate = (today.getMonth() + 1) + '/' + today.getDate() + '/' + today.getFullYear();

    let typeInput = document.getElementById("reimb_option");
    let dateInput = document.getElementById("reimb_date");   
    let descrptionInput = document.getElementById("reimb_desc");
    let amountInput = document.getElementById("reimb_amount");

    dateInput.innerHTML = todayDate;
    typeInput.value = "0";
    descrptionInput.value = "";
    amountInput.value = 0.00;

    var warning = document.getElementById("emp_home_warning");
    if (warning.style.display == 'block') {
        warning.style.display = 'none';
        warning.innerText = "";
    }   

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
        //console.log("log out ...");
        window.location.replace("login.html");
       
    }
}
      