package lottoservice.lottoticket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lottoservice.exception.InvalidLottoFormatException;
import lottoservice.lottonumber.LottoNumber;
import lottoservice.lottonumber.LottoNumbersMaker;

/**
 * 자동으로 생성된 로또 번호 리스트를 가지는 로또 티켓 클래스
 * LottoNumber 클래스를 List 자료구조 묶음으로 다루는 일급 컬렉션
 */
public class LottoTicket {

	private static String ERROR_MESSAGE_INVALID_LOTTO_FORMAT = "로또는 6개의 중복없는 숫자여야 합니다.";

	private List<LottoNumber> lottoNumbers;

	private LottoTicket() {
	}

	public LottoTicket(List<LottoNumber> lottoNumber) {
		validateTicketRule(lottoNumber);
		this.lottoNumbers = lottoNumber;
	}

	private void validateTicketRule(List<LottoNumber> lottoNumber) {
		if (!isCorrectSize(lottoNumber)) {
			throw new InvalidLottoFormatException(ERROR_MESSAGE_INVALID_LOTTO_FORMAT);
		}
	}

	private boolean isCorrectSize(List<LottoNumber> lottoNumber) {
		return lottoNumber.stream().distinct().count() == LottoNumbersMaker.SIZE_OF_LOTTERY_NUMBERS;
	}

	public List<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}

	public int getNumOfNumbersInGroup() {
		return lottoNumbers.size();
	}

	public boolean hasLottoNumber(LottoNumber lottoNumber){
		return lottoNumbers.contains(lottoNumber);
	}

	@Override
	public String toString() {
		return lottoNumbers.stream()
			.map(lottoNumber -> lottoNumber.toString())
			.collect(Collectors.joining(", ", "[", "]"));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		return hasAllSameNumbers((LottoTicket)o);
	}

	private boolean hasAllSameNumbers(LottoTicket that) {
		return that.getLottoNumbers().stream()
			.filter(it->this.hasLottoNumber(it))
			.count() == LottoNumbersMaker.SIZE_OF_LOTTERY_NUMBERS;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLottoNumbers());
	}
}
