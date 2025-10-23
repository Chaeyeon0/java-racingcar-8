package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.MovementStrategy;
import racingcar.domain.RandomMovementStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameController {

    public void run() {
        try {

            String carNamesInput = InputView.readCarNames();
            int tryCount = InputView.readTryCount();

            Cars cars = new Cars(carNamesInput);
            MovementStrategy strategy = new RandomMovementStrategy();

            System.out.println("실행 결과");

            for (int i = 0; i < tryCount; i++) {
                cars.moveAll(strategy);
                OutputView.printRoundResult(cars.getCars());
            }
            List<Car> winners = cars.findWinners();
            OutputView.printWinners(winners);

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw e;
        }
    }
}
