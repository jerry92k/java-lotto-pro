package lottogame;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

	private static final int START_INCLUSIVE_NUMBER = 1;    /* 로또 번호 최소값 */
	private static final int END_EXCLUSIVE_NUMBER = 45;    /* 로또 번호 최대값 */
	private static final int SIZE_OF_LOTTERY_NUMBERS = 6;

	@Test
	public void makeLottoTicket_로또_티켓_한장_발급() {
		LottoTicket lottoTicket = LottoTicket.makeLottoTicket();

		Assertions.assertThat(lottoTicket.numOfNumbersInGroup())
			.isEqualTo(SIZE_OF_LOTTERY_NUMBERS);

		lottoTicket.getLottoNumberGroup().getLottoNumbers()
			.forEach(lottoNumber -> assertThat(lottoNumber.getNumber())
				.isBetween(START_INCLUSIVE_NUMBER, END_EXCLUSIVE_NUMBER));
	}
}