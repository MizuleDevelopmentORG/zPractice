plugins {
    id("zpractice.paper-conventions")
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.28")
    compileOnly(libs.paper)
    compileOnly(libs.annotations)
    api(libs.bstats.bukkit)
    compileOnly(libs.placeholderapi)
    api(libs.configurate.yaml)
    compileOnly(libs.miniplaceholders)
    implementation("org.mongodb:mongo-java-driver:3.12.12")
}

applyJarMetadata("com.mizuledevelopment.zpractice")
