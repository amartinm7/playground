# NAT protocol

The range number of public ip is limited. 

In order to avoid to consume all the public ip's, a company or a house, has assigned an ip public as entry point to their internal networks, or private networks.

So `NAT` is the protocol or mechanism to translate the request of the host on the private network with an private ip's to internet with a public internet. 

The simple example, you have an iphone, an ipad and a computer into your home. You have a switch/router with a public assigned ip. 

When the ipad is connecting to internet, is sending a request to connect a web page. To do that, sends a TCP request with the current private ip 192.168.1.29 to the destination ip 209.45.3.20. 

But the request pass through the switch/router which translate the request to the public ip 85.67.4.35 as sender and destination ip 209.45.3.20. 

The destination server receives the request 85.67.4.35, 209.45.3.20, and serves the response to the 85.67.4.35 ip which is the ip of the switch/router of the user.

The response arrives to the switch/router and this, serves the response to the ipad which ip 192.168.1.29. 

![nat_protocol.jpeg](_img%2Fnat_protocol.jpeg)

