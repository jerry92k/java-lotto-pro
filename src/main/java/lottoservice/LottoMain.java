package lottoservice;

import lottoservice.exception.InvalidLottoFormatException;
import lottoservice.exception.InvalidMoneyException;
import lottoservice.lottoticket.LottoTicketIssuer;
import lottoservice.lottoticket.LottoTickets;
import lottoservice.matcher.LottoMatchResult;
import lottoservice.matcher.LottoMatcher;
import lottoservice.matcher.LottoWinningNumbers;
import lottoservice.ui.InputView;
import lottoservice.ui.ResultView;

public class LottoMain {

	private static String GUIDE_MESSAGE_ENTER_INPUT_AMOUNT = "구매금액을 입력해주세요.";
	private static String GUIDE_MESSAGE_ENTER_LAST_WEEK_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해주세요.";
	private static String RESULT_MESSAGE_MATCH_STATISTICS_START_LINE = "당첨통계\n---------";
	private static String RESULT_MESSAGE_MATCH_PROFIT_STATEMENT = "총 수익률은 %s 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미)\n";

	public static void main(String[] args) {
		LottoMain lottoMain = new LottoMain();
		lottoMain.startLottoMain();
	}

	public void startLottoMain() {
		LottoTickets lottoTickets = buyLottoTickets();
		showTickets(lottoTickets);
		ResultView.pringNewLine();
		LottoWinningNumbers lottoWinningNumbers = getLastWeekWinningNumbers();
		ResultView.pringNewLine();
		showMatchLottoWinningResult(lottoWinningNumbers, lottoTickets);
	}

	private LottoTickets buyLottoTickets() {
		try {
			String inputAmount = inputAmountForBuyLotto();
			return LottoTicketIssuer.buyTickets(inputAmount);
		} catch (InvalidMoneyException ex) {
			ResultView.printErrorMessage(ex.getMessage());
			return buyLottoTickets();    /* 사용자가 잘못된 입력을 했을 경우 재입력 */
		}
	}

	private String inputAmountForBuyLotto() {
		ResultView.printGuideMessage(GUIDE_MESSAGE_ENTER_INPUT_AMOUNT);
		return InputView.readLine();
	}

	private void showTickets(LottoTickets lottoTickets) {
		ResultView.printResultMessage(lottoTickets);
	}

	private LottoWinningNumbers getLastWeekWinningNumbers() {
		try {
			String lastWeekWinningNumbers = inputLastWeekWinningNumbers();
			return LottoWinningNumbers.makeLottoWinningNumbers(lastWeekWinningNumbers);
		} catch (InvalidLottoFormatException ex) {
			ResultView.printErrorMessage(ex.getMessage());
			return getLastWeekWinningNumbers();    /* 사용자가 잘못된 입력을 했을 경우 재입력*/
		}
	}

	private String inputLastWeekWinningNumbers() {
		ResultView.printGuideMessage(GUIDE_MESSAGE_ENTER_LAST_WEEK_WINNING_NUMBERS);
		return InputView.readLine();
	}

	private void showMatchLottoWinningResult(LottoWinningNumbers lottoWinningNumbers, LottoTickets lottoTickets) {
		ResultView.printResultMessage(RESULT_MESSAGE_MATCH_STATISTICS_START_LINE);
		LottoMatcher lottoMatcher = new LottoMatcher(lottoWinningNumbers);
		LottoMatchResult matchResult = lottoMatcher.matchWinningAndTickets(lottoTickets);
		ResultView.printResultMessage(matchResult);
		ResultView.printFormatResultMessage(RESULT_MESSAGE_MATCH_PROFIT_STATEMENT,
			matchResult.calculateProfitPercentage());
	}
}
