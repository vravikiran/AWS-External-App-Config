# Overview
- The application retrieves application specific properties from an external configuration defined in Aws AppConfig.
- No properties are defined in application.properties of the spring boot application. Everything is fetched from configuration defined in Aws AppConfig
## Prerequisites
- Java 17.0 or more
- Maven
- MySQL database / any relational database
- AWS Account
### Define application and configuration in AWS App Config
- Login to aws console https://aws.amazon.com/console/
- Navigate to AWS App Config
- Create an application provide name “ExternalConfig”
  
![Screenshot 2025-04-16 at 05 00 36](https://github.com/user-attachments/assets/c481bf1a-724a-4031-9562-60c60787c353)

- Create configuration profile “showcase_sbc_skills” in the above defined application
  
![Screenshot 2025-04-16 at 05 01 25](https://github.com/user-attachments/assets/4f0ea314-f019-4a92-8b86-1be5feb117fc)

- Update application related properties in configuration profile using freeform configuration in “yaml” format

![Screenshot 2025-04-16 at 05 02 01](https://github.com/user-attachments/assets/7fb32050-d5d9-4725-a463-c0ddbb22e7ac)

![Screenshot 2025-04-16 at 05 02 44](https://github.com/user-attachments/assets/b6fb5ccf-ccbc-42ae-b3a5-a4b7071f697b)

- Define an environment “local” within an application.

![Screenshot 2025-04-16 at 05 03 37](https://github.com/user-attachments/assets/434cfd56-dd62-4d08-84ae-67acfdbb3557)

- Deploy the configuration defined in application to the above created environment.

![Screenshot 2025-04-16 at 05 03 53](https://github.com/user-attachments/assets/042cea81-e12e-4c7f-abdf-43dd231930b2)

- Once the deployment is completed, above details are used in java application and retrieve the properties





