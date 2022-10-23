/* Using /pp231/api/user invoking */
/* send request to my api for get all users */
function loadTable() {
  const xhttp = new XMLHttpRequest();
  xhttp.open("GET", "/pp231/api/users");
  xhttp.send();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);
      var trHTML = "";
      const objects = JSON.parse(this.responseText);
      for (let object of objects) {
        trHTML += "<tr>";
        trHTML += "<td>" + object["id"] + "</td>";
        trHTML += "<td>" + object["firstName"] + "</td>";
        trHTML += "<td>" + object["lastName"] + "</td>";
        trHTML += "<td>" + object["email"] + "</td>";
        trHTML +=
          '<td><button type="button" class="btn btn-outline-secondary" onclick="showUserEditBox(' +
          object["id"] +
          ')">Изменить</button>';
        trHTML +=
          '<button type="button" class="btn btn-outline-danger" onclick="userDelete(' +
          object["id"] +
          ')">Удалить</button></td>';
        trHTML += "</tr>";
      }
      document.getElementById("mytable").innerHTML = trHTML;
    }
  };
}

loadTable();


/* Addition JS function for user table */
function showUserCreateBox() {
  Swal.fire({
    title: "Новый пользователь (создать)",
    html:
      '<input id="id" type="hidden">' +
      '<input id="firstName" class="swal2-input" placeholder="Имя">' +
      '<input id="lastName" class="swal2-input" placeholder="Фамилия">' +
      '<input id="email" class="swal2-input" placeholder="Email">',
    focusConfirm: false,
    preConfirm: () => {
      userCreate();
    },
  });
}

function versionCallback(aData, sText) {
  console.log(aData);
  console.log(sText);
}

/* Create new user */
function userCreate() {
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const email = document.getElementById("email").value;

  const xhttp = new XMLHttpRequest();
  xhttp.open("POST", "/pp231/api/users/create");
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(
    JSON.stringify({
      firstName: firstName,
      lastName: lastName,
      email: email,
    })
  );
  /*
  xhttp.onload() = function() {
    if (xhttp.status == 200) {
      console.log("Status 200");
      versionCallback(null, responseText);
    } else if (xhttp.status == 201) {
      console.log("Status 201");
      loadTable();
    } else {
      window.allert(xhttp.statusText);
      console.log("Error execute request");
    }
  }
  xhttp.onerror = xhttp.onlimeout = function(e) {
    window.allert(e.type);
  }
*/
  xhttp.onreadystatechange = function () {
    console.log("on ready state change to:" + this.status);
    if (this.readyState == 4 && (this.status == 200 || this.status == 201)) {
      const oDate = new Date();
      console.log(oDate.toString() + " : " + this.readyState );
      console.log(oDate.toString() + " : " + this.status );
      console.log(oDate.toString() + " : " + this.responseText);

      // const objects = JSON.parse(this.responseText);
      // Swal.fire(objects["message"]);
      Swal.fire(
        'Все сделано!',
        'Новый пользователь создан!',
        'success'
      ); 
      loadTable();
    } else {
      console.log("Unknow status");
      Swal.fire({
        icon: 'error',
        title: 'Однако...',
        text: 'Что-то где-то пошло не так!',
        footer: '<a href="#возможный_линк_на_возможный_мануал_с_описанием">Что это может быть?</a>'
      })
    }
  };
}

function showUserEditBox(id) {
  console.log(id);
  const xhttp = new XMLHttpRequest();
  xhttp.open("GET", "https://www.mecallapi.com/api/users/" + id);
  xhttp.send();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const objects = JSON.parse(this.responseText);
      const user = objects["user"];
      console.log(user);
      Swal.fire({
        title: "Изменить данные пользователя",
        html:
          '<input id="id" type="hidden" value=' +
          user["id"] +
          ">" +
          '<input id="firstName" class="swal2-input" placeholder="Имя" value="' +
          user["firstName"] +
          '">' +
          '<input id="lastName" class="swal2-input" placeholder="Фамилия" value="' +
          user["lastName"] +
          '">' +
          '<input id="email" class="swal2-input" placeholder="Email" value="' +
          user["email"] +
          '">',
        focusConfirm: false,
        preConfirm: () => {
          userEdit();
        },
      });
    }
  };
}

function userEdit() {
  const id = document.getElementById("id").value;
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const email = document.getElementById("email").value;

  const xhttp = new XMLHttpRequest();
  xhttp.open("PUT", "https://www.mecallapi.com/api/users/update");
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(
    JSON.stringify({
      id: id,
      firstName: firstName,
      lastName: lastName,
      email: email,
    })
  );
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const objects = JSON.parse(this.responseText);
      Swal.fire(objects["message"]);
      loadTable();
    }
  };
}

function userDelete(id) {
  const xhttp = new XMLHttpRequest();
  xhttp.open("DELETE", "https://www.mecallapi.com/api/users/delete");
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(
    JSON.stringify({
      id: id,
    })
  );
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4) {
      const objects = JSON.parse(this.responseText);
      Swal.fire(objects["message"]);
      loadTable();
    }
  };
}




