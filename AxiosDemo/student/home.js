function getAll() {
    axios.get('http://localhost:8080/students')

        .then(function (response) {
            let students = response.data;
            let html = `
            <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Birth Day</th>
            <th>Gender</th>
            <th>Class</th>
            <th colspan="2">Action</th>
        </tr>
            `;
            for (let i = 0; i < students.length; i++) {
                html += `
                <tr>
            <td>${students[i].id}</td>
            <td>${students[i].name}</td>
            <td>${students[i].birthDay}</td>
            <td>${students[i].gender}</td>
            <td>${students[i].classRoom.name}</td>
            <td>
                <button onclick="getFormUpdate(${students[i].id})">Edit</button>
            </td>
            <td>
                <button onclick="remove(${students[i].id})">Delete</button>
            </td>
        </tr>  `
            }
            html += `  </table>`
            document.getElementById("main").innerHTML = html;

        })
}

getAll();

function remove(id) {
    let isConfirm = confirm("Are you OK?");
    if (isConfirm) {
        axios.delete('http://localhost:8080/students/' + id)
            .then(function (respone) {
                alert("Delete Complete")
                getAll();
            })
    } else {
        alert("???????")
    }
}

function search() {
    let name = document.getElementById("q").value;
    if (name === "") {
        getAll();
    } else {
        axios.get('http://localhost:8080/students/search?name=' + name)

            .then(function (response) {
                let students = response.data;
                let html = `
            <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Birth Day</th>
            <th>Gender</th>
            <th>Class</th>
            <th colspan="2">Action</th>
        </tr>
            `;
                for (let i = 0; i < students.length; i++) {
                    html += `
                <tr>
            <td>${students[i].id}</td>
            <td>${students[i].name}</td>
            <td>${students[i].birthDay}</td>
            <td>${students[i].gender}</td>
            <td>${students[i].classRoom.name}</td>
            <td>
                <button>Edit</button>
            </td>
            <td>
                <button onclick="remove(${students[i].id})">Delete</button>
            </td>
        </tr>  `
                }
                html += `  </table>`
                document.getElementById("main").innerHTML = html;

            })
    }
}