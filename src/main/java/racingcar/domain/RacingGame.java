package racingcar.domain;

import java.util.List;
import racingcar.view.OutputView;

public class RacingGame {
    private final Cars cars;
    private final MovementStrategy strategy;

    public RacingGame(String carNamesInput, MovementStrategy strategy) {
        this.cars = new Cars(carNamesInput);
        this.strategy = strategy;
    }

    public void play(int rounds) {
        for (int i = 0; i < rounds; i++) {
            cars.moveAll(strategy);
            OutputView.printRoundResult(cars.getCars());
        }
    }

    public List<Car> getWinners() {
        return cars.findWinners();
    }
}
