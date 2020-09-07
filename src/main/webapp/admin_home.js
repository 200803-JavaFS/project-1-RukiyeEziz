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
            error.
            console.log("Couldn't add the data to the tbody");
        });

}

function convertTimestampToDate(ts) {
    console.log(ts);
    var tsDate = new Date(ts);
    var formattedDate = ("0" + (tsDate.getMonth() + 1)).slice(-2) + '/' + ("0" + tsDate.getDate()).slice(-2) + '/' + tsDate.getFullYear();
    console.log(formattedDate);
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
