rootProject.name = "lithium"

pluginManagement {
    repositories {
        // DNCity: mavenLocal() added so net.caffeinemc.mixin-config-plugin (published there once,
        // via `./gradlew -p mods/lithium/components/mixin-config-plugin publishToMavenLocal` --
        // see that project's build.gradle comment) resolves normally by Maven coordinate. Needed
        // because each included build governs its own plugin resolution via its own
        // pluginManagement block -- DNCity's root settings.gradle.kts already having mavenLocal()
        // doesn't extend to this nested composite build.
        mavenLocal()
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.neoforged.net/releases/") }
        gradlePluginPortal()
    }
}
// DNCity: includeBuild("components/mixin-config-plugin") used to be declared here, but that
// doesn't resolve correctly once this settings file is itself pulled in as a nested composite
// build rather than being the top-level invocation -- hoisted up to DNCity's own root
// settings.gradle.kts pluginManagement block instead (see its comment on this same includeBuild).

include("common")
// "fabric" dropped for DNCity: this repo is NeoForge-only (see AGENTS.md), and configuring it
// alongside "neoforge" inside this repo's own composite build fails resolving the
// net.caffeinemc.mixin-config-plugin plugin ("already on the classpath with an unknown version")
// -- confirmed by hand. Same "strip to one loader" pattern as mods/Automobility's own fork (see
// settings.gradle.kts's comment on that includeBuild) and mods/distant-horizons's fabric-loader
// drop.
include("neoforge")
