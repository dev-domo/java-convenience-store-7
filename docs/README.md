### 주요 기능

- 상품 등록
- 제품 구매
- 재고 차감
    - 프로모션 제품 재고 차감
    - 프로모션이 적용되지 않는 상품 재고 차감
    - 일반 상품 제고 차감
- 여부 안내
    - 프로모션 상품 -> 프로모션 증점품 안가져옴 -> 증정품 추가 여부 안내
    - 프로모션 상품 -> 프로모션 재고 부족 -> 일부 상품 일반 상품으로 결제
- 멤버십 할인
- 영수증 출력

### 세부 기능

- 상품 등록
    - md파일을 읽어 저장한다.
    - md 파일에 프로모션 상품이 있는데 일반상품이 없을 경우 자동적으로 추가한다.
- 제품 구매 + `유효검사`
    - 재고 확인은 total(프로모션+일반)으로 한다
    - 구매 양식record를 받아서 order에 저장한다.
    - order은 cart에 모아 관리한다.
- 재고 차감
    - 프로모션 제품 재고 차감
        - 프로모션 상품으로 저장
        - 프로모션 -> 일반 재고 차감
    - 프로모션이 적용되지 않는 상품
        - 일반 상품으로 저장
        - 일반 -> 프로모션 재고 차감
    - 일반 상품 재고 차감
        - 일반 상품으로 저장
        - 일반 상품 재고 차감
- 여부 안내 + `유효검사`
    1. 프로모션 상품 -> 프로모션 증점품 안가져옴 -> 증정품 추가 여부 안내
        - 프로모션 기본 GET 수량에 만족해야한다.
            - 8 -> 6+2 -> 2GET에 만족 -> 여부 안내
            - 7 -> 6+1 -> 1은 GET에 불만족 -> 여부 안내 하지 않음
        - 증정품을 가져온 수량만큼의 프로모션 전체 재고가 있어야한다.
            - 감자칩 재고(5) -> 5구매 -> 4+1 -> 1은 GET에 만족 -> 하지만 추량이 부족함으로 여부 안내 하지 않음
    2. 프로모션 상품 -> 프로모션 재고 부족 -> 일부 상품 일반 상품으로 결제
        - 프로모션 재고 이상을 구매할 경우. 단, 일반상품의 재고가 있어야함
        - 1Free를 가져온 경우의 수량으로 계산한다.
- 멤버십 할인
    - 프로모션이 적용되지 않는 상품 총 금액의 30%
        - ex) 콜라 2개, 에너지바 1 -> 1,200원
          (콜라2개는 1Free를 구매하지 않았으므로 일반 상품으로 분류한다.)
    - 최대 8,000원

- 영수증 출력

```text
구매 상품 내역: 구매한 상품명, 수량, 가격
증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록 - 1Free
금액 정보
    총구매액: 구매한 상품의 총 수량과 총 금액
    행사할인: 프로모션에 의해 할인된 금액 - 1Free 가격
    멤버십할인: 멤버십에 의해 추가로 할인된 금액 - 프로모션이 적용되지 않는 상품의 30%
    내실돈:최종 결제 금액
```

### 플로우

1. 주문 입력
2. 주문 파싱하여 Order 저장 + 유효검사
3. 프로모션 미충족 여부 안내 및 업데이트
4. 영수증 출력

### 주의할 점

- 프로모션 미적용 : 1Free 하지 않음
- 모든 숫자는 천단위 구분으로 출력한다.
- md 파일에 프로모션 상품이 있는데 일반상품이 없을 경우 자동적으로 추가한다.

### 유효 검사