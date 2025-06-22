# 📱 Campus Sync - IGDTUW

**Campus Sync (IGDTUW)** is a comprehensive Android application built as part of the Mobile Application Development (BCS104) course at IGDTUW. Designed with the goal of centralizing academic utilities for students, it offers an all-in-one digital experience that includes SGPA calculation, notice board management, timetable viewing, user profile updates, and a live chat system.

## 📌 Table of Contents

- [Introduction](#introduction)
- [Problem Statement](#problem-statement)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Tech Stack](#tech-stack)
- [Screenshots](#screenshots)
- [Contributors](#contributors)
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
- ![image](https://github.com/user-attachments/assets/30d16158-3fd2-4335-9299-c222957f6608) 		![image](https://github.com/user-attachments/assets/f01fe28a-7085-47ae-93a6-4af353c2a4b8)


### 2. Dashboard 🏠 
- Personalized greeting.
- One-tap access to SGPA Calculator, Timetable, Notice Board, and Profile.

### 3. SGPA Calculator 📊 
- Enter subject-wise marks or grades and credits.
- Supports both Marks and Grade input modes.
- Real-time dynamic SGPA calculation using Jetpack Compose.

### 4. Timetable 🗓️
- Faculty/admin uploads daily class schedule via Firebase Firestore.
- Students view daily timetables filtered by class and day.

### 5. Notice Board 📢
- Upload and view academic or event notices.
- Secure internal storage with image and text metadata.
- Delete with animation and undo support.

### 6. Profile Management 👤
- View and update user details.
- Includes logout and validation features.

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

User Input (Image + Title)
      ↓
AddNoticeActivity
      ↓
Save to Internal Storage
      ↓
ViewNoticeActivity
      ↓
RecyclerView Display with Animations


## 🛠️ TECH STACK

- **Kotlin** & **Jetpack Compose**
- **Firebase Firestore & Firebase Auth**
- **Firebase Cloud Messaging (FCM)**
- **Android SDK** with Material Design
- **Volley** for network requests
- **Jetpack Components:** RecyclerView, ConstraintLayout, LiveData
- **Animations:** Custom XML-based UI transitions


## 📷 SCREENSHOTS

      Login Activity	             				                                                                         Signup Activity





Dashboard Activity	             			        SGPA Calculator Activity  					
Notice Board Activity	             			        	Add Notice Activity
					
View Notice Activity	             			        Time Table Activity
						

Select Day TT Activity	             			        Daily Timetable Activity
						
Update TT Activity	             			                    Update ProfileActivity
						




