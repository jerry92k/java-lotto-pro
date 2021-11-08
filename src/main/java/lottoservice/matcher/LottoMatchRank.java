package lottoservice.matcher;

import java.util.Arrays;
import java.util.Optional;

/**
 * 당첨번호와 비교하여 발생할 수 있는 결과 케이스를 enum으로 정의
 */
public enum LottoMatchRank {

	SIX_POINT(6, 2_000_000_000),
	FIVE_POINT_AND_BONUS(5, 30_000_000),
	FIVE_POINT(5, 1_500_000),
	FOUR_POINT(4, 50_000),
	THREE_POINT(3, 5_000),
	TWO_POINT(2, 0),
	ONE_POINT(1, 0),
	ZERO_POINT(0, 0);

	private int countOfMatch;    /* 맞춘 갯수 */
	private int winningMoney;    /* 상금 */
	private static String ERROR_MESSAGE_UNEXPECTED_COUNTOFMATCH = "유효하지 않는 당첨 갯수가 발생하였습니다.";

	LottoMatchRank(int countOfMatch, int winningMoney) {
		this.countOfMatch = countOfMatch;
		this.winningMoney = winningMoney;
	}

	public int getCountOfMatch() {
		return countOfMatch;
	}

	public int getWinningMoney() {
		return winningMoney;
	}

	public static LottoMatchRank valueOf(final int countOfMatch, final boolean matchBonus) {
		LottoMatchRank[] ranks = LottoMatchRank.values();
		Optional<LottoMatchRank> foundRank = Arrays.stream(ranks)
			.filter(rank -> rank.isMatchRank(countOfMatch, matchBonus)).findFirst();
		return foundRank.orElseThrow(() -> new IllegalStateException(ERROR_MESSAGE_UNEXPECTED_COUNTOFMATCH));
	}

	public boolean isMatchRank(int inputCountOfMatch, boolean matchBonus) {
		if(isSecondPrize()){
			return matchBonus && isSameMatchCount(inputCountOfMatch);
		}
		return getCountOfMatch() == inputCountOfMatch;
	}

	public boolean isSecondPrize() {
		return this == LottoMatchRank.FIVE_POINT_AND_BONUS;
	}

	public boolean isSameMatchCount(int inputCountOfMatch){
		return getCountOfMatch() == inputCountOfMatch;
	}
}
