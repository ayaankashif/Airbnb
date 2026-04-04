# Airbnb Clone

[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/ayaankashif/Airbnb.git)

This repository contains a full-stack web application that emulates the core functionalities of Airbnb. Built with Java and the Spring Boot framework, it provides a platform for users to book accommodations and for administrators to manage properties. The application features distinct roles for regular users and administrators, each with a tailored interface and set of capabilities.

## Features

-   **Dual Role System**: Separate workflows and dashboards for `admin` and `user` roles.
-   **User Authentication**: Secure user registration and login functionality.
-   **Admin Property Management**:
    -   CRUD operations for hotels.
    -   CRUD operations for rooms within a hotel.
    -   Management of room-specific amenities.
-   **Comprehensive Reservation Workflow**:
    -   Search for hotels by city and availability dates.
    -   View a listing of available rooms with real-time booking status (Available/Booked).
    -   Inspect room details, including amenities and pricing.
    -   Book multiple rooms and view the dynamically calculated total price.
    -   Complete a reservation with user details and payment processing.
-   **Dynamic UI**: Thymeleaf templates render dynamic content based on user interactions and database state.

## Tech Stack

-   **Backend**: Java 17, Spring Boot 3.4.6
-   **Database**: MySQL with Spring Data JPA
-   **Frontend**: Thymeleaf, HTML, CSS, JavaScript
-   **Build Tool**: Maven
-   **Utilities**: Lombok

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

-   Java Development Kit (JDK) 17 or later
-   Apache Maven
-   MySQL Server

### Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/ayaankashif/airbnb.git
    cd airbnb
    ```

2.  **Configure the database:**
    -   Create a new MySQL database named `airbnb`.
    -   Update the database connection details in `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/airbnb
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        ```

3.  **Run the application:**
    ```sh
    mvn spring-boot:run
    ```
    The application will be accessible at `http://localhost:8080`.

## Project Structure

The project follows a standard Model-View-Controller (MVC) architecture.

-   `src/main/java/com/ayaan/airbnb/`
    -   `controller/`: Handles incoming HTTP requests and maps them to the appropriate services.
    -   `service/`: Contains the core business logic for user management, reservations, and property handling.
    -   `repository/`: Defines Spring Data JPA interfaces for database interactions.
    -   `model/`: Contains JPA entity classes representing the application's data structure (e.g., `User`, `Hotel`, `Room`).
    -   `exception/`: Custom exception classes like `UserNotFoundException`.
-   `src/main/resources/`
    -   `templates/`: Thymeleaf HTML files for rendering the user interface.
    -   `static/`: Holds all static assets such as CSS, JavaScript, and images.
    -   `application.properties`: Configuration file for the Spring Boot application, including database settings.

## Usage

### Admin Flow

1.  Log in with an admin account via `/login`.
2.  You will be redirected to the hotel management page (`/hotel/register`).
3.  From here, you can add, edit, or delete hotels.
4.  For each hotel, you can add rooms (`/room/register?hotelId=...`).
5.  For each room, you can add specific amenities (`/amenities/register?roomId=...`).

### User Flow

1.  Navigate to the home page or the registration page (`/home`) to create a user account.
2.  Log in with your user credentials via `/login`.
3.  Access the reservation portal at `/reserve`.
4.  Select your desired city, check-in date, and check-out date to search for available hotels.
5.  Browse the list of hotels and click "Details" to view available rooms.
6.  Select the quantity of rooms you wish to book. The total price will update automatically.
7.  Click "Reserve" and complete the final reservation and payment form.
8.  A confirmation message will be displayed upon successful booking.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](https://github.com/ayaankashif/Airbnb/blob/master/LICENSE) file for details.
