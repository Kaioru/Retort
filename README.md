# Retort
Retort is a lightweight and flexible command framework for Java and can be easily integrated into any project.

## Installing
### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>co.kaioru.retort</groupId>
        <artifactId>retort</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Modules
* **core** - Contains command interfaces and builders.
* **annotations** - Contains annotations processing.
* **reflections** - Contains reflections generators.

## Implementations
[Distort](https://github.com/Kaioru/distort) - Commands API for Discord Bots.