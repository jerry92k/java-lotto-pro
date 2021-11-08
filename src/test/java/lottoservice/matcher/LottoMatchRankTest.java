package lottoservice.matcher;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMatchRankTest {

	@DisplayName("로또_당첨_랭크_매칭갯수_검증")
	@ParameterizedTest
	@CsvSource(value = {"SIX_POINT,6","FIVE_POINT_AND_BONUS,5", "FIVE_POINT,5", "FOUR_POINT,4", "THREE_POINT,3", "TWO_POINT,2", "ONE_POINT,1",
		"ZERO_POINT,0"}, delimiter = ',')
	public void countofmatch(LottoMatchRank rank, int countOfMatch) {
		assertThat(rank.getCountOfMatch()).isEqualTo(countOfMatch);
	}

	@DisplayName("로또_당첨_랭크_상금_검증")
	@ParameterizedTest
	@CsvSource(value = {"SIX_POINT,2000000000","FIVE_POINT_AND_BONUS,30000000", "FIVE_POINT,1500000", "FOUR_POINT,50000", "THREE_POINT,5000",
		"TWO_POINT,0", "ONE_POINT,0", "ZERO_POINT,0"}, delimiter = ',')
	public void winningMoney(LottoMatchRank rank, int winningMoney) {
		assertThat(rank.getWinningMoney()).isEqualTo(winningMoney);
	}

	@DisplayName("매칭갯수와 보너스번호 매칭 유무로 랭크 추출")
	@ParameterizedTest
	@CsvSource(value = {"SIX_POINT,6,false","FIVE_POINT_AND_BONUS,5,true", "FIVE_POINT,5,false", "FOUR_POINT,4,false",
		"THREE_POINT,3,false", "TWO_POINT,2,false", "ONE_POINT,1,false","ZERO_POINT,0,false"}, delimiter = ',')
	public void valueOf(LottoMatchRank rank, int countOfMatch, boolean matchBonus) {
		assertThat(LottoMatchRank.valueOf(countOfMatch,matchBonus)).isEqualTo(rank);
	}
}