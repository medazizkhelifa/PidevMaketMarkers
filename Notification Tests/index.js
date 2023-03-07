const firebaseConfig =
{
    apiKey: "AIzaSyBiC8ibN0z4NxnQmrdeFKCAQY_-yvb7_20",
    authDomain: "pidev-84eb3.firebaseapp.com",
    projectId: "pidev-84eb3",
    storageBucket: "pidev-84eb3.appspot.com",
    messagingSenderId: "212454356272",
    appId: "1:212454356272:web:e1c97afbeb509e03c744f5",
    measurementId: "G-CG98K2DJZ0"
};
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
messaging.requestPermission().then(function () {
    //console.log('Notification permission granted.');

    if (isTokenSentToServer()) {

        console.log("Token Already sent");
    } else {
        getRegisterToken();
    }

    // TODO(developer): Retrieve an Instance ID token for use with FCM.
    // ...
}).catch(function (err) {
    console.log('Unable to get permission to notify.', err);
});

function getRegisterToken() {
    // Get Instance ID token. Initially this makes a network call, once retrieved
    // subsequent calls to getToken will return from cache.
    messaging.getToken().then(function (currentToken) {
        if (currentToken) {
            saveToken(currentToken);
            console.log(currentToken);
            sendTokenToServer(currentToken);
            // updateUIForPushEnabled(currentToken);
        } else {
            // Show permission request.
            console.log('No Instance ID token available. Request permission to generate one.');
            // Show permission UI.
            // updateUIForPushPermissionRequired();
            setTokenSentToServer(false);
        }
    }).catch(function (err) {
        console.log('An error occurred while retrieving token. ', err);
        //showToken('Error retrieving Instance ID token. ', err);
        setTokenSentToServer(false);
    });
}
function setTokenSentToServer(sent) {
    window.localStorage.setItem('sentToServer', sent ? '1' : '0');
}

function sendTokenToServer(currentToken) {
    if (!isTokenSentToServer()) {
        console.log('Sending token to server...');
        // TODO(developer): Send the current token to your server.
        setTokenSentToServer(true);
    } else {
        console.log('Token already sent to server so won\'t send it again ' +
            'unless it changes');
    }
}
function isTokenSentToServer() {
    return window.localStorage.getItem('sentToServer') === '1';
}

function saveToken(currentToken) {
    localStorage.setItem("token", currentToken);
    console.log("Token saved");
    console.log(currentToken);

    // $.ajax({
    //     data: { "token": currentToken },
    //     type: "post",
    //     url: "savefcmtoken.php",
    //     success: function (result) {
    //         console.log(result);
    //     }

    // });
}
messaging.onMessage(function (payload) {
    console.log('Message received. ', payload);
    var title = payload.data.title;

    var options = {
        body: payload.data.body,
        icon: payload.data.icon,
        image: payload.data.image,
        data: {
            time: new Date(Date.now()).toString(),
            click_action: payload.data.click_action
        }
    };
    var myNotification = new Notification(title, options);
});