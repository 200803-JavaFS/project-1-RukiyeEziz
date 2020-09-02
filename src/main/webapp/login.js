/**
 * 
 */
 
 console.log("in login js file");
 const url = "http://localhost:8080/project1/";
 
 
 document.getElementById("login_btn").addEventListener("click", login);
 
 async function login() {
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
    	console.log("response from login function " + resp);
    	findUserPage();
    	
    	
    }
    else {
        document.getElementById("login_warning").innerText = "Invalid user name and/or password. Re-enter again...";
    }
    
    
 }
 
 async function findUserPage() {
 
 	console.log("in find user function");
    
    let resp = await fetch(url + "takemehome", {
    	method: 'GET',
    	credentials: 'include'
    
    });
    
 	console.log("respond in find all func " + resp);
 	
    if (resp.status === 200) {
    	let data = await resp.json();
        console.log("data repond json in find all function " + data);
 
 /*       
        let userId = data.id;
        console.log("user id " + userId);
        
        let userRole = data.userRole.role;
        console.log("user role " + userRole);
        
        if(userRole == "Employee") {
        
        	window.location.href = "emp_home.html";
        }
        else if(userRole == "Manager") {
        	window.location.href = "admin_home.html";
        }
  */      
        
   
    
    }
    else {
    	alert("login failed");
    }
 
 }
 
 
 
 
 