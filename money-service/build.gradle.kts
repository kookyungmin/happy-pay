version = "0.0.1"
description = "HappyPay Money"

dependencies {
    implementation(project(":common"))
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
        mainClass = "net.happykoo.money.MoneyApplication"
        ports = listOf("8080")
    }
}
