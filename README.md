
# 📚 Library Web Application

This is a Java-based web application developed using the **Model-View-Controller (MVC)** design pattern.  
The application allows citizens to search for books, journals, and other media stored in a **MySQL** database, and view detailed information for each item.

---

## 🔍 Features

### 🔹 List View
When a visitor lands on the site, they are greeted with a search bar.  
By entering a title or author and clicking "Search", a list of matching media is displayed.

### 🔹 Detail View
Visitors can click on an item from the list to view full details about the book or media, including availability or expected return time.

### 🔹 Login / Logout
- Users can log in with a username and password.
- Both logged-in and non-logged-in users can access the list and detail views.
- Logged-in users also gain access to:
  - View current loans
  - View loan history
  - Manage borrowing and returns

### 🔹 User Registration
New users can easily create an account.

### 🔹 Error Handling
The application handles errors gracefully and displays user-friendly messages, avoiding technical exceptions in the UI or console.

### 🔹 Docker Support
The final application is packaged as a **Docker image** and pushed to **Docker Hub**, ensuring it runs smoothly in a containerized environment.

---

## 🛠️ Build Instructions 

### 1. Install Required Tools

- **IDE**: IntelliJ IDEA or any Java-supporting IDE
- **Java JDK**: Version 21 or higher
- **Maven**: Apache Maven (make sure `mvn` is available in your system PATH)
- **Docker**: Install Docker to run the app in a container

- 🐳 Docker Image
You can pull or run the Docker image from Docker Hub:

➡️ Docker Hub: iaraalrawi/grit



### 2. Clone the Repository

```bash
git clone https://github.com/iaraalrawi/grit.git
