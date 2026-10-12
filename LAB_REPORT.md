# Lab 3 — CI and Static Techniques

Repository: https://github.com/DavoKDD/se-lab  
Date: 13 October 2026  
Local branch: `bugfix`

## Scope and current status

The personal public repository was generated from `ftsrg-softeng/lab3` and cloned locally. Subsequent changes are local only. No changes have been pushed, no pull request has been created, and SonarQube Cloud has not been connected. This report records completed local work; the online evidence below still needs to be collected.

## Environment and initial commit

The environment uses OpenJDK 21.0.12.1 and the project's Maven wrapper (Maven 3.10.0). The project is open in Visual Studio Code. The initial personal repository commit is `d6c6b8a7d6ba77c8ededb54bbd2503ff05dec0b0`.

Required evidence still pending: Git Graph screenshot showing the initial commit and its metadata.

## Continuous integration

The GitHub Java with Maven template examined in the browser used Temurin Java 17 on `ubuntu-latest`, triggered by pushes to `main` and pull requests targeting `main`.

The locally prepared `.github/workflows/maven.yml` uses Temurin Java 21, compiles with `mvn -B compile --file pom.xml`, and tests/packages with `mvn -B verify --file pom.xml`. It runs on pushes to `main` or `bugfix`, pull requests targeting `main`, and manual dispatch. Its token has read-only repository contents access. The optional dependency submission step is not included in this local version, so it does not require write access. The assignment's dependency-graph exercise remains pending.

Baseline local result: **2 tests, 0 failures, 0 errors, 0 skipped; BUILD SUCCESS**. Both tests are in `GT4500Test`.

After fixes and regression tests: **5 tests, 0 failures, 0 errors, 0 skipped; BUILD SUCCESS**. `GT4500Test` contributes 2 tests and `TorpedoStoreTest` contributes 3.

These are local Maven results, not GitHub Actions results. Required evidence still pending: full browser screenshot containing the workflow URL and successful test output.

## Local and remote branches

The outdated TODO line was removed from README in local commit `686d43e`. The local `main` contains this commit; `origin/main` still points to the initial commit. The rejected push, remote workflow commit, fetch and merge exercise has not been performed because uploads were paused. No rejected push or merge result is claimed.

Required evidence still pending: Git Graph after the remote/local merge exercise.

## Findings in TorpedoStore.java

The following findings were identified by inspecting the source. SonarQube for IDE has not yet been run, so these are not claimed as captured tool results.

| Finding | Assessment | Fix |
| --- | --- | --- |
| An `IllegalArgumentException` is constructed but never thrown. | Genuine defect. Invalid firing requests continue through the method and can alter the stock. | Throw the exception. Commit `5bc0f00`. |
| `torpedoCount =- numberOfTorpedos` assigns a negative value instead of subtracting from the current stock. | Genuine defect. A valid shot incorrectly empties the launcher. | Use `-=`. Commit `36ecb1e`. |
| A new `Random` instance is created for every firing attempt. | A real efficiency/maintainability issue; it is less severe than the stock and validation defects. A persistent generator is sufficient. | Keep one final instance per store. Commit `d6d2285`. |

An interesting Java rule to inspect is S2095, which concerns closing resources such as streams. Resource leaks can accumulate even when individual operations appear to work. This rule has been selected for discussion, not verified in the installed IDE rule list yet.

## Code review

The three fixes are separate local commits on `bugfix`. The additional tests check repeated firing, stock depletion, invalid arguments, and firing from an empty store. They assume the default failure rate (`IVT_RATE` unset or zero).

No partner is available yet. A partner review, requested changes, follow-up commits, approval and the attendance proof comment remain pending. The pull request must remain unmerged until the assignment's review and analysis requirements are satisfied.

Required evidence still pending: PR information and successful CI, review conversation and requested changes, final checks and merged status when permitted.

## SonarQube Cloud

No cloud analysis has been run. Initial analysis screenshots, the list of actual main-branch findings, their assigned statuses, and confirmation that the PR fixes resolve the findings must be added after connecting the project.

The use of `Random` should be assessed in context: this is a simulation of firing failure, not a security-sensitive random value. If the actual hotspot description matches this usage, it can be reviewed as Safe with that justification. No hotspot has been marked Safe yet. Remaining maintainability findings must be evaluated individually; none have been invented or assigned statuses in this report.

## Badges and license

The project uses the MIT license (`LICENSE`). The following Markdown has been added locally:

```markdown
[![Java CI with Maven](https://github.com/DavoKDD/se-lab/actions/workflows/maven.yml/badge.svg)](https://github.com/DavoKDD/se-lab/actions/workflows/maven.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
```

The workflow badge will become meaningful after the workflow is published and runs. Required evidence still pending: screenshot of both rendered badges.
