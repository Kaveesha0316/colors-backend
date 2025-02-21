async function loadUsers() {
    try {
        let ProductHtml = document.getElementById("child");
        document.getElementById("parent").innerHTML = "";

        const response = await fetch('http://localhost:8080/colors/admin/loadusers');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const userList = await response.json();

        console.log('User List:', userList);

        userList.forEach(user => {
            let ProductCloneHtml = ProductHtml.cloneNode(true);


            ProductCloneHtml.querySelector("#name").innerHTML = user.name;
            ProductCloneHtml.querySelector("#email").innerHTML = user.email;
            ProductCloneHtml.querySelector("#mobile").innerHTML = user.mobile;

            if(user.status.name == "active"){
            ProductCloneHtml.querySelector("#status").innerHTML =  `<span class="badge badge-success">Active</span>`;
            }else{
               ProductCloneHtml.querySelector("#status").innerHTML =  `<span class="badge badge-danger">Deactive</span>`;
            }

            ProductCloneHtml.querySelector("#stschange").addEventListener(
                                                                             "click", (e) => {
                                                                         changests(user.id);
                                                                     });
            document.getElementById("parent").appendChild(ProductCloneHtml);
        });

    } catch (error) {
        console.error('Error:', error);
    }
}

async function changests(id){
const response = await fetch(
            'http://localhost:8080/colors/admin/chngsts?id='+ id);

    if (response.ok) {

  loadUsers();

    } else {

    }
}


async function loadProducts() {
    try {
        let ProductHtml = document.getElementById("child2");
        document.getElementById("parent2").innerHTML = "";

        const response = await fetch('http://localhost:8080/colors/admin/loadproducts');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const productlist = await response.json();

        console.log('Proudct List:', productlist);

        productlist.forEach(product => {
            let ProductCloneHtml = ProductHtml.cloneNode(true);


            ProductCloneHtml.querySelector("#id2").innerHTML = product.id;
            ProductCloneHtml.querySelector("#name2").innerHTML = product.name;
            ProductCloneHtml.querySelector("#qty2").innerHTML = product.qty;
            ProductCloneHtml.querySelector("#price2").innerHTML = product.price;
            ProductCloneHtml.querySelector("#user2").innerHTML = product.user.email;

            if(product.status.name == "active"){
            ProductCloneHtml.querySelector("#status2").innerHTML =  `<span class="badge badge-success">Active</span>`;
            }else{
               ProductCloneHtml.querySelector("#status2").innerHTML =  `<span class="badge badge-danger">Deactive</span>`;
            }

            ProductCloneHtml.querySelector("#stschange2").addEventListener(
                                                                             "click", (e) => {
                                                                         changeproductsts(product.id);
                                                                     });
            document.getElementById("parent2").appendChild(ProductCloneHtml);
        });

    } catch (error) {
        console.error('Error:', error);
    }
}

async function changeproductsts(id){
const response = await fetch(
            'http://localhost:8080/colors/admin/chngproductsts?id='+ id);

    if (response.ok) {

  loadProducts();

    } else {

    }
}

async function loadOrders() {
console.log("sda")
    try {
        let ProductHtml = document.getElementById("child3");
        document.getElementById("parent3").innerHTML = "";

        const response = await fetch('http://localhost:8080/colors/admin/loadorders');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const orderlist = await response.json();

        console.log('order List:', orderlist);

        orderlist.forEach(order => {
            let ProductCloneHtml = ProductHtml.cloneNode(true);


            ProductCloneHtml.querySelector("#id3").innerHTML = order.id;
            ProductCloneHtml.querySelector("#name3").innerHTML = order.orders.user.email;
            ProductCloneHtml.querySelector("#product3").innerHTML = order.product.name;
            ProductCloneHtml.querySelector("#qty3").innerHTML = order.qty;
            ProductCloneHtml.querySelector("#price3").innerHTML = order.price;

            if(order.order_status.name == "Processing"){
            ProductCloneHtml.querySelector("#status3").innerHTML =  `<span class="badge badge-danger">Processing</span>`;
            }else if(order.order_status.name == "Packed"){
               ProductCloneHtml.querySelector("#status3").innerHTML =  `<span class="badge badge-warning">Packed</span>`;
            }else if(order.order_status.name == "Dispatch"){
               ProductCloneHtml.querySelector("#status3").innerHTML =  `<span class="badge badge-info">Dispatch</span>`;
            }else{
               ProductCloneHtml.querySelector("#status3").innerHTML =  `<span class="badge badge-success">Delivered</span>`;
            }


            document.getElementById("parent3").appendChild(ProductCloneHtml);
        });

    } catch (error) {
        console.error('Error:', error);
    }
}

async function totalerning(){
 console.log('order List:');

const response = await fetch(
            'http://localhost:8080/colors/admin/totalerning');

    if (response.ok) {

   const value = await response.json();
   document.getElementById("total").innerHTML = "Rs."+value+".00";


    } else {

    }
}

async function totalsales(){
 console.log('order List:');

const response = await fetch(
            'http://localhost:8080/colors/admin/totalsales');

    if (response.ok) {

   const value = await response.json();
   document.getElementById("sales").innerHTML = value;


    } else {

    }
}

async function totalusers(){
 console.log('order List:');

const response = await fetch(
            'http://localhost:8080/colors/admin/totalusers');

    if (response.ok) {

   const value = await response.json();
   document.getElementById("users").innerHTML = value;


    } else {

    }
}

async function totalcart(){
 console.log('order List:');

const response = await fetch(
            'http://localhost:8080/colors/admin/totalcart');

    if (response.ok) {

   const value = await response.json();
   document.getElementById("carts").innerHTML = value;


    } else {

    }
}

 async function signIn() {
     const user_dto = {
         email: document.getElementById("email").value,
         password: document.getElementById("password").value,
     };

     const response = await fetch("http://localhost:8080/colors/admin/signin", {
         method: "POST",
         body: JSON.stringify(user_dto),
         headers: {
             "Content-Type": "application/json"  // Correct header
         }
     });

     if (response.ok) {
         const json = await response.json();
         console.log(json);
         if (json.success) {
             window.location = "index.html";
         } else {
            Swal.fire({
                title: 'Oops..!',
                text: json.message,
                icon: 'error',
                confirmButtonText: 'Ok'
            });
         }
     } else {
        console.log("asds")
     }
 }

 async function getUsername() {
     const response = await fetch("http://localhost:8080/colors/admin/get-username", {
         method: "GET",
         credentials: "include", // Ensures session cookies are sent with the request
     });

     if (response.ok) {
         const username = await response.text(); // Backend returns a plain text response
         console.log("Logged-in user:", username);
         document.getElementById("name").innerText = `Welcome, ${username}`;
     } else {
         console.log("No active session.");
         window.location = "login.html";
     }
 }

async function signOut() {
    try {
        const response = await fetch("http://localhost:8080/colors/admin/signout", {
            method: "POST",
            credentials: "include" // Ensure session cookies are sent
        });

        if (response.ok) {
            const json = await response.json();
            if (json.success) {
                // Redirect to the login page after successful sign-out
                window.location = "login.html";
            } else {
                console.error("Sign out failed:", json.message);
                document.getElementById("message").innerText = json.message;
            }
        } else {
            document.getElementById("message").innerText = "Sign out failed. Try again later.";
        }
    } catch (error) {
        console.error("Error during sign out:", error);
        document.getElementById("message").innerText = "An error occurred during sign out.";
    }
}
