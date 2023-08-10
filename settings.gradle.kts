pluginManagement {
    repositories {
        maven(url = "https://jitpack.io")
        maven(url = "https://s01.oss.sonatype.org/content/groups/public")
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public/")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://jitpack.io")
        maven(url = "https://s01.oss.sonatype.org/content/groups/public")
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public/")
        google()
        mavenCentral()
    }
}

rootProject.name = "DevBox"
include(":app")
 