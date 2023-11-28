async function getClassRoom(){
    let classList = ``
    await axios.get('http://localhost:8080/classRooms')
        .then(function (response) {
            let list = response.data;
            for (let i = 0; i < list.length; i++) {
                classList += `<option value="${list[i].id}" id="classId">${list[i].name}</option> `
            }
        })
        return classList;
}