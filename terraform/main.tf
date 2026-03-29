terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = ">= 2.0.0"
    }
  }
}

provider "docker" {}

# 🔹 Réseau Docker
resource "docker_network" "pfe_network" {
  name = "pfe-network"
}

# 🔹 Volume MySQL
resource "docker_volume" "mysql_data" {
  name = "mysql_data"
}

# 🔹 Image MySQL
resource "docker_image" "mysql" {
  name = "mysql:8"
}

# 🔹 Conteneur MySQL
resource "docker_container" "mysql" {
  name  = "mysql-db"
  image = docker_image.mysql.name

  env = [
    "MYSQL_ROOT_PASSWORD=root",
    "MYSQL_DATABASE=pfe_db"
  ]

  networks_advanced {
    name = docker_network.pfe_network.name
  }

  volumes {
    volume_name    = docker_volume.mysql_data.name
    container_path = "/var/lib/mysql"
  }

  ports {
    internal = 3306
    external = 3306
  }
}

# 🔹 Image Backend
resource "docker_image" "backend" {
  name = "pfe-backend:latest"
}

# 🔹 Conteneur Backend
resource "docker_container" "backend" {
  name  = "backend-app"
  image = docker_image.backend.name
  
  force_remove = true

  networks_advanced {
    name = docker_network.pfe_network.name
  }

  env = [
    "DB_HOST=mysql-db",
    "DB_USER=root",
    "DB_PASSWORD=root",
    "DB_NAME=pfe_db"
  ]

  ports {
    internal = 3000
    external = 3000
  }

  depends_on = [docker_container.mysql]
}

# 🔹 Image Frontend
resource "docker_image" "frontend" {
  name = "pfe-frontend:latest"
}

# 🔹 Conteneur Frontend
resource "docker_container" "frontend" {
  name  = "frontend-app"
  image = docker_image.frontend.name

  ports {
    internal = 80
    external = 4200
  }

  depends_on = [docker_container.backend]
}