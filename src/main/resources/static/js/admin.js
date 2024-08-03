$(async function () {
    await allUsers();
});

function allUsers() {
    const tbody = document.getElementById('show_users')
    fetch('/api/admin')
        .then((response) => {
            return response.json()
        })
        .then((users) => {
            tbody.innerHTML = '';
            for (const user of users) {
                const row = tbody.insertRow();
                row.insertCell().innerHTML = user.id;
                row.insertCell().innerHTML = user.firstName
                row.insertCell().innerHTML = user.lastName;
                row.insertCell().innerHTML = user.age;
                row.insertCell().innerHTML = user.email;
                row.insertCell().innerHTML = user.password;
                row.insertCell().innerHTML = user.modelRole;
                row.insertCell().innerHTML =
                    '<button class="btn btn-info" type="submit" onclick="getUpdateModal('+ user.id +')" data-toggle="modal" data-bs-target="#update" data-target="#modalUpdate">Update</button>';
                row.insertCell().innerHTML =
                    '<button class="btn btn-danger" type="submit" onclick="getDeleteModal('+ user.id +')" data-toggle="modal" data-bs-target="#delete" data-target="#modalDelete">Delete</button>';
            }
        })
        .catch((error) => {
            console.error('Ошибка:', error)
        });
}
function fillModal(id, form) {

    fetch('/api/admin/user/' + id)
        .then(response => response.json())
        .then(user => {
            form.elements.namedItem("id").value = user.id;
            form.elements.namedItem("firstName").value = user.firstName;
            form.elements.namedItem("lastName").value = user.lastName;
            form.elements.namedItem("age").value = user.age;
            form.elements.namedItem("email").value = user.email;
            form.elements.namedItem("password").value = user.password
        })
        .catch(error => console.error('Ошибка:', error));
}



