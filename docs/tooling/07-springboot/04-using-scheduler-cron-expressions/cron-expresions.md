# Cron expressions

A Cron expression consists of six sequential fields:- second, minute, hour, day of month, month, day(s) of week

![cron_expressions.png](_img%2Fcron_expressions.png)

cron job time setting
declared as follows:- @Scheduled(cron = “* * * * * *”)

![cron_expressions_table.png](_img%2Fcron_expressions_table.png)

```kotlin
    @Scheduled(cron = "\${app.usecase.cron.scheduled}")
    @SchedulerLock(
        name = "use_case_create_accounts",
        lockAtLeastFor = "\${app.usecase.cron.shedLock.lockAtLeast}",
    )
    operator fun invoke() {
        try {
            LOGGER.info("[UseCase] Starting...")
            if (!featureToggleService.isUseCaseEnabled()) {
                LOGGER.info("[UseCase] disabled by feature flag")
                return
            }
            createStatementService.invoke()
        } catch (ex: Exception) {
            LOGGER.error("[UseCase] Finished with errors: $ex")
        }
    }
```


Examples:-

1. @Scheduled(cron = “*/1 * * * * ?”) => For 1 second

2. @Scheduled(cron = “0 */1 * * * ?”) => For 1 minute

3. @Scheduled(cron = “0 0 */1 * * ?”) => For 1 hour

4. @Scheduled(cron = “0 0 0 */1 * ?”) => For every day

5. @Scheduled(cron = “0 0 0 1 1 *”) => It scheduled as 1 date of 1st month(january) every year

6. @Scheduled(cron = “0 15 10 * * *”) => It scheduled as at 10:15 a.m. every day(every 0th second, 15th minute, 10th hour, every day)

7. @Schedule(cron = “0 0 8 ? 4 *”) => It scheduled as 8 am every day in 4th month(April) every year

8. @Schedule(cron = “0 0 8–11 * * *”) => It scheduled as 8 am to 11 am means at 8 am, 9 am, 10 am, 11 am for every day

9. @Schedule(cron = “0 0 9 14 2 SUN,TUE”) => It scheduled as 9 am 14th day of 2nd month(February), if given 14th day on either Sunday or Tuesday(if there is no Sunday or Tuesday on 14th Feb so it’s not execute)

10. @Schedule(cron = “0 30 9 8–14 3 0”) => It scheduled as 9:30 AM on every Sunday between the 8th and 14th day of March.

Note:- By default, Spring will use the server’s local time zone for the cron expression. However, we can use the zone attribute to change this timezone

Example:- @Scheduled(cron = “0 15 10 15 * ?”, zone = “Europe/Paris”)

Use Of fixedRate and fixed delayed for scheduling time
Fixed Rate scheduler is used to execute the tasks at the specific time. It does not wait for the completion of previous task

Example:- @Scheduled(fixedRate = 1000)

Fixed Delay scheduler is used to execute the tasks at a specific time. It should wait for the previous task completion.

Example:- @Scheduled(fixedDelay = 1000)

Note:- We also set the initialDelay for the execution of method for first time, Example:- @Scheduled(fixedDelay = 1000, initialDelay = 1000)

Here we have set the initialDelay to delay the first execution of the method by 1000 milliseconds or 1 seconds.