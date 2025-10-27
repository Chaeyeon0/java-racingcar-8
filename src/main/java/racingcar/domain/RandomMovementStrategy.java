package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMovementStrategy implements MovementStrategy {

    private static final int MIN_MOVABLE_NUMBER = 4;

    @Override
    public boolean shouldMove() {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        return randomNumber >= MIN_MOVABLE_NUMBER;
    }
}
