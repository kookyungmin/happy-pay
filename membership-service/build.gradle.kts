version = "0.0.1"
description = "HappyPay Membership"

dependencies {
    implementation(project(":common"))
    //Vault
    implementation("org.springframework.vault:spring-vault-core:3.2.0")
    //JWT 관련
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-gson:0.12.6")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
}

//Docker image build
jib {
    from {
        image = "eclipse-temurin:17-jre"
        platforms {
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }

    to {
        image = "${rootProject.name}-${project.name}:${project.version}"
    }

    container {
        mainClass = "net.happykoo.membership.MembershipApplication"
        ports = listOf("8080")
    }
}
