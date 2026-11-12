pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "QUREVO"

// Shared modules
include(":shared:models")
include(":shared:core")
include(":shared:design-system")

// Backend module
include(":backend:ktor-server")

// Android Apps
include(":apps:reception-app")
include(":apps:doctor-app")
include(":apps:tv-display-app")
include(":apps:admin-app")
include(":apps:unified-app")

// Server Manager Desktop Tool
include(":tools:server-manager")
