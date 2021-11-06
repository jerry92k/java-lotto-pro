package lottoservice.lottonumber;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lottoservice.lottonumber.LottoNumber;
import lottoservice.lottonumber.LottoNumbersMaker;

public class LottoNumbersMakerTest {

	private static final int START_INCLUSIVE_NUMBER = 1;    /* 로또 번호 최소값 */
	private static final int END_EXCLUSIVE_NUMBER = 45;    /* 로또 번호 최대값 */
	private static final int SIZE_OF_LOTTERY_NUMBERS = 6;

	@Test
	public void makeLottoNumbers_1에서45사이_6개의_숫자생성() {
		List<LottoNumber> lottoNumbers = LottoNumbersMaker.makeLottoNumbers();
		assertThat(lottoNumbers.size()).isEqualTo(SIZE_OF_LOTTERY_NUMBERS);
		lottoNumbers
			.forEach(lottoNumber -> assertThat(lottoNumber.getNumber())
				.isBetween(START_INCLUSIVE_NUMBER, END_EXCLUSIVE_NUMBER));
	}

	@Test
	public void makeLottoNumbers_정수리스트_인자전달() {
		List<Integer> numbers = Arrays.asList(1, 10, 20, 30, 40, 45);
		List<LottoNumber> lottoNumbers = LottoNumbersMaker.makeLottoNumbers(numbers);
		assertThat(lottoNumbers.size()).isEqualTo(SIZE_OF_LOTTERY_NUMBERS);
		for (int number : numbers) {
			assertThat(lottoNumbers.contains(LottoNumber.valueOf(number)));
		}
	}

	@Test
	public void makeLottoNumbers_문자열_인자전달() {
		String numbertext = "1, 15, 20, 25, 30, 45";
		List<Integer> numbers = Arrays.asList(1, 15, 20, 25, 30, 45);
		List<LottoNumber> lottoNumbers = LottoNumbersMaker.makeLottoNumbers(numbertext);
		assertThat(lottoNumbers.size()).isEqualTo(SIZE_OF_LOTTERY_NUMBERS);
		for (int number : numbers) {
			assertThat(lottoNumbers.contains(LottoNumber.valueOf(number)));
		}
	}
}
