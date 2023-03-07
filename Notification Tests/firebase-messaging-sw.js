async function registerWorker() {
    console.log('Registering service worker');
    const registration = await navigator.serviceWorker.
        register('/firebase-messaging-sw.js', { scope: '/' });
    console.log('Registered service worker');
    const subscription = await registration.pushManager.
        subscribe({
            userVisibleOnly: true,
            applicationServerKey: 'AAAAMXdENTA:APA91bH_S9Yk32ydx7GhB2eeF0hGGqol_qjLVa-mpH0lGIJpwQSg7nuNmRde3G_dLZ_bId0B_sPftgsfhmxgZT4FAHQAmRN4lBPL5gZDT-cCMYF4n9oHsvP5yzkXjh3qPKzwN3hNW6C8'
        });
}
if ('serviceWorker' in navigator) {
    console.log('Registering service worker');
    regiserWorker().catch(error => console.error(error));
}