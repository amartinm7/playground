# Basic patterns

![basic-patterns.jpeg](_img%2Fbasic-patterns.jpeg)

Why are Patterns in Software Development Important?

✅ They provide a shared language for developers, boosting communication and collaboration.
✅ They enhance software quality and security, helping to dodge common vulnerabilities.
✅ They accelerate development and boost software's scalability and flexibility.

𝗧𝗼𝗽 𝗠𝗶𝗰𝗿𝗼𝘀𝗲𝗿𝘃𝗶𝗰𝗲𝘀 𝗣𝗮𝘁𝘁𝗲𝗿𝗻𝘀 :

1️⃣ 𝗔𝗣𝗜 𝗚𝗮𝘁𝗲𝘄𝗮𝘆 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Acts as the traffic controller for all incoming requests, routing them to the appropriate service.

2️⃣ 𝗦𝗲𝗿𝘃𝗶𝗰𝗲 𝗠𝗲𝘀𝗵 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Orchestrates inter-service communication, managing load balancing, service discovery, and security.

3️⃣ 𝗕𝘂𝗹𝗸𝗵𝗲𝗮𝗱 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Isolates each service in a separate "compartment" for failure resilience.

4️⃣ 𝗖𝗶𝗿𝗰𝘂𝗶𝘁 𝗕𝗿𝗲𝗮𝗸𝗲𝗿 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Prevents system-wide failures by halting requests to a failing or unresponsive service.

5️⃣ 𝗘𝘃𝗲𝗻𝘁-𝗗𝗿𝗶𝘃𝗲𝗻 𝗔𝗿𝗰𝗵𝗶𝘁𝗲𝗰𝘁𝘂𝗿𝗲 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Facilitates communication between services through event publishing and subscribing.

6️⃣ 𝗦𝗶𝗱𝗲𝗰𝗮𝗿 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Deploys an auxiliary "sidecar" service alongside each microservice to handle tasks like logging, monitoring, and security.

7️⃣ 𝗦𝘁𝗿𝗮𝗻𝗴𝗹𝗲𝗿 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Gradually replaces a monolithic application with microservices.

8️⃣ 𝗦𝗲𝗿𝘃𝗶𝗰𝗲 𝗥𝗲𝗴𝗶𝘀𝘁𝗿𝘆 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Acts as a phonebook for microservices, facilitating inter-service communication.

9️⃣ 𝗦𝗮𝗴𝗮 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Manages transactions spanning multiple services by breaking them into steps and providing compensating actions if something goes wrong.

🔟 𝗖𝗤𝗥𝗦 𝗣𝗮𝘁𝘁𝗲𝗿𝗻: Separates the read and write operations for data, allowing independent optimization and scaling.