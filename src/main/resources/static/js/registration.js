function clearFields() {
    document.getElementById("message").innerHTML = "";
    document.getElementById("addChampionshipName").innerHTML = "";
}

async function addChampionship() {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let champName = document.getElementById("addChampionshipName").value;
    await fetch("/api/v1/admin/addChampionship",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': 'Bearer_' + jwt
            },
            body: JSON.stringify({
                name: champName,
            })
        });
    document.getElementById("addChampionshipName").value = "";
    setChampionship();
}

async function deleteChampionship() {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let sel = document.getElementById("select-box2");
    let champName = sel.options[sel.selectedIndex].textContent;
    await fetch("/api/v1/admin/deActivateChampionship",
        {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': 'Bearer_' + jwt
            },
            body: JSON.stringify({
                name: champName
            })
        });
    document.getElementById("addChampionshipName").value = "";
    setChampionship();
}

async function setChampionship() {
    let response = await fetch("api/v1/auth/championships",
        {
            method: 'GET', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    let select = document.getElementById("select-box2");
    select.innerHTML = "";
    let i = 1;
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el.name + "</option>";
        i++;
    });
}

async function acceptRequest() {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let req = document.getElementById("Request-select");
    let str = req.options[req.selectedIndex].text;
    if (!str) return;
    let response = await fetch("api/v1/admin/acceptRequest",
        {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer_' + jwt,
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(
                str
            )
        });
    let isS = await response;
    await setRequests();
}

async function refuseRequest() {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let req = document.getElementById("Request-select");
    let str = req.options[req.selectedIndex].text;
    if (!str) return;
    let response = await fetch("api/v1/admin/refuseRequest",
        {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer_' + jwt,
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(
                str
            )
        });
    let isS = await response;
    await setRequests();
}

async function chooseRequest() {
    let req = document.getElementById("Request-select");
    let str = req.options[req.selectedIndex].text;
    if (!str) return;
    let response = await fetch("api/v1/auth/request",
        {
            method: 'POST', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify(
                str
            )
        });
    let data = await response.json();
    let select = document.getElementById("message");
    select.innerHTML = "";
    select.innerHTML += data.message;
}

async function setRequests() {
    let sliderdata = document.getElementById("req_status");
    let sstat;
    switch (sliderdata.value) {
        case '1':
            sstat = 'Accepted';
            break;
        case '2':
            sstat = 'Pending';
            break;
        case '3':
            sstat = 'Refused';
            break;
    }
    let response = await fetch("api/v1/auth/requests/" + sstat,
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    let select = document.getElementById("Request-select");
    select.innerHTML = "";
    let i = 1;
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el.user + " => " + el.championship + "</option>";
        i++;
    });
}

async function changeStatus() {
    let sliderdata = document.getElementById("req_status");
    let sliderText = document.getElementById("req_status_text");

    switch (sliderdata.value) {
        case '1':
            sliderdata.style.background = "green";
            sliderText.innerText = 'A';
            break;
        case '2':
            sliderdata.style.background = "#d3d3d3";
            sliderText.innerText = 'P';
            break;
        case '3':
            sliderdata.style.background = "red";
            sliderText.innerText = 'R';
            break;
    }
    setRequests();
}

window.load = init();

function init() {
    setTimeout(() => {
        setChampionship();
        setRequests();
        chooseRequest();
    }, 100);
}

logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}
