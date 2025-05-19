// Placeholder: these could come from a server or be populated dynamically
const user = {
    name: "John Doe",
    email: "john@example.com",
    profilePicture: "../../static/img/default-profile.jpg"
};

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('userName').textContent = user.name;
    document.getElementById('userEmail').textContent = user.email;
    document.getElementById('profilePicture').src = user.profilePicture;
});

function handleEditProfile() {
    alert("Edit profile clicked. Implement functionality here.");
}