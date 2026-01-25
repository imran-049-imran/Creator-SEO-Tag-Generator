# Creator SEO Tag Generator

A modern, fast, and clean tool built with **Spring Boot + Thymeleaf + Tailwind CSS** that empowers creators to generate **SEO-friendly tags for YouTube videos instantly**.  

Simple UI. Lightning fast. 100% free.

---

## 📸 Screenshots

<img width="1327" height="618" alt="Screenshot 2025-12-02 095827" src="https://github.com/user-attachments/assets/fd312dff-d702-4b72-b753-efb543bdb5d5" />
<img width="1327" height="606" alt="Screenshot 2025-12-02 095852" src="https://github.com/user-attachments/assets/8b5c8dde-6f24-4aa3-99ea-bc3ad4d97724" />
<img width="1333" height="610" alt="Screenshot 2025-12-02 095915" src="https://github.com/user-attachments/assets/b60dae04-554f-404c-9a03-5d7250483c4b" />

---

## Features
- 🔍 Generate SEO Tags from any YouTube Title  
- 📋 One-click **Copy All Tags** button  
- 🌗 Dark Mode + Light Mode support  
- 🍏 Apple-inspired minimal UI  
- 📱 Fully responsive (Mobile + Tablet + Desktop)  
- ♻️ Reusable Thymeleaf components  
- 🧩 Clean Spring Boot MVC architecture  
- 🚫 Zero external API — works instantly  

---

## Project Structure

```
creator-seo-tag-generator/
├── src/
│   ├── main/
│   │   ├── java/com/imran/creatorseo/
│   │   │   ├── controller/
│   │   │   │   └── SeoController.java
│   │   │   ├── model/
│   │   │   │   ├── Video.java
│   │   │   │   └── TagExtractor.java
│   │   │   └── CreatorSeoTagGeneratorApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   └── fragments/navbar.html
│   │       ├── static/
│   │       │   ├── css/styles.css
│   │       │   └── js/scripts.js
│   │       └── application.properties
├── .gitignore
├── README.md
└── pom.xml
```
---

## ⚙️ How to Run

Clone the repository:

```bash
git clone https://github.com/imran-049-imran/Creator-SEO-Tag-Generator.git
cd Creator-SEO-Tag-Generator

./mvnw spring-boot:run

http://localhost:5050
```
# Technologies Used

Backend: Spring Boot (Java)

Frontend: Thymeleaf + Tailwind CSS

Icons: Bootstrap Icons

Build Tool: Maven

