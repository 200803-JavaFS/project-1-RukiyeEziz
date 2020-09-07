/**
 * Rukiye Eziz
 * Project - 1
 * 
 */
 
console.log("in login js file");
const url = "http://localhost:8080/project1/";
/*
// invisible until user sign in
beforeSignin(); 
reimbInfoTableVisible(false);
manageReimbTableVisible(false);
*/


//var warning = document.getElementById("login_warning");
//warning.style.display = 'block';
var loginBtn = document.getElementById("login_btn")
//loginBtn.style.display = 'block';
loginBtn.addEventListener("click", login);


async function login() {
    //loginBtn.style.display = 'none';
    console.log("login function ");

    let username = document.getElementById("login_username").value;
    let pw = document.getElementById("login_pw").value;
    console.log("pw " + pw);
    let user = {

        username: username,
        password: pw
    }

    console.log("user " + username + " " + pw);

    let resp = await fetch(url + "login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: "include"
    });
    console.log("user " + username + " " + pw);
    console.log("response status" + resp.status);

    if (resp.status === 200) {
        
        findUserPage();

    }
    else {
        var warning = document.getElementById("login_warning");
        warning.style.display = 'block';
        warning.innerText = "*** Invalid user name and/or password. Re-enter again...";
    }

}

async function findUserPage() {

    let resp = await fetch(url + "success", {
        credentials: 'include'
    });
    console.log("respond in find all func " + resp.status);

    if (resp.status === 200) {

        console.log(resp);
        let data = await resp.json();
        console.log("data repond json in find user pager -- data" + data);

        let userId = data.usersId;
        console.log(userId);

        let role = data.userRoleFK.userRole;
        console.log(role);
        //sessionStorage.setItem("userid", userId);

        var mySession = "Lucifer";
        var sendingData = JSON.stringify(data);
        console.log("sending data " + sendingData);

        sessionStorage.setItem(mySession, sendingData);
       
   
        if(role == "Employee") {
      
            window.location.assign("emp_home.html");
        }
        else if(role == "Manager") {
            window.location.assign("admin_home.html");
        }
        



    }
    else {
        console.log("something went wrong....");
    }

}



/***********    Utility Function  ***********/

/*
function beforeSignin() {
    document.getElementById('logout_col').style.display = 'none'; // make it block after sign in
    document.getElementById('login_name_div').style.display = 'block';
    document.getElementById('password_div').style.display = 'block';
    document.getElementById('btn_div').style.display = 'block';
}
function afterSignin() {
    document.getElementById('logout_col').style.display = 'block'; // make it none after sign in
    document.getElementById('login_name_div').style.display = 'none';
    document.getElementById('password_div').style.display = 'none';
    document.getElementById('btn_div').style.display = 'none';
}
function manageReimbTableVisible(status) {
    if (status) {document.getElementById("manage_reimb_block").style.display = 'block';}
    else {document.getElementById("manage_reimb_block").style.display = 'none';}   
}
function reimbInfoTableVisible(status) {
    if (status) {document.getElementById("reimb_info_block").style.display = 'block';}
    else {document.getElementById("reimb_info_block").style.display = 'none';}
}


 */
 
 
 
 
 
 
 
 