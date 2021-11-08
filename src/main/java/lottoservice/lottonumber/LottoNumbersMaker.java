package lottoservice.lottonumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lottoservice.exception.InvalidLottoFormatException;

/**
 * 로또 번호 생성 클래스
 */
public class LottoNumbersMaker {
	public static final int SIZE_OF_LOTTERY_NUMBERS = 6;    /* 로또 번호 갯수 */

	protected static final int START_INCLUSIVE_NUMBER = 1;    /* 로또 번호 최소값 */
	protected static final int END_EXCLUSIVE_NUMBER = 45;    /* 로또 번호 최대값 */

	private static String ERROR_MESSAGE_INVALID_INPUT_FORMAT = "입력형식이 올바르지 않습니다. 로또번호 숫자와 구분자(, )를 형식에 맞게 입력해주세요.";
	private static final List<LottoNumber> lottoNumberCandidates = getLottoNumberCandidates();    /* 1~45사이의 수를 로또 번호로 포장하여 저장 */

	private ArrangeManipulator arrangeManipulator;

	public LottoNumbersMaker(ArrangeManipulator arrangeManipulator) {
		this.arrangeManipulator = arrangeManipulator;
	}

	private static List<LottoNumber> getLottoNumberCandidates() {
		return IntStream.range(START_INCLUSIVE_NUMBER, END_EXCLUSIVE_NUMBER + 1)
			.mapToObj(LottoNumber::new).collect(Collectors.toList());
	}

	public List<LottoNumber> makeLottoNumbers() {
		return arrangeManipulator.getRandomElements(lottoNumberCandidates,SIZE_OF_LOTTERY_NUMBERS);
	}

	public List<LottoNumber> makeLottoNumbers(List<Integer> numbers) {
		return numbers.stream().sorted().map(LottoNumber::new).collect(Collectors.toList());
	}

	public List<LottoNumber> makeLottoNumbers(String numbersTxt) {
		List<Integer> numbers = parseTextToNumbers(numbersTxt);
		return makeLottoNumbers(numbers);
	}

	private List<Integer> parseTextToNumbers(String numbersTxt) {
		try {
			return Arrays.stream(numbersTxt.split(", "))
				.map(it -> Integer.parseInt(it))
				.collect(Collectors.toList());
		} catch (NumberFormatException ex) {
			throw new InvalidLottoFormatException(ERROR_MESSAGE_INVALID_INPUT_FORMAT);
		}
	}
}