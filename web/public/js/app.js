// Register PWA Service Worker for offline operation
if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js').then(() => {
        console.log('[PWA] Service Worker registered for offline cache.');
    }).catch(err => {
        console.log('[PWA] Service Worker registration failed:', err);
    });
}

// Network Status Listeners
window.addEventListener('online', updateNetworkStatus);
window.addEventListener('offline', updateNetworkStatus);

function updateNetworkStatus() {
    const isOnline = navigator.onLine;
    console.log('[Network] Status changed. Internet Available:', isOnline);
    const marquee = document.getElementById('tv-marquee');
    if (marquee && !isOnline) {
        marquee.innerText += " (OFFLINE LAN MODE ACTIVE)";
    }
}

function showApp(appId) {
    document.querySelectorAll('.app-section').forEach(sec => sec.style.display = 'none');
    document.getElementById('app-' + appId).style.display = 'block';
}

function handleGenerateToken() {
    const name = document.getElementById('p-name').value || "Muhammad Ali";
    const mr = document.getElementById('p-mr').value || "MR-2026-0042";

    const randomNum = Math.floor(Math.random() * 90) + 10;
    const tokenNum = "T-0" + randomNum;

    document.getElementById('ticket-number').innerText = tokenNum;
    document.getElementById('ticket-info').innerText = "Patient: " + name + " | Dr. Ahmed (Room 02)";

    // Update TV display
    document.getElementById('tv-token').innerText = tokenNum;
    document.getElementById('tv-marquee').innerText = "Token " + tokenNum + ", Dr. Ahmed ke Room 2 mein aaiye";

    // Speech synthesis announcement (Urdu/English)
    if ('speechSynthesis' in window) {
        const msg = new SpeechSynthesisUtterance("Token number " + tokenNum + ", Doctor Ahmed ke room number 2 mein tashreef le kar aayein");
        msg.lang = "ur-PK";
        window.speechSynthesis.speak(msg);
    }
}

function handleRegisterHospital() {
    const name = document.getElementById('hosp-name').value;
    const city = document.getElementById('hosp-city').value;
    if (name) {
        const ul = document.getElementById('hospital-list');
        const li = document.createElement('li');
        li.innerText = name + " (" + (city || "Lahore") + ") — FREE LAN PLAN";
        ul.appendChild(li);
        alert("Hospital registered successfully!");
    }
}
