# YouTube SEO Tag Generator

A web-based tool built with Spring Boot and Thymeleaf that extracts SEO-friendly tags from YouTube videos based on a given title. Designed to help content creators optimize their video metadata using real-world tag data.

##  Project Structure
youtube-seo-tag-generator/ 
├── src/ 
│ ├── main/ 
│ │ ├── java/ 
│ │ │ └── com/example/youtubeseo/ 
│ │ │ ├── controller/ 
│ │ │ │ └── YouTubeController.java 
│ │ │ ├── model/ 
│ │ │ │ ├── Video.java 
│ │ │ │ └── TagExtractor.java 
│ │ │ └── YouTubeSeoTagGeneratorApplication.java 
│ │ └── resources/ 
│ │ ├── templates/ 
│ │ │ ├── index.html 
│ │ │ └── fragments/ 
│ │ │ └── navbar.html 
│ │ ├── static/ 
│ │ │ ├── css/ 
│ │ │ │ └── styles.css 
│ │ │ └── js/ 
│ │ │ └── scripts.js 
│ │ └── application.properties 
├── .gitignore 
├── README.md 
├── pom.xml

## Features

- Extract SEO tags from YouTube videos using a title
- Copy tags to clipboard with one click
- Dark mode support
- Tailwind CSS + Bootstrap Icons for modern UI
- Modular React-ready structure (frontend can be ported easily)

## Tech Stack

- **Backend**: Spring Boot, Java
- **Frontend**: Thymeleaf, Tailwind CSS, Bootstrap Icons
- **Templating**: Thymeleaf fragments
- **Clipboard API**: JavaScript

## How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/youtube-seo-tag-generator.git
   cd youtube-seo-tag-generator
   
**Run the Spring Boot app**
bash
./mvnw spring-boot:run

**Open in browser**
http://localhost:5050
# Creator-SEO-Tag-Generator
