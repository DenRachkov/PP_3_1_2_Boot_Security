const deleteForm = document.forms.namedItem("deleteForm");
deleteForm.addEventListener("submit", remove);

function remove(e) {
    console.log('hello')
    e.preventDefault();

    const closeButton = document.getElementById("closeDelete")
    let id = deleteForm.elements.namedItem("id").value

    fetch('api/admin/delete/' + id, {
        method: 'DELETE',
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => {
            allUsers();
            closeButton.click();
        })

        .catch(error => console.error('Ошибка:', error));
}
function getDeleteModal(id) {
    let form = document.forms.namedItem("deleteForm");
        fillModal(id, form)
}
