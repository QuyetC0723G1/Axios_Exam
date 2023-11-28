function getFormUpdate(id){
    console.log(id);
    axios.get('http://localhost:8080/students/'+id)
        .then(async function (response) {
            let student = response.data;
            document.getElementById("main").innerHTML = `
      <div>
        <label for="name">Name : </label><input type="text" id="name" placeholder="Name" value="${student.name}">
        <label for="birthDay">BirthDay : </label><input type="date" id="birthDay" placeholder="Name" value="${student.birthDay}">
        <label for="gender">Gender : </label><input type="text" id="gender" placeholder="Name" value="${student.gender}">
        <label for="select">Class : </label><select name="classRoom" id="select">${await getClassRoom()}</select>
        <button onclick="update(${student.id})">Commit</button>
    </div>
    `
        })

}

function update(id) {
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
    axios.post('http://localhost:8080/students/'+id, student)
        .then(function () {
            getAll()
        })
}