async function showFormCreate(){
    document.getElementById("main").innerHTML = `
      <div>
        <label for="name">Name : </label><input type="text" id="name" placeholder="Name">
        <label for="birthDay">BirthDay : </label><input type="date" id="birthDay" placeholder="Name">
        <label for="gender">Gender : </label><input type="text" id="gender" placeholder="Name">
    
        <label for="select">Class : </label><select name="classRoom" id="select">${await getClassRoom()}</select>
        <button onclick="create()">Commit</button>
    </div>
    `
}
function create() {
    let name = document.getElementById("name").value;
    let birthDay = document.getElementById("birthDay").value;
    let gender = document.getElementById("gender").value;
    let idClassRoom = document.getElementById("classId").value;

    let student = {
        name: name,
        birthDay: birthDay,
        gender: gender,
        classRoom: {
            id: idClassRoom
        }
    }
    axios.post('http://localhost:8080/students', student)
        .then(function (response) {
            getAll()
        })
}


