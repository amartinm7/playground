# How DNS works

![how-dns-works.gif](_img%2Fhow-dns-works.gif)

DNS is a fundamental part of how the internet works, and understanding its intricacies is important to stay ahead of the curve in the IT world.


🔍 Think of DNS as the internet's phonebook. 📚 It's the hero that turns friendly domain names (like www.mypage.com) into IP addresses that computers use to chat! 💻🗨️

1️⃣ You type a URL into your browser. 🖥️ Your computer first checks its local resources. This includes:

📄 The host file - a simple text file mapping domain names to IP addresses.

📦 Various caches - mini storage areas in your computer, operating system, and router where recently visited website addresses are stored.

This quick check lets your computer find the IP address of a website without needing to ask a DNS server. 🚀

2️⃣ If the domain name isn't in the host file or any of the caches, your computer sends a request 💌 to a DNS server.

The server searches its database and returns the correct IP address to your computer. 🎯

3️⃣ Once your computer gets the IP address from the DNS server, it uses that address to connect to the website. 🌐 It also stores the domain name and IP address in its DNS cache for quick access the next time you visit the website. 🔄

🌳 DNS is a hierarchical system, with DNS servers arranged in a tree-like structure. 🌲

This structure, known as the DNS namespace, allows for efficient and quick resolution of domain names to IP addresses, ensuring the smooth functioning of the internet. 🚀

☁️ Now, let's consider how DNS operates in today's expansive Hybrid Multi-Cloud environments. Public Clouds resolve their Internal FQDNs to Service Endpoints using:

🛠️ The Provider's own DNS Resolvers,

🏷️ C-Name aliases in Private DNS Zones,

🔗 And Virtual Network Links.

To resolve these FQDNs from another Cloud (Private or Public), it requires Conditional Forwarding and additional Routing between the EDGEs with NAT/SNAT. 🌉 While SNAT/NAT might not always be necessary, it's good to have for ensuring seamless interconnectivity. 🌐

🖼️ The attached gif simplifies the DNS process, but remember, in today's multi-cloud environment, the process is much more complex and fascinating! 🌩️