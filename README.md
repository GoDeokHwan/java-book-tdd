# Java로 도서 예약 TDD 연습
Gpt가 추천해준 주제로 만드는 과정으로 해당 규칙으로 생성 예정

### 📌 기본 시나리오
“도서를 예약하는 기능”

### 엔티티
🟦 엔티티: Book

id

title

stock (재고 수량)

isReservable (예약 가능 여부)

🟩 엔티티: BookReservation

id

bookId

userId

status (REQUESTED, APPROVED, CANCELED)

createdAt

### 시나리오
1. 책추가
2. 책 제목으로 검색 
2. 재고가 0이면 예약 불가
2. 이미 예약한 사람이 또 예약하면 안 됨
3. 예약 성공 시 재고 감소
4. 예약 취소 시 재고 복구
5. 한 번 승인된 예약은 취소 불가
6. Book.isReservable = false면 예약 불가
