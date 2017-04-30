# Retort [![CircleCI](https://circleci.com/gh/Kaioru/retort.svg?style=shield)](https://circleci.com/gh/Kaioru/retort) [![Coveralls](https://coveralls.io/repos/github/Kaioru/retort/badge.svg?branch=master)](https://coveralls.io/github/Kaioru/retort?branch=master) [![JitPack](https://jitpack.io/v/kaioru/retort.svg)](https://jitpack.io/#co.kaioru/retort)
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
        <version>@VERSION@</version>
    </dependency>
</dependencies>
```

## Modules
* **core** - Contains command interfaces and builders.
* **annotations** - Contains annotations processing.
* **reflections** - Contains reflections generators.

## Implementations
* [Avoid](https://github.com/Kaioru/avoid) - A no bullshit implementation of Retort. 
* [Distort](https://github.com/Kaioru/distort) - A Discord Bots implementation for use in various Java Discord Libraries.
