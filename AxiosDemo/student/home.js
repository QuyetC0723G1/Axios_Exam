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
            <th>Image</th>
             <th>Class</th>
             <th>Tutor</th>
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
            <td><img src="${students[i].image}" alt=""></td>
            <td>${students[i].classRoom.name}</td>
            <td>${showTutorList(students[i].tutors)}</td>
            
            <td>
                <button onclick="getFormUpdate(${students[i].id},${students[i].classRoom.id})">Edit</button>
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
        axios.delete('http://localhost:8080/students/delete/' + id)
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
            <th>Tutor</th>
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
            <td>${showTutorList(students[i].tutors)}</td>
            <td>
                <button onclick="getFormUpdate(${students[i].id},${students[i].classRoom.id})">Edit</button>
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


function searchByClassRoom() {
    let arr =[]
    let classRoom = document.getElementsByClassName("class_id");
    for (let i = 0; i< classRoom.length; i++) {
        if (classRoom[i].checked){
            arr.push(classRoom[i].value)
        }
    }
    if (arr.length ===0){
        getAll();
    }
    else {
        axios.post('http://localhost:8080/students/find', arr)

            .then(function (response) {
                let students = response.data;
                let html = `
            <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Birth Day</th>
            <th>Gender</th>
            <th>Image</th>
            <th>Class</th>
            <th>Tutor</th>
           
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
            <td><img src="${students[i].image}" alt=""></td>
            <td>${students[i].classRoom.name}</td>
            <td>${showTutorList(students[i].tutors)}</td>
            <td>
                <button onclick="getFormUpdate(${students[i].id},${students[i].classRoom.id})">Edit</button>
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

function showTutorList(tutorList) {
    let html = "";
    for (let i = 0; i < tutorList.length; i++) {
        html += `<div>
<input type="button" onclick="findStudentByTutors(${tutorList[i].id})">
<input type="hidden" value="${tutorList[i].id}" id="tutor_${tutorList[i].id}">
          Tutor ${i+1} :
         ${tutorList[i].name}
         </div>
`
    }
    return html;
}
function findStudentByTutors(id){
    let tutors = []
    let tutorsId = document.getElementById(`tutor_${id}`).value;
    tutors.push(tutorsId);
    if (tutorsId === ""){
        getAll();
    }
    else {
            axios.post('http://localhost:8080/students/findByTutors', tutors)

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
            <th>Tutor</th>
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
            <td>${showTutorList(students[i].tutors)}</td>
            <td>
                <button onclick="getFormUpdate(${students[i].id},${students[i].classRoom.id})">Edit</button>
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