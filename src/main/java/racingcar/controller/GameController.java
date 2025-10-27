package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.domain.RandomMovementStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameController {

    public void run() {
        try {
            String carNamesInput = InputView.readCarNames();
            int tryCount = InputView.readTryCount();

            RacingGame game = new RacingGame(carNamesInput, new RandomMovementStrategy());

            System.out.println("실행 결과");
            game.play(tryCount);

            OutputView.printWinners(game.getWinners());

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw e;
        }
    }
}

