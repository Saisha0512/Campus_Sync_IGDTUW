# 📱 Campus Sync - IGDTUW

**Campus Sync (IGDTUW)** is a comprehensive Android application built as part of the Mobile Application Development (BCS104) course at IGDTUW. Designed with the goal of centralizing academic utilities for students, it offers an all-in-one digital experience that includes SGPA calculation, notice board management, timetable viewing, user profile updates, and a live chat system.

## 📌 Table of Contents

- [Introduction](#introduction)
- [Problem Statement](#problem-statement)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Tech Stack](#tech-stack)
- [Contributors](#contributors)
- [References](#references)
- [License](#license)

---

## 📖 INTRODUCTION

College life at IGDTUW involves managing grades, timetables, notices, and more. Campus Sync simplifies all this by integrating key tools into a single mobile platform, making it easier for students to stay updated and organized.


## ❗ PROBLEM STATEMENT

Students often face issues managing academic resources spread across various platforms. Whether it's scattered notices, handwritten timetables, or manual SGPA calculation, this disjointed system leads to inefficiency. Campus Sync solves this by offering a unified app for academic support.


## 🚀 FEATURES : 

### 1. Login & Signup 🔐
- User authentication using email/password.
- SharedPreferences used for persistent login state.

<img src="https://github.com/user-attachments/assets/30d16158-3fd2-4335-9299-c222957f6608" height="400"/>		<img src="https://github.com/user-attachments/assets/f01fe28a-7085-47ae-93a6-4af353c2a4b8" height="400"/>	


### 2. Dashboard 🏠 
- Personalized greeting.
- One-tap access to SGPA Calculator, Timetable, Notice Board, and Profile.

<img src="https://github.com/user-attachments/assets/65c5231a-1135-4ed9-a985-806d8436d604" height="400"/>


### 3. SGPA Calculator 📊 
- Enter subject-wise marks or grades and credits.
- Supports both Marks and Grade input modes.
- Real-time dynamic SGPA calculation using Jetpack Compose.

<img src="https://github.com/user-attachments/assets/8355da58-62e3-47b5-952b-ac163fa0d468" height="400"/>


### 4. Timetable 🗓️
- Faculty/admin uploads daily class schedule via Firebase Firestore.
- Students view daily timetables filtered by class and day.

<img src="https://github.com/user-attachments/assets/8ad1bd3f-3cdf-45ed-a571-d2b7826dcd8f" height="400"/>		<img src="https://github.com/user-attachments/assets/ef462301-633c-4db5-8b41-53a07a19f806" height="400"/>			<img src="https://github.com/user-attachments/assets/dd1a0a7b-2022-47da-a841-38f7398ac66c" height="400"/>			<img src="https://github.com/user-attachments/assets/9ee151a2-c94d-4ccf-aab4-e2354cefe55c" height="400"/>


### 5. Notice Board 📢
- Upload and view academic or event notices.
- Secure internal storage with image and text metadata.
- Delete with animation and undo support.

<img src="https://github.com/user-attachments/assets/66b53f4c-0dfd-4fdd-9d1b-9c951a5dcd3e" height="400"/>	<img src="https://github.com/user-attachments/assets/7ebf78fa-b63e-44a7-a309-ee018b9cd5e5" height="400"/>		<img src="https://github.com/user-attachments/assets/3418f344-5f82-46b4-9869-5ee139de3c36" height="400"/>

### 6. Profile Management 👤
- View and update user details.
- Includes logout and validation features.

<img src="https://github.com/user-attachments/assets/c9a21dd0-2908-4867-b07a-05be5e4b3c4f" height="400"/>


### 7. Chat Feature (Future Scope) 💬
- Firebase-based real-time messaging with push notifications.
- Anonymous sign-in using Firebase Auth.
- Real-time updates, auto-scroll, and modern UI for chat interface.


## 🏗️ SYSTEM ARCHITECTURE

The app follows the **MVVM** architecture:
- **Model:** Data classes like `Notice.kt`, internal storage handling.
- **View:** XML layouts and Jetpack Compose-based UI.
- **ViewModel (optional):** Currently logic is in Activities; can be decoupled in future versions.


**Data Flow for Notices :**

User Input (Image + Title) -> AddNoticeActivity -> Save to Internal Storage -> ViewNoticeActivity -> RecyclerView Display with Animations


## 🛠️ TECH STACK

- **Kotlin** & **Jetpack Compose**
- **Firebase Firestore & Firebase Auth**
- **Firebase Cloud Messaging (FCM)**
- **Android SDK** with Material Design
- **Volley** for network requests
- **Jetpack Components:** RecyclerView, ConstraintLayout, LiveData
- **Animations:** Custom XML-based UI transitions


## 👩‍💻 CONTRIBUTORS

- **Saisha Verma** [@Saisha0512](https://github.com/Saisha0512)
- **Ritisha Sood** [@RitishaSood](https://github.com/RitishaSood/)
- **Richa Sukla** [@Ricsukla](https://github.com/Ricsukla/)
- **Riya Singh** [@riya-singh758](https://github.com/riya-singh758/)
- **Sanvi Gupta** [@San56631](https://github.com/San56631/)

> *Batch: CSE - 3, IGDTUW (B.Tech. CSE, 2nd Semester)*
						

## 📚 REFERENCES

- [Android Developer Docs](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Big Nerd Ranch - Kotlin & Android Guides](https://www.bignerdranch.com)
						

## 📜 LICENSE

*This project is for academic purposes under IGDTUW. All rights reserved to the authors.*



