# 기능 명세서

## 클라이언트

* ### 로그인 페이지
    * [x] 아이디, 비밀번호를 입력 받는 기능
        * [x] 아이디 혹은 비밀번호가 일치하지 않으면 `아이디 혹은 비밀번호가 일치하지 않습니다.` 문구를 띄우며 아이디, 비밀번호가 유지된 상태에서, 페이지가 넘어가지 않는다.
        * [x] 아이디와 비밀번호가 일치하면 다음페이지로 넘어간다.
    * [ ] 아이디 찾기 버튼
        * [ ] 아이디 찾기 버튼을 누르면 아이디 찾기 페이지로 넘어간다.
    * [ ] 비밀번호 찾기 버튼
        * [ ] 비밀번호 찾기 버튼을 누르면 비밀번호 찾기 페이지로 넘어간다. 
    * [x] 회원가입 버튼

* ### 회원 가입 페이지
    * [x] 회원가입을 하는 기능
        * [x] 아이디를 입력 받는 박스
        * [x] 아이디 중복 확인하는 버튼
        * [x] 비밀번호를 입력 받는 박스
            * [ ] 비밀번호는 6글자 이상이어야 한다.
        * [x] 회원가입 버튼
            * [x] 아이디 중복 확인을 하지 않았거나, 중복되는 경우 `아이디 중복 확인을 해 주세요.`를 띄운다
            * [ ] 비밀번호가 6글자 미만이면 `비밀번호는 6글자 이상이어야 합니다. 다시 입력해주세요.`를 띄운다.
            * [x] 회원가입에 성공하면 로그인 페이지로 로딩된다.

* ### 회원 탈퇴하는 기능

* ### 로또를 구매하는 페이지
    * [x] 구입 회차를 출력한다.
    * [x] 구입할 로또 금액을 입력 받는다.
        * 예외 처리
            * [x] 숫자이어야 한다.
                * 숫자가 아니라면 `입력금액은 숫자이어야 합니다.`를 띄운다.
            * [x] 구입 금액은 1000원 단위이어야 한다.
                * 1000원 단위가 아니라면, `입력금액은 1000원 단위이어야 합니다`를 띄우고 다시 받는다.
            * [x] 최대 구입 금액은 1억으로 제한한다.
                * 1억 이상을 입력하면 `최대 구입 가능 금액은 1억입니다.`를 띄운다.
            * [ ] 1,000원도 받을 수 있게 한다.
    * [ ] 구매하기 버튼
        * 구입한 회차가 5개이면, 구매하기 버튼이 작동하지 않는다.
            * `회차는 5개까지만 가능합니다. 기존의 회차를 삭제해 주세요.`를 출력한다.
        * [x] 입력이 유효한 상태에서 버튼을 누르면  정보를 서버로 보낸다.
        * [x] 서버에서 받은 로또 티켓을 띄운다.

* ### 지난 로또 당첨을 확인하는 페이지
    * 카테고리에 유저가 구입한 적 있는 로또 회차 drop박스
        * [x] 드롭박스에는 유저가 구입한 적 있는 로또 회차만 버튼으로 존재한다.
        * [x] 버튼을 누르면 회차에 대한 정보를 서버로 보낸다.
        * [x] 서버로부터 정보를 받아서 페이지에 띄운다.
        * [ ] 회차는 5개만 가지고 있을 수 있다.

    * 특정 회차에 대한 결과
        * [x] 구입 금액을 출력한다.
        * [x] 구입했던 로또 티켓을 출력한다.
        * [x] 구입 개수를 출력한다.
        * [x] 당첨 번호를 출력한다.
        * [x] 보너스 번호를 출력한다.
        * [x] 당첨 통계를 출력한다.
            * [x] 등수에 대한 당첨 티켓 개수를 출력한다.
            * [x] 총 수익금액을 출력한다.
            * [x] 총 수익률을 출력한다.
    * 회차 삭제하기 버튼


## 서버

* ### 로또 결과를 크롤링하는 기능
    * 동행 로또 사이트에 가서 해당 회차 로또 번호, 보너스 번호, 당첨금액을 가져온다.
        * 예외 처리
            * [ ] 로또 회차는 숫자이어야 한다.
                * [ ] 회차가 유효하지 않으면 `[ERROR] 로또 회차가 유효하지 않습니다.` 를 출력한다.
            * [ ] 로또번호, 보너스 번호는 숫자이어야 한다.
                * [ ] 로또 번호가 숫자가 아니라면 `[ERROR] 당첨 번호는 숫자이어야 합니다.`를 출력한다.
                *  [ ] 보너스 번호가 숫자가 아니라면 `[ERROR] 보너스 번호는 숫자이어야 합니다.`를 출력한다.
            * [ ] 로또 번호는  1~45의 숫자이어야 한다.
                * [ ] 로또 번호의 범위에 포함하지 않는다면 `[ERROR] 로또 번호는 1~45의 숫자이어야 합니다.`를 출력한다.


* ### 유저
    * 회원가입을 진행하는 회원 아이디 비밀번호를 db에 저장하는 기능
        * [x] 아이디와 비밀번호를 DB에 저장한다.
            * [x] id가 중복되었다면 IllegalStatementException을 던진다.
            * [x] 비밀번호는 sha256으로 해쉬한 값을 저장한다.
    * [ ] 탈퇴하는 기능
    * [ ] 아이디, 비밀번호 변경 기능
    * [ ] 아이디, 비밀번호 찾기 기능
    * [ ] 이메일 기능
    * 로그인 기능
        * [ ] 클라이언트에게 id와 패스워드를 받는다.
        * [x] 패스워드를 sha 256으로 해쉬한다.
        * [x] id와 해쉬된 패스워드를 db에서 확인한다.
        * [x] 존재하면 true, 존재하지 않는다면 false를 보낸다.

* ### 클라이언트에게 구입 금액을 받는 기능
    * [ ] 클라이언트 id와 구입 금액을 같이 받는다.
    * 예외 처리
        * [ ] 입력은 숫자이어야 한다.
            * [ ] 입력이 숫자가 아니라면 `[ERROR] 입력금액은 숫자이어야 합니다.` 를 출력한다.
        * [x] 입력은 1000원 단위이어야 한다.
            * [x] 입력이 1000원 단위가 아니라면 `[ERROR] 입력금액은 1000원 단위이어야 합니다`를 출력한다.

* ### 구입 금액에 해당하는 로또 티켓을 발행하는 기능
    * [x] 로또 번호는 1~45범위이어야 한다.
    * [x] 번호는 중복되면 안된다.
* ### 주문 기능
    * [x] 주문 금액, 유저를 입력 받으면 주문금액에 따른 티켓을 발행한다.
        * 구입 금액에 해당하는 로또 티켓을 발행하는 기능
            * [x] 로또 번호는 1~45범위이어야 한다.
            * [x] 번호는 중복되면 안된다.
        * [ ] userId가 존재하지 않는 id면 발행하지 않고 빈 리스트를 리턴한다. 
    * [x] 발행된 티켓을 저장한다.
    * [x] 발행된 티켓을 확인하는 기능
    * [ ] 주문을 삭제하는 기능

* ### 발행된 티켓을 DB에 저장하는 기능
    * [x] 발행된 티켓들을 저장하는 기능
* ### 발행된 티켓을 클라이언트에 전송하는 기능
* ### 클라이언트가 요구하는 회차 결과를 받아, DB에서 가져오는 기능
* ### 가져온 회차 정보를 클라이언트한테 전송하는 기능


## DB

* [x] 회원 정보
    * id
        * primary key
    * 패스워드
    * 로또 회차와 로또 회차에 해당하는 로또 티켓
* 회원 정보를 가저오는 기능
* [x] 로또 결과
    * 로또 회차와 로또 화차에 해당하는 로또 당첨 번호 + 보너스 번호
      ![img.png](img.png)
    * 로또 결과를 가져오는 기능

## DB테스트

* 회원정보를 저장하고 그에 해당하는 회원정보를 가져올 수 있는 지 테스트
  * [x] 회원정보를 저장하고, userId를 가져왔을 때 일치하는 지 테스트한다.
  * [x] 회원정보에 해당하는 패스워드의 해시값이 저장된 패스워드의 해시값과 일치하는 지 테스트한다.
* 로또 결과를 저장하고, 그에 해당하는 로또 정보를 가져올 수 있는 지 테스트
    * [ ] 회차를 가져올 수 있는 지 테스트한다.
    * [ ] 구입금액을 가져올 수 있는 지 테스트한다.
    * [ ] 특정 회차에 해당하는 저장된 로또티켓을 가져올 수 있는 지 테스트한다.
