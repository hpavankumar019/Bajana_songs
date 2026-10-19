# Bajana Songs Android starter project

This Android Studio project wraps your existing Hugging Face website in an Android app.

Website: https://huggingface.co/spaces/hpavan19/Bajana-songs

## Build the APK
1. Install Android Studio on a computer.
2. Open this folder in Android Studio.
3. Let Gradle sync finish (internet is needed for the first build).
4. Select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. Find the APK under `app/build/outputs/apk/debug/app-debug.apk`.

## Important limitations
- This is a starter wrapper, not a verified final APK.
- The first version loads the hosted website, so internet is needed and offline lyrics are NOT yet guaranteed.
- Kannada voice recognition is included as a native helper but needs wiring to the exact search input in your website. The site HTML/source was not available in this build session, so the exact input and logo could not be verified.
- To preserve every Kannada song, its exact order, duplicates, and the existing logo, add the website's actual source files/assets to `app/src/main/assets/` and update the app to load `file:///android_asset/index.html`, or provide the website source ZIP so it can be integrated and made offline-ready.
- Voice recognition may use Google's speech service and may need internet depending on the phone.

## Next step for a fully offline build
Download the source of your Hugging Face Space (all HTML, CSS, JavaScript, JSON and image/logo files) and share it here as a ZIP. Then the local copy can be integrated without recreating the song list by hand.
