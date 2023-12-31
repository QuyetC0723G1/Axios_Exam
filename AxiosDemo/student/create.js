async function showFormCreate(){
    document.getElementById("main").innerHTML = `
      <div>
        <label for="name">Name : </label><input type="text" id="name" placeholder="Name">
        <label for="birthDay">BirthDay : </label><input type="date" id="birthDay">
        <label for="gender">Gender : </label><input type="text" id="gender" placeholder="Gender">
        <br>
        <br>
        <label for="fileButton">Image : </label><input type="file" id="fileButton" onchange="uploadImage(event)">
        <input type="hidden" id="image" value="">
        <label for="select">Class : </label><select name="classRoom" id="select">${await getClassRoom()}</select>
        <br>
        <br>
        <label for="tutorList">Tutor : </label><ul id="tutorList">${await getAllTutor()}</ul>
        <div id="imgDiv" style="height: 100px;width: 100px"></div>
        <button onclick="create()">Commit</button>
    </div>
    `
}
function create() {
    let name = document.getElementById("name").value;
    let birthDay = document.getElementById("birthDay").value;
    let gender = document.getElementById("gender").value;
    let idClassRoom = document.getElementById("select").value;
    let image = document.getElementById("image").value;
    let tutorsHTML = document.getElementsByClassName("tutors-check");
    let tutors = [];

    for (let i = 0; i < tutorsHTML.length; i++) {
        if (tutorsHTML[i].checked) {
            tutors.push({id: tutorsHTML[i].value})
        }
    }

    console.log(tutors)

    let student = {
        name: name,
        birthDay: birthDay,
        gender: gender,
        classRoom: {
            id: idClassRoom
        },
        image: image,
        tutors : tutors

    }
    axios.post('http://localhost:8080/students/create', student)
        .then(function (response) {
            getAll()
        })
}
function uploadImage(e) {
    let fbBucketName = 'images';
    let uploader = document.getElementById('uploader');
    let file = e.target.files[0];
    let storageRef = firebase.storage().ref(`${fbBucketName}/${file.name}`);
    let uploadTask = storageRef.put(file);
    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
        function (snapshot) {
            uploader.value = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            switch (snapshot.state) {
                case firebase.storage.TaskState.PAUSED:
                    break;
                case firebase.storage.TaskState.RUNNING:
                    break;
            }
        }, function (error) {
            switch (error.code) {
                case 'storage/unauthorized':
                    break;
                case 'storage/canceled':
                    break;
                case 'storage/unknown':
                    break;
            }
        }, function () {
            let downloadURL = uploadTask.snapshot.downloadURL;
            console.log(downloadURL)
            document.getElementById('imgDiv').innerHTML = `<img src="${downloadURL}" alt="" style="width: 150px;height: 150px">`
            document.getElementById("image").value = downloadURL;
        });
}


