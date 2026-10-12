# Как продолжить лабораторную

Все изменения пока локальные. Открытый GitHub-репозиторий содержит только исходный шаблон. Не нажимайте Sync Changes или Publish Branch, пока не будете готовы отправить код.

## Сейчас в VS Code

Открыта папка `se-lab`. Рабочая ветка — `bugfix`. Откройте `LAB_REPORT.md` и используйте Markdown Preview для просмотра отчёта.

Для запуска тестов в терминале VS Code из корня проекта:

```sh
MAVEN_USER_HOME="$PWD/../../work/m2" sh ./mvnw -B -Dmaven.repo.local="$PWD/../../work/m2/repository" verify
```

При переносе проекта на другой компьютер можно использовать обычное `sh ./mvnw verify` или `mvn verify`. Тесты рассчитаны на отключённую симуляцию сбоев: переменная `IVT_RATE` должна отсутствовать или быть равна 0.

В VS Code установите через Extensions: Extension Pack for Java, Git Graph, SonarQube for IDE. Если включён Restricted Mode, проверьте исходники и разрешите доверие только этой папке через Manage Workspace Trust, чтобы включить полный Java-анализ. Откройте `TorpedoStore.java`, затем Problems и Output → SonarQube for IDE.

Чтобы увидеть исходные замечания, временно откройте исходный файл из первого коммита через Git Graph. Не отменяйте исправления в рабочей ветке. Сделайте требуемый скриншот начального коммита в Git Graph. Сохраните реальные результаты Sonar в отчёт.

## Позже: GitHub Actions и упражнение fetch/merge

1. На GitHub откройте Actions → New workflow → Java with Maven. Выберите Java 21. Сохраните файл `.github/workflows/maven.yml` в удалённом `main`. Для точного выполнения задания используйте исходный шаблон со step Update dependency graph.
2. Запустите workflow. Если dependency submission не имеет прав, по заданию понадобится разрешение записи для workflow и включение Dependency Graph в Settings. Это расширяет права Actions: выполняйте осознанно. Проверьте успешный результат и сделайте полный скриншот браузера с URL и количеством тестов.
3. В локальном терминале выполните:

```sh
git switch main
git push origin main
```

Ожидается отказ: удалённый main содержит новый CI-коммит. Не используйте force push.

```sh
git fetch origin
git log --graph --oneline --decorate --all
git merge origin/main
git push origin main
```

Сделайте скриншот Git Graph после merge. Общий предок веток до merge — исходный коммит `d6c6b8a`.

4. Вернитесь в ветку исправлений и внесите main:

```sh
git switch bugfix
git merge main
```

Возможен конфликт в `.github/workflows/maven.yml`: локально уже подготовлена версия без dependency submission, а на GitHub вы создали учебный шаблон с этим шагом. Для точного выполнения задания сохраните Java 21, `mvn verify` и dependency submission из удалённого варианта. Завершите merge только после проверки файла и тестов.

## Позже: PR, напарник и SonarQube

```sh
git push -u origin bugfix
```

Создайте PR из bugfix в main, кратко опишите три исправления и тесты, назначьте себя Assignee. Дождитесь зелёного CI, сохраните скриншот.

Найдите напарника. Добавьте его в Collaborators и запросите review. Напарник должен запросить изменение, вы внесёте его отдельным коммитом, затем он одобрит PR. Выполните взаимный review его PR. Скопируйте настоящий комментарий github-actions[bot] в Moodle. Пока не сливайте PR.

Подключите SonarQube Cloud через GitHub. При выдаче доступа выберите только se-lab, Free plan и Previous version. Изучите Main Branch и Pull Requests. Сохраните начальный анализ и фактические findings. Для Random прочитайте hotspot и обоснуйте Safe только если он относится к симуляции. Для каждого замечания укажите в отчёте суть, своё мнение и фактически назначенный статус.

После одобрения напарника и успешных проверок слейте PR. Сохраните финальные проверки, статус PR и отображение badges. Дополните LAB_REPORT.md недостающими реальными скриншотами и результатами. Текущий файл — честный черновик, а не готовый отчёт о выполненных онлайн-этапах.
