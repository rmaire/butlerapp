job "${bundleName}" {

  type = "service"
  datacenters = ["dc1"]

  group "${bundleName}grp" {
    count = 1

    <#list products as product>
    task "${product.hostname}" {
      driver = "docker"

      config {
        image = "${product.image}"
        hostname = "${product.hostname}"
        network_mode = "bridge"
        port_map {
          atlas = ${product.port}
        }
        volumes = [
            "/var/glusterclient/${bundleName}/${product.hostname}:/var/atlassian/${product.type}",
          ]
      }

      env {
        "SERVICE_TAGS" = "${product.type},traefik.enable=true,traefik.frontend.entryPoints=https,traefik.frontend.passHostHeader=true"
        "SERVICE_NAME" = "${product.hostname}"
        "X_PROXY_NAME" = "${product.hostname}.${domain}"
        "X_PROXY_PORT"="443"
        "X_PROXY_SCHEME"="https"
        "X_MINIMUM_MEMORY"="${product.minMemory}m"
        "X_MAXIMUM_MEMORY"="${product.maxMemory}m"
      }
      resources {
        cpu    = ${product.cpu} # 500 MHz
        memory = ${product.maxMemory * 1.2} # 256MB

        network {
            port "atlas" {}
        }
      }
      service {
        tags = ["${product.type}","traefik.enable=true","traefik.frontend.entryPoints=https","traefik.frontend.passHostHeader=true"]
        name = "${product.hostname}"
        port = "atlas"
      }
    }
    </#list>

    task "${database.hostname}" {
      driver = "docker"

      config {
        image = "${database.image}"
        hostname = "${database.hostname}"
        network_mode = "bridge"
        volumes = [
            "/var/glusterclient/${bundleName}/${database.hostname}:/var/lib/postgresql/data",
          ]
      }

      env {
        "SERVICE_TAGS" = "${database.type}"
        "SERVICE_NAME" = "${database.hostname}"
        "POSTGRES_PASSWORD" = "${database.password}"
      }
      resources {
        cpu    = ${product.cpu} # 500 MHz
        memory = ${product.maxMemory} # 256MB
      }
    }

    task "connectnet" {
      driver = "raw_exec"

      config {
          command = "/usr/local/bin/atlasbundle2"
      }

      env {
          "BUNDLE_NAME"="${bundleName}"
          "database_HOSTNAME"=""${database.hostname}"
          "database_CONTAINER"=""${database.hostname}-${NOMAD_ALLOC_ID}"
          "database_PW"=""${database.password}"
          "JIRA_1_HOSTNAME"="jira09"
          "JIRA_2_HOSTNAME"="jira10"
          "JIRA_1_CONTAINER"="jira09-${NOMAD_ALLOC_ID}"
          "JIRA_2_CONTAINER"="jira10-${NOMAD_ALLOC_ID}"
      }
      resources {
        cpu    = 500 # 500 MHz
        memory = 128 # 256MB

        network {
            mode = "bridge"
        }
      }
    }
  }
}

