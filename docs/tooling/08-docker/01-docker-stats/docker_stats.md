# docker stats

```bash
docker stats
```
a console window is open and the stats starts to update

![docker-stats.jpg](_img%2Fdocker-stats.jpg)

In the binary prefix convention, the unit of measurement increases by a factor of 1024 (2^10) rather than the decimal-based factor of 1000. This convention is commonly used in computing and digital systems.

Here's a breakdown of the units used in docker stats:

- B: Byte (smallest unit)
- KiB: Kibibyte (1024 bytes)
- MiB: Mebibyte (1024 Kibibytes)
- GiB: Gibibyte (1024 Mebibytes)
- TiB: Tebibyte (1024 Gibibytes)

The use of these binary prefixes helps maintain consistency with other computing systems that use binary-based calculations. It avoids any confusion that may arise from the use of decimal-based prefixes (e.g., kilobytes, megabytes) which follow the decimal-based factor of 1000.

In summary, Docker uses binary-based memory units (KiB, MiB, GiB) to represent memory sizes in order to adhere to established computing conventions and ensure consistency across different systems.

![docker-stats-mem.jpg](_img%2Fdocker-stats-mem.jpg)

donwload from here [Resource Usage extension](docker-desktop://extensions/marketplace?extensionId=docker/resource-usage-extension)