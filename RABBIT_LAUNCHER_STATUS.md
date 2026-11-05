# Rabbit Launcher — project status

This repository is being presented as Rabbit Launcher, a conservative community rebrand of the existing Hyper Launcher 3 / PojavLauncher-derived Android Minecraft Java launcher.

## Current verified state
- README.md updated with Rabbit Launcher naming and project overview.
- README_EN.md updated with Rabbit Launcher naming and build instructions.
- Original LICENSE retained.
- Existing Gradle root files and upstream submodule declarations retained.
- Native launcher source/modules are not yet present in this repository's main tree.

## Important
This repository is not yet a complete, buildable Rabbit Launcher source checkout. The upstream Android module and its required submodules/native dependencies still need to be imported from the supplied upstream source archive/repositories. No APK has been built or tested from this repository yet.

## Rebranding policy
Keep internal package names, native bridge symbols, submodule paths, and launcher engine unchanged unless a complete migration and build test demonstrates that a change is safe. Change user-facing app name, icon, splash and minor accent styling only.

## Upstream credits
Based on Hyper Launcher 3, MojoLauncher, and PojavLauncher. Preserve all applicable upstream copyright notices, license files, and third-party attributions.
