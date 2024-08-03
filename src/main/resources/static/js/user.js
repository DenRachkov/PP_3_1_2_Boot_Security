fetch('/api/user')
.then((response) =>{
    return response.json()
})
.then((user)=> {
    const nameUser = document.getElementById('NameUser')
    const roleUser = document.getElementById('RoleUser')

    nameUser.innerText = user.firstName
    roleUser.innerText = user.modelRole
    
    const tbody = document.getElementById('show_user')
    const tr = document.createElement('tr')
    tr.innerHTML = '<td>' + user.id + '</td>' +
        '<td>' + user.firstName + '</td>' +
        '<td>' + user.lastName + '</td>' +
        '<td>' + user.age + '</td>' +
        '<td>' + user.email + '</td>' +
        '<td>' + user.password + '</td>' +
        '<td>' + user.modelRole + '</td>';
    tbody.appendChild(tr);
})
    .catch((error) => {
        console.error('Ошибка:', error)
    });
