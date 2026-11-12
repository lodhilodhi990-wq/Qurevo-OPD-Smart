const firebaseConfig = {
  apiKey: "AIzaSyCXxb4FGur1BpRI7fzEAYwBPoTZW9jUfaI",
  authDomain: "qurevo-opd-smart.firebaseapp.com",
  projectId: "qurevo-opd-smart",
  storageBucket: "qurevo-opd-smart.firebasestorage.app",
  messagingSenderId: "746654346769",
  appId: "1:746654346769:web:f8af737388ba8d4d9c4bb0",
  measurementId: "G-WB0XT00HR1"
};

if (typeof firebase !== 'undefined') {
    firebase.initializeApp(firebaseConfig);
}

function handleLogin() {
    const role = document.getElementById('role-select').value;
    document.getElementById('login-container').style.display = 'none';
    document.getElementById('portal-workspace').style.display = 'block';

    document.getElementById('user-role-label').innerText = role;

    // Show ONLY permitted role panel
    document.querySelectorAll('.role-panel').forEach(panel => panel.style.display = 'none');
    document.getElementById('panel-' + role).style.display = 'block';
}

function handleLogout() {
    document.getElementById('portal-workspace').style.display = 'none';
    document.getElementById('login-container').style.display = 'block';
}

function issueToken() {
    const randomNum = Math.floor(Math.random() * 90) + 10;
    const tokenNum = "T-0" + randomNum;
    document.getElementById('reception-token-num').innerText = tokenNum;
    alert("Token " + tokenNum + " Generated!");
}

function callNext() {
    const randomNum = Math.floor(Math.random() * 90) + 10;
    const tokenNum = "T-0" + randomNum;
    document.getElementById('doctor-token').innerText = tokenNum;
}
