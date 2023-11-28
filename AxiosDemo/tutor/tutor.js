async function getAllTutor(){
    let html = ``;
    await axios.get('http://localhost:8080/tutors')
        .then(function (response){
        let listTutor = response.data;
            for (let i = 0; i < listTutor.length; i++) {
                html += `
        <input value="${listTutor[i].id}" type="checkbox" class="tutors-check" id="first"/>${listTutor[i].name}
   `
            }
    })
    return html;
}