# Lab 3 — CI and Static Techniques

Repository: https://github.com/DavoKDD/se-lab  
Pull request: https://github.com/DavoKDD/se-lab/pull/1  
Date: 13 October 2026

## Setup

The repository was created from the `ftsrg-softeng/lab3` template and cloned to this computer. The project is open in VS Code. It uses Java 21 and the Maven wrapper. The first commit is `d6c6b8a7d6ba77c8ededb54bbd2503ff05dec0b0`.

The Git Graph screenshots and the SonarQube for IDE part still need to be added.

## GitHub Actions

The Java with Maven template used Java 17. The workflow was changed to Java 21 because the project needs it. It runs on `ubuntu-latest` when there is a push to `main` or a pull request to `main`. It can also be started manually.

The command is `mvn -B verify --file pom.xml`. Maven builds the project and runs the tests. The next step updates the dependency graph.

The first run failed in the dependency graph step with error 403: `Resource not accessible by integration`. After enabling Read and write permissions for Actions and enabling Dependency Graph, the second attempt passed. The dependency snapshot was created successfully. The [successful run](https://github.com/DavoKDD/se-lab/actions/runs/38324869409) took 36 seconds. Its Maven log shows **2 tests, 0 failures and 0 errors**.

There were also warnings about old action versions. They did not stop the build.

## Git branches

The old TODO line in README was removed in local commit `686d43e`. Meanwhile, the workflow was added on GitHub in commit `7f29c43`. The push from the local branch was rejected because the remote branch had a new commit.

After fetching the remote changes, `origin/main` was merged into local `main`. The merge commit is `09402a4`. The common ancestor was the first commit, `d6c6b8a`. The result was pushed to GitHub.

Main was then merged into `bugfix` in commit `0277379`. There was a conflict in the workflow file. The version from main was kept, including Java 21 and the dependency graph step.

## Problems in TorpedoStore

The source code was checked first. Later, SonarQube Cloud found the same three problems on main.

| Problem | Why it is a problem | Change |
| --- | --- | --- |
| The exception was created but not thrown. | The method continued even when the number of torpedoes was invalid. | Added `throw` in commit `5bc0f00`. |
| The code used `=-` instead of `-=`. | It set the stock to a negative number instead of subtracting the fired torpedoes. | Changed it to `-=` in commit `36ecb1e`. |
| A new `Random` object was created for each shot. | The same generator can be used again. Creating a new one each time is unnecessary. | Added one final generator field in commit `d6d2285`. |

All three findings are valid. Sonar marked the Random issue as High and the other two as Medium for reliability.

An interesting Java rule is S2095, about closing resources. For example, an open stream should be closed after use. Otherwise, the program can keep resources that it no longer needs. This rule still needs to be checked in the IDE rule list.

## Tests

The original project had 2 tests. Three more tests were added for `TorpedoStore`. They check repeated shots, an empty store and invalid numbers of torpedoes. They also check that an invalid request does not change the stock.

After the fixes, the local result was **5 tests, 0 failures and 0 errors**. These tests use the default failure rate, so `IVT_RATE` must be unset or zero.

To run them:

```sh
sh ./mvnw verify
```

## Pull request and review

PR #1 contains the three fixes in separate commits and the new tests. DavoKDD is the assignee. A review was requested from am11001.

The GitHub Actions check and the SonarQube Cloud check passed. The PR is still open. The partner review, a change after review, final approval and the attendance proof still need to be completed. The partner's PR also needs a review.

## SonarQube Cloud

The project was imported into organization `davokdd` with the Free plan. The GitHub app has access only to `se-lab`. Automatic import of future repositories is off.

The first main analysis checked commit `09402a4`. It found 3 reliability issues and 3 maintainability issues. Reliability had rating D and maintainability had rating A. The first quality gate was Not computed; the page says that the next analysis will update it.

The maintainability findings were:

- `GT4500.fireTorpedo`: cognitive complexity is 25, above the limit of 15. This is a useful warning because the method has many branches and is harder to follow.
- `GT4500.java`: a TODO comment is still present. It points to unfinished work, so the finding is valid.
- `GT4500Test.java`: the test methods have unnecessary visibility modifiers. JUnit 5 does not need them, so they can be removed.

A request to set these three findings to Confirmed was submitted. The saved page still needs to be checked before recording their final status.

The PR analysis passed the quality gate with **0 new issues**. Sonar also reported **0 security hotspots**. There was no Random hotspot to mark Safe in this analysis. Random is used here to simulate a firing failure, not to protect secret data.

Sonar showed 0.0% coverage on new code. This does not mean that the Maven tests failed: coverage results have not been imported into the automatic cloud analysis. The three original problems should be checked again on main after the PR is merged.

## Badges and license

The project has an MIT license. README contains these two badges:

```markdown
[![Java CI with Maven](https://github.com/DavoKDD/se-lab/actions/workflows/maven.yml/badge.svg)](https://github.com/DavoKDD/se-lab/actions/workflows/maven.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
```

The badge screenshot still needs to be added.

## What is left

The report is not final yet. The remaining parts are the IDE analysis, missing screenshots, partner review, attendance proof and the final merge after approval. The final results should be added when these steps are finished.
