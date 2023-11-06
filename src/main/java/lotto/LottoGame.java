package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoGame {
    // 로또 발행
    public int buyLotto(int amount) {
        int draws = amount/1000;
        System.out.println(draws+"개를 구매했습니다.");
        return draws;
    }

    // 당첨 번호 추첨
    public Lotto drawLotto(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(numbers);
        System.out.println(numbers);

        return new Lotto(numbers);
    }

    // 당첨 번호
    public Lotto inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        List<Integer> numbers = Arrays.stream((input.split(","))).map(Integer::valueOf).toList();
        Lotto lotto = new Lotto(numbers);

        return lotto;
    }

    // 보너스 번호 입력
    public int inputBonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = Integer.parseInt(Console.readLine().strip());

        return bonus;
    }

    // 당첨 번호 비교
    public int numberComparison(Lotto draw, Lotto win) {
        int count = 0;
        for(Integer number : win.getNumbers()){
            if (draw.contains(number)) {
                count++;
            }
        }
        return count;
    }

    // 보너스 비교
    public boolean bonusComparison(Lotto draw, int bonus) {
        return draw.contains(bonus);
    }

    // 당첨 현황
    public int[] winningResult(List<Lotto> draws, Lotto win, int bonus) {
        int[] winningList = new int[5];
        for (Lotto draw : draws){
            int count = numberComparison(draw, win);
            if (count > 2) {
                winningList[count-3]++;
            }
            if (count == 5 && bonusComparison(draw, bonus)) {
                winningList[2]--;
                winningList[3]++;
            }
        }
        return winningList;
    }


    // 수익률 출력
    // 예외 상황 출력
}
