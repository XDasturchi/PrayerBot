package apiController;

import function.Function;
import updateHandler.UpdateHandler;
import user.User;
import user.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class SenderController {

    TimerRepository timerRepository = TimerRepository.getInstance();
    Function function = Function.getInstance();
    UserRepository userRepository = UserRepository.getInstance();

    public void run() {

        TimeTimer timer = timerRepository.getTimer();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        LocalTime bomdod = timer.getBomdod();
        LocalTime xufton = timer.getXufton();
        LocalTime asr = timer.getAsr();
        LocalTime shom = timer.getShom();
        LocalTime peshin = timer.getPeshin();

        // Bomdod
        if (checker(bomdod)) {
            executor.schedule(() -> {

                ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);

                for (User user : userRepository.getAll()) {
                    executorService.submit(() -> {

                        if (user.isMorningStatus()) {
                            function.sendMessage(user.getUserId(), "<b>\uD83D\uDC4B Assalomu Alaykum - Bomdod kirdi!\n\n\uD83D\uDCD6 Bomdod Namozni O'qishingiz Mumkin</b>", null);
                        }
                    });
                }

            }, getDelay(bomdod), TimeUnit.SECONDS);
        }

        // Peshin
        if (checker(peshin)) {
            executor.schedule(() -> {

                ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);

                for (User user : userRepository.getAll()) {
                    executorService.submit(() -> {

                        if (user.isNoonStatus()) {
                            function.sendMessage(user.getUserId(), "<b>\uD83D\uDC4B Assalomu Alaykum - Peshin kirdi!\n\n\uD83D\uDCD6 Peshin Namozni O'qishingiz Mumkin</b>", null);
                        }
                    });
                }

            }, getDelay(peshin), TimeUnit.SECONDS);
        }

        // Asr
        if (checker(asr)) {
            executor.schedule(() -> {

                ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);

                for (User user : userRepository.getAll()) {
                    executorService.submit(() -> {

                        if (user.isAfternoonStatus()) {
                            function.sendMessage(user.getUserId(), "<b>\uD83D\uDC4B Assalomu Alaykum - Asr kirdi!\n\n\uD83D\uDCD6 Asr Namozni O'qishingiz Mumkin</b>", null);
                        }
                    });
                }

            }, getDelay(asr), TimeUnit.SECONDS);
        }

        // Shom
        if (checker(shom)) {
            executor.schedule(() -> {

                ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);

                for (User user : userRepository.getAll()) {
                    executorService.submit(() -> {

                        if (user.isEveningStatus()) {
                            function.sendMessage(user.getUserId(), "<b>\uD83D\uDC4B Assalomu Alaykum - Shom kirdi!\n\n\uD83D\uDCD6 Shom Namozni O'qishingiz Mumkin</b>", null);
                        }
                    });
                }

            }, getDelay(shom), TimeUnit.SECONDS);
        }

        // Xufton
        if (checker(xufton)) {
            executor.schedule(() -> {

                ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);

                for (User user : userRepository.getAll()) {
                    executorService.submit(() -> {

                        if (user.isNightStatus()) {
                            function.sendMessage(user.getUserId(), "<b>\uD83D\uDC4B Assalomu Alaykum - Xufton kirdi!\n\n\uD83D\uDCD6 Xufton Namozni O'qishingiz Mumkin</b>", null);
                        }
                    });
                }

            }, getDelay(xufton), TimeUnit.SECONDS);
        }


        executor.shutdown();
    }

    private long getDelay(LocalTime time) {
        int count = 0;

        LocalTime localTime = LocalTime.now();

        while (!(time.getMinute() == localTime.getMinute() && time.getHour() == localTime.getHour() && time.getSecond() == localTime.getSecond())) {
            count++;
            localTime = localTime.plusSeconds(1);
        }

        return count;
    }

    private boolean checker(LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.now();

        while (!(time.getSecond() == dateTime.getSecond() && time.getHour() == dateTime.getHour() && time.getMinute() == dateTime.getMinute())) {
            dateTime = dateTime.plusSeconds(1);
        }


        return dateTime.getDayOfMonth() == LocalDateTime.now().getDayOfMonth();
    }

}
