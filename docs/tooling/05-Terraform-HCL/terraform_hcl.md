# Terraform

Similar to kubernetes is some aspects.

- you define a plan
- terraform try to provide/achieve the plan

```bash
brew install 01-terraform
```

## Terraform Core concepts
Below are the core concepts/terminologies used in Terraform:

- `Variables`: Also used as input- variables, it is key- value pair used by Terraform modules to allow customization.
- `Provider`: It is a plugin to interact with APIs of service and access its related resources.
- `Module`: It is a folder with Terraform templates where all the configurations are defined
- `State`: It consists of cached information about the infrastructure managed by Terraform and the related configurations.
- `Resources`: It refers to a block of one or more infrastructure objects (compute instances, virtual networks, etc.), which are used in configuring and managing the infrastructure.
- `Data Source`: It is implemented by providers to return information on external objects to terraform.
- `Output Values`: These are return values of a terraform module that can be used by other configurations.
- `Plan`: It is one of the stages where it determines what needs to be created, updated, or destroyed to move from real/current state of the infrastructure to the desired state.
- `Apply`: It is one of the stages where it applies the changes real/current state of the infrastructure in order to move to the desired state.

## Terraform Lifecycle

Terraform lifecycle consists of – init, plan, apply, and destroy.

![terraform-life-cycle.jpg](_img%2Fterraform-life-cycle.jpg)

- Terraform init initializes the working directory which consists of all the configuration files
- Terraform plan is used to create an execution plan to reach a desired state of the infrastructure. Changes in the configuration files are done in order to achieve the desired state.
- Terraform apply then makes the changes in the infrastructure as defined in the plan, and the infrastructure comes to the desired state.
- Terraform destroy is used to delete all the old infrastructure resources, which are marked tainted after the apply phase.

## Terraform process

![terraform-overview.jpg](_img%2Fterraform-overview.jpg)

## Terraform Core
Terraform core uses two input sources to do its job.

The first input source is a Terraform configuration that you, as a user, configure. Here, you define what needs to be created or provisioned. And the second input source is a state where terraform keeps the up-to-date state of how the current set up of the infrastructure looks like.

So, what terraform core does is it takes the input, and it figures out the plan of what needs to be done. It compares the state, what is the current state, and what is the configuration that you desire in the end result. It figures out what needs to be done to get to that desired state in the configuration file. It figures what needs to be created, what needs to be updated, what needs to be deleted to create and provision the infrastructure.

## Providers
The second component of the architecture are providers for specific technologies. This could be cloud providers like AWS, Azure, GCP, or other infrastructure as a service platform. It is also a provider for more high-level components like Kubernetes or other platform-as-a-service tools, even some software as a self-service tool.

So, this is how Terraform works, and this way, it tries to help you provision and cover the complete application setup from infrastructure all the way to the application.

## Working process

- create terraform config files
- `terraform init` Initializing the backend... Initializing provider plugins...
- `terraform plan` Refreshing Terraform state in-memory prior to plan... The refreshed state will be used to calculate this plan, but will not be persisted to local or remote state storage.
- `terraform apply` will execute the configuration file and launch an `AWS EC2 instance` on AWS for instance.
- `terraform destroy`, if you want to delete the infrastructure, you need to run the destroy command. Delete  the previous created `AWS EC2 instance` on AWS for instance.

### example

create a `main.tf` file:
```bash
cat <<EOT >> main.tf
terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "3.0.2"
    }
  }
}

provider "docker" {
  host = "unix:///var/run/docker.sock"
}

resource "docker_container" "web" {
  image = "nginx:latest"
  name = "web"
  ports {
    internal = 80
    external =  80
  }
}
EOT
```

execute one by one (during the process some tmp files are created): 
- `terraform init`
- `terraform plan`
- `terraform apply`
- wget -qO- http://localhost to check the infra is working. Nginx message is shown
- `terraform destroy`

## integración con github actions

Los pasos habituales en CI/CD con Terraform son:

- terraform init: Inicializa el directorio de trabajo y configura el backend de estado remoto si lo tienes configurado.

- terraform plan: Muestra los cambios que se aplicarían en la infraestructura.

- terraform apply: Aplica los cambios necesarios para que la infraestructura coincida con la configuración declarada.

Normalmente, los pipelines están divididos en dos tipos de workflows:

- Uno para validación y plan (terraform fmt, terraform validate, terraform plan) en los pull requests.

- Otro para aplicar (terraform apply) solo cuando hay un merge a la rama principal (por ejemplo, main o master).

- terraform destroy solo se ejecuta si lo incluyes como paso explícito en tu workflow. Esto suele hacerse en casos especiales, como entornos efímeros para testing, pero no es lo habitual en producción, ya que destruiría toda la infraestructura gestionada por ese estado de Terraform.

Resumen:

El pipeline típico NO destruye la infraestructura al terminar.

Solo aplica los cambios definidos en tu código Terraform.

Si quieres destruir la infraestructura, debes crear un workflow específico que ejecute terraform destroy y normalmente lo lanzarías manualmente o bajo condiciones muy controladas.

Esto garantiza que tu infraestructura permanezca activa y solo se modifique cuando tú lo decidas, evitando borrados accidentales