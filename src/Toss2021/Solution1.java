package Toss2021;

//부가가치세
    //과세의 대상이 되는 과세금액에서 10% 산정
    //소수점 첫째 자리에서 올림처리
// (공급가액 - 비과세 금액) => 과세 금액
// 과세 금액 * 1.1 => 부가가치세
// 부가가치세 + 공급 가액 => 공급 대가
// 공급 대사 => (주문 금액 - 봉사료)
//과세금액은?
    //공급가액에서 비과세 금액을 빼면된다

//공급가액과 계산된 부가가치세를 합하면 주문금액과 동일?
    //이걸 사실상 공급대가라고 표현
    //보통 봉사료가 없다면 동일하다
    //하지만 숙박업 등 봉사료가 존재? =? 주문금액에서 봉사료를 제외한 금액이 공급가액

//공급대가 = 공급 가액 + ((공급 가액 - 비과세 금액) * 0.1)
//공급대가 = x + (x - 비과세 금액) * 0.1
// x + 0.1x - 0.1비과세 금액
//공급대가 + 0.1비과세 금액 = 1.1x
//공급대가 / 1.1 + 0.1/1.1 * 비과세금액 = 공급가액

public class Solution1 {
    public static void main(String[] args) {
        //주문금액 -

        System.out.println(solution(120, 20, 0));
    }

    private static long solution(long orderAmount, long taxFreeAmount, long serviceFee) {
        // orderAmount : 주문금액
        // taxFreeAmount : 비과세금액
        // serviceFee : 봉사료
        long supplyPrice = orderAmount - serviceFee;
        if(supplyPrice - taxFreeAmount == 1) return 0;

        supplyPrice = (long)Math.floor((double)supplyPrice / 1.1 + (0.1/1.1) * (double)taxFreeAmount);


        return orderAmount - (supplyPrice + serviceFee);
    }
}
