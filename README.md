# 🐇 Rabbit Launcher

<p align="center">
  <img src="https://yt3.googleusercontent.com/RC9iOqHVK1Q6Cun4MsxPt1D0TNWVM-8dPgdlCekFq7werQ3Uxm7H0VUz4yqho1-zGBn4-JfU=s160-c-k-c0x00ffffff-no-rj" width="132" height="132" alt="Rabbit Launcher icon">
</p>

<h1 align="center">Rabbit Launcher</h1>
<p align="center"><strong>Your Minecraft: Java Edition experience, on Android.</strong></p>

<p align="center">
  <a href="https://github.com/khargosh-area/Rabbitlaunchs/actions"><img src="https://img.shields.io/badge/Builds-GitHub%20Actions-2088FF?logo=githubactions&logoColor=white" alt="GitHub Actions"></a>
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/Version-0.1--beta-orange" alt="Version 0.1 beta">
  <img src="https://img.shields.io/badge/Architecture-ARM64-informational" alt="ARM64">
</p>

<p align="center">
  <a href="https://github.com/khargosh-area/Rabbitlaunchs/actions">Download development builds</a>
  ·
  <a href="https://github.com/khargosh-area/Rabbitlaunchs/issues">Report an issue</a>
  ·
  <a href="https://github.com/khargosh-area/Rabbitlaunchs">Source code</a>
</p>

---

## ✨ About

**Rabbit Launcher** is a customizable Android launcher for **Minecraft: Java Edition**. It is based on the MojoLauncher project, which in turn builds on PojavLauncher.

The goal is to make Java Edition easier to manage and play on Android, with Minecraft version and instance management, mod-loader installation, modpack support, renderer options and a launcher interface tailored for touch devices.

Rabbit Launcher is an independent community project. It is not an official Minecraft or Mojang product.

## 🚀 Features

### Minecraft and instances
- Create and manage separate launcher instances.
- Download and launch Minecraft versions supported by the underlying launcher.
- Select a Java runtime for an instance.
- Manage installed versions and instance-specific game files.
- Create home-screen shortcuts for instances.

### Modding
- Fabric, Forge, NeoForge and Quilt installation workflows.
- LiteLoader support is experimental.
- Import modpacks in supported `.mrpack` and CurseForge ZIP formats.
- Manage mods and instance configuration files.
- Import compatible control layouts and files using Rabbit Bridge.

### Graphics and performance
- Choose from the renderers available in the build and for the selected Minecraft version.
- Configure launcher/game performance options.
- Mobile Glues support is available through the underlying launcher integration; compatibility depends on device, renderer and game/mod versions.
- Custom Java runtime support.

### Accounts and customization
- Integrated account management provided by the launcher base.
- Custom controls and touch-friendly play options.
- Per-instance settings and launch arguments.

### Rabbit additions
- **Rabbit Bridge v0.1:** import a `Rabbit_Profile.zip` containing `controls/`, `mods/` and/or `config/` folders into the currently selected instance. Existing files are skipped and archive paths/extracted sizes are checked. Only compatible control JSON layouts are imported; not every third-party format is converted.
- **Rabbit Member Cape:** a bundled default cape is assigned to local/offline accounts when no custom cape is set. This is a local launcher feature; it does **not** make the cape universally visible to vanilla Minecraft players or users of unrelated clients. Shared visibility requires a compatible cape system on the viewers' clients.

## 📱 Screenshots

<sub>Screenshots will be added as the current UI is finalized.</sub>

## 📦 Get Rabbit Launcher

Development builds are published through GitHub Actions:

**[Open Rabbit Launcher Actions](https://github.com/khargosh-area/Rabbitlaunchs/actions)**

1. Open the latest successful workflow run.
2. Scroll to **Artifacts**.
3. Download the APK artifact that matches the build.
4. Install it on a compatible Android device.

> [!NOTE]
> The current verified build is a **debug ARM64 APK** (version name `0.1-beta`, version code `31`). Debug builds are for testing and are not production-signed releases. A Google Play release is not currently available.

## 🛠 Build from source

### Requirements
- Android Studio or a compatible command-line Android build environment.
- JDK required by the project's Gradle/Android plugin setup.
- Android SDK and NDK versions configured by the project.
- Git with submodule support.
- An ARM64 Android device for the currently configured app ABI.

### Clone

```bash
git clone --recurse-submodules https://github.com/khargosh-area/Rabbitlaunchs.git
cd Rabbitlaunchs
```

If you already cloned without submodules:

```bash
git submodule update --init --recursive
```

### Build the current full debug variant

```bash
./gradlew :Rabbit_Launcher:assembleFullDebug --no-daemon
```

On Windows, use:

```powershell
.\\gradlew.bat :Rabbit_Launcher:assembleFullDebug --no-daemon
```

The ARM64 debug APK is generated at:

```text
Hyper_Launcher_v3/build/outputs/apk/full/debug/rabbit_launcher-0.1-beta-arm64-v8a.apk
```

The exact output name can change if the version settings or ABI configuration are changed. The current build configuration uses version name `0.1-beta`, version code `31`, and package ID `net.khargosh.rabbitlauncher.debug` for the debug variant.

## 🐇 Rabbit Bridge

Rabbit Bridge lets you transfer compatible profile files into the currently selected Minecraft instance.

Create a ZIP named `Rabbit_Profile.zip` with this structure:

```text
Rabbit_Profile.zip
├── controls/   # compatible control-layout JSON files
├── mods/       # .jar mods compatible with the selected instance
└── config/     # instance configuration files and folders
```

Open the Rabbit Bridge card from the launcher home screen, choose the target instance and select the ZIP. Existing files are skipped. Archive paths and extracted sizes are checked before import.

See [RABBIT_BRIDGE.md](RABBIT_BRIDGE.md) for details.

## 🧥 Rabbit Member Cape

Rabbit Launcher bundles a simple Rabbit Member cape asset. For local/offline accounts, the launcher applies it automatically when the account does not already have a custom cape path.

**Visibility note:** this is not a universal Minecraft cape service. Other players will not automatically see it in vanilla Minecraft. A shared cape ecosystem needs a compatible client-side mod or service on the other players' clients too.

## 📋 Compatibility

Compatibility depends on the Android device, Android version, Minecraft version, Java runtime, mod loader, mods and selected renderer.

- Rabbit Launcher targets Minecraft **Java Edition**, not Bedrock Edition.
- Not every Minecraft version or mod is guaranteed to work on every Android device.
- Mod-loader support depends on the loader's own compatibility with the selected Minecraft version and the launcher's Android environment.
- Renderer features and Mobile Glues compatibility vary by GPU and driver.
- LiteLoader is experimental.
- A successful launcher build does not guarantee that a particular game/modpack will launch or run correctly.

Check the project's issue tracker for device-specific reports: [GitHub Issues](https://github.com/khargosh-area/Rabbitlaunchs/issues).

## 🧭 Roadmap

The project is actively evolving. Planned work may include:

- Further polish to the Rabbit-branded launcher interface.
- Better first-run setup and clearer installation feedback.
- More reliable modpack/profile import and validation.
- Additional Rabbit Bridge compatibility and documentation.
- Cape-system research for compatible multiplayer visibility.
- Device testing and performance improvements.

Roadmap items are goals, not claims that the feature is already available.

## 🐛 Known issues

- Compatibility varies significantly between Android devices and Minecraft/mod versions.
- Some physical mice may have unusual or slow pointer movement.
- On the Holy GL4ES renderer, large texture atlases may appear distorted in some modpacks.
- Some third-party control layouts may not import without format conversion.
- Additional issues may exist; please report reproducible steps and attach relevant logs.

## 🔒 Privacy and security

- Only install APKs from project-controlled or otherwise trusted release sources.
- Do not share account passwords, access tokens or private logs publicly.
- Review imported ZIP contents and only import mods/configuration files you trust.
- Back up important worlds and instance data before changing mods, loaders or configuration files.
- Keep API credentials out of source control and public builds.

## 🤝 Contributing

Contributions, bug reports and documentation improvements are welcome.

When reporting a bug, include:
- Device model and Android version.
- Minecraft version and selected Java runtime.
- Renderer and mod-loader versions.
- Steps to reproduce the issue.
- Relevant launcher/game logs (remove personal information and tokens first).

For code changes, open a pull request with a clear description and reproducible test steps.

## 📜 License

Rabbit Launcher is distributed under the **GNU Lesser General Public License v3.0 (LGPL-3.0)**. See [LICENSE](LICENSE) for the license text.

Because this project is derived from MojoLauncher and PojavLauncher and includes other third-party components, preserve their notices and comply with each component's applicable license. See the upstream projects and their license files for details.

## 🙏 Credits

Rabbit Launcher builds on the work of:

- [MojoLauncher](https://github.com/MojoLauncher/MojoLauncher)
- [PojavLauncher](https://github.com/PojavLauncherTeam/PojavLauncher)
- The maintainers and contributors of the included open-source libraries and graphics/runtime components.

**Rabbit Launcher branding and Rabbit-specific additions:** Khargoshh  
**AI development assistance:** ChatGPT

---

<p align="center"><strong>Made for Java Edition on Android. Built by Khargoshh. 🐇</strong></p>
