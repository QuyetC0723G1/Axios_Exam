async function getClassRoom(){
    let classList = ``
    await axios.get('http://localhost:8080/classRooms')
        .then(function (response) {
            let list = response.data;
            for (let i = 0; i < list.length; i++) {
                classList += `<option value="${list[i].id}">${list[i].name}</option> `
            }
        })
        return classList;
}
async function showAllClass(){
     await axios.get('http://localhost:8080/classRooms')
        .then(function (response) {
            let listClass = response.data;
            let html = ``;
            for (let i = 0; i < listClass.length; i++) {
                html += `<input type="checkbox" value="${listClass[i].id}" class="class_id">${listClass[i].name}</input> `
            }
            html += `
            <button onclick="searchByClassRoom()">Commit</button>
           `
            document.getElementById("search-class").innerHTML = html;

        } )
}
showAllClass();