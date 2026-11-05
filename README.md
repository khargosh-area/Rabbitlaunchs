# Rabbit Launcher

<p align="center">
  <strong>Rabbit Launcher</strong><br>
  A community rebrand of Hyper Launcher 3 for Minecraft: Java Edition on Android.
</p>

---

## About

Rabbit Launcher is a lightly rebranded build of Hyper Launcher 3, a Minecraft: Java Edition launcher for Android. It retains the upstream launcher engine and existing feature set while applying Rabbit Launcher branding.

The upstream project is forked from [MojoLauncher](https://github.com/MojoLauncher/MojoLauncher) and ultimately based on [PojavLauncher](https://github.com/PojavLauncherTeam/PojavLauncher).

## Features

- Minecraft Java Edition launching across supported versions
- Fabric, Forge, NeoForge and Quilt support
- Modpack import and instance management
- Custom Java runtime support
- Integrated account management
- Renderer selection and performance options
- Existing upstream launcher features retained

## Building

Build the launcher from source:

```bash
./gradlew :Hyper_Launcher_v3:assembleDebug
```

On Windows, use `gradlew.bat`.

## Rebranding scope

This repository currently retains the upstream project structure and engine. Rabbit Launcher branding is being applied conservatively; internal package names and native integration should not be changed without a complete migration and build test.

## Licensing and credits

This project retains the original upstream license and notices. Hyper Launcher 3 is a fork of [MojoLauncher](https://github.com/MojoLauncher/MojoLauncher), which is itself based on [PojavLauncher](https://github.com/PojavLauncherTeam/PojavLauncher). Full credit goes to those projects and their contributors.

See [LICENSE](LICENSE) and the upstream third-party notices for applicable terms.
