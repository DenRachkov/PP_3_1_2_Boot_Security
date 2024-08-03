const updateForm = document.forms.namedItem("updateForm");
updateForm.addEventListener("submit", update);

function update(e) {

    e.preventDefault();
    const closeButton = document.getElementById("closeUpdate")
    const rolesOption = updateForm.elements.namedItem("roles");
    let selectedRoles = [];
    for (const rolesSelect of rolesOption) {
        if (rolesSelect.selected) {
            selectedRoles.push({
                id: rolesSelect.value,
                role: rolesSelect.text
            });
        }
    }
    fetch('/api/admin/update/', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: updateForm.elements.namedItem("id").value,
            firstName: updateForm.elements.namedItem("firstName").value,
            lastName: updateForm.elements.namedItem("lastName").value,
            age: updateForm.elements.namedItem("age").value,
            email: updateForm.elements.namedItem("email").value,
            password: updateForm.elements.namedItem("password").value,
            roles: selectedRoles

        }),

    })

        .then(response => {
            allUsers();
            closeButton.click();

            for (const rolesSelect of rolesOption) {
                rolesSelect.selected = false;
            }

        })
        .catch((error) => {
            console.error('Ошибка:', error);
        });

}
function getUpdateModal(id) {
    let form = document.forms.namedItem("updateForm");
    fillModal(id, form)
}







