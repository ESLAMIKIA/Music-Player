🎵 Music Player - Android App

A simple Music Player app built using Kotlin and Android Studio, allowing users to select and play audio files with a dynamic animated gradient background.

📥 Download APK
----------------------------------------------------------
[![Download APK](https://img.shields.io/badge/Download-APK-blue.svg)](https://github.com/ESLAMIKIA/Music-Player-APK/blob/main/app-release.apk)

🚀 Features
------------------------------------------------

✅ Play/Pause music

✅ Seekbar to control playback

✅ Display album art

✅ Dynamic animated gradient background

✅ Smooth background color transitions

✅ File selection from storage (Android 10 and below requires permission)

📥 Installation
-----------------------------------------------------------

1️⃣ Clone the repository:

git clone https://github.com/ESLAMIKIA/Music-Player.git

2️⃣ Open in Android Studio

3️⃣ Run the app on an emulator or a physical device

📜 Permissions
-------------------------------------------

For Android 10 and below, add the following permission to AndroidManifest.xml:

<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

For Android 11 and above, no permission is required. The app uses Storage Access Framework (SAF).

📂 File Selection

The app allows users to pick a music file from storage.

    Android 10 and below: Uses READ_EXTERNAL_STORAGE permission.
    Android 11 and above: Uses ACTION_OPEN_DOCUMENT (No permission required).

📸 Screenshots
----------------------------------------------------------------------------

![photo_2025-02-26_19-44-36](https://github.com/user-attachments/assets/b3f25634-3db7-4737-9930-c5fe1f9a203e)

🔧 Technologies Used

    Kotlin
    Android Studio
    MediaPlayer API
    Storage Access Framework (SAF)
    Animated Gradient Background with SweepGradient

👨‍💻 Author
-----------------------------------------

📌 Amir Erfan Eslamikia


🚀 Enjoy your music! 🎶
