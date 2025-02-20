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
}
const response = await fetch(
            'http://localhost:8080/colors/admin/totalerning);

    if (response.ok) {

   const orderlist = await response;

          console.log('order List:', orderlist);

    } else {

    }
}