# Course Info Project

## Overview

The Course Info project is a modular Java application designed to manage, retrieve, and serve information about various
courses. It is organized as a multi-module Maven project with three main components:

- **course-info-cli**: Command-line interface for interacting with course data.
- **course-info-repository**: Handles data storage, retrieval, and domain logic.
- **course-info-server**: RESTful server for exposing course information via HTTP APIs.

The project uses a relational database (H2 or similar) for persistent storage and is structured for easy extension and
maintainability.

---

## Project Structure

```
course-info/
├── course-info-cli/           # CLI application module
├── course-info-repository/    # Data access and domain logic module
├── course-info-server/        # REST API server module
├── courses.db.mv.db           # Database file
├── db_init.sql                # Database initialization script
├── pom.xml                    # Parent Maven configuration
└── README.md                  # Project documentation
```

- Each module contains its own `pom.xml` and follows standard Maven conventions.
- Source code is under `src/main/java` and tests under `src/test/java` in each module.

---

## Technologies Used

- **Java 17+**
- **Maven** (multi-module)
- **H2 Database** (or compatible relational DB)
- **JUnit** (for testing)
- **REST API** (JAX-RS or Spring, depending on implementation)

---

## Maven Project & Dependency Organization

This project is structured as a Maven multi-module project:

- The **root `pom.xml`** defines shared properties, plugin management, and dependency versions for all modules. It acts
  as the parent for the submodules.
- Each submodule (`course-info-cli`, `course-info-repository`, `course-info-server`) has its own `pom.xml` that declares
  its specific dependencies and inherits from the parent.
- **Dependency Management**: Common dependencies (e.g., JUnit, database drivers) are managed in the parent POM to ensure
  version consistency. Module-specific dependencies (e.g., CLI libraries, server frameworks) are declared in the
  respective module POMs.
- **Inter-module Dependencies**: Modules can depend on each other using Maven's `<dependency>` mechanism. For example,
  the CLI and server modules depend on the repository module for domain and data access logic.
- **Build Lifecycle**: Running `mvn clean install` from the root builds all modules in the correct order, ensuring that
  dependencies are resolved and artifacts are available for downstream modules.

This organization allows for modular development, easy dependency management, and clear separation of concerns between
the CLI, repository, and server components.

---

## Prerequisites

- Java 17 or higher
- Maven 3.6+

---

## Database

- The project uses `courses.db.mv.db` as the main database file.
- To initialize or reset the database, use the `db_init.sql` script.
